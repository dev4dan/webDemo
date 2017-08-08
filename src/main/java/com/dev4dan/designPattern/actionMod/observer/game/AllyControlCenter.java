package com.dev4dan.designPattern.actionMod.observer.game;

import java.util.ArrayList;

//战队控制中心类：目标类
abstract class AllyControlCenter {  
    protected String teamName; //战队名称
    protected ArrayList<Observer> players = new ArrayList<Observer>(); //定义一个集合用于存储战队成员
      
    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }  
      
    public String getTeamName() {
        return this.teamName;
    }  
      
    //注册方法  
    public void join(Observer obs) {  
        System.out.println(obs.getName() + "加入" + this.teamName + "战队！");
        players.add(obs);  
    }  
      
    //注销方法  
    public void quit(Observer obs) {  
        System.out.println(obs.getName() + "退出" + this.teamName + "战队！");
        players.remove(obs);  
    }  
      
    //声明抽象通知方法  
    public abstract void notifyObserver(String name);  
}  