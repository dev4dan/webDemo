package com.dev4dan.utils;

public class MethodCase {

	private static final ThreadLocal<MethodCase> local = new ThreadLocal<MethodCase>();
	static{
		init();
	}
	
	public static void init(){
		System.out.println("------------init....");
		local.set(new MethodCase());
	}

	public static void SHOWINFO(){
		System.out.println("------" + local.get().getClass().getName());
	}
		
}
