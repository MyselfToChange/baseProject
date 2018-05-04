package com.gcx.api.common.ftl;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;



/** 
 * @author	yang 
 * @version 创建时间：2017年3月22日 上午10:38:17 
 * 类说明:通过自定义模板生成Controller和Service层	
 */
public class FreeMarkerForJava {
	
	//项目名
	private static String workName = "myProject";
	//实体类名称 首字母大写
	private static String modelName="Flicker";
	//创建人
	private static String author="zhanglin";
	//API描述
	private static String apiDescription="群组";
	//选择采用的模板
	private static String controllerFtl = "Controller5.ftl";
	private static String serviceFtl = "Service5.ftl";
	private static String serviceImplFtl = "ServiceImpl5.ftl";

	//工作空间地址
    private static String URL = "E:\\gcx\\workspace_gcx\\" + workName + "\\src\\main\\java\\com\\gcx\\api";
	
	
	public static void main(String[] args) throws Exception {
		genCodeByCustomModelName(modelName);
	}
	
//    public static void genCode(String... tableNames) throws Exception {
//        for (String tableName : tableNames) {
//            genCodeByCustomModelName(tableName);
//        }
//    }
	//外部调用方法
	public static void genCodeByCustomModelName(String param) throws Exception {
		controllerFreeMarker(param);
		ServiceFreeMarker(param);
		ServiceImplFreeMarker(param);
	}
	
	//controller代码生成
	private static void controllerFreeMarker(String param1) throws Exception {
		// 第一步：把freemarker的jar包添加到工程中
		// 第二步：freemarker的运行不依赖web容器，可以在java工程中运行。创建一个测试方法进行测试。
		// 第三步：创建一个Configuration对象
		Configuration configuration = new Configuration(Configuration.getVersion());
		// 第四步：告诉config对象模板文件存放的路径。
		configuration.setDirectoryForTemplateLoading(new File(URL + "\\common\\ftl"));
		// 第五步：设置config的默认字符集。一般是utf-8
		configuration.setDefaultEncoding("utf-8");
		// 第六步：从config对象中获得模板对象。需要制定一个模板文件的名字。
		Template template = configuration.getTemplate(controllerFtl);
		// 第七步：创建模板需要的数据集。可以是一个map对象也可以是一个pojo，把模板需要的数据都放入数据集。
		Map<String,Object> root = new HashMap<String, Object>();
		root.put("param1", param1);
		root.put("param2", FirstToLower(param1));
		root.put("time", getTime());
		root.put("author",author);
		root.put("apiDescription",apiDescription);
		// 第八步：创建一个Writer对象，指定生成的文件保存的路径及文件名。
		Writer out = new FileWriter(new File(URL + "\\controller\\" + param1 + "Controller.java"));
		// 第九步：调用模板对象的process方法生成静态文件。需要两个参数数据集和writer对象。
		template.process(root, out);
		// 第十步：关闭writer对象。
		out.flush();
		out.close();
	}
	//service代码生成
	private static void ServiceFreeMarker(String param1) throws Exception {
		// 第一步：把freemarker的jar包添加到工程中
		// 第二步：freemarker的运行不依赖web容器，可以在java工程中运行。创建一个测试方法进行测试。
		// 第三步：创建一个Configuration对象
		Configuration configuration = new Configuration(Configuration.getVersion());
		// 第四步：告诉config对象模板文件存放的路径。
		configuration.setDirectoryForTemplateLoading(new File(URL + "\\common\\ftl"));
		// 第五步：设置config的默认字符集。一般是utf-8
		configuration.setDefaultEncoding("utf-8");
		// 第六步：从config对象中获得模板对象。需要制定一个模板文件的名字。
		Template template = configuration.getTemplate(serviceFtl);
		// 第七步：创建模板需要的数据集。可以是一个map对象也可以是一个pojo，把模板需要的数据都放入数据集。
		Map<String,Object> root = new HashMap<String, Object>();
		root.put("param1", param1);
		root.put("time", getTime());
		root.put("author", author);
		// 第八步：创建一个Writer对象，指定生成的文件保存的路径及文件名。
		Writer out = new FileWriter(new File(URL + "\\service\\" + param1 + "Service.java"));
		// 第九步：调用模板对象的process方法生成静态文件。需要两个参数数据集和writer对象。
		template.process(root, out);
		// 第十步：关闭writer对象。
		out.flush();
		out.close();
	}
	//serviceImpl代码生成
	private static void ServiceImplFreeMarker(String param1) throws Exception {
		// 第一步：把freemarker的jar包添加到工程中
		// 第二步：freemarker的运行不依赖web容器，可以在java工程中运行。创建一个测试方法进行测试。
		// 第三步：创建一个Configuration对象
		Configuration configuration = new Configuration(Configuration.getVersion());
		// 第四步：告诉config对象模板文件存放的路径。
		configuration.setDirectoryForTemplateLoading(new File(URL + "\\common\\ftl"));
		// 第五步：设置config的默认字符集。一般是utf-8
		configuration.setDefaultEncoding("utf-8");
		// 第六步：从config对象中获得模板对象。需要制定一个模板文件的名字。
		Template template = configuration.getTemplate(serviceImplFtl);
		// 第七步：创建模板需要的数据集。可以是一个map对象也可以是一个pojo，把模板需要的数据都放入数据集。
		Map<String,Object> root = new HashMap<String, Object>();
		root.put("param1", param1);
		root.put("param2", FirstToLower(param1));
		root.put("time", getTime());
		root.put("author", author);
		// 第八步：创建一个Writer对象，指定生成的文件保存的路径及文件名。
		Writer out = new FileWriter(new File(URL + "\\service\\impl\\" + param1 + "ServiceImpl.java"));
		// 第九步：调用模板对象的process方法生成静态文件。需要两个参数数据集和writer对象。
		template.process(root, out);
		// 第十步：关闭writer对象。
		out.flush();
		out.close();
	}
	//获取当前时间
	private static String getTime(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		return df.format(new Date());// new Date()为获取当前系统时间
	}
	//首字母转换小写
	private static String FirstToLower(String str) {
        char[] chars = new char[1];
        chars[0] = str.charAt(0);
        String temp = new String(chars);
        if (chars[0] >= 'A' && chars[0] <= 'Z') {//当为字母时，则转换为小写
            return str.replaceFirst(temp, temp.toLowerCase());
        }
        return str;
    }


}
