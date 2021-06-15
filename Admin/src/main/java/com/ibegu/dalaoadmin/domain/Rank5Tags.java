package com.ibegu.dalaoadmin.domain;

public class Rank5Tags {
    private Long t5Id;

    private String t5Name;

    private Long t4Id;

    private Integer processStatus;

    private Integer auditStatus;

    private String desc;

    public Long getT5Id() {
        return t5Id;
    }

    public void setT5Id(Long t5Id) {
        this.t5Id = t5Id;
    }

    public String getT5Name() {
        return t5Name;
    }

    public void setT5Name(String t5Name) {
        this.t5Name = t5Name;
    }

    public Long getT4Id() {
        return t4Id;
    }

    public void setT4Id(Long t4Id) {
        this.t4Id = t4Id;
    }

    public Integer getProcessStatus() {
        return processStatus;
    }

    public void setProcessStatus(Integer processStatus) {
        this.processStatus = processStatus;
    }

    public Integer getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", t5Id=").append(t5Id);
        sb.append(", t5Name=").append(t5Name);
        sb.append(", t4Id=").append(t4Id);
        sb.append(", processStatus=").append(processStatus);
        sb.append(", auditStatus=").append(auditStatus);
        sb.append(", desc=").append(desc);
        sb.append("]");
        return sb.toString();
    }
}