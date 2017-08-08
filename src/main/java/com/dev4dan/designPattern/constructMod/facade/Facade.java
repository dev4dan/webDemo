package com.dev4dan.designPattern.constructMod.facade;

class Facade
{
    private SubSystemA obj1 = new SubSystemA();
    private SubSystemB obj2 = new SubSystemB();
    private SubSystemC obj3 = new SubSystemC();

    public void method()
    {
        obj1.methodA();
        obj2.methodB();
        obj3.methodC();
    }
}