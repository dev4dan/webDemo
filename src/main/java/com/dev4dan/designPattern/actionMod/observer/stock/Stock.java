package com.dev4dan.designPattern.actionMod.observer.stock;

/**
 * Created by danielwood on 19/06/2017.
 */
public class Stock {
    private int stockNo;
    private String stockName;

    public int getStockNo() {
        return stockNo;
    }

    public void setStockNo(int stockNo) {
        this.stockNo = stockNo;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }
}
