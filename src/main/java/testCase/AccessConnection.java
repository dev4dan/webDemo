package testCase;

import java.io.File;
import java.sql.*;

/**
 * Created by danielwood on 13/05/2017.
 */
public class AccessConnection {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        String accessdb = "/lite_db/lexiang123.mdb";
        String path = AccessConnection.class.getResource("").getPath();
        String[] tmps = path.split("/");
        String targetPath = "";
        for(int i=0 ; i<tmps.length-4; i++){
            if(!tmps[i].equals("")){
                targetPath += "/"+tmps[i];
            }
        }
        File file = new File(targetPath+accessdb);
        if(file.exists()){
            System.out.println(file.getAbsoluteFile());
        }

        String   dbUr1="jdbc:Access://"+ targetPath+accessdb;
        System.out.println(dbUr1);

        String   user="";
        String   password="";
        Class.forName("com.hxtt.sql.access.AccessDriver");
        Connection c= DriverManager.getConnection(dbUr1,user,password);
        Statement s=c.createStatement();
        ResultSet r=s.executeQuery("SELECT name FROM MSYSOBJECTS WHERE TYPE=1 AND NAME NOT LIKE 'Msys*'");
        while(r.next()){
            System.out.println(r.getString("NAME"));
        }
        s.close();
    }

}
