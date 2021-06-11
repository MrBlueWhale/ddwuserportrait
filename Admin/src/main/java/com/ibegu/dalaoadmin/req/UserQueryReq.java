package com.ibegu.dalaoadmin.req;

import java.util.Date;

public class UserQueryReq extends PageReq {
    // private Long uid;

    private String userName;

    // private String name;

    private String email;

    private String telNum;

    // private String password;

    // private Date createTime;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    @Override
    public String toString() {
        return "UserQueryReq{" +
                "userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", telNum='" + telNum + '\'' +
                '}';
    }
}