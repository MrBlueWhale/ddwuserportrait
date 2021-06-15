package com.ibegu.dalaoadmin.resp;

public class Rank3TagsQueryResp {
    private Long t3Id;

    private String t3Name;

    private Integer processStatus;

    private Integer auditStatus;

    private String desc;

    public Long getT3Id() {
        return t3Id;
    }

    public void setT3Id(Long t3Id) {
        this.t3Id = t3Id;
    }

    public String getT3Name() {
        return t3Name;
    }

    public void setT3Name(String t3Name) {
        this.t3Name = t3Name;
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
        sb.append(", t3Id=").append(t3Id);
        sb.append(", t3Name=").append(t3Name);
        sb.append(", processStatus=").append(processStatus);
        sb.append(", auditStatus=").append(auditStatus);
        sb.append(", desc=").append(desc);
        sb.append("]");
        return sb.toString();
    }
}