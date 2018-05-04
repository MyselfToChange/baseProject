package com.gcx.api.common.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

/**
 *<p>Title:Page</p>
 *<p>Description:分页类</p>
 *<p>Company:gcx</p>
 *<p>Author:zhanglin</p>
 *<p>Date:2017年9月28日</p>
 */
public class Page {
	
	protected int pageNo = 1; // 当前页码
	protected int pageSize =10; // 页面大小，设置为“-1”表示不进行分页（分页无效）0
	protected int firstResult=0; //每页的起始索引    	
	public Page(){
		
	}	
	/**
	 * 构造方法
	 * @param request 传递 repage 参数，来记住页码
	 */
	public Page(HttpServletRequest request){
		String no = request.getParameter("pageNo");
		String size = request.getParameter("pageSize");		
		if (StringUtils.isNumeric(no))
			this.setPageNo(Integer.parseInt(no));
		if (StringUtils.isNumeric(size))
			this.setPageSize(Integer.parseInt(size));
	}
	
	/**
	 * @return the firstResult
	 */
	public int getFirstResult() {
		int firstResult = (getPageNo() - 1) * getPageSize();
		return firstResult;
	}
	
	/**
	 * @param firstResult the firstResult to set
	 */
	public void setFirstResult(int firstResult) {
		this.firstResult = firstResult;
	}
	/**
	 * @return the pageNo
	 */
	public int getPageNo() {
		return pageNo;
	}
	/**
	 * @param pageNo the pageNo to set
	 */
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	/**
	 * @return the pageSize
	 */
	public int getPageSize() {
		return pageSize;
	}
	/**
	 * @param pageSize the pageSize to set
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	
}
