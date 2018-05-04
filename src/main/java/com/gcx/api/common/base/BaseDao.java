package com.gcx.api.common.base;

import java.util.List;

import org.apache.ibatis.annotations.Param;
/**
 *<p>Title:BaseDao</p>
 *<p>Description:公共Dao</p>
 *<p>Company:gcx</p>
 *<p>Author:zhanglin</p>
 *<p>Date:2017年9月28日</p>
 */
public interface BaseDao<T> {
	    int deleteByPrimaryKey(String tid);

	    int insert(T record);

	    int insertSelective(T record);

	    T selectByPrimaryKey(String tid);

	    int updateByPrimaryKeySelective(T record);

	    int updateByPrimaryKey(T record);
	    
		long findAllListCount(T record);

		List<T> findAllList(T record);
	    
		int delRecords(@Param("tidsArray")String[] tidsArray);
}
