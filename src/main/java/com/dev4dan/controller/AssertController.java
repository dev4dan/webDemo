package com.dev4dan.controller;

import com.dev4dan.dao.IOrderDao;
import com.dev4dan.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * Created by danielwood on 25/03/2017.
 */
@RestController
public class AssertController {

    @Autowired
    private IOrderDao iOrderDao;

    public List<Order> list(List<Date> dates) {
        return iOrderDao.getOrderByDates(dates);
    }
}
