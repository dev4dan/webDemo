package testCase;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by danielwood on 10/06/2017.
 */
public class InsertDataTest {
    private static int courseNum = 20; // 课程数量
    private static int departNum = 20; // 学院数量
    private static int clzNum = 20; // 班级数量
    private static int studentNum = 1000; // 学生数量
    private static int teacherNum = 20; // 教师数量
    private static int gender = 2; // 性别，0为男生，1为女生
    private static Random rand = new Random();
    private static int january = 31;
    public static void main(String[] args) {
        String  s = "swwwwwwwwtrwwwwwwinwwwwwwwwwwwwg";
        String s1 =s.replaceAll("w+", " ");
        System.out.println(s + " length : "+s.length() + ", " + s1 + " length : " + s1.length());
//        course();
//        department();
//        studentClazz();
//        student();
//        teacher();
//        score();
//        duty();
    }

    public static void student(){
        int day = 0;
        String studentInsert = "INSERT INTO student(name, sex, birth, departmentId, clazzId, address) VALUES";
        StringBuilder data = new StringBuilder();
        for(int i=1 ; i<=studentNum ; i++){
            String date = "1991-01-";
            day = rand.nextInt(january) + 1;
            if(day < 10 ){
                date += "0"+day;
            }else{
                date += day;
            }

            data.append("('danny"+i+"', "
                    + rand.nextInt(gender) + ", "
                    + "'"+date+"', "
                    + (rand.nextInt(departNum)+1)+", "
                    + (rand.nextInt(clzNum)+1)+", "
                    + "'somewhere'"
                    + ")");
             if(i < studentNum){
                 data.append(",\n");
             }else{
                 data.append(";\n");
             }

//            System.out.println(data);
        }

        System.out.println(studentInsert + data.toString());
    }

    public static void teacher(){
        int day = 0;
        String teacherInsert = "INSERT INTO teacher(name, sex, birth, departmentId, address) VALUES";
        String data = "";
        for(int i=1 ; i<=teacherNum ; i++){
            String date = "1985-01-";
            day = rand.nextInt(january) + 1;
            if(day < 10 ){
                date += "0"+i;
            }else{
                date += i;
            }
            data += "('Thomas"+i+"',"
                    + rand.nextInt(gender)+ ", "
                    + "'"+date+"', "
                    + (rand.nextInt(departNum)+1)+", "
                    + "'somewhere'"
                    + ")";

            if(i < teacherNum){
                data += ",\n";
            }else{
                data += ";\n";
            }

        }
        System.out.println(teacherInsert+data);
    }

    public static void course(){

        for(int i = 1; i<= courseNum; i++){
            System.out.println("INSERT INTO course(courseName) VALUES('课程"+i+"');");
        }
    }

    public static void score(){
        int extra = 41;
        String scoreInsert = "INSERT INTO score(stu_id, courseId, grade, isAvailable) VALUES";
        StringBuilder data = new StringBuilder();
        for(int i=1 ; i<=studentNum ; i++){
            for(int j=1; j<=courseNum ; j++){


                String val = "(" + i + ", "
                        + j + ", "
                        + (rand.nextInt(extra)+60) + ", "
                        + 1
                        +")";

                data.append(val);
                if(i<studentNum || j< courseNum){
                    data.append(",\n");
                }
            }

            if(i == studentNum){
                data.append(";\n");
            }
        }
        System.out.println(scoreInsert+data.toString());
    }

    public static void department(){

        for(int i=1 ; i<=departNum ; i++){
            System.out.println("INSERT INTO department(department) VALUES('学院"+i+"');");
        }
    }

    public static void duty(){

        String dutyInsert = "INSERT INTO duty(teacherId, courseId, clazzId) VALUES";

        List<Integer> courses = new ArrayList<>();
        List<Integer> clazz = new ArrayList<>();
        List<Integer> teachers = new ArrayList<>();
        for(int i=1 ; i<=courseNum ; i++){
            courses.add(i);
            clazz.add(i);
            teachers.add(i);
        }

        randList(courses);
        randList(clazz);
        randList(teachers);
//        System.out.println("list : " + courses.toString());
//        System.out.println("list : " + clazz.toString());
//        System.out.println("list : " + teachers.toString());

        StringBuilder data = new StringBuilder();
        data.append(dutyInsert);

        for(int i=1 ; i<=teacherNum ; i++){
            int size = courses.size();
            int index = rand.nextInt(size);

            int courseId = courses.get(index);
            int clazzId = clazz.get(index);
            int teacher = teachers.get(index);

            courses.remove(index);
            clazz.remove(index);
            teachers.remove(index);

            data.append("("
                    + teacher + ","
                    + courseId + ", "
                    + clazzId + ")");
            if(i < teacherNum){
                data.append(",\n");
            }else{
                data.append(";\n");
            }

//            System.out.println("----list size : "+size);
//            System.out.println("coursedId : "+courseId
//                    + ", clazzId : "+ clazzId
//                    + ", teacherId : "+ teacher
//                    +", list size : "+courses.size());

        }
        System.out.println(data.toString());
    }

    private static void randList(List<Integer> list){
        int times = courseNum/2;
        Random rand = new Random();
        for(int i=0 ; i<times ; i++){
            int index = rand.nextInt(times) + times;
            int changeIndex = courseNum-index-1;
            int tmp = list.get(index);
            int val = list.get(changeIndex);
            list.set(changeIndex, tmp);
            list.set(index, val);
//            System.out.println("index : " + index + ", changeIndex : " + changeIndex);
        }
    }

    public static void studentClazz(){

        for(int i=1 ; i<=clzNum ; i++){
            System.out.println("INSERT INTO clazz(clazz) VALUES('一年"+i+"班');");
        }
    }
}
