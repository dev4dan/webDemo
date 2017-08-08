package com.dev4dan.designPattern.actionMod.observer.sample;

import java.util.ArrayList;

abstract class Subject {
    //定义一个观察者集合用于存储所有观察者对象  
    protected ArrayList<ObserverPattern> observerPatterns = new ArrayList();

    //注册方法，用于向观察者集合中增加一个观察者
    public void attach(ObserverPattern observerPattern) {
        observerPatterns.add(observerPattern);
    }

    //注销方法，用于在观察者集合中删除一个观察者  
    public void detach(ObserverPattern observerPattern) {
        observerPatterns.remove(observerPattern);
    }

    //声明抽象通知方法  
    public abstract void notifyObserverPattern();
}  