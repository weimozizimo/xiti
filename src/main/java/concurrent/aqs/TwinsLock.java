package concurrent.aqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
*@Description  自定义同步组件
 * 要求:该工具在同一时刻，只允许之多两个线程同时访问，超过两个线程的访问将被阻塞。
*@Author weiyifei
*@date 2022/5/4
*/
public class TwinsLock implements Lock {

    private final Sync sync = new Sync(2);

    private static class Sync extends AbstractQueuedSynchronizer{

        public Sync(int count) {
            if(count<=0){
                throw new IllegalArgumentException("count必须大于0");
            }
            setState(count);
        }

        @Override
        protected int tryAcquireShared(int reduceCount) {
            for (;;){
                int current = getState();
                int newCount = current-reduceCount;
                if(newCount<0||compareAndSetState(current,newCount)){
                    return newCount;
                }
            }
        }

        @Override
        protected boolean tryReleaseShared(int returnCount) {
            for(;;){
                int current = getState();
                int newCount = current+returnCount;
                if(compareAndSetState(current,newCount)){
                    return true;
                }
            }
        }

        protected Condition newCondition(){
            return new ConditionObject();
        }
    }

    public void lock(){
        sync.acquireShared(1);
    }


    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    public void unlock(){sync.releaseShared(1);}

    public Condition newCondition() {
        return sync.newCondition();
    }

}
