package com.dev4dan.model.articleModel;

/**
 * Created by danielwood on 15/05/2017.
 */
public class User {
    private long id;
    private String userAccount; // 用户账号名称
    private String userName; // 用户昵称
    private int userType; // 用户类型

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }
}
