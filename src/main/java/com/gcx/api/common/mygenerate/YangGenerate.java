package com.gcx.api.common.mygenerate;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;

/** 
 * @author	yang 
 * @version 创建时间：2017年8月4日 上午10:18:13 
 * 类说明:	
 */
public class YangGenerate {

	private static String projectName = "feesysapi";//项目名

	private static String databaseName = "feesys";//数据库名

	private static String tableName = "PAY_PASS";//表名

	private  static String author="qimy";//作者

	private static boolean flag1 = true;//dao、model、mapper开关

	private static boolean flag2 = false;//controller、service、serviceImpl开关

	private static String workeSpace = "E:\\WorkSpace\\";//工作空间地址

    private static String basePath = workeSpace + projectName + "\\src\\main\\";//项目的基础目录拼接

    private static String fileURL = workeSpace + projectName + "\\src\\main\\java\\com\\gcx\\feesys\\common";


	public static void main(String[] args) throws Exception {
		//获取数据库连接
		Connection conn = getConnection();
		//获取表信息
		Table table = getTable(conn);
		//生成dao、model、mapper
		if(flag1){
			freeMarker(table, "dao.ftl", basePath+"java\\com\\gcx\\feesys\\dao", "Mapper.java");
			freeMarker(table, "model.ftl", basePath+"java\\com\\gcx\\feesys\\model", ".java");
			freeMarker(table, "mapper.ftl", basePath+"resources\\mapper", "Mapper.xml");
		}
		//生成controller、service、serviceImpl
		if(flag2){
			freeMarker(table, "Controller.ftl", basePath+"java\\com\\gcx\\feesys\\controller", "Controller.java");
			freeMarker(table, "Service.ftl", basePath+"java\\com\\gcx\\feesys\\service", "Service.java");
			freeMarker(table, "ServiceImpl.ftl", basePath+"java\\com\\gcx\\feesys\\service\\impl", "ServiceImpl.java");
		}

	}

	//FreeMarker代码生成    table:表数据结构  	ftl:模板文件名 	  packageName:包名	fileEnd:文件后缀
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static void freeMarker(Table table, String ftl, String packageName, String fileEnd) throws Exception {
		// 第一步：把freemarker的jar包添加到工程中
		// 第二步：freemarker的运行不依赖web容器，可以在java工程中运行。创建一个测试方法进行测试。
		// 第三步：创建一个Configuration对象
		Configuration configuration = new Configuration(Configuration.getVersion());
		// 第四步：告诉config对象模板文件存放的路径。
		configuration.setDirectoryForTemplateLoading(new File(fileURL + "\\mygenerate\\ftl"));
		// 第五步：设置config的默认字符集。一般是utf-8
		configuration.setDefaultEncoding("utf-8");
		// 第六步：从config对象中获得模板对象。需要制定一个模板文件的名字。
		Template template = configuration.getTemplate(ftl);
		// 第七步：创建模板需要的数据集。可以是一个map对象也可以是一个pojo，把模板需要的数据都放入数据集。
		Map root = new HashMap<>();
		root.put("table", table);
		root.put("projectName", "feesys");
		root.put("time", getTime());
		root.put("author", author);

		// 第八步：创建一个Writer对象，指定生成的文件保存的路径及文件名。
		Writer out = new FileWriter(new File( packageName + "\\"+ table.getJavaName() + fileEnd));
		// 第九步：调用模板对象的process方法生成静态文件。需要两个参数数据集和writer对象。
		template.process(root, out);
		// 第十步：关闭writer对象。
		out.flush();
		out.close();
	}




	//获取表信息
	public static Table getTable(Connection conn){
		Table table = new Table();
		ArrayList<Column> columns = new ArrayList<Column>();
		//表名
		table.setTableName(tableName);
		//实体名
		table.setJavaName(upperCase(Underline2Camel.underline2Camel(tableName, true)));
		//实体名(首字母小写)
		table.setJavaName2(Underline2Camel.underline2Camel(tableName, true));

		String sql = "select * from " + tableName;
		String sqlco = "show full columns from " + tableName;
		Map<String, String> commentMap=new HashMap<>();
		Statement stmt1;
		PreparedStatement stmt;
		try {
			stmt1 = conn.createStatement();
			ResultSet rs1 = stmt1.executeQuery(sqlco);
	
			while (rs1.next()) { 
				
//				System.out.println(rs1.getString("Field") + "\t:\t"+  rs1.getString("Comment") );
				commentMap.put(rs1.getString("Field"), rs1.getString("Comment"));
			} 
			
			
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery(sql);
			ResultSetMetaData data = rs.getMetaData();
			for (int i = 1; i <= data.getColumnCount(); i++) {
				Column column = new Column();
				//字段名
				column.setColumnName(data.getColumnName(i));
				//java对象属性名
				column.setJavaName(Underline2Camel.underline2Camel(data.getColumnName(i), true));
				//java对象属性名(首字母大写)
				column.setJavaName2(upperCase(Underline2Camel.underline2Camel(data.getColumnName(i), true)));
				//字段类型
				column.setColumnType(IntToInteger(data.getColumnTypeName(i)));
				//java属性类型
				column.setJavaType(data.getColumnClassName(i));
				//类型简称
				column.setJavaTypeSimple(IntToInteger2(getSimple(data.getColumnClassName(i))));
				column.setComment(commentMap.get(column.getColumnName()));

				columns.add(column);
				//默认只有一个主键，第一个字段为主键
				if(i == 1)
					table.setColumn(column);
			}
			table.setColumns(columns);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return table;
	}




	//连接数据库
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://119.90.51.183:3306/" +databaseName+
					"?useUnicode=true&amp;characterEncoding=UTF-8&amp;allowMultiQueries=true";
			String user = "root";
			String pass = "isoftadmin";
			conn = DriverManager.getConnection(url, user, pass);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	//首字母转大写
	public static String upperCase(String str) {  
		char[] ch = str.toCharArray();  
		if (ch[0] >= 'a' && ch[0] <= 'z') {  
			ch[0] = (char) (ch[0] - 32);  
		}  
		return new String(ch);  
	}


	//获取当前时间
	private static String getTime(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		return df.format(new Date());// new Date()为获取当前系统时间
	}



	//根据java类型获取类型名称简称
	private static String getSimple(String str){
		String[] strs = str.split("\\.");
		return strs[strs.length-1];
	}


	//字段类型转换 "INT"转"INTEGER"
	private static String IntToInteger(String str){
		if("INT".equals(str))
			return "INTEGER";
		if("DATETIME".equals(str))
			return "TIMESTAMP";

		return str;
	}



	//对象属性类型转换 
	private static String IntToInteger2(String str){
		if("Timestamp".equals(str))
			return "Date";
		
		return str;
	}

}
