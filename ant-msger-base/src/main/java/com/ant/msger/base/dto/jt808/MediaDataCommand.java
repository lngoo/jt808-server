package com.ant.msger.base.dto.jt808;

import com.ant.msger.base.annotation.Property;
import com.ant.msger.base.annotation.Type;
import com.ant.msger.base.enums.DataType;
import com.ant.msger.base.message.AbstractBody;
import com.ant.msger.base.common.MessageId;

@Type(MessageId.单条存储多媒体数据检索上传命令)
public class MediaDataCommand extends AbstractBody {

    private Integer id;
    private Integer deleteSign;

    @Property(index = 0, type = DataType.DWORD, desc = "多媒体ID")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Property(index = 4, type = DataType.BYTE, desc = "删除标志:0.保留；1.删除；")
    public Integer getDeleteSign() {
        return deleteSign;
    }

    public void setDeleteSign(Integer deleteSign) {
        this.deleteSign = deleteSign;
    }
}