package com.gcx.api.controller;

import javax.servlet.http.HttpServletRequest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gcx.api.common.base.BaseController;
import com.gcx.api.common.validator.ValidatorUtils;
import com.gcx.api.common.validator.groups.MajorKey;
import com.gcx.api.common.util.MyResult;
import com.gcx.api.model.${param1};
import com.gcx.api.service.${param1}Service;

/**
 * @author ${author}
 * @version 创建时间：${time}
 */

@RestController
@Api(value="${param2}s",description="${apiDescription}")
@RequestMapping("/${param2}s")
class ${param1}Controller extends BaseController {


	@Autowired
	${param1}Service  ${param2}Service;

	// 查询所有记录 分页默认每页为10条记录，查询全部pageSize传-1
	@GetMapping
	@ApiOperation(value = "列表")
	MyResult  list(${param1} record) throws Exception{
		MyResult result=${param2}Service.findAllRecords(record);	
		return result;
	}
	
	// 添加记录
	@PostMapping
	@ApiOperation(value = "新增")
	MyResult add(@RequestBody ${param1} record,HttpServletRequest request) throws Exception{	
	    record.setCreateTime(getNowTime());
		record.setCreateUser(getUserId(request));	
		MyResult result = ${param2}Service.addRecord(record);				
		return result;
	}

	// 删除记录 id
	@DeleteMapping("/{tids}")
	@ApiOperation(value = "删除")
	MyResult delete(@PathVariable("tids")String tids) throws Exception{
	    ValidatorUtils.isBlank(tids, "参数不能为空或null");
	    String[] tidsArray=tids.split(",");
		MyResult result = ${param2}Service.delRecords(tidsArray);
		return result;
	}

	// 修改记录 id 要修改的属性
	@PutMapping
	@ApiOperation(value = "修改")
	MyResult update(@RequestBody ${param1} record,HttpServletRequest request) throws Exception{
	    ValidatorUtils.validateEntity(record, MajorKey.class);
		MyResult result = ${param2}Service.updateRecord(record);
		return result;
	}

	// 根据ID查询详情 id
	@GetMapping("/{tid}")
	@ApiOperation(value = "查询详情")
	MyResult get(@PathVariable("tid")String tid) throws Exception{
	    ValidatorUtils.isBlank(tid, "参数不能为空或null");
		MyResult result = ${param2}Service.findById(tid);
		return result;
	}

}
