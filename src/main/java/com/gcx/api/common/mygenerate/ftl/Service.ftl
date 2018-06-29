package com.gcx.${projectName}.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gcx.${projectName}.common.util.MyResult;
import com.gcx.${projectName}.model.${table.javaName};

/** 
 * @author	${author} 	
 * @version 创建时间：${time}  
 */
public interface ${table.javaName}Service {

	MyResult<Object> findRecords(HttpServletRequest request, HttpServletResponse response, ${table.javaName} record, int pagenow, int pagesize);
	
	MyResult<Object> addRecord(HttpServletRequest request, HttpServletResponse response, ${table.javaName} record);
	
	MyResult<Object> delRecord(HttpServletRequest request, HttpServletResponse response, ${table.javaName} record);
	
	MyResult<Object> updateRecord(HttpServletRequest request, HttpServletResponse response, ${table.javaName} record);

	MyResult<Object> auditfind(HttpServletRequest request, HttpServletResponse response, ${table.javaName} record, int pagenow, int pagesize);
	
	MyResult<Object> auditRecord(HttpServletRequest request, HttpServletResponse response, ${table.javaName} record);
	
	MyResult<Object> findById(HttpServletRequest request, HttpServletResponse response, String ${table.column.javaName});
	
	MyResult<Object> showList(HttpServletRequest request, HttpServletResponse response, ${table.javaName} record, int pagenow, int pagesize);
	
	MyResult<Object> sumbitAudit(HttpServletRequest request, HttpServletResponse response, ${table.javaName} record);

    MyResult<Object> batchUpdateRecord(HttpServletRequest request, HttpServletResponse response, String[] ids,String status);

    MyResult<Object> batchDelectRecord(HttpServletRequest request, HttpServletResponse response, String[] ids);
}
