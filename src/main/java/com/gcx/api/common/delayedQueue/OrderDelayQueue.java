package com.gcx.api.common.delayedQueue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Iterator;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.ExecutorService;

/**
 * @ClassName OrderDelayQueue
 * @Description 延时队列实现订单的超时取消
 * @Author qimy
 * @Date 2018/6/26 13:52
 **/
@Component
@Lazy(false)
public class OrderDelayQueue{

    @Autowired
    private ExecutorService executorService;//线程池

    private static DelayQueue<OrderMessage> queue =  new DelayQueue<>();//新建一个延时队列队列

    @PostConstruct
    public void init() throws Exception {//初始化的方法，初始化加载数据到队列

    }

    /**
     * 放入数据到延时队列
     * @param orderMessage 延时队列Model
     * @return 是否成功放入
     */
    public boolean addToOrderDelayQueue(OrderMessage orderMessage){
        return queue.add(orderMessage);
    }

    /**
     * 移除队列中的数据
     * @param orderMessage 延时队列对象
     */
    public void removeToOrderDelayQueue(OrderMessage orderMessage){
        if(orderMessage==null){
            return;
        }
        for (Iterator<OrderMessage> iterator = queue.iterator(); iterator.hasNext();) {
            OrderMessage order =iterator.next();
            if(orderMessage.getId().equals(order.getId())){
                queue.remove(order);
            }
        }
    }

    /**
     * 线程池，监控延时队列中的数据，超时的数据poll出队列
     */
    public void orderChecker(){
        executorService.execute(() -> {
            while (true){
                try {
                    OrderMessage take = queue.take();//取出数据
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
    }
}
