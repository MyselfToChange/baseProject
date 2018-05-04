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
import com.gcx.api.model.Flicker;
import com.gcx.api.service.FlickerService;

/**
 * @author zhanglin
 * @version 创建时间：2018-03-27 11:16:17
 */

@RestController
@Api(value="flickers",description="表扬激励")
@RequestMapping("/flickers")
class FlickerController extends BaseController {


	@Autowired
	FlickerService  flickerService;

	// 查询所有记录 分页默认每页为10条记录，查询全部pageSize传-1
	@GetMapping
	@ApiOperation(value = "列表")
	MyResult  list(Flicker record) throws Exception{
		MyResult result=flickerService.findAllRecords(record);	
		return result;
	}
	
	// 添加记录
	@PostMapping
	@ApiOperation(value = "新增")
	MyResult add(@RequestBody Flicker record,HttpServletRequest request) throws Exception{	
	    record.setCreateTime(getNowTime());
		record.setCreateUser(getUserId(request));	
		MyResult result = flickerService.addRecord(record);				
		return result;
	}

	// 删除记录 id
	@DeleteMapping("/{tids}")
	@ApiOperation(value = "删除")
	MyResult delete(@PathVariable("tids")String tids) throws Exception{
	    ValidatorUtils.isBlank(tids, "参数不能为空或null");
	    String[] tidsArray=tids.split(",");
		MyResult result = flickerService.delRecords(tidsArray);
		return result;
	}

	// 修改记录 id 要修改的属性
	@PutMapping
	@ApiOperation(value = "修改")
	MyResult update(@RequestBody Flicker record,HttpServletRequest request) throws Exception{
	    ValidatorUtils.validateEntity(record, MajorKey.class);
		MyResult result = flickerService.updateRecord(record);
		return result;
	}

	// 根据ID查询详情 id
	@GetMapping("/{tid}")
	@ApiOperation(value = "查询详情")
	MyResult get(@PathVariable("tid")String tid) throws Exception{
	    ValidatorUtils.isBlank(tid, "参数不能为空或null");
		MyResult result = flickerService.findById(tid);
		return result;
	}

}
