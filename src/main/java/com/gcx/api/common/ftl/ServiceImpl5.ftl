package com.gcx.api.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gcx.api.common.util.MyResult;
import com.gcx.api.dao.${param1}Mapper;
import com.gcx.api.model.${param1};
import com.gcx.api.service.${param1}Service;

/**
 * @author ${author}
 * @version 创建时间：${time}
 */

@Service
public class ${param1}ServiceImpl implements ${param1}Service {

	@Autowired
	private ${param1}Mapper ${param2}Dao;

	// 查询所有记录 
	public MyResult findAllRecords(${param1} record) throws Exception{
			List<${param1}> list = ${param2}Dao.findAllList(record);
			int count = (int) ${param2}Dao.findAllListCount(record);
			return MyResult.ok(count, list);
	}
	// 添加记录
	@Transactional(rollbackFor={Exception.class})
	public MyResult addRecord(${param1} record) throws Exception{
				// UUID主键
				String uuid = UUID.randomUUID().toString();
				record.setTid(uuid);
				int i = ${param2}Dao.insertSelective(record);
				return MyResult.ok(i, "新增");		
	}

	// 批量删除
	@Transactional(rollbackFor={Exception.class})
	public MyResult delRecords(String[] tidsArray) throws Exception{
				int i = ${param2}Dao.delRecords(tidsArray);
				return MyResult.ok(i, "删除");
	}

	// 修改记录
	@Transactional(rollbackFor={Exception.class})
	public MyResult updateRecord(${param1} record) throws Exception{
				int i = ${param2}Dao.updateByPrimaryKeySelective(record);
				return MyResult.ok(i, "修改");
	}
	// 根据ID查看详情
	public MyResult findById(String tid) throws Exception{
				${param1} record = ${param2}Dao.selectByPrimaryKey(tid);
				return MyResult.ok(record);
	}
	
}
