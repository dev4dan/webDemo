package com.dev4dan.designPattern.actionMod.command.commandQueue;

import com.dev4dan.designPattern.actionMod.command.cumstomizedMenu.PatternCommand;

import java.util.ArrayList;

class CommandQueue {
    //定义一个ArrayList来存储命令队列
    private ArrayList<PatternCommand> PatternCommands = new ArrayList<PatternCommand>();

    public void addPatternCommand(PatternCommand PatternCommand) {
        PatternCommands.add(PatternCommand);
    }

    public void removePatternCommand(PatternCommand PatternCommand) {
        PatternCommands.remove(PatternCommand);
    }

    //循环调用每一个命令对象的execute()方法
    public void execute() {
        for (Object PatternCommand : PatternCommands) {
            ((PatternCommand)PatternCommand).execute();
        }
    }
}