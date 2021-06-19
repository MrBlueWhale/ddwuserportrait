package com.ibegu.dalaoadmin.resp;

import java.util.Map;

public class GroupTagQueryResp {
    private Long gtId;

    private Map<String, String> baseTagNames;

    private String desc;

    private Integer status;


    public Long getGtId() {
        return gtId;
    }

    public void setGtId(Long gtId) {
        this.gtId = gtId;
    }


    public void setBaseTagNames(Map<String, String> baseTagNames) {
        this.baseTagNames = baseTagNames;
    }

    public Map<String, String> getBaseTagNames() {
        return baseTagNames;
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
        return "GroupTagQueryResp{" +
                "gtId=" + gtId +
                ", baseTagNames=" + baseTagNames +
                ", desc='" + desc + '\'' +
                ", status=" + status +
                '}';
    }
}