package com.dev4dan.mongoDao.impl;

import com.dev4dan.model.peoplesInfo.Citizen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by danielwood on 03/08/2017.
 */
@Component
public class CitizenDaoImpl extends MongoDaoImpl<Citizen> {
    @Autowired
    private MongoTemplate mongoTemplate;

    CitizenDaoImpl(){
//        super.createCollection(Citizen.class);
    }

    public MongoTemplate getMongoTemplate() {
        return mongoTemplate;
    }
}
