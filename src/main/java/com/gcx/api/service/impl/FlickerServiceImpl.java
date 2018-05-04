package com.gcx.api.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcx.api.common.dataSource.CustomDataSource;
import com.gcx.api.common.dataSource.DataSourceName;
import com.gcx.api.common.note.ModelDescription;
import com.gcx.api.common.util.MyResult;
import com.gcx.api.dao.FlickerMapper;
import com.gcx.api.model.Flicker;
import com.gcx.api.service.FlickerService;

/**
 * @author zhanglin
 * @version 创建时间：2018-03-27 11:16:17
 */

@Service
@CustomDataSource(value = DataSourceName.DATA_ZW)
@ModelDescription(value="表扬激励",key="flicker")
public class FlickerServiceImpl implements FlickerService {

	@Autowired
	private FlickerMapper flickerDao;

	// 查询所有记录 
	public MyResult findAllRecords(Flicker record) {
			List<Flicker> list = flickerDao.findAllList(record);
			int count = (int) flickerDao.findAllListCount(record);
			return MyResult.ok(count, list);
	}
	// 添加记录
	public MyResult addRecord(Flicker record) {
				// UUID主键
				String uuid = UUID.randomUUID().toString();
				record.setTid(uuid);
				int i = flickerDao.insertSelective(record);
				return MyResult.ok(i, "新增");		
	}

	// 批量删除
	public MyResult delRecords(String[] tidsArray) {
				int i = flickerDao.delRecords(tidsArray);
				return MyResult.ok(i, "删除");
	}

	// 修改记录
	public MyResult updateRecord(Flicker record) {
				int i = flickerDao.updateByPrimaryKeySelective(record);
				return MyResult.ok(i, "修改");
	}
	// 根据ID查看详情
	public MyResult findById(String tid) {
				Flicker record = flickerDao.selectByPrimaryKey(tid);
				return MyResult.ok(record);
	}
	
}
