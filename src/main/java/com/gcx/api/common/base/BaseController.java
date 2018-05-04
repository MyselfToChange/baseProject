package com.gcx.api.common.base;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;



import com.gcx.api.common.model.User;
import com.gcx.api.common.util.UserUtils;
/**
 *<p>Title:BaseController</p>
 *<p>Description:公共Controller</p>
 *<p>Company:gcx</p>
 *<p>Author:zhanglin</p>
 *<p>Date:2017年6月20日</p>
 */
public class BaseController {
	
    /**
     * 获取当前用户ID
     * @return
     */
	protected static String getUserId(HttpServletRequest request){
		String userId="";
		User user = UserUtils.getUser(request);
		if(user!=null)
		userId=user.getUserId().toString()==null?"":user.getUserId().toString(); //员工ID
		return userId;
	}
	/**
	 * 获取用户名字
	 * @param request
	 * @param response
	 * @return
	 */
	protected static String getUserName(HttpServletRequest request){
		String userName="";
		User user = UserUtils.getUser(request);
		if(user!=null)
		  userName=user.getUserName()==null?"":user.getUserName(); //员工名字
		return userName;
	}
	
	/**
	 * 获取当前时间 返回String类型
	 * @return
	 */
	protected static String getNowTime(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String date=df.format(new Date());
		return date;
	}
	 /**
	  * 获取当前时间 返回Date类型
	  * @return
	  * @throws ParseException 
	  */
	protected static Date getDate(){
		 Date now = new Date();
		 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//可以方便地修改日期格式 
		 String s = dateFormat.format(now); 
		 Date data;
		try {
			data = dateFormat.parse(s);
			return data;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	 }
	
}
