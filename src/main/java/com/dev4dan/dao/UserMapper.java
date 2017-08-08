package com.dev4dan.dao;

import com.dev4dan.model.User;

import java.util.List;

public interface UserMapper {
	
	public User selectUserByName(String name);
	
    public User selectUserByID(int id);

    public int insertUserById(User user);

    public int updateUser(User user);

    public int insertUsers(List<User> users);

    public int updateUsers(List<User> users);

    public int deleteUserById(int _id);

    public int deleteUsersById(List<User> users);

}