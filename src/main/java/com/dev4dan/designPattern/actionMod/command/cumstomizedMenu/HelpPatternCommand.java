package com.dev4dan.designPattern.actionMod.command.cumstomizedMenu;

//帮助命令类：具体命令类
class HelpPatternCommand extends PatternCommand {
    private HelpHandler hhObj; //维持对请求接收者的引用  
      
    public HelpPatternCommand() {
        hhObj = new HelpHandler();  
    }  
      
    //命令执行方法，将调用请求接收者的业务方法  
    public void execute() {  
        hhObj.display();  
    }  
} 