package testCase;

import com.dev4dan.dao.UserMapper;
import com.dev4dan.dataFactory.IdCardGenerator;
import com.dev4dan.dataFactory.RandomInfo;
import com.dev4dan.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by danielwood on 07/08/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/applicationContext.xml"})
public class MybatisTestcase {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void testUserdaoFind(){
        User user = userMapper.selectUserByName("bio1");
        if(user != null){
            System.out.println(user.getUserAddress());
        }else{
            System.out.println(user);
        }
    }

    @Test
    public void testUserMapperInsert(){
        User user = new User();
        user.setUserName("bio2");
        user.setBirthday(new Date());
        user.setUserAddress("新窖西路300号铂涛大厦");
        int result = userMapper.insertUserById(user);
        System.out.println("result : "+result);
    }

    @Test
    public void testUserMapperUpdate(){
        User user = userMapper.selectUserByName("bio2");
        System.out.println(user.getUserAddress());
        user.setUserAddress("----新窖西路300号铂涛大厦");
        System.out.println("result : "+userMapper.updateUser(user));
    }

    @Test
    public void testUserMapperDelete(){
        User user = userMapper.selectUserByName("bio2");
        System.out.println(user.getUserAddress());
        System.out.println("result : "+userMapper.deleteUserById(user.getId()));
    }

    @Test
    public void testUserMapperInsertBatch(){
        int num = 10;
        List<User> users = new ArrayList<>();
        for(int i=0 ; i<num ; i++){
            User user = new User();
            user.setUserAddress(RandomInfo.getRoad());
            user.setUserName(RandomInfo.getChineseName());
            user.setBirthday(IdCardGenerator.randomBirthday2Date());
            users.add(user);
            System.out.println(user.toString());
        }
        System.out.println("result : "+userMapper.insertUsers(users));
    }

}
