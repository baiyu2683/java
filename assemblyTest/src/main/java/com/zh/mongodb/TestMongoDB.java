package com.zh.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoIterable;
import org.bson.Document;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class TestMongoDB {

    private static final String mongodb_url = "mongodb://127.0.0.1:27017/";

    private static MongoClient client;

    @BeforeClass
    public static void beforeClass() {
        MongoClientURI mongoClientURI = new MongoClientURI(mongodb_url);
        client = new MongoClient(mongoClientURI);
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
        doc.put("1", "12");
        client.getDatabase("zh").getCollection("test")
                .insertOne(new Document(doc));
        FindIterable<Document> result = client.getDatabase("zh").getCollection("test")
                .find();
        for (Document document : result) {
            System.out.println(document.get("1"));
        }
    }
}
