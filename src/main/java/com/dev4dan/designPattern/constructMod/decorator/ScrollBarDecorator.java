package com.dev4dan.designPattern.constructMod.decorator;

//滚动条装饰类：具体装饰类
class ScrollBarDecorator extends  ComponentDecorator
{
       public ScrollBarDecorator(Component  component)
       {
              super(component);
       }
 
       public void display()
       {
              this.setScrollBar();
              super.display();
       }
 
       public  void setScrollBar()
       {
              System.out.println("为构件增加滚动条！");
       }
}
 