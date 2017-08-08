package com.dev4dan.designPattern.actionMod.mediator.sample;

class ConcreteColleague extends Colleague {
    public ConcreteColleague(Mediator mediator) {
        super(mediator);
    }

    //实现自身方法  
    public void communicate() {
        System.out.println("ConcreteColleague communicate");
    }
}  