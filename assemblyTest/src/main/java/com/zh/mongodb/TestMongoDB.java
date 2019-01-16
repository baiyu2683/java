package com.zh.mongodb;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoIterable;
import org.bson.*;
import org.bson.types.ObjectId;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class TestMongoDB {

    private static MongoClient client;

    @BeforeClass
    public static void beforeClass() {
        ServerAddress serverAddress = new ServerAddress("127.0.0.1", 27017);

        MongoClientOptions options = MongoClientOptions.builder()
                .minConnectionsPerHost(10)
                .connectionsPerHost(20)
                .build();

        client = new MongoClient(serverAddress, options);
    }

    @AfterClass
    public static void afterClass() {
        try {
            client.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void showCollections() {
        CountDownLatch latch = new CountDownLatch(1);
        for (int i = 0 ; i < 40 ; i++) {
            new Thread(() -> {
                MongoIterable<String> iterable = client.getDatabase("zh")
                        .listCollectionNames();
                for (String collection : iterable) {
                    System.out.println(collection);
                }
                try {
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        latch.countDown();
    }

    @Test
    public void showCollectionFields() {
        BsonDocument bson = new BsonDocument();
        bson.append("_id", new BsonObjectId(new ObjectId("5c120cd0e6175028f8819192")));
        FindIterable<Document> result = client.getDatabase("zh").getCollection("test")
                .find(bson);
        for (Document doc : result) {
            System.out.println(doc.toJson());
        }
    }

    @Test
    public void createDbAndColl() {
        client.dropDatabase("zh");
        client.getDatabase("zh").createCollection("test");
        MongoIterable<String> iterable = client.listDatabaseNames();
        for (String name : iterable) {
            System.out.println(name);
        }
    }

    @Test
    public void insert() {
        Map<String, Object> doc = new HashMap<>();
        doc.put("field1", "12");
        client.getDatabase("zh").getCollection("test")
                .insertOne(new Document(doc));
//        FindIterable<Document> result = client.getDatabase("zh").getCollection("test")
//                .find();
//        for (Document document : result) {
//            System.out.println(document.get("1"));
//        }
        Map<String, Object> newDoc = new HashMap<>();
        newDoc.put("field2", "asdf");
        newDoc.put("field3", new Date());
        client.getDatabase("zh").getCollection("test").insertOne(new Document(newDoc));
        newDoc.clear();
        JSONArray jsonArray = new JSONArray();
        jsonArray.add("123");
        jsonArray.add("asdf");
        newDoc.put("field4", jsonArray);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("key1", "1");
        jsonObject.put("key2", "2");
        newDoc.put("field5", jsonObject);
        client.getDatabase("zh").getCollection("test").insertOne(new Document(newDoc));

        newDoc.clear();
        newDoc = new HashMap<>();
        newDoc.put("field2", "asdf");
        newDoc.put("field3", new Date());
        newDoc.put("_id", UUID.randomUUID().toString());
        client.getDatabase("zh").getCollection("test").insertOne(new Document(newDoc));
    }

    @Test
    public void insertObjectId() {
        Map<String, Object> newDoc = new HashMap<>();
        newDoc.put("_id", new Date());
        client.getDatabase("zh").getCollection("test").insertOne(new Document(newDoc));
    }

    @Test
    public void query() {
        FindIterable<Document> result = client.getDatabase("zh").getCollection("test")
                .find();
        StringBuilder keyStringBuilder = new StringBuilder();
        for (Document document : result) {
            keyStringBuilder.setLength(0);
            Set<String> keySet= document.keySet();
            for (String key : keySet) {
                keyStringBuilder.append(key).append("-");
            }
            System.out.println(keyStringBuilder);
        }
    }
}
