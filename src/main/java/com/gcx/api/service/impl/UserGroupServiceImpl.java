package com.gcx.api.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gcx.api.common.note.ModelDescription;
import com.gcx.api.common.util.MyResult;
import com.gcx.api.dao.UserGroupMapper;
import com.gcx.api.model.UserGroup;
import com.gcx.api.service.UserGroupService;

/**
 * @author zhanglin
 * @version 创建时间：2018-03-21 14:03:56
 */

@Service
@ModelDescription(value="群组",key="userGroup")
public class UserGroupServiceImpl implements UserGroupService {

	@Autowired
	private UserGroupMapper userGroupDao;

	// 查询所有记录 
	public MyResult findAllRecords(UserGroup record) throws Exception{
			List<UserGroup> list = userGroupDao.findAllList(record);
			int count = (int) userGroupDao.findAllListCount(record);
			return MyResult.ok(count, list);
	}
	// 添加记录
	@Transactional(rollbackFor={Exception.class})
	public MyResult addRecord(UserGroup record) throws Exception{
				// UUID主键
				String uuid = UUID.randomUUID().toString();
				record.setTid(uuid);
				int i = userGroupDao.insertSelective(record);
				return MyResult.ok(i, "新增");		
	}

	// 批量删除
	@Transactional(rollbackFor={Exception.class})
	public MyResult delRecords(String[] tidsArray) throws Exception{
				int i = userGroupDao.delRecords(tidsArray);
				return MyResult.ok(i, "删除");
	}

	// 修改记录
	@Transactional(rollbackFor={Exception.class})
	public MyResult updateRecord(UserGroup record) throws Exception{
				int i = userGroupDao.updateByPrimaryKeySelective(record);
				return MyResult.ok(i, "修改");
	}
	// 根据ID查看详情
	public MyResult findById(String tid) throws Exception{
				UserGroup record = userGroupDao.selectByPrimaryKey(tid);
				return MyResult.ok(record);
	}
	
}
