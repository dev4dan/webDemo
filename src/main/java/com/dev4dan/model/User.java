package com.dev4dan.model;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    private static final long serialVersionUID = -6011241820070393952L;
    private int id;
    private String userName;
    private Date birthday;
    private String userAddress;

    public User(){}

    public User(int id, String userName, Date birthday, String userAddress) {
        this.id = id;
        this.userName = userName;
        this.birthday = birthday;
        this.userAddress = userAddress;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getUserAddress() {
        return userAddress;
    }
    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", birthday=" + birthday +
                ", userAddress='" + userAddress + '\'' +
                '}';
    }
}