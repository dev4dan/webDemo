package com.dev4dan.designPattern.actionMod.memo.chess;

import java.util.ArrayList;

class MemoCaretaker {
    //定义一个集合来存储多个备忘录
    private ArrayList mementolist = new ArrayList();

    public ChessmanMemo getMemento(int i) {
        return (ChessmanMemo)mementolist.get(i);
    }

    public void setMemento(ChessmanMemo memento) {
        mementolist.add(memento);
    }
}
