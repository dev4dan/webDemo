package testCase;

import java.io.Reader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.dev4dan.dao.IEmployeeDao;
import com.dev4dan.dao.IOrderDao;
import com.dev4dan.dao.IUserDao;
import com.dev4dan.model.Employee;
import com.dev4dan.model.Order;
import com.dev4dan.model.User;
import com.dev4dan.utils.DateUtils;

public class Test4MyBatis {
	private static SqlSessionFactory sqlSessionFactory;
	private static String fileName = "config/MyBatisConfig.xml";
	private static Reader reader;
	static {
		try {
//			File file = new File("resources/" + fileName);
//			System.out.println(fileName + " : "
//					+ file.exists());
//			System.out.println(file.getAbsolutePath());
			System.out.println(Thread.currentThread().getContextClassLoader().getResource("").getPath());
			reader = Resources.getResourceAsReader(fileName);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
			LogFactory.useLog4JLogging();
			
			//在mapper的namespace中没有指明mapper完整类名(package+class)，记得使用前先注册
//			sqlSessionFactory.getConfiguration().addMapper(IUserDao.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static SqlSessionFactory getSession() {
		return sqlSessionFactory;
	}

	public static void main(String[] args) {
//		queryByDao();
//		queryUsereByFn();
//		queryEmployeeByDao();
//		queryOrderByDaoTest();
	}
	
	public static void queryUsereByFn(){
		SqlSession session = sqlSessionFactory.openSession();
		try {
			String qryStmt = "selectUserByName";
			User user = (User) session.selectOne(qryStmt, "summer");
			System.out.println(user.getUserAddress());
			System.out.println(user.getUserName());
		} finally {
			session.close();
		}
	}
	
	public static void queryUserByDao(){
		 SqlSession session = sqlSessionFactory.openSession();
	        try {
	            IUserDao userDao=session.getMapper(IUserDao.class);
	            User user = userDao.selectUserByID(1);
	            System.out.println(user.getUserAddress());
	            System.out.println(user.getUserName());
	        } finally {
	            session.close();
	        }
	}
	
	public static void queryEmployeeByDao(){
		 SqlSession session = sqlSessionFactory.openSession();
	        try {
	            IEmployeeDao employeeDao=session.getMapper(IEmployeeDao.class);
	            Employee empl = employeeDao.getEmployeeById(1);
	            System.out.println(empl.getName());
	            
	            List<Employee> emps = employeeDao.getEmployeeByName("bio2");
	            if(emps != null && emps.size() != 0){
	            	for(Employee emp : emps){	            		
	            		System.out.println(emp.getName());
	            	}
	            }
	        } finally {
	            session.close();
	        }
	}

	public void queryOrderByDaoTest(){
		 SqlSession session = sqlSessionFactory.openSession();
		 
	        try {
	            IOrderDao orderDao=session.getMapper(IOrderDao.class);
	            List<Integer> empsID = new ArrayList<Integer>();
	            
	            for(int i=1 ; i<=20 ; i++){
	            	empsID.add(i);
	            }
	            
	            List<Date> dates = new ArrayList<Date>();
	            
	            dates.add(DateUtils.getDateYYMMDD("2012-01-01"));
	            
	            List<Order> orders = orderDao.getOrderByEmps(empsID);
	            
	            Order order = orderDao.getOrderById(1);
	            
	            List<Order> ordersByDates = orderDao.getOrderByDates(dates);
	            
	            
	            System.out.println("#########################");
	            
	            System.out.println(orders.size());
	            
	            if(order != null){	            	
	            	System.out.println(order.getDate());
	            }else{
	            	System.out.println("order is null...");
	            }
	            
	            System.out.println(ordersByDates.size());
	            System.out.println("#########################");
	        } finally {
	            session.close();
	        }
	}
}