package com.ibegu.dalaoadmin.req;

public class Rank5TagsQueryReq extends PageReq {
//    private Long t5Id;

    private String t5Name;

    private Long t4Id;

    private Integer processStatus;

    private Integer auditStatus;

    private String desc;

    private String t4Name;



    public String getT4Name() {
        return t4Name;
    }

    public void setT4Name(String t4Name) {
        this.t4Name = t4Name;
    }

//    public Long getT5Id() {
//        return t5Id;
//    }
//
//    public void setT5Id(Long t5Id) {
//        this.t5Id = t5Id;
//    }

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
        return "Rank5TagsQueryReq{" +
//                "t5Id=" + t5Id +
                ", t5Name='" + t5Name + '\'' +
                ", t4Id=" + t4Id +
                ", processStatus=" + processStatus +
                ", auditStatus=" + auditStatus +
                ", desc='" + desc + '\'' +
                ", t4Name='" + t4Name + '\'' +
                '}';
    }

}