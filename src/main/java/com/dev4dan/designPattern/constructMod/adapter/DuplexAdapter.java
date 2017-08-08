package com.dev4dan.designPattern.constructMod.adapter;

// 双向适配器
class DuplexAdapter implements Target,Adaptee {
    //同时维持对抽象目标类和适配者的引用  
    private Target target;  
    private Adaptee adaptee;  
      
    public DuplexAdapter(Target target) {
        this.target = target;  
    }  
      
    public DuplexAdapter(Adaptee adaptee) {
        this.adaptee = adaptee;  
    }  
      
    public void request() {  
        adaptee.specificRequest();  
    }  
      
    public void specificRequest() {  
        target.request();  
    }  
}
interface Target{
    public void request();
}

interface Adaptee{
    public void specificRequest();
}