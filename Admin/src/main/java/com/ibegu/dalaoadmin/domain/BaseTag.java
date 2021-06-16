package com.ibegu.dalaoadmin.domain;

public class BaseTag {
    private Long btId;

    private String btName;

    private Long parentId;

    private Integer processStatus;

    private Integer auditStatus;

    private String desc;

    public Long getBtId() {
        return btId;
    }

    public void setBtId(Long btId) {
        this.btId = btId;
    }

    public String getBtName() {
        return btName;
    }

    public void setBtName(String btName) {
        this.btName = btName;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
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
        sb.append(", btId=").append(btId);
        sb.append(", btName=").append(btName);
        sb.append(", parentId=").append(parentId);
        sb.append(", processStatus=").append(processStatus);
        sb.append(", auditStatus=").append(auditStatus);
        sb.append(", desc=").append(desc);
        sb.append("]");
        return sb.toString();
    }
}