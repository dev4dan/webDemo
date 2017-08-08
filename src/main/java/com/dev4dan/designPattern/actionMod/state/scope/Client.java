package com.dev4dan.designPattern.actionMod.state.scope;

class Client {
    public static void main(String args[]) {  
        Screen screen = new Screen();  
        screen.onClick();  
        screen.onClick();  
        screen.onClick();
        screen.onClick();
    }  
}  