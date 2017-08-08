package com.dev4dan.designPattern.constructMod.facade;

import com.dev4dan.utils.Constants;

class Program {
    public static void main(String[] args) {
        EncryptFacade ef = new EncryptFacade();
        ef.fileEncrypt(Constants.getDefaultProjectPath()+"/src.txt", "des.txt");
        System.out.println("done");
    }
}