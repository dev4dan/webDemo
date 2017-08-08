package com.dev4dan.designPattern.actionMod.observer.stock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by danielwood on 19/06/2017.
 */
public abstract class AbsStockCenter implements StockCenter{
    private List<StockObserver> stockObservers = new ArrayList<>();
    private String centerName;
    protected static final String DEFAULT_CENTER = "default_center";
    private Map<Integer, List<StockObserver>> stocksMap = new HashMap<>();
    private List<Integer> saleStocks;
    private List<Integer> buyStocks;


    AbsStockCenter(){}

    AbsStockCenter(String centerName){
        this.centerName = centerName;
    }

    public String getCenterName() {
        return centerName;
    }

    public void setCenterName(String centerName) {
        this.centerName = centerName;
    }

    public void register(StockObserver stockObserver){
        stockObservers.add(stockObserver);
        stockObserver.registerCenter(this);

        if(stockObserver.getStocks() != null){
            List<Stock> stocks = stockObserver.getStocks();
            for(Stock stock : stocks){
                if(!stocksMap.containsKey(stock.getStockNo())){
                    System.out.println("stockNo_" + stock.getStockNo() + " new stockObserver list...");
                    List<StockObserver> tmp = new ArrayList<>();
                    tmp.add(stockObserver);
                    stocksMap.put(Integer.valueOf(stock.getStockNo()), tmp);
                }else{
                    List<StockObserver> tmp = stocksMap.get(stock.getStockNo());
                    System.out.println("add stockNo"+ stock.getStockNo() +" stockObserver to list...");
                    if(!tmp.contains(stockObserver)){
                        tmp.add(stockObserver);
                    }
                }
            }
        }

    }

    public void logoff(StockObserver stockObserver){
        stockObservers.remove(stockObserver);
    }
    @Override
    public void sendMsg(String msg){
        System.out.println(centerName + " notify stock message...");
        for(StockObserver stockObserver : stockObservers){
            stockObserver.acceptMsg(msg);
        }
    }

    public void setBuyStock(int stockNo){
        if(buyStocks == null){
            buyStocks = new ArrayList<>();
        }
        buyStocks.add(stockNo);
    }

    public void setBuyStocks(List<Integer> stockNos){
        this.buyStocks = stockNos;
    }

    public void setSaleStock(int stockNo){
        if(saleStocks == null){
            saleStocks = new ArrayList<>();
        }
        saleStocks.add(stockNo);
    }

    public void setSaleStocks(List<Integer> stockNos){
        this.saleStocks = stockNos;
    }

}
