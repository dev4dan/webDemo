package com.dev4dan.designPattern.actionMod.mediator.sample;

class ConcreteMediator extends Mediator {
    //实现业务方法，封装同事之间的调用
    public void operation() {
        if(colleagues != null && colleagues.size() != 0){
            for(Colleague colleague : colleagues){
                colleague.communicate();
                colleague.executeWork();
            }
        }
    }
}