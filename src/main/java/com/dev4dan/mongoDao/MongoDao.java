package com.dev4dan.mongoDao;

import java.util.List;

/**
 * Created by danielwood on 03/08/2017.
 */
public interface MongoDao<T> {
    public void createCollection(Class<?> clz);

    public List<T> findList(int skip, int limit, Class<T> clz);

    public T findById(long id, Class<T> clz);

    public T findOneByKey(String key, Object value, Class<T> clz);

    public int insert(T t);

    public int insertList(List<T> list, Class<?> clz);

    public int update(T t);

    public int delete(T t);

//    public MongoTemplate getMongoTemplate();

}
