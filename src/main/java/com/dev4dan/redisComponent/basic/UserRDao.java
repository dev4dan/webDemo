package com.dev4dan.redisComponent.basic;

import com.dev4dan.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author danny.wood
 * @version <b>1.0</b>
 */
@Service
public interface UserRDao {

    /**
     * 新增
     * 
     *
     * @param user
     *
     * @return
     */
    boolean add(User user);

    /**
     * 批量新增 使用pipeline方式
     * 
     *
     * @param list
     *
     * @return
     */
    boolean add(List<User> list);

    /**
     * 删除
     * 
     *
     * @param key
     */
    void delete(int key);

    /**
     * 删除多个
     * 
     *
     * @param keys
     */
    void delete(List<String> keys);

    /**
     * 修改
     * 
     *
     * @param user
     *
     * @return
     */
    boolean update(User user);

    /**
     * 通过key获取
     * 
     *
     * @param keyId
     *
     * @return
     */
    User get(String keyId);

}