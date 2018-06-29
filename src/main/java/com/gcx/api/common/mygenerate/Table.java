package com.gcx.api.common.mygenerate;

import java.util.ArrayList;

/** 
 * @author	yang 
 * @version 创建时间：2017年8月4日 上午9:59:13 
 * 类说明:	
 */
public class Table {
	//表名
	private String tableName;
	//实体名
	private String javaName;
	//实体名(首字母小写)
	private String javaName2;
	//字段集合
	private ArrayList<Column> columns;
	//主键
	private Column column;
	
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
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
	public ArrayList<Column> getColumns() {
		return columns;
	}
	public void setColumns(ArrayList<Column> columns) {
		this.columns = columns;
	}
	public Column getColumn() {
		return column;
	}
	public void setColumn(Column column) {
		this.column = column;
	}
	@Override
	public String toString() {
		return "Table [tableName=" + tableName + ", javaName=" + javaName + ", javaName2=" + javaName2 + ", columns="
				+ columns + ", column=" + column + "]";
	}
	

	
}
