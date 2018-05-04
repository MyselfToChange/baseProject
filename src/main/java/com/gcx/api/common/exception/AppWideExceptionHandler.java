package com.gcx.api.common.exception;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.gcx.api.common.util.MyResult;
/**
 *<p>Title:AppWideExceptionHandler</p>
 *<p>Description:控制层异常统一处理类</p>
 *<p>Company:gcx</p>
 *<p>Author:zhanglin</p>
 *<p>Date:2017年10月10日</p>
 */
@RestControllerAdvice
public class AppWideExceptionHandler {
	
	
	@ExceptionHandler(Exception.class)
	public MyResult exception(HttpServletRequest request,HttpServletResponse response, Exception ex){
		String data="异常名:"+ex.getClass()+"||方法名:"+ex.getStackTrace()[0].getMethodName()+"||类名:"+ex.getStackTrace()[0].getClassName()+"||行数:"+ex.getStackTrace()[0].getLineNumber();
		return MyResult.exception(data,ex.getMessage(),500);
	}
	@ExceptionHandler(ParameterException.class)
	public MyResult parameterException(ParameterException ex){
		return MyResult.exception(ex.getMessage(),"参数异常", ex.getStatus());
	}
}
