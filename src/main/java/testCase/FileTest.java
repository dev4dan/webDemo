package testCase;

import com.dev4dan.demo.lucene.AnalyzerTest;
import com.dev4dan.utils.Constants;
import com.dev4dan.utils.FileUtils;

import java.util.Iterator;
import java.util.Map;

/**
 * Created by danielwood on 28/04/2017.
 */
public class FileTest {
    public static void main(String[] args) {

        System.out.println(FileUtils.getRootDir("com/dev4dan/utils", "/"));

        Map<String, String> envs= Constants.envs;

        if(envs != null){
            Iterator<String> iterator = envs.keySet().iterator();
            while (iterator.hasNext()){
                String key = iterator.next();
                System.out.println("key_"+key +"---value_"+envs.get(key));
            }
        }else{
            System.out.println(envs);
        }

        System.out.println(AnalyzerTest.class.getResource("").getPath());

        String[] tmps = AnalyzerTest.class.getResource("").getPath().split("/");
        StringBuilder path = new StringBuilder();
        for(int i=0 ; i<tmps.length-4 ; i++){
            if(tmps[i] != null && !tmps[i].equals("")){
                path.append("/"+tmps[i]) ;
            }
        }
        System.out.println("path : "+path.toString());
    }
}
