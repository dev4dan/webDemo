package com.dev4dan.demo.mongo;

import com.dev4dan.dataFactory.IdCardGenerator;
import com.dev4dan.dataFactory.RandomInfo;
import com.dev4dan.model.peoplesInfo.Citizen;
import com.dev4dan.utils.BeanInfoAssistant;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.*;
import org.bson.Document;

import java.util.*;

public class MongoDBJDBC {

    private static final String host = "localhost";
    private static final int port = 27017;
    private static final String username = "danny";
    private static final String databaseName = "myMongo";
    private static final String password = "myMongo123ABC";
    private static List<String> collections = new ArrayList<>();
    static{
        collections.add("course");
        collections.add("citizen");
    }


    public static void main(String args[]) {
        generateData();
    }

    public static void generateData() {
        for(int i=0 ; i<10 ; i++){
            Citizen citizen = new Citizen();
            citizen.setCitizenName(RandomInfo.getChineseName());
            citizen.setMobile(RandomInfo.getTel());
            citizen.setAddress(RandomInfo.getRoad());
            citizen.setEmail(RandomInfo.getEmail(10,20));
            citizen.setIdCard(IdCardGenerator.generate());
            citizen.setBirthday(IdCardGenerator.randomBirthday2Date());
            System.out.println(citizen.toString());
        }

        Citizen citizen = new Citizen();
        List<String> fields = BeanInfoAssistant.getBeanFieldsList(citizen);
        for(String field : fields){
            System.out.println("field : "+field);
        }

    }

    public static void createCollection(){
        MongoDatabase mongoDatabase = getMongoDatabase();

        MongoIterable<String> iterable = mongoDatabase.listCollectionNames();
//            mongoDatabase.createCollection(table);
            Iterator iterator = iterable.iterator();
            if(!iterator.hasNext()){
                for(String collection : collections){
                    mongoDatabase.createCollection(collection);
                }
            }else{
                while(iterator.hasNext()){
                    String collection = (String)iterator.next();
                    if(collections.contains(collection)){
                        System.out.println(collection);
                        collections.remove(collection);
                    }
                }

                for(int i=0 ; i<collections.size() ; i++){
                    String collection = collections.get(i);
                    System.out.println("not exist...collection will be create...");
                    mongoDatabase.createCollection(collection);
                }
            }


    }

    public static void queryCollection(){
//        MongoClient mongoClient = new MongoClient( "localhost" , 27017 );

        // 连接到数据库
//        MongoDatabase mongoDatabase = mongoClient.getDatabase("myMongo");
        String table = "user";
        MongoDatabase mongoDatabase = getMongoDatabase();
        MongoCollection<Document> collection = mongoDatabase.getCollection(table);
        FindIterable<Document> documents = collection.find();
        MongoCursor<Document> mongoCursor= documents.iterator();
        while (mongoCursor.hasNext()){
            Document document = mongoCursor.next();
            System.out.println(document.toJson());
        }

    }

    public static MongoDatabase getMongoDatabase(){
        try {
            //连接到MongoDB服务 如果是远程连接可以替换“localhost”为服务器所在IP地址
            //ServerAddress()两个参数分别为 服务器地址 和 端口
            ServerAddress serverAddress = new ServerAddress(host,port);
            List<ServerAddress> addrs = new ArrayList<ServerAddress>();
            addrs.add(serverAddress);

            //MongoCredential.createScramSha1Credential()三个参数分别为 用户名 数据库名称 密码
            MongoCredential credential = MongoCredential.createScramSha1Credential(username, databaseName, password.toCharArray());
            List<MongoCredential> credentials = new ArrayList<MongoCredential>();
            credentials.add(credential);

            //通过连接认证获取MongoDB连接
            MongoClient mongoClient = new MongoClient(addrs,credentials);

            //连接到数据库
            MongoDatabase mongoDatabase = mongoClient.getDatabase(databaseName);
            System.out.println("Connect to database successfully");
            return mongoDatabase;
        } catch (Exception e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            return null;
        }
    }
}