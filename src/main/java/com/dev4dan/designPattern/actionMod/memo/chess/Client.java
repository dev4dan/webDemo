package com.dev4dan.designPattern.actionMod.memo.chess;

class Client {

    private static int index = -1; //定义一个索引来记录当前状态所在位置

    private static MemoCaretaker mc = new MemoCaretaker();

    public static void main(String args[]) {  
        Chessman chess = new Chessman("车",1,1);
        display(chess);  
        mc.setMemento(chess.save()); //保存状态       
        chess.setY(4);  
        display(chess);  
        mc.setMemento(chess.save()); //保存状态  
        display(chess);  
        chess.setX(5);  
        display(chess);  
        System.out.println("******悔棋******");     
        chess.restore(mc.getMemento(1)); //恢复状态
        display(chess);  
    }  
      
    public static void display(Chessman chess) {  
        System.out.println("棋子" + chess.getLabel() + "当前位置为：" + "第" + chess.getX() + "行" + "第" + chess.getY() + "列。");  
    }

    //下棋
    public static void play(Chessman chess) {
        mc.setMemento(chess.save()); //保存备忘录
        index ++;
        System.out.println("棋子" + chess.getLabel() + "当前位置为：" + "第" + chess.getX() + "行" + "第" + chess.getY() + "列。");
    }

    //悔棋
    public static void undo(Chessman chess,int i) {
        System.out.println("******悔棋******");
        index --;
        chess.restore(mc.getMemento(i-1)); //撤销到上一个备忘录
        System.out.println("棋子" + chess.getLabel() + "当前位置为：" + "第" + chess.getX() + "行" + "第" + chess.getY() + "列。");
    }

    //撤销悔棋
    public static void redo(Chessman chess,int i) {
        System.out.println("******撤销悔棋******");
        index ++;
        chess.restore(mc.getMemento(i+1)); //恢复到下一个备忘录
        System.out.println("棋子" + chess.getLabel() + "当前位置为：" + "第" + chess.getX() + "行" + "第" + chess.getY() + "列。");
    }
}  