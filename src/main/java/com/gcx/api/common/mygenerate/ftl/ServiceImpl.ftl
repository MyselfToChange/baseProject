package com.gcx.${projectName}.service.impl;
import java.util.List;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.gcx.${projectName}.common.util.MyResult;
import com.gcx.${projectName}.common.model.User;
import com.gcx.${projectName}.dao.${table.javaName}Mapper;
import com.gcx.${projectName}.model.${table.javaName};
import com.gcx.${projectName}.common.base.CommonImpl;
import com.gcx.${projectName}.service.${table.javaName}Service;
import com.gcx.feesys.common.util.UUIDUtils;



/** 
 * @author	${author} 
 * @version 创建时间：${time} 
 * 类说明:	
 */
@Service
public class ${table.javaName}ServiceImpl extends CommonImpl implements ${table.javaName}Service {

    private final Logger log = LoggerFactory.getLogger(${table.javaName}ServiceImpl.class);

	@Autowired
	${table.javaName}Mapper ${table.javaName2}Dao;

	@Override
	public MyResult<Object> findRecords(HttpServletRequest request, HttpServletResponse response, 
			                ${table.javaName} record, int pagenow, int pagesize) {

		int limitStart = (pagenow-1)*pagesize;
		int limitEnd = pagesize;

		try {
			String userId = getUserId(request);
			record.setUserIdAdd(userId);
			List<${table.javaName}> list = ${table.javaName2}Dao.findByRecord(record, limitStart, limitEnd);
			int count = ${table.javaName2}Dao.findByRecordCount(record);

			return MyResult.ok(count, list);
		} catch (Exception e) {
			e.printStackTrace();
			return MyResult.error("查询失败");	
		}
	}

	@Override
	public MyResult<Object> addRecord(HttpServletRequest request, HttpServletResponse response,${table.javaName} record){
		try {
			User user = getUser(request);
			record.setUserIdAdd(user.getUserId()+"");
			record.setUserName(user.getUserName());
			record.setId(UUIDUtils.getUUID());
			record.setTimeAdd(new Date());
			record.setRealName(user.getRealName());
			${table.javaName2}Dao.insertSelective(record);
			return MyResult.ok("新增成功");
		} catch (Exception e) {
			e.printStackTrace();
			return MyResult.error("新增失败");	
		}
	}

	@Override
	public MyResult<Object> delRecord(HttpServletRequest request, HttpServletResponse response,${table.javaName} record){
		try {
			${table.javaName2}Dao.deleteLogicById(1, record.get${table.column.javaName2}());//1表示逻辑删除
			return MyResult.ok("删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			return MyResult.error("删除失败");	
		}
	}

	@Override
	public MyResult<Object> updateRecord(HttpServletRequest request, HttpServletResponse response,${table.javaName} record) {
		try {
			if(record.get${table.column.javaName2}() == null){
                return MyResult.error("ID不能为空");
            }
			${table.javaName2}Dao.updateByPrimaryKeySelective(record);
			return MyResult.ok("更新成功");

		} catch (Exception e) {
			e.printStackTrace();
			return MyResult.error("更新失败");	
		}
	}

	@Override
	public MyResult<Object> auditfind(HttpServletRequest request, HttpServletResponse response,
                            ${table.javaName} record, int pagenow, int pagesize) {
		int limitStart = (pagenow-1)*pagesize;
		int limitEnd = pagesize;
		try {
			List<${table.javaName}> list = ${table.javaName2}Dao.findByRecord(record, limitStart, limitEnd);
			int count = ${table.javaName2}Dao.findByRecordCount(record);
			return MyResult.ok(count, list);
		} catch (Exception e) {
			e.printStackTrace();
			return MyResult.error("查询失败");	
		}
	}
	
	@Override
	public MyResult<Object> auditRecord(HttpServletRequest request, HttpServletResponse response,${table.javaName} record) {
		try {
			User user = getUser(request);
			if(record.get${table.column.javaName2}() == null){
                return MyResult.error("ID不能为空");
            }
			record.setTimeAudit(new Date());
			record.setUserAudit(user.getUserName());
			${table.javaName2}Dao.updateByPrimaryKeySelective(record);
			return MyResult.ok("更新成功");

		} catch (Exception e) {
			e.printStackTrace();
			return MyResult.error("更新失败");	
		}
	}

	@Override
	public MyResult<Object> findById(HttpServletRequest request, HttpServletResponse response,String ${table.column.javaName}){
		try {
			${table.javaName} record = ${table.javaName2}Dao.selectByPrimaryKey(${table.column.javaName});
			return MyResult.ok(record);
		} catch (Exception e) {
			e.printStackTrace();
			return MyResult.error("查询失败");	
		}
	}
	
	@Override
	public MyResult<Object> showList(HttpServletRequest request, HttpServletResponse response,
                            ${table.javaName} record, int pagenow, int pagesize) {
		int limitStart = (pagenow-1)*pagesize;
		int limitEnd = pagesize;
		try {
			List<${table.javaName}> list = ${table.javaName2}Dao.findByRecord(record, limitStart, limitEnd);
			int count = ${table.javaName2}Dao.findByRecordCount(record);
			return MyResult.ok(count, list);
		} catch (Exception e) {
			e.printStackTrace();
			return MyResult.error("查询失败");	
		}
	}
	
	@Override
	public MyResult<Object> sumbitAudit(HttpServletRequest request, HttpServletResponse response,${table.javaName} record) {
		try {
			if(record.getId() == null){
                return MyResult.error("ID不能为空");
            }
			record.setTimeApp(new Date());
			${table.javaName2}Dao.updateByPrimaryKeySelective(record);
			return MyResult.ok("更新成功");
		} catch (Exception e) {
			e.printStackTrace();
			return MyResult.error("更新失败");	
		}
	}

    @Override
    public MyResult<Object> batchUpdateRecord(HttpServletRequest request, HttpServletResponse response, String[] ids,String status) {

        if(ids.length==0 || ids==null|| StringUtils.isBlank(status)){
            return MyResult.error("ids不能为空");
        }
        int resultCount=${table.javaName2}Dao.batchUpdateStatus(ids,status);
        return MyResult.ok(resultCount);
        }

    @Override
    public MyResult<Object> batchDelectRecord(HttpServletRequest request, HttpServletResponse response, String[] ids) {
        if(ids.length==0 || ids==null){
            return MyResult.error("ids不能为空");
        }
        int resultCount=${table.javaName2}Dao.batchDelectRecord(ids);
        return MyResult.ok(resultCount);
        }
}
