package com.dev4dan.designPattern.actionMod.state.sample;

/**
 * Created by danielwood on 19/06/2017.
 */
public class ConcreteStateA extends State{
    @Override
    public void handle() {
        System.out.println("ConcreteStateA");
    }
}
