package com.zh.chapter2;
/**
 * 一个可比较类型
 * @author zh
 * 2018年9月11日
 */
public class Date implements Comparable<Date> {
	
	private final int day;
	private final int month;
	private final int year;
	
	public Date(int day, int month, int year) {
		this.day = day;
		this.month = month;
		this.year = year;
	}

	@Override
	public int compareTo(Date other) {
		if (this.year > other.year) return 1;
		if (this.year < other.year) return -1;
		if (this.month > other.month) return 1;
		if (this.month < other.month) return -1;
		if (this.day > other.day) return 1;
		if (this.day < other.day) return -1;
		return 0;
	}
	
	public String toString() {
		return year + "-" + month + "-" + day;
	}
}
