package com.dev4dan.designPattern.constructMod.facade;


class EncryptFacade {
    //维持对其他对象的引用
    private FacadeFileReader reader;
    private CipherMachine cipher;
    private FacadeFileWriter writer;

    public EncryptFacade() {
        reader = new FacadeFileReader();
        cipher = new CipherMachine();
        writer = new FacadeFileWriter();
    }

    //调用其他对象的业务方法
    public void fileEncrypt(String fileNameSrc, String fileNameDes) {
        String plainStr = reader.read(fileNameSrc);
        String encryptStr = cipher.Encrypt(plainStr);
        writer.write(encryptStr, fileNameDes);
    }
}