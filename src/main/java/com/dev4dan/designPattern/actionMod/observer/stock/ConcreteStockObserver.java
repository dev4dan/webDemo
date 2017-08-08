package com.dev4dan.designPattern.actionMod.observer.stock;

/**
 * Created by danielwood on 19/06/2017.
 */
public class ConcreteStockObserver extends AbsStockObserver{
    public ConcreteStockObserver(){
        setName(DEFAULT_NAME);
    }

    public ConcreteStockObserver(String name){
        setName(name);
    }
}
