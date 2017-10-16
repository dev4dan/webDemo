package com.dev4dan.redisComponent.basic.impl;

import com.dev4dan.model.Order;
import com.dev4dan.redisComponent.basic.AbstractBaseRedisDao;
import com.dev4dan.redisComponent.basic.OrderRDao;

import java.util.Map;

/**
 * Created by danielwood on 29/08/2017.
 */

public class OrderRDaoImpl extends AbstractBaseRedisDao implements OrderRDao{
    private static final String keyName = "ORDER";

    @Override
    public void save(Order order) {
        redisTemplate.opsForHash().put(keyName, keyName+"_"+order.getID(), order);
    }

    @Override
    public Order getOrderBy(int id) {
        return (Order) redisTemplate.opsForHash().get(keyName, keyName+"_"+id);
    }

    @Override
    public void deleteById(int id) {
        redisTemplate.opsForHash().delete(keyName, keyName+"_"+id);
    }

    @Override
    public Map<Integer, Order> getAll() {
        return redisTemplate.opsForHash().entries(keyName);
    }

    @Override
    public String getRKeyName() {
        return keyName;
    }
}
