package com.dev4dan.designPattern.actionMod.state.switchState;

abstract class State {
    public abstract void on(Switch s);  
    public abstract void off(Switch s);  
}