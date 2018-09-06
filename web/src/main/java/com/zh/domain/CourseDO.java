package com.zh.domain;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 课程
 * @author zh
 * 2018年9月6日
 */
@Data
public class CourseDO implements Serializable {
	
	private static final long serialVersionUID = 5348569861759255484L;
	
	/**唯一标示*/
	private String id;
	/**课程编码,唯一值*/
	private String code;
	/**课程名称*/
	private String name;
	/**开课时间*/
	private Date startDate;
	/**结课时间*/
	private Date endDate;
}
