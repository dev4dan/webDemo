package com.dev4dan.redisComponent.basic;

import com.dev4dan.model.Order;

import java.util.Map;

/**
 * Created by danielwood on 29/08/2017.
 */
public interface OrderRDao {

    void save(Order order);
    Order getOrderBy(int id);
    void deleteById(int id);
    Map<Integer, Order> getAll();
}
