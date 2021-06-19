package com.ibegu.dalaoadmin.resp;

import java.util.List;

public class GroupTagQueryResp {
    private Long gtId;

    private List<String> baseTagNames;

    private String desc;

    private Integer tagStatus;


    public Long getGtId() {
        return gtId;
    }

    public void setGtId(Long gtId) {
        this.gtId = gtId;
    }


    public List<String> getBaseTagNames() {
        return baseTagNames;
    }

    public void setBaseTagNames(List<String> baseTagNames) {
        this.baseTagNames = baseTagNames;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getTagStatus() {
        return tagStatus;
    }

    public void setTagStatus(Integer tagStatus) {
        this.tagStatus = tagStatus;
    }

    @Override
    public String toString() {
        return "GroupTagQueryResp{" +
                "gtId=" + gtId +
                ", baseTagNames=" + baseTagNames +
                ", desc='" + desc + '\'' +
                ", tagStatus=" + tagStatus +
                '}';
    }
}