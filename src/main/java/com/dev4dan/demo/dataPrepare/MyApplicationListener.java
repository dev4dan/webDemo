package com.dev4dan.demo.dataPrepare;

import com.dev4dan.model.peoplesInfo.Citizen;
import com.dev4dan.mongoDao.MongoDao;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import javax.annotation.Resource;

public class MyApplicationListener implements ApplicationListener<ContextRefreshedEvent>{
    private Object lock = new Object();
    /** 
     * 是否已经初始化过了，确保只初始化一次 
     */  
    private static volatile boolean initialled = false;

    @Resource
    private MongoDao mongoDao;
  
    public void onApplicationEvent(ContextRefreshedEvent event) {  
        //通过判断是否为根applicationContext（没有父级）来防止下面的代码多次执行，  
        //因为还有可能有其他子applicationContext存在。  
        synchronized(lock) {
            if(event.getApplicationContext().getParent() != null && initialled) {  
                return;  
            }

//            System.out.println("----after initiation...");
//            Citizen citizen = (Citizen) mongoDao.findOneByKey("_id", 12, Citizen.class);
//            System.out.println(citizen.toString());

            initialled = true;  
        }  
        // do something...  
    }  
      
}  