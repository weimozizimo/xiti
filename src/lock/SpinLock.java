package lock;

import java.util.concurrent.atomic.AtomicReference;

/**
*@description 基础自旋锁(可重入
*@author weiyifei
*@date 2020/10/29
*/
public class SpinLock {

    private AtomicReference<Thread> cas = new AtomicReference<>();

    private int count;

    public void lock(){
        Thread currentThread = Thread.currentThread();
        if(currentThread == cas.get()){ //如果当前线程获取倒了锁，线程数增加1，然后返回
            count++;
            return;
        }
        //如果没有获取到锁，则通过CAS自旋
        while(!cas.compareAndSet(null,currentThread)){
            //Do nothing
        }
    }

    public void unLock(){
        Thread currentThread = Thread.currentThread();
        if(currentThread == cas.get()){
            if(count>0){ // 如果大于0，表示当前线程多次获取了该锁，释放锁通过count减一来模拟
                count--;
            }else { // 如果count==0，可以将锁释放，这样就能保证获取锁的次数与释放锁的次数是一致的了。
                cas.compareAndSet(currentThread,null);
            }
        }

    }
}
