package com.dev4dan.model;

import java.io.Serializable;

public class Order implements Serializable{
	private int ID = 0;
	private int empID = 0;
	private String date = null;
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public int getEmpID() {
		return empID;
	}
	public void setEmpID(int empID) {
		this.empID = empID;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
}
