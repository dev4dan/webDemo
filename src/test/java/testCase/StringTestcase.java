package testCase;

import com.dev4dan.dataFactory.IdCardGenerator;
import com.dev4dan.dataFactory.IdcardUtils;

/**
 * Created by danielwood on 03/08/2017.
 */
public class StringTestcase {
    public static void main(String[] args) {
        String dateStr = IdcardUtils.getBirthByIdCard(IdCardGenerator.generate());
        System.out.println(dateStr.substring(0,4)+"-"+dateStr.substring(4,6)+"-"+dateStr.substring(6,8));
    }
}
