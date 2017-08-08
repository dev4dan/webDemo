package com.dev4dan.designPattern.constructMod.flyweight;

//围棋棋子类：抽象享元类
abstract class IgoChessman {  
    public abstract String getColor();

    public void display(Coordinates coord){
        System.out.println("棋子颜色：" + this.getColor() + "，棋子位置：" + coord.getX() + "，" + coord.getY() );
    }
}  
  

