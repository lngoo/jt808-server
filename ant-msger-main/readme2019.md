1. 终端1注册，下发授权码
7e0100405401123456789138888888880066000200022020202020204d414b453220202020202020202020202050524f424f4f4b207a68616e36362d616d3200000000000000000000000000000000000000000000000000000000413202b4a8413737373737927e
2. 终端2注册，下发授权码

3. 两终端发送心跳，打印心跳信息
7e0002400001123456789138888888880066847e

4. 中止所有客户端连接2分钟

5. 终端1位置信息汇报 被拒绝
7e020040160112345678913888888888006600000002000000020000000100000002000100010001907e

6. 终端1鉴权
7e0102401b0112345678913888888888006631323334353637383931333838383838383838385f617574686564c37e

7. 终端1位置信息汇报
7e020040160112345678913888888888006600000002000000020000000100000002000100010001907e

8. 服务端查询终端1参数
<?xml version="1.0" ?><com.ant.msger.base.dto.jt808.basics.Message><body class="com.ant.msger.base.dto.jt808.ParameterSetting"><parameters></parameters></body><type>33028</type><bodyProperties>1</bodyProperties><mobileNumber>013888888888</mobileNumber><serialNumber>102</serialNumber><bodyLength>1</bodyLength><encryptionType>0</encryptionType><subPackage>false</subPackage><reservedBit>0</reservedBit></com.ant.msger.base.dto.jt808.basics.Message>

a:b:c:response

---------------------------------------
http://www.websocket-test.com/





--------