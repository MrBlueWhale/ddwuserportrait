package com.ibegu.dalaoadmin.req;

public class Rank4TagsQueryReq extends PageReq {
//    private Long t4Id;

    private String t4Name;

    private Long t3Id;

    private Integer processStatus;

    private Integer auditStatus;

    private String desc;

    private String t3Name;

//    public Long getT4Id() {
//        return t4Id;
//    }
//
//    public void setT4Id(Long t4Id) {
//        this.t4Id = t4Id;
//    }


    public String getT3Name() {
        return t3Name;
    }

    public void setT3Name(String t3Name) {
        this.t3Name = t3Name;
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
        return "Rank4TagsQueryReq{" +
                "t4Name='" + t4Name + '\'' +
                ", t3Id=" + t3Id +
                ", processStatus=" + processStatus +
                ", auditStatus=" + auditStatus +
                ", desc='" + desc + '\'' +
                ", t3Name='" + t3Name + '\'' +
                '}';
    }
}