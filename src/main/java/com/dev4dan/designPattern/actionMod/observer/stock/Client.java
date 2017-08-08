package com.dev4dan.designPattern.actionMod.observer.stock;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by danielwood on 19/06/2017.
 */
public class Client {
    public static void main(String[] args) {
        List<StockObserver> observers = new ArrayList<>();
        Random random = new Random();
        int seed = 10;
        for(int i=1 ; i<=5 ; i++){
            StockObserver observer = new ConcreteStockObserver("observer"+i);
            int num = random.nextInt(seed)+1;
            for(int j=1 ; j<=num ; j++){
                Stock stock = new Stock();
                stock.setStockNo(j);
                stock.setStockName("stockName_"+j);
                observer.addStock(stock);
            }
            System.out.println("----------------------------------------");
            observers.add(observer);
        }

        StockCenter stockCenter = new ConcreteStockCenter("Universe NO.1 Stock Center");

        for(StockObserver observer : observers){
            stockCenter.register(observer);
        }

        stockCenter.sendMsg("rise ￥100");

    }
}
