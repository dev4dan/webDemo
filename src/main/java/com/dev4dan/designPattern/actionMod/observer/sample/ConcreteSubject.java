package com.dev4dan.designPattern.actionMod.observer.sample;

import java.util.Observer;

class ConcreteSubject extends Subject {
    //实现通知方法  
    public void notifyObserverPattern() {
        //遍历观察者集合，调用每一个观察者的响应方法  
        for (ObserverPattern obs : observerPatterns) {
            obs.update();
        }
    }
} 