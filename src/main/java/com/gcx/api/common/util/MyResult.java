package com.gcx.api.common.util;

import java.io.Serializable;

public class MyResult  implements Serializable{

	    public MyResult()
	    {
	    }

	    public static MyResult ok()
	    {
	        MyResult result = new MyResult();
	        result.setStatus(0);
	        result.setMsg("success");
	        result.setData(null);
	        return result;
	    }

	    public static MyResult ok(Object object)
	    {
	        MyResult result = new MyResult();
	        result.setStatus(0);
	        result.setMsg("success");
	        result.setData(object);
	        return result;
	    }

	    public static MyResult ok(int count, Object object)
	    {
	        MyResult result = new MyResult();
	        result.setStatus(0);
	        result.setMsg("success");
	        result.setData(object);
	        result.setCount(count);
	        return result;
	    }

	    public static MyResult error(String msg)
	    {
	        MyResult result = new MyResult();
	        result.setStatus(500);
	        result.setMsg(msg);
	        result.setData(null);
	        return result;
	    }

	    public static MyResult error(Object object)
	    {
	        MyResult result = new MyResult();
	        result.setStatus(500);
	        result.setMsg("fail");
	        result.setData(object);
	        return result;
	    }
	    
		public static MyResult ok(int i,String msg){
			 MyResult result = new MyResult();
			 if (i > 0) {
					result.setStatus(0);
					result.setMsg(msg+"成功");
					result.setData(null);
				} else {
					result.setStatus(1);
					result.setMsg(msg+"失败");
					result.setData(null);
				}
			 return result;
		}
		
		public static MyResult exception(Object data,String msg,int status){
			 MyResult result = new MyResult();
			  result.setStatus(status);
		      result.setMsg(msg);
		      result.setData(data);
			 return result;
		}

	    public int getStatus()
	    {
	        return status;
	    }

	    public void setStatus(int status)
	    {
	        this.status = status;
	    }

	    public String getMsg()
	    {
	        return msg;
	    }

	    public void setMsg(String msg)
	    {
	        this.msg = msg;
	    }

	    public Object getData()
	    {
	        return data;
	    }

	    public void setData(Object data)
	    {
	        this.data = data;
	    }

	    public int getCount()
	    {
	        return count;
	    }

	    public void setCount(int count)
	    {
	        this.count = count;
	    }
	    

	    public String toString()
	    {
	        return (new StringBuilder("MyResult [status=")).append(status).append(", msg=").append(msg).append(", data=").append(data).append(", count=").append(count).append("]").toString();
	    }

	    private static final long serialVersionUID = 1L;
	    private int status; //状态码
	    private String msg; //异常信息
	    private Object data;
	    private int count;

}
