package com.zh.annotations.database;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhheng on 2016/3/26.
 */
public class TableCreator {

    public static void main(String[] args) throws Exception{
//        if(args.length > 1){
//            System.out.println("arguments : annotated classes");
//            System.exit(0);
//        }
        buildSQL();
    }

    public static List<String> buildSQL() throws ClassNotFoundException {
        //抽出来的方法没有加传入参数，只是内部写了一个clarr数组
        String[] clarr = new String[]{"com.zh.annotations.database.Member"};
        List<String> sqlList = new ArrayList<String>();
        String createSql = "";
        for(String className : clarr){
            Class<?> cl = Class.forName(className);
            DBTable dbTable = cl.getAnnotation(DBTable.class);
            if(dbTable == null) {
                System.out.println("No DBTable annotations in class " + className);
                continue;
            }
            String tableName = dbTable.name();
            //如果name为null，则使用类名
            if(tableName.length() < 1){
                tableName = cl.getName().toUpperCase();
            }
            List<String> columnDefs = new ArrayList<String>();
            for(Field field : cl.getDeclaredFields()){
                String columnName = null;
                Annotation[] anns = field.getDeclaredAnnotations();
                if(anns.length < 1) continue;
                if(anns[0] instanceof SQLInteger){
                    SQLInteger sInt = (SQLInteger) anns[0];
                    //如果name没有指定则用域的名字
                    if(sInt.name().length() < 1){
                        columnName = field.getName().toUpperCase();
                    }else {
                        columnName = sInt.name();
                    }
                    columnDefs.add(columnName + " INT" + getConstraints(sInt.constraints()));
                }
                if(anns[0] instanceof SQLString){
                    SQLString sString = (SQLString) anns[0];
                    if(sString.name().length() < 1){
                        columnName = field.getName().toUpperCase();
                    }else{
                        columnName = sString.name();
                    }
                    columnDefs.add(columnName + " VARCHAR(" + sString.value() + ")" + getConstraints(sString.constraints()));
                }
                StringBuilder createCommand = new StringBuilder("CREATE TABLE " + tableName + "(");
                for(String columnDef : columnDefs)
                    createCommand.append("\n      " + columnDef + ",");
                createSql = createCommand.substring(0, createCommand.length()-1) + " \n);";
            }
//            System.out.println("Table Creation SQL for " + className + " is :\n" + createSql);
            sqlList.add(createSql);
            createSql = "";
        }
        return sqlList;
    }

    /**
     *
     * <class name="类名" table="表名">
     *  <property column="列名" [length="长度" unique="true" not-null="true" primary-key=""
     * </class>
     */
    public static List<Document> buildXML() throws ClassNotFoundException {
        //抽出来的方法没有加传入参数，只是内部写了一个clarr数组
        String[] clarr = new String[]{"com.zh.annotations.database.Member"};
        List<Document> docList = new ArrayList<Document>();
        for(String className : clarr){
//            构建xml
            Document doc = DocumentHelper.createDocument();
            doc.setXMLEncoding("utf-8");
            Element root = DocumentHelper.createElement("class");
            doc.setRootElement(root);
            //xml加入类名
//            root.add(DocumentHelper.createAttribute(root, "name", className));
            root.addAttribute("name", className);
            Class<?> cl = Class.forName(className);
            DBTable dbTable = cl.getAnnotation(DBTable.class);
            if(dbTable == null) {
                System.out.println("No DBTable annotations in class " + className);
                continue;
            }
            String tableName = dbTable.name();
            //如果name为null，则使用类名
            if(tableName.length() < 1){
                tableName = cl.getName().toUpperCase();
            }
            //xml中加入表名
//            root.add(DocumentHelper.createAttribute(root,"table",tableName));
            root.addAttribute("table", tableName);
            List<String> columnDefs = new ArrayList<String>();
            for(Field field : cl.getDeclaredFields()){
                String columnName = null;
                Annotation[] anns = field.getDeclaredAnnotations();
                //建立列节点
                Element ele = DocumentHelper.createElement("property");
                if(anns.length < 1) continue;
                if(anns[0] instanceof SQLInteger){
                    root.add(ele);
                    SQLInteger sInt = (SQLInteger) anns[0];
                    //如果name没有指定则用域的名字
                    if(sInt.name().length() < 1){
                        columnName = field.getName().toUpperCase();
                    }else {
                        columnName = sInt.name();
                    }
                    //加入列名属性
//                    DocumentHelper.createAttribute(ele, "column", columnName);
                    ele.addAttribute("column", columnName);
                    //构建其他属性
                    createConstraints(sInt.constraints(),ele);
                }
                if(anns[0] instanceof SQLString){
                    root.add(ele);
                    SQLString sString = (SQLString) anns[0];
                    if(sString.name().length() < 1){
                        columnName = field.getName().toUpperCase();
                    }else{
                        columnName = sString.name();
                    }
                    //加入列名属性
//                    DocumentHelper.createAttribute(ele, "column", columnName);
                    ele.addAttribute("column", columnName);
                    //string还要加入长度属性
//                    DocumentHelper.createAttribute(ele, "length", String.valueOf(sString.value()));
                    ele.addAttribute("length", String.valueOf(sString.value()));
                    //构建其他属性
                    createConstraints(sString.constraints(),ele);
                }
            }
            docList.add(doc);
        }
        return docList;
    }

    private static String getConstraints(Constraints con) {
        String constraints = "";
        if(!con.allowNull())
            constraints += " NOT NULL";
        if(con.primaryKey())
            constraints += " PRIMARY KEY";
        if(con.unique())
            constraints += " UNIQUE";
        return constraints;
    }

    private static void createConstraints(Constraints con, Element ele) {
        String constraints = "";
        if(!con.allowNull())
            ele.add(DocumentHelper.createAttribute(ele, "not-null", "true"));
        if(con.primaryKey())
            ele.add(DocumentHelper.createAttribute(ele, "primary-key", "true"));
        if(con.unique())
            ele.add(DocumentHelper.createAttribute(ele, "unique", "true"));
    }
}
