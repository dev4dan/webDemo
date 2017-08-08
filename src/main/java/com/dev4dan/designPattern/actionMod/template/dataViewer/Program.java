package com.dev4dan.designPattern.actionMod.template.dataViewer;

class Program {
    public static void main(String[] args) {
        DataViewer dv;
        dv = new XMLDataViewer();
        dv.Process();
    }
}