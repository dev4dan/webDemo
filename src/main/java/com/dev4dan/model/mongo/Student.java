package com.dev4dan.model.mongo;

import com.dev4dan.mongoDao.basic.AutoIncKey;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
  
@Document(collection = "student")  
public class Student {  
  
    @AutoIncKey
    @Id  
    private Long id = 0L;
  
    @Field  
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}