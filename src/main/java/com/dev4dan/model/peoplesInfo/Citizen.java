package com.dev4dan.model.peoplesInfo;

import com.dev4dan.mongoDao.basic.MongoBaseModel;

import java.util.Date;

/**
 * Created by danielwood on 29/06/2017.
 */
public class Citizen extends MongoBaseModel{

    private String citizenName;
    private String idCard;
    private Date birthday;
    private String address;
    private String mobile;
    private String email;

    public String getCitizenName() {
        return citizenName;
    }

    public void setCitizenName(String citizenName) {
        this.citizenName = citizenName;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Citizen{" +
                ", citizenName='" + citizenName + '\'' +
                ", idCard='" + idCard + '\'' +
                ", birthday=" + birthday +
                ", address='" + address + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
