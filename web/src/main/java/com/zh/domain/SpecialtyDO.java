package com.zh.domain;

import java.io.Serializable;

import lombok.Data;

/**
 * 专业
 * @author zh
 * 2018年9月6日
 */
@Data
public class SpecialtyDO implements Serializable {
	
	private static final long serialVersionUID = -4986955679849596194L;

	/**唯一标示*/
	private String id;
	/**专业编码,唯一值*/
	private String code;
	/**专业名称*/
	private String name;
	/**显示名称*/
	private String dispName;
}
