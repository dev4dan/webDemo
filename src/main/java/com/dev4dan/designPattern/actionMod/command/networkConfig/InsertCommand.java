package com.dev4dan.designPattern.actionMod.command.networkConfig;

//增加命令类：具体命令
class InsertCommand extends Command {  
    public InsertCommand(String name) {  
        super(name);  
    }  
      
    public void execute(String args) {  
        this.args = args;  
        configOperator.insert(args);  
    }  
      
    public void execute() {  
        configOperator.insert(this.args);  
    }  
} 