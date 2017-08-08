package com.dev4dan.designPattern.actionMod.state.switchState;

//打开状态
class OnState extends State {  
    public void on(Switch s) {  
        System.out.println("已经打开！");  
    }  
      
    public void off(Switch s) {  
        System.out.println("关闭！");  
        s.setState(Switch.getState("off"));  
    }  
} 