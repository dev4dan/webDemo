package com.dev4dan.designPattern.actionMod.observer.stock;

import java.util.List;

/**
 * Created by danielwood on 19/06/2017.
 */
public interface StockObserver {
    public void setName(String name);
    public String getName();
    public void addStock(Stock stock); //添加股票编号
    public void setStocks(List<Stock> stocks); //设置股民持有的股票
    public List<Stock> getStocks(); //获取股民持有的所有股票

    public void acceptMsg(String msg);
    public void registerCenter(StockCenter stockCenter);
}
