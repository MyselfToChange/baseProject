package com.gcx.api.common.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUitl {

	   //获得当天24点时间
	   public static Date getTimesnight(){
	   Calendar cal = Calendar.getInstance();
	   cal.set(Calendar.HOUR_OF_DAY, 24);
	   cal.set(Calendar.SECOND, 0);
	   cal.set(Calendar.MINUTE, 0);
	   cal.set(Calendar.MILLISECOND, 0);
	   long ltime2 = (cal.getTimeInMillis()/1000);  
       Date lDate = new Date(ltime2*1000);  
	   return lDate ;
	   } 
	   
	   public static void main(String[] args) {
		   System.out.println(getTimesnight()); 
		    SimpleDateFormat lsdFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
	        System.out.println(lsdFormat.format(getTimesnight()));  
	   }
}
