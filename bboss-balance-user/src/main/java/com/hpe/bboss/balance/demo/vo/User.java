package com.hpe.bboss.balance.demo.vo;

import java.util.Date;

public class User {
    private Integer uId;

    private String uName;

    private Short uAge;

    private String uSex;

    private Date ucTime;

    public Integer getuId() {
        return uId;
    }

    public void setuId(Integer uId) {
        this.uId = uId;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName == null ? null : uName.trim();
    }

    public Short getuAge() {
        return uAge;
    }

    public void setuAge(Short uAge) {
        this.uAge = uAge;
    }

    public String getuSex() {
        return uSex;
    }

    public void setuSex(String uSex) {
        this.uSex = uSex == null ? null : uSex.trim();
    }

    public Date getUcTime() {
        return ucTime;
    }

    public void setUcTime(Date ucTime) {
        this.ucTime = ucTime;
    }
}