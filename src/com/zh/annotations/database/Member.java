package com.zh.annotations.database;

/**
 * Created by zhheng on 2016/3/26.
 */
@DBTable(name = "MEMBER")
public class Member {
    @SQLString(250) String firstName;
    @SQLString(250) String lastName;
    @SQLInteger Integer age;
    @SQLString(value = 30, constraints = @Constraints(primaryKey = true)) String handle;
    static int memberCount;
    public String getHandle(){ return handle; }
    public String getFirstName(){ return firstName; }
    public String getLastName() { return handle; }
    public Integer getAge(){ return age; }
}
