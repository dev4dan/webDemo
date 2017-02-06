package com.dev4dan.model;

import java.util.Date;

/**
 * Created by danielwood on 2017/2/3.
 */
public class QcCode4Association {
    private int id; // 二维码id
    private int adminMemberId; // 管理员会员id
    private Date startDate; // 二维码生效时间
    private Date endDate; // 二维码失效时间
    private int limitEmployees; // 该二维码限制员工人数
    private int limitMonth; // 关联员工可使用储值限额
    private int dempartmentId; // 关联员工指定部门

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAdminMemberId() {
        return adminMemberId;
    }

    public void setAdminMemberId(int adminMemberId) {
        this.adminMemberId = adminMemberId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getLimitEmployees() {
        return limitEmployees;
    }

    public void setLimitEmployees(int limitEmployees) {
        this.limitEmployees = limitEmployees;
    }

    public int getLimitMonth() {
        return limitMonth;
    }

    public void setLimitMonth(int limitMonth) {
        this.limitMonth = limitMonth;
    }

    public int getDempartmentId() {
        return dempartmentId;
    }

    public void setDempartmentId(int dempartmentId) {
        this.dempartmentId = dempartmentId;
    }
}
