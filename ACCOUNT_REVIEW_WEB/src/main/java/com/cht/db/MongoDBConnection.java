/*
 * 版權宣告: FDC all rights reserved.
 */
package com.cht.db;

import org.bson.Document;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**
 * 程式資訊摘要：
 * <P>
 * 類別名稱　　：MongoDBConnection.java
 * <P>
 * 程式內容說明：
 * <P>
 * 程式修改記錄：
 * <P>
 * XXXX-XX-XX：
 * <P>
 * 
 * @author chtd
 * @version 1.0
 * @since 1.0
 */
public class MongoDBConnection {
    public static void main(String[] args) {
//        MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));

        MongoClient mongo = new MongoClient(new MongoClientURI("mongodb://cht:cht@ds035683.mongolab.com:35683/ilm"));
        MongoDatabase db = mongo.getDatabase("ilm");
        
        MongoCollection<Document> collection = db.getCollection("test");
        Document doc = new Document("name", "MongoDB").append("type", "database").append("count", 1)
                .append("info", new Document("x", 203).append("y", 102));
        collection.insertOne(doc);
        
        
        mongo.close();
        
    //mongodb://cht:cht@ds035683.mongolab.com:35683/ilm
}
}
