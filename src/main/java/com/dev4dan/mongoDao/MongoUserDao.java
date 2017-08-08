package com.dev4dan.mongoDao;

import com.dev4dan.model.mongo.UserEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
  
@Transactional  
public interface MongoUserDao {
  
    public void _test();
  
    public void createCollection();
  
    public List<UserEntity> findList(int skip, int limit);
  
    public List<UserEntity> findListByAge(int age);
  
    public UserEntity findOne(String id);
  
    public UserEntity findOneByUsername(String username);
  
    public void insert(UserEntity entity);
  
    public void update(UserEntity entity);
  
} 