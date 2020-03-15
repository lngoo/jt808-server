package com.ant.msger.base.message;

import com.ant.msger.base.enums.OperateType;
import com.ant.msger.base.enums.SubjectType;
import com.ant.msger.base.dto.persistence.PersistenceData;

/**
 * 任务通道消息类型
 */
public class MsgerTaskMsg {
    private OperateType operateType;
    private SubjectType subjectType;
    private PersistenceData data;

    public MsgerTaskMsg(OperateType operateType, SubjectType subjectType, PersistenceData data) {
        this.operateType = operateType;
        this.subjectType = subjectType;
        this.data = data;
    }

    public OperateType getOperateType() {
        return operateType;
    }

    public void setOperateType(OperateType operateType) {
        this.operateType = operateType;
    }

    public SubjectType getSubjectType() {
        return subjectType;
    }

    public void setSubjectType(SubjectType subjectType) {
        this.subjectType = subjectType;
    }

    public PersistenceData getData() {
        return data;
    }

    public void setData(PersistenceData data) {
        this.data = data;
    }
}
