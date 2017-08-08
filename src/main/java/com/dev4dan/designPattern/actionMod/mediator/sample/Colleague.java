package com.dev4dan.designPattern.actionMod.mediator.sample;

abstract class Colleague {
    protected Mediator mediator; //维持一个抽象中介者的引用  

    public Colleague(Mediator mediator) {
        this.mediator = mediator;
    }

    public abstract void communicate(); //声明自身方法，处理自己的行为

    //定义依赖方法，与中介者进行通信  
    public void executeWork() {
        System.out.println("Colleague executeWork ");
        mediator.operation();
    }
} 