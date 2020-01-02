package com.ant.jt808.base.dto.jt808;

import com.ant.jt808.base.annotation.Property;
import com.ant.jt808.base.annotation.Type;
import com.ant.jt808.base.enums.DataType;
import com.ant.jt808.base.message.AbstractBody;
import com.ant.jt808.base.config.Charsets;
import com.ant.jt808.base.common.MessageId;

@Type(MessageId.信息服务)
public class Information extends AbstractBody {

    private Integer type;
    private Integer length;
    private String content;

    @Property(index = 0, type = DataType.BYTE, desc = "标志")
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Property(index = 1, type = DataType.WORD, desc = "信息长度")
    public Integer getLength() {
        if (length == null)
            this.length = content.getBytes(Charsets.GBK).length;
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    @Property(index = 3, type = DataType.STRING, lengthName = "length", desc = "文本信息")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
        this.length = content.getBytes(Charsets.GBK).length;
    }
}