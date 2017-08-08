package com.dev4dan.designPattern.actionMod.observer.stock;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by danielwood on 19/06/2017.
 */
public abstract class AbsStockObserver implements StockObserver{
    protected static final String DEFAULT_NAME = "defaultName";
    private String name;
    private StockCenter stockCenter;
    private List<Stock> stocks;

    public void addStock(Stock stock){
        if(stock == null ){
            return ;
        }

        if(stocks == null){
            stocks = new ArrayList<>();
        }
        System.out.println(getName() + " add stockNo : "+stock.getStockNo() + " to list");
        stocks.add(stock);
    }

    public List<Stock> getStocks(){
        return this.stocks;
    }

    public void setStocks(List<Stock> stocks){
        this.stocks = stocks;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public void registerCenter(StockCenter stockCenter){
        this.stockCenter = stockCenter;
        if(stockCenter != null){
            System.out.println(getName() + " register to stock center : "+stockCenter.getCenterName());
        }
    }

    @Override
    public void acceptMsg(String msg){
        System.out.println(getName() + " accept msg : "+ msg);
    }
}
