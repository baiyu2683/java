package com.zh.domain;

import java.io.Serializable;
import java.util.Date;

import com.zh.constant.SexEnum;

import lombok.Data;

/**
 * 学生信息
 * @author zh
 * 2018年9月6日
 */
@Data
public class StudentDO implements Serializable {

	private static final long serialVersionUID = 927830074944906800L;
	
	/**唯一标示*/
	private String id;
	/**学号*/
	private String studentId;
	/**姓名*/
	private String name;
	/**性别*/
	private SexEnum sex;
	/**年龄*/
	private Integer age;
	/**籍贯*/
	private String nativePlace;
	/**出生日期*/
	private Date birthday;
	/**入学时间*/
	private Date enrolmentTime;
	/**专业*/
	private String specialtyId;
}
