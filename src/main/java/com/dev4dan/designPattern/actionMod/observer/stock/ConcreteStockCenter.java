package com.dev4dan.designPattern.actionMod.observer.stock;

/**
 * Created by danielwood on 19/06/2017.
 */
public class ConcreteStockCenter extends AbsStockCenter{
    ConcreteStockCenter(){
        super(DEFAULT_CENTER);
    }

    ConcreteStockCenter(String stockCenter){
        super(stockCenter);
    }
}
