package com.dev4dan.mongoDao.basic;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;  
import java.lang.annotation.RetentionPolicy;  
import java.lang.annotation.Target;  
  
/** 
 * 自定义注解，标识主键字段需要自动增长 
 * <p> 
 * ClassName: AutoIncKey 
 * </p> 
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)  
public @interface AutoIncKey {  
      
}  