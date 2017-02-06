package com.dev4dan.utils;

public class Constants {
	public static enum CONDITION{
		OR("OR"), AND("AND"), GROUP_BY("GROUP BY"), HAVING("HAVING"),ORDER_BY("ORDER BY");
		private String condition;
		private CONDITION(String cond){
			this.condition = cond;
		}
		
		public String toString(){
			return condition;
		}
	}
}
