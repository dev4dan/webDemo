package com.dev4dan.designPattern.actionMod.mediator.customerMessage;

class Client {
    public static void main(String args[]) {  
        //定义中介者对象  
        ConcreteMediator mediator;  
        mediator = new ConcreteMediator();  
          
        //定义同事对象  
        MediatorButton addBT = new MediatorButton();
        ComponentList list = new ComponentList();
        ComboBox cb = new ComboBox();  
        TextBox userNameTB = new TextBox();  
  
        addBT.setMediator(mediator);  
        list.setMediator(mediator);  
        cb.setMediator(mediator);  
        userNameTB.setMediator(mediator);  
  
        mediator.addMediatorButton = addBT;
        mediator.list = list;  
        mediator.cb = cb;  
        mediator.userNameTextBox = userNameTB;  
          
        addBT.changed();  
        System.out.println("-----------------------------");  
        list.changed();  
    }  
}  