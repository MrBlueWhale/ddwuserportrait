package com.ibegu.dalaoadmin.resp;

public class UserProfileResp<T> {
    private Long uid;

    private T populationAttrs;
    private T commercialAttrs;
    private T behaviorAttrs;
    private T userValueAttrs;

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public T getPopulationAttrs() {
        return populationAttrs;
    }

    public void setPopulationAttrs(T populationAttrs) {
        this.populationAttrs = populationAttrs;
    }

    public T getCommercialAttrs() {
        return commercialAttrs;
    }

    public void setCommercialAttrs(T commercialAttrs) {
        this.commercialAttrs = commercialAttrs;
    }

    public T getBehaviorAttrs() {
        return behaviorAttrs;
    }

    public void setBehaviorAttrs(T behaviorAttrs) {
        this.behaviorAttrs = behaviorAttrs;
    }

    public T getUserValueAttrs() {
        return userValueAttrs;
    }

    public void setUserValueAttrs(T userValueAttrs) {
        this.userValueAttrs = userValueAttrs;
    }

    @Override
    public String toString() {
        return "UserProfileResp{" +
                "uid=" + uid +
                ", populationAttrs=" + populationAttrs +
                ", commercialAttrs=" + commercialAttrs +
                ", behaviorAttrs=" + behaviorAttrs +
                ", userValueAttrs=" + userValueAttrs +
                '}';
    }
}