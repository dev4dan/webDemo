package testCase;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;  
  
public class ApplicationSpring {  
  
    public static void main(String[] args) {  
  
        System.out.println("Bootstrapping HelloMongo");  
  
        ConfigurableApplicationContext context = null;  
        context = new ClassPathXmlApplicationContext("applicationContext-mongo.xml");
  
    }
  
}