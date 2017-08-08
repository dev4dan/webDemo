package com.dev4dan.designPattern.actionMod.observer.sample;

/**
 * Created by danielwood on 19/06/2017.
 */
public class Client {
    public static void main(String[] args) {
        ObserverPattern observerPattern1 = new ConcreteObserverPattern("observerPattern1");
        ObserverPattern observerPattern2 = new ConcreteObserverPattern("observerPattern2");
        ObserverPattern observerPattern3 = new ConcreteObserverPattern("observerPattern3");
        ObserverPattern observerPattern4 = new ConcreteObserverPattern("observerPattern4");

        ConcreteSubject subject = new ConcreteSubject();
        subject.attach(observerPattern1);
        subject.attach(observerPattern2);
        subject.attach(observerPattern3);
        subject.attach(observerPattern4);

        subject.notifyObserverPattern();
    }
}
