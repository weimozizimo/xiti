package lock;

import java.util.concurrent.atomic.AtomicInteger;

/**
*@description 自旋锁的一种变种，解决公平性的问题，类似于银行排号
*@author weiyifei
*@date 2020/10/29
*/
public class TikectLock {
    //服务号
    private AtomicInteger serviceNum = new AtomicInteger(1);
    //排队号
    private AtomicInteger ticketNum  = new AtomicInteger(0);

    //声明threadLocal变量用于独立的存储各个线程的编号
    private ThreadLocal<Integer> ticketNumHolder = new ThreadLocal<>();

    public void lock(){
        int currentTicketNum = ticketNum.incrementAndGet();

        ticketNumHolder.set(currentTicketNum);
        while(currentTicketNum!=serviceNum.get()){
            //Do nothing
        }

    }

    public void unLock(){
        Integer currentTickNum = ticketNumHolder.get();
        serviceNum.compareAndSet(currentTickNum,currentTickNum+1);
    }
}
