package com.dev4dan.controller;

import org.springframework.data.redis.core.ListOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by danielwood on 2017/1/21.
 */
@Controller
public class ToolsLabController {

    @Resource(name="redisTemplate")
    private ListOperations<String, String> listOps;

    @RequestMapping("/welcome")
    public ModelAndView helloWorld() {
        listOps.getOperations().boundSetOps("key1").add("val1");
        Map<String, String> map = new HashMap<>();
        for(int i=0 ; i<10 ; i++){
            map.put("key_"+i, "val_"+i);
        }
        listOps.getOperations().boundHashOps("map_1").putAll(map);

        String message = "<br><div style='text-align:center;'>"
                + "<h3>********** Hello World, Spring MVC Tutorial</h3>This message is coming from ToolsLabController.java **********</div><br><br>";
        message += "\n<br>keys:"+listOps.getOperations().boundHashOps("map_1").values()+"</br>";
        return new ModelAndView("welcome", "message", message);
    }
}
