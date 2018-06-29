package com.gcx.${projectName}.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import com.gcx.${projectName}.common.util.MyResult;
import com.gcx.${projectName}.model.${table.javaName};
import com.gcx.${projectName}.service.${table.javaName}Service;

/** 
 * @author ${author}
 * @version 创建时间：${time} 
 */
@Controller
@Api(value="",description="")
@RequestMapping("${table.javaName2}Controller")
public class ${table.javaName}Controller {

	@Autowired
	${table.javaName}Service ${table.javaName2}Service;


    @ApiOperation(value = "查询")
	@GetMapping("findRecords")
	@ResponseBody
	public MyResult<Object> findRecords(HttpServletRequest request,HttpServletResponse response, ${table.javaName} record,
			@RequestParam(value="pagenow", defaultValue="1") int pagenow, 
			@RequestParam(value="pagesize", defaultValue="10") int pagesize){
		MyResult<Object> result = ${table.javaName2}Service.findRecords(request,response, record, pagenow, pagesize);
		return result;
	}

    @ApiOperation(value = "新增")
	@PostMapping("addRecord")
	@ResponseBody
	public MyResult<Object> addRecord(HttpServletRequest request,HttpServletResponse response,${table.javaName} record){
		MyResult<Object> result = ${table.javaName2}Service.addRecord(request, response, record);
		return result;
	}

    @ApiOperation(value = "删除")
	@GetMapping("delRecord")
	@ResponseBody
	public MyResult<Object> delRecord(HttpServletRequest request,HttpServletResponse response,${table.javaName} record){
		MyResult<Object> result = ${table.javaName2}Service.delRecord(request, response, record);
		return result;
	}

    @ApiOperation(value = "修改")
	@PostMapping("updateRecord")
	@ResponseBody
	public MyResult<Object> updateRecord(HttpServletRequest request,HttpServletResponse response,${table.javaName} record){
		MyResult<Object> result = ${table.javaName2}Service.updateRecord(request, response, record);
		return result;
	}



    @ApiOperation(value = "审核查询")
	@GetMapping("auditfind")
	@ResponseBody
	public MyResult<Object> auditfind(HttpServletRequest request,HttpServletResponse response, ${table.javaName} record,
			@RequestParam(value="pagenow", defaultValue="1") int pagenow, 
			@RequestParam(value="pagesize", defaultValue="10") int pagesize){
		MyResult<Object> result = ${table.javaName2}Service.auditfind(request,response, record, pagenow, pagesize);
		return result;
	}

    @ApiOperation(value = "审核")
	@GetMapping("auditRecord")
	@ResponseBody
	public MyResult<Object> auditRecord(HttpServletRequest request,HttpServletResponse response,${table.javaName} record){
		MyResult<Object> result = ${table.javaName2}Service.auditRecord(request, response, record);
		return result;
	}

    @ApiOperation(value = "根据ID查询")
	@GetMapping("findById")
	@ResponseBody
	public MyResult<Object> showDetailById(HttpServletRequest request,HttpServletResponse response,String ${table.column.javaName}){
		MyResult<Object> result = ${table.javaName2}Service.findById(request, response, ${table.column.javaName});
		return result;
	}

    @ApiOperation(value = "前台展示")
	@GetMapping("showList")
	@ResponseBody
	public MyResult<Object> showList(HttpServletRequest request,HttpServletResponse response, ${table.javaName} record,
			@RequestParam(value="pagenow", defaultValue="1") int pagenow, 
			@RequestParam(value="pagesize", defaultValue="10") int pagesize){
		MyResult<Object> result = ${table.javaName2}Service.showList(request,response, record, pagenow, pagesize);
		return result;
	}


    @ApiOperation(value = "提交审核")
	@GetMapping("sumbitAudit")
	@ResponseBody
	public MyResult<Object> auditSumbit(HttpServletRequest request,HttpServletResponse response,${table.javaName} record){
		MyResult<Object> result = ${table.javaName2}Service.sumbitAudit(request, response, record);
		return result;
	}


    @ApiOperation(value = "批量更新")
    @PostMapping("auditBatchUpdateRecord")
    @ResponseBody
    public MyResult<Object> batchUpdateRecord(HttpServletRequest request,HttpServletResponse response,String[] ids,String status){
        MyResult<Object> result = ${table.javaName2}Service.batchUpdateRecord(request, response,ids,status);
            return result;
    }

    @ApiOperation(value = "批量删除")
    @PostMapping("auditbatchDelectRecord")
    @ResponseBody
    public MyResult<Object> batchDelectRecord(HttpServletRequest request,HttpServletResponse response,String[] ids){
        MyResult<Object> result = ${table.javaName2}Service.batchDelectRecord(request, response,ids);
            return result;
    }








}
