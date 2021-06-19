package com.ibegu.dalaoadmin.domain;

public class GroupTag {
    private Long gtId;

    private Long bt5Id;

    private String bt5Name;

    private String desc;

    private Integer status;

    public Long getGtId() {
        return gtId;
    }

    public void setGtId(Long gtId) {
        this.gtId = gtId;
    }

    public Long getBt5Id() {
        return bt5Id;
    }

    public void setBt5Id(Long bt5Id) {
        this.bt5Id = bt5Id;
    }

    public String getBt5Name() {
        return bt5Name;
    }

    public void setBt5Name(String bt5Name) {
        this.bt5Name = bt5Name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", gtId=").append(gtId);
        sb.append(", bt5Id=").append(bt5Id);
        sb.append(", bt5Name=").append(bt5Name);
        sb.append(", desc=").append(desc);
        sb.append(", status=").append(status);
        sb.append("]");
        return sb.toString();
    }
}