package com.dev4dan.designPattern.constructMod.adapter;

/**
 * Created by danielwood on 15/06/2017.
 */
interface DefaultAdapter {
    public void adapterMethod1();
    public void adapterMethod2();
    public void adapterMethod3();
}


abstract class AbsDefaultAdapter implements DefaultAdapter{

    @Override
    public void adapterMethod1() {

    }

    @Override
    public void adapterMethod2() {

    }

    @Override
    public void adapterMethod3() {

    }
}

class ServiceAdapter extends AbsDefaultAdapter{
    @Override
    public void adapterMethod1() {
        System.out.println("ServiceAdapter.adapterMethod1--");
    }
}

