package com.dev4dan.redisComponent.basic.impl;

import com.dev4dan.model.User;
import com.dev4dan.redisComponent.basic.AbstractBaseRedisDao;
import com.dev4dan.redisComponent.basic.UserRDao;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
  
/**  
 * Dao 
 * @author http://blog.csdn.net/java2000_wl  
 * @version <b>1.0</b>  
 */   
public class UserRDaoImpl extends AbstractBaseRedisDao<String, User> implements UserRDao {
    /**
     * 新增 
     * 
     * @param user
     * @return
     */
    public boolean add(final User user) {
        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
            public Boolean doInRedis(RedisConnection connection)
                    throws DataAccessException {
                RedisSerializer<String> serializer = getRedisSerializer();
                byte[] key  = serializer.serialize(""+user.getId());
                byte[] name = serializer.serialize(user.getUserName());
                return connection.setNX(key, name);
            }
        });
        return result;
    }

    /**
     * 批量新增 使用pipeline方式
     * 
     *@param list
     *@return
     */
    public boolean add(final List<User> list) {
        Assert.notEmpty(list);
        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
            public Boolean doInRedis(RedisConnection connection)
                    throws DataAccessException {
                RedisSerializer<String> serializer = getRedisSerializer();
                for (User user : list) {
                    byte[] key  = serializer.serialize(""+user.getId());
                    byte[] name = serializer.serialize(user.getUserName());
                    connection.setNX(key, name);
                }
                return true;
            }
        }, false, true);
        return result;
    }

    /**
     * 删除
     *  
     * @param key
     */
    public void delete(int key) {
        List<String> list = new ArrayList<String>();
        list.add(""+key);
        delete(list);
    }

    /**
     * 删除多个
     *  
     * @param keys
     */
    public void delete(List<String> keys) {
        redisTemplate.delete(keys);
    }

    /**
     * 修改
     *  
     * @param user
     * @return
     */
    public boolean update(final User user) {
        String key = ""+user.getId();
        if (get(key) == null) {
            throw new NullPointerException("数据行不存在, key = " + key);
        }
        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
            public Boolean doInRedis(RedisConnection connection)
                    throws DataAccessException {
                RedisSerializer<String> serializer = getRedisSerializer();
                byte[] key  = serializer.serialize(""+user.getId());
                byte[] name = serializer.serialize(user.getUserName());
                connection.set(key, name);
                return true;
            }
        });
        return result;
    }

    /**
     * 通过key获取
     *  
     * @param keyId 
     * @return 
     */  
    public User get(final String keyId) {
        User result = redisTemplate.execute(new RedisCallback<User>() {  
            public User doInRedis(RedisConnection connection)  
                    throws DataAccessException {  
                RedisSerializer<String> serializer = getRedisSerializer();  
                byte[] key = serializer.serialize(keyId);
                byte[] value = connection.get(key);  
                if (value == null) {  
                    return null;  
                }  
                String name = serializer.deserialize(value);  
                return new User(Integer.valueOf(keyId), name, null, null);
            }  
        });  
        return result;  
    }

    @Override
    public String getRKeyName() {
        return "USER";
    }
}