package com.dev4dan.designPattern.actionMod.memo.sample;

//备忘录类，默认可见性，包内可见
class Memento {  
    private String state;  
  
    public Memento(Originator o) {
        this.state = o.getState();
    }  
  
    public void setState(String state) {  
        this.state=state;  
    }  
  
    public String getState() {  
        return this.state;  
    }  
}  