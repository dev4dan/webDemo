package com.dev4dan.designPattern.actionMod.command.caculator;

class Client {
    public static void main(String args[]) {  
        CalculatorForm form = new CalculatorForm();  
        AbstractCommand command;  
        command = new ConcreteCommand();  
        form.setCommand(command); //向发送者注入命令对象  
          
        form.compute(10);  
        form.compute(5);  
        form.compute(10);  
        form.undo();  
    }  
}