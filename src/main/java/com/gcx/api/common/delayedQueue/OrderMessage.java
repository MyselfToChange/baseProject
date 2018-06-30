package com.gcx.api.common.delayedQueue;

import com.gcx.api.common.util.DateUitl;

import java.util.Date;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName OrderMessage
 * @Description 订单队列对象
 * @Author qimy
 * @Date 2018/6/26 13:58
 **/
public class OrderMessage implements Delayed {
    private final static long DELAY =1*60*1000L;//默认延迟半个小时分钟(毫微秒)

    private  String id;//订单ID(主键，无实际意义)
    private  String orderName;//订单姓名
    private  String orderNum;//订单编号
    private  long expire;//到期时间
    private  long saveTime;//开始时间
    private  long now; //当前时间



    public OrderMessage(String id, String orderName, String orderNum) {//构造方法
        this.id = id;
        this.orderName = orderName;
        this.orderNum = orderNum;
        this.saveTime = DELAY;
        this.now=System.currentTimeMillis();
        this.expire=this.now+DELAY;
    }

    public OrderMessage(String id, String orderName, String orderNum, Date insertDate) {//构造方法
        this.id = id;
        this.orderName = orderName;
        this.orderNum = orderNum;
        this.saveTime =DELAY;
        this.now=DateUitl.foramtDate(insertDate);
        this.expire=this.now+this.saveTime;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(this.expire-System.currentTimeMillis(),TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        return (int) (this.getDelay(TimeUnit.MILLISECONDS) -o.getDelay(TimeUnit.MILLISECONDS));
    }

    @Override
    public String toString() {
        return "OrderMessage{" +
                "id='" + id + '\'' +
                ", orderName='" + orderName + '\'' +
                ", orderNum='" + orderNum + '\'' +
                ", expire=" + expire +
                ", saveTime=" + saveTime +
                ", now=" + now +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public long getExpire() {
        return expire;
    }

    public void setExpire(long expire) {
        this.expire = expire;
    }

    public long getSaveTime() {
        return saveTime;
    }

    public void setSaveTime(long saveTime) {
        this.saveTime = saveTime;
    }

    public long getNow() {
        return now;
    }

    public void setNow(long now) {
        this.now = now;
    }


}
