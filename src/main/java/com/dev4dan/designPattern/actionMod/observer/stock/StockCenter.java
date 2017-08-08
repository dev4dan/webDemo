package com.dev4dan.designPattern.actionMod.observer.stock;

import java.util.List;

/**
 * Created by danielwood on 19/06/2017.
 */
public interface StockCenter {

    public String getCenterName();

    public void setBuyStock(int stockNo);

    public void setBuyStocks(List<Integer> stockNos);

    public void setSaleStock(int stockNo);

    public void setSaleStocks(List<Integer> stockNos);

    public void register(StockObserver stockObserver);

    public void logoff(StockObserver stockObserver);

    public void sendMsg(String msg);
}
