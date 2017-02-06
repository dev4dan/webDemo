package com.dev4dan.dao;

import java.util.Date;
import java.util.List;

import com.dev4dan.model.Order;

public interface IOrderDao {
	
	public List<Order> getOrderByEmps(List<Integer> empsID);
	
	public Order getOrderById(int orderId);
	
	public List<Order> getOrderByDates(List<Date> dates); 
	
}
