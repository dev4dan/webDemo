package testCase;

import com.dev4dan.dataFactory.IdCardGenerator;
import com.dev4dan.dataFactory.IdcardUtils;
import com.dev4dan.dataFactory.RandomInfo;
import com.dev4dan.model.mongo.NameEntity;
import com.dev4dan.model.mongo.UserEntity;
import com.dev4dan.model.peoplesInfo.Citizen;
import com.dev4dan.mongoDao.MongoDao;
import com.dev4dan.utils.DateUtils;
import com.dev4dan.utils.GeekStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by danielwood on 02/08/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/applicationContext.xml"})
public class MongoDemo {
//    @Resource
//    private MongoUserDao userDao;
    @Resource
    private MongoDao mongoDao;

    @Test
    public void testCreateDoc(){
        System.out.println("test");
        List<UserEntity> userEntityList = mongoDao.findList(0, 1, UserEntity.class);
        for (UserEntity userEntity : userEntityList){
            System.out.println(userEntity.toString());
        }

        UserEntity user = new UserEntity();
        user.setAge(20);
        user.setName(new NameEntity("ken", "kenny"));
        user.setPassword("password");
        user.setRegionName("beijing");
        user.setSpecial(new String[]{"test1", "test2"});
        user.setWorks(1);
        try {
            user.setBirth(DateUtils.convertStrToDate("1997-07-27 13:00:00", DateUtils.format_yyMMdd_HHmmss));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        mongoDao.insert(user);
    }

    @Test
    public void testCitizen(){
        int insertTimes = 100;
        int size = 10000;
        mongoDao.createCollection(Citizen.class);
        for(int j = 0 ; j < insertTimes ; j++){
            List<Citizen> citizens = new ArrayList<>();
            for(int i=0 ; i<size; i++){
                Citizen citizen = new Citizen();
                citizen.setCitizenName(RandomInfo.getChineseName());
                String idCard = IdCardGenerator.generate();
                citizen.setIdCard(idCard);

                String dateStr = IdcardUtils.getBirthByIdCard(idCard);
                citizen.setBirthday(GeekStringUtils.toDate(dateStr.substring(0,4)
                        + "-"+dateStr.substring(4,6)
                        + "-"+dateStr.substring(6,8)
                        + " 00:00:00"));

                citizen.setEmail(RandomInfo.getEmail(10, 30));
                citizen.setAddress(RandomInfo.getRoad());
                citizen.setMobile(RandomInfo.getTel());
                citizens.add(citizen);
//                System.out.println(citizen.toString());
            }
            mongoDao.insertList(citizens, Citizen.class);
            try {
                System.out.println("times : "+(j+1));
                Thread.currentThread().sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testFindById(){
        Citizen citizen = (Citizen) mongoDao.findById(12, Citizen.class);
        System.out.println(citizen.toString());
    }

    @Test
    public void testFindOneByKey(){
        Citizen citizen = (Citizen) mongoDao.findOneByKey("_id", 12, Citizen.class);
        System.out.println(citizen.toString());

    }

    @Test
    public void testDelete(){
        Citizen citizen = (Citizen) mongoDao.findOneByKey("_id", 11, Citizen.class);
        System.out.println(citizen.toString());
        mongoDao.delete(citizen);
    }

    @Test
    public void testUpdate(){
        Citizen citizen = (Citizen) mongoDao.findOneByKey("_id", 11, Citizen.class);
        System.out.println(citizen.toString());
        citizen.setAddress("海珠区新窖西路300号");
        mongoDao.update(citizen);
    }
}
