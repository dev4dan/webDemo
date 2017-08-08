package com.dev4dan.mongoDao.impl;

import com.dev4dan.model.mongo.UserEntity;
import com.mongodb.DB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
  
@Component
public class UserDaoImpl extends MongoDaoImpl<UserEntity>{
  
    public static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);  
  
    @Autowired  
    private MongoTemplate mongoTemplate;  
  
    public void _test() {
        Set<String> colls = this.mongoTemplate.getCollectionNames();  
        for (String coll : colls) {  
            logger.info("CollectionName=" + coll);
            System.out.println(("CollectionName=" + coll));
        }  
        DB db = this.mongoTemplate.getDb();  
        logger.info("db=" + db.toString());  
    }  
  
    public void createCollection() {
        if (!this.mongoTemplate.collectionExists(UserEntity.class)) {
            this.mongoTemplate.createCollection(UserEntity.class);  
        }  
    }  
  
    public List<UserEntity> findList(int skip, int limit) {
        Query query = new Query();  
        query.with(new Sort(new Order(Direction.ASC, "_id")));  
        query.skip(skip).limit(limit);  
        return this.mongoTemplate.find(query, UserEntity.class);  
    }  
  
    public List<UserEntity> findListByAge(int age) {
        Query query = new Query();  
        query.addCriteria(new Criteria("age").is(age));
        return this.mongoTemplate.find(query, UserEntity.class);
    }  
  
    public UserEntity findOne(String id) {
        Query query = new Query();  
        query.addCriteria(new Criteria("_id").is(id));  
        return this.mongoTemplate.findOne(query, UserEntity.class);  
    }  
  
    public UserEntity findOneByUsername(String username) {
        Query query = new Query();  
        query.addCriteria(new Criteria("name.username").is(username));  
        return this.mongoTemplate.findOne(query, UserEntity.class);  
    }  
  
}