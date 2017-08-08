package com.dev4dan.designPattern.actionMod.visitor.sample;

class ConcreteElementA implements Element {
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void operationA() {
        //业务方法  
    }
}  