package com.dev4dan.designPattern.actionMod.observer.sample;

class ConcreteObserverPattern implements ObserverPattern {
    private String name;

    public ConcreteObserverPattern(){}

    public ConcreteObserverPattern(String name){
        this.name = name;
    }

    //实现响应方法  
    public void update() {  
        //具体响应代码
        System.out.println(this.name + " update");
    }  
} 