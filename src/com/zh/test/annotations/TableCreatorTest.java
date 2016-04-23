package com.zh.test.annotations;

import com.zh.annotations.database.TableCreator;
import com.zh.annotations.database.exercise.SQLExecuteUtil;
import org.dom4j.Document;
import org.junit.Test;

import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Created by zhheng on 2016/3/26.
 */
public class TableCreatorTest {
    @org.junit.Before
    public void setUp() throws Exception {

    }

    @org.junit.After
    public void tearDown() throws Exception {

    }

    @org.junit.Test
    public void testExecuteSql() throws Exception {
        System.out.println("asdf");
//        List<String> sqlList = TableCreator.buildSQL();
//        for(String sql : sqlList){
//            SQLExecuteUtil.executeSql(sql);
//        }
    }
    @Test
    public void testBuildXML() throws Exception{
        try {
            List<Document> docList = TableCreator.buildXML();
            for(Document doc : docList){
                System.out.println(doc.asXML());
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}