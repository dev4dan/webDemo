package testCase;

import com.dev4dan.model.Order;
import com.dev4dan.model.User;
import com.dev4dan.redisComponent.basic.OrderRDao;
import com.dev4dan.redisComponent.basic.UserRDao;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试
 *
 * @author danny.wood
 * @version <b>1.0</b>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationContext-redis.xml"})
public class RedisTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private UserRDao userDao;
    @Autowired
    private OrderRDao orderRDao;
    /**
     * 新增
     */
    @Test
    public void testAddUser() {
        User user = new User();
        user.setId(1);
        user.setUserName("java2000_wl");
        boolean result = userDao.add(user);
        Assert.assertTrue(result);
    }

    /**
     * 批量新增 普通方式
     */
    @Test
    public void testAddUsers1() {
        List<User> list = new ArrayList<User>();
        for (int i = 10; i < 50000; i++) {
            User user = new User();
            user.setId(i);
            user.setUserName("java2000_wl" + i);
            list.add(user);
        }
        long begin = System.currentTimeMillis();
        for (User user : list) {
            userDao.add(user);
        }
        System.out.println(System.currentTimeMillis() - begin);
    }

    /**
     * 批量新增 batch
     */
    @Test
    public void testAddUsers2() {
        List<User> list = new ArrayList<User>();
        for (int i = 10; i < 1500000; i++) {
            User user = new User();
            user.setId(i);
            user.setUserName("java2000_wl" + i);
            list.add(user);
        }
        long begin = System.currentTimeMillis();
        boolean result = userDao.add(list);
        System.out.println(System.currentTimeMillis() - begin);
        Assert.assertTrue(result);
    }

    /**
     * 修改
     */
    @Test
    public void testUpdate() {
        User user = new User();
        user.setId(1);
        user.setUserName("new_password");
        boolean result = userDao.update(user);
        Assert.assertTrue(result);
    }

    /**
     * 通过key删除单个
     */
    @Test
    public void testDelete() {
        int key = 1;
        userDao.delete(key);
    }

    /**
     * 批量删除
     */
    @Test
    public void testDeletes() {
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < 10; i++) {
            list.add("" + i);
        }
        userDao.delete(list);
    }

    /**
     * 获取
     */
    @Test
    public void testGetUser() {
        String id = "" + 1;
        User user = userDao.get(id);
        System.out.println("name : " + user.getUserName() + " , birthday : " + user.getBirthday());
        Assert.assertNotNull(user);
        Assert.assertEquals(user.getUserName(), "java2000_wl");
    }

    /**
     * 设置userDao
     *
     * @param userDao the userDao to set
     */
    public void setUserDao(UserRDao userDao) {
        this.userDao = userDao;
    }

    @Test
    public void testAddOrder(){
        Order order = new Order();
        order.setID(1);
        order.setDate("2017-08-29 17:32:00");
        order.setEmpID(1);
        orderRDao.save(order);
    }

    @Test
    public void testGetOrder(){
        Order order = orderRDao.getOrderBy(1);
        if(order != null){
            System.out.println(order.getDate());
        }else {
            System.out.println("null");
        }
    }

    public void testDeleteOrder(){
        orderRDao.deleteById(1);
    }
}