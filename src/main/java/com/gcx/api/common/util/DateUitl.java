package com.gcx.api.common.util;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUitl {

    /**
     * @return 当天24点时间
     */
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

    /**
     * 获取某一时间一天之后的时间
     * @param date 某一时间
     * @return 一天之后的时间
     */
    public static String getDayLater(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE,1);
        return  new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(calendar.getTime());
    }

    /**
     * @param date 需要判断的时间
     * @return 判断某个是在是否大于小于等于当前时间
     */
    public static boolean compare_date(Date date) {
        boolean flag=false;
        if (date.getTime() > new Date().getTime()) {
            flag = false;
        } else if (date.getTime() <= new Date().getTime()) {
            flag= true;
        }
        return  flag;
    }

    /**
     * 得到某一时间的时间戳
     * @param date 需要格式化的时间
     * @return time 返回指定时间的纳秒时间
     */
    public static long foramtDate(Date date){
        return date.getTime();
    }



    public static void main(String[] args) {
        long l = DateUitl.foramtDate(new Date());
        long time = new Date().getTime();
        long s=System.nanoTime();
        System.out.println("时间》》》"+l);
        System.out.println("时间》》》"+s);
        System.out.println("时间》》》"+time);
    }
}
