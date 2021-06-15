package com.ibegu.dalaoadmin.domain;

public class Rank4Tags {
    private Long t4Id;

    private String t4Name;

    private Long t3Id;

    private Integer processStatus;

    private Integer auditStatus;

    private String desc;

    public Long getT4Id() {
        return t4Id;
    }

    public void setT4Id(Long t4Id) {
        this.t4Id = t4Id;
    }

    public String getT4Name() {
        return t4Name;
    }

    public void setT4Name(String t4Name) {
        this.t4Name = t4Name;
    }

    public Long getT3Id() {
        return t3Id;
    }

    public void setT3Id(Long t3Id) {
        this.t3Id = t3Id;
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
        sb.append(", t4Id=").append(t4Id);
        sb.append(", t4Name=").append(t4Name);
        sb.append(", t3Id=").append(t3Id);
        sb.append(", processStatus=").append(processStatus);
        sb.append(", auditStatus=").append(auditStatus);
        sb.append(", desc=").append(desc);
        sb.append("]");
        return sb.toString();
    }
}