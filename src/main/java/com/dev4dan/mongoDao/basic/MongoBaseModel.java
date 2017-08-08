package com.dev4dan.mongoDao.basic;

import org.springframework.data.annotation.Id;

/**
 * Created by danielwood on 06/08/2017.
 */
public class MongoBaseModel {
    @AutoIncKey
    @Id
    private long id = 0l;
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
