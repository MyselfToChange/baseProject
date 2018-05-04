package com.gcx.api.common.base;

import com.gcx.api.common.util.MyResult;
/**
 *<p>Title:BaseService</p>
 *<p>Description:公共Service</p>
 *<p>Company:gcx</p>
 *<p>Author:zhanglin</p>
 *<p>Date:2017年9月28日</p>
 */
public interface BaseService<T> {
	MyResult findAllRecords(T record) throws Exception;

	MyResult addRecord(T record) throws Exception;

	MyResult delRecords(String[] tidsArray) throws Exception;

	MyResult updateRecord(T record) throws Exception;

	MyResult findById(String tid) throws Exception;
	
}
