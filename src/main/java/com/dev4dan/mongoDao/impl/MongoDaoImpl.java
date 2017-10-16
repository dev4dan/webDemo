package com.dev4dan.mongoDao.impl;

import com.dev4dan.mongoDao.MongoDao;
import com.dev4dan.utils.BeanInfoAssistant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by danielwood on 03/08/2017.
 */
public class MongoDaoImpl<T> implements MongoDao<T> {

    private static final int SUCC = 1;
    private static final int FAIL = 0;
    @Autowired
    private MongoTemplate mongoTemplate;

    public void createCollection(Class<?> clz){
        if (!mongoTemplate.collectionExists(clz)) {
            mongoTemplate.createCollection(clz);
        }
    }

    public List<T> findList(int skip, int limit, Class<T> clz){
        Query query = new Query();
        query.with(new Sort(new Sort.Order(Sort.Direction.ASC, "_id")));
        query.skip(skip).limit(limit);
        return mongoTemplate.find(query, clz);
    }

    @Override
    public T findById(long id, Class<T> clz) {
        Query query = new Query();
        query.addCriteria(new Criteria("_id").is(id));
        return mongoTemplate.findOne(query, clz);
    }

    @Override
    public T findOneByKey(String key, Object value, Class<T> clz) {
        Query query = new Query();
        query.addCriteria(Criteria.where(key).is(value));
        return mongoTemplate.findOne(query, clz);
    }

    @Override
    public int insert(T t) {
        if(t != null){
            createCollection(t.getClass());
            mongoTemplate.insert(t);
            return SUCC;
        }
        return FAIL;
    }

    public int insertList(List<T> list, Class<?> clz){
        if(list != null && list.size() > 0){
//            mongoTemplate.insertAll(list);
//            createCollection(clz);
            createCollection(clz);
            System.out.println("list.size : "+list.size());
            mongoTemplate.insert(list, clz);
            return SUCC;
        }
        return FAIL;
    }

    @Override
    public int update(T t) {
        try {
            Map<String, Object> map = BeanInfoAssistant.setBean2Map(t);
            if(map != null && map.size() > 0){
                Query query = new Query();
                String _id = "id";
                query.addCriteria(new Criteria(_id).is(map.get(_id)));
                Iterator<String> iterator = map.keySet().iterator();
                Update update = new Update();

                while (iterator.hasNext()){
                    String key = iterator.next();
//                    if(key.equals(_id)){
//                        continue;
//                    }

                    update.set(key, map.get(key));
                }
                mongoTemplate.updateFirst(query, update, t.getClass());
            }
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            return FAIL;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return FAIL;
        }
        return SUCC;
    }

    public int delete(T t){
        if(t != null){
            mongoTemplate.remove(t);
            return SUCC;
        }
        return FAIL;
    }

}
