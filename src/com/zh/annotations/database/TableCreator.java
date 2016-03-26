package com.zh.annotations.database;

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
        String[] clarr = new String[]{"com.zh.annotations.database.Member"};
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
//                System.out.println("Table Creation SQL for " + className + " is :\n" + tableCreate);
            }
            System.out.println("Table Creation SQL for " + className + " is :\n" + createSql);
        }
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
}
