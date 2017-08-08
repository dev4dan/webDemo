package com.dev4dan.designPattern.actionMod.visitor.sample;

import java.util.ArrayList;
import java.util.Iterator;

class ObjectStructure {
    private ArrayList<Element> list = new ArrayList<Element>(); //定义一个集合用于存储元素对象

    public void accept(Visitor visitor) {
        Iterator i = list.iterator();

        while (i.hasNext()) {
            ((Element) i.next()).accept(visitor); //遍历访问集合中的每一个元素
        }
    }

    public void addElement(Element element) {
        list.add(element);
    }

    public void removeElement(Element element) {
        list.remove(element);
    }
}  