package com.ibegu.dalaoadmin.resp;

import java.util.Date;

public class UserQueryResp {
    private Long uid;

    private String userName;

    private String name;

    private String email;

    private String telNum;

    private String password;

    private Date createTime;

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelNum() {
        return telNum;
    }

    public void setTelNum(String telNum) {
        this.telNum = telNum;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", uid=").append(uid);
        sb.append(", userName=").append(userName);
        sb.append(", name=").append(name);
        sb.append(", email=").append(email);
        sb.append(", telNum=").append(telNum);
        sb.append(", password=").append(password);
        sb.append(", createTime=").append(createTime);
        sb.append("]");
        return sb.toString();
    }
}