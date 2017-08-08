package com.dev4dan.designPattern.constructMod.facade;

class CipherMachine {
    public String Encrypt(String plainText) {
        System.out.println("数据加密，将明文转换为密文：");
        String es = "";
        char[] chars = plainText.toCharArray();
        for (int i=0 ; i<chars.length ; i++)
        {
            char ch = chars[i];
            String c = ((ch % 7)+"").toString();
            es += c;
        }
        System.out.println(es);
        return es;
    }
}  