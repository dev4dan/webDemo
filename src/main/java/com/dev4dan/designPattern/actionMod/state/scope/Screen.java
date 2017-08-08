package com.dev4dan.designPattern.actionMod.state.scope;

//屏幕类
class Screen {
    //枚举所有的状态，currentState表示当前状态  
    private State currentTimes, oneTimes, twoTimes, fourTimes, eightTimes;

    public Screen() {
        this.oneTimes = new OneTime(); //创建正常状态对象
        this.twoTimes = new TwoTimes(); //创建二倍放大状态对象
        this.fourTimes = new FourTimes(); //创建四倍放大状态对象
        this.eightTimes = new EightTimes(); //创建八倍放大状态对象
        this.currentTimes = oneTimes; //设置初始状态
        this.currentTimes.display();
    }

    public void setState(State state) {
        this.currentTimes = state;
    }

    //单击事件处理方法，封转了对状态类中业务方法的调用和状态的转换  
    public void onClick() {
        if (this.currentTimes == oneTimes) {
            this.setState(twoTimes);
            this.currentTimes.display();
        } else if (this.currentTimes == twoTimes) {
            this.setState(fourTimes);
            this.currentTimes.display();
        } else if (this.currentTimes == fourTimes) {
            this.setState(eightTimes);
            this.currentTimes.display();
        }else if (this.currentTimes == eightTimes) {
            this.setState(oneTimes);
            this.currentTimes.display();
        }
    }
}