package com.dev4dan.dao;

import com.dev4dan.model.User;

public interface IUserDao { 
	
	public User selectUserByName(String name);
	
    public User selectUserByID(int id);
    
}