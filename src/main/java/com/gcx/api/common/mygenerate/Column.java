package com.gcx.api.common.mygenerate;
/** 
 * @author	yang 
 * @version 创建时间：2017年8月4日 上午10:01:27 
 * 类说明:	
 */
public class Column {
	//字段名
	private String columnName;
	//java属性名
	private String javaName;
	//java属性名(首字母大写)
	private String javaName2;
	//字段类型
	private String columnType;
	//java类型
	private String javaType;
	//类型简称
	private String javaTypeSimple;
	//注释
	private String comment;
	
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public String getJavaName() {
		return javaName;
	}
	public void setJavaName(String javaName) {
		this.javaName = javaName;
	}
	public String getJavaName2() {
		return javaName2;
	}
	public void setJavaName2(String javaName2) {
		this.javaName2 = javaName2;
	}
	public String getColumnType() {
		return columnType;
	}
	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}
	public String getJavaType() {
		return javaType;
	}
	public void setJavaType(String javaType) {
		this.javaType = javaType;
	}
	public String getJavaTypeSimple() {
		return javaTypeSimple;
	}
	public void setJavaTypeSimple(String javaTypeSimple) {
		this.javaTypeSimple = javaTypeSimple;
	}
	@Override
	public String toString() {
		return "Column [columnName=" + columnName + ", javaName=" + javaName + ", javaName2=" + javaName2
				+ ", columnType=" + columnType + ", javaType=" + javaType + ", javaTypeSimple=" + javaTypeSimple
				+ ", comment=" + comment
				+ "]";
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}


	
	
}
