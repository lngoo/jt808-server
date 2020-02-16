package com.ant.msger.main.mq.subscriber;

import com.ant.msger.base.common.MessageId;
import com.ant.msger.base.dto.jt808.CommonResult;
import com.ant.msger.base.message.MessageExternal;
import com.ant.msger.main.framework.commons.constant.GlobalConfig;
import com.ant.msger.main.framework.commons.enumeration.ProtocolCommunication;
import com.ant.msger.main.framework.sender.ProtocolMsgSender;
import com.ant.msger.main.framework.session.Session;
import com.ant.msger.main.framework.session.SessionManager;
import com.ant.msger.main.mq.util.LocalProtoBufUtil;
import com.ant.msger.main.mq.util.MessageConvertUtil;
import com.antnest.msger.proto.ProtoMain;
import com.google.protobuf.InvalidProtocolBufferException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * 响应通道（to-persistence）数据处理器
 */
public class ResponseMsgSubscriber implements MessageListener {
    private static final Logger LOG = LoggerFactory.getLogger("responseChannel");

    private ProtocolMsgSender protocolMsgSender = new ProtocolMsgSender();

    @Override
    public void onMessage(Message message, byte[] bytes) {
        LOG.info("### receive one data. msg={}", message.getBody());

        byte[] body = message.getBody();
        try {
            ProtoMain.Message message1 = ProtoMain.Message.parseFrom(body);
            MessageExternal external = LocalProtoBufUtil.copyProtoBeanToMessageExternal(message1);
            com.ant.msger.base.dto.jt808.basics.Message message2 = MessageConvertUtil.toInternal(external, GlobalConfig.protocolBusinessMap());
            // 发送到设备
            sendMsgToDevice(message2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("#################### end ... ");
    }

    private void sendMsgToDevice(com.ant.msger.base.dto.jt808.basics.Message message) {
        Session session = SessionManager.getInstance().getByMobileNumber(message.getMobileNumber());
        if (message.getBody() instanceof CommonResult) {
            CommonResult commonResult = (CommonResult) message.getBody();
            if (commonResult.getReplyId() == MessageId.终端鉴权
                    && commonResult.getResultCode() == CommonResult.Success) {
                // 终端session鉴权成功，更新下链接时间
                session.setLastCommunicateTimeStamp(System.currentTimeMillis());
            }
        }

        message.setSerialNumber(session.currentFlowId());

        // 发送消息
        if (ProtocolCommunication.UDP == session.getProtocolCommunication()) {
            protocolMsgSender.sendUdpMsgSingle(session, message);
        } else if (ProtocolCommunication.WEBSOCKET == session.getProtocolCommunication()) {
            protocolMsgSender.sendWebsocketMsgSingle(session, message);
        } else {
            protocolMsgSender.sendTcpMsgSingle(session, message);
        }
    }
}