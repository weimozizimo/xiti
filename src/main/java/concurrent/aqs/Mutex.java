package concurrent.aqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class Mutex implements Lock {
    //静态内部类，自定义同步器
    private static class Sync extends AbstractQueuedSynchronizer{

        //当0的时候获取锁
        @Override
        protected boolean tryAcquire(int arg) {
            if(compareAndSetState(0,1)){
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }else {
                return false;
            }
        }

        //释放锁，将状态设置为0
        @Override
        protected boolean tryRelease(int arg) {
            //如果独占状态为0，则抛出监视器异常
            if(getState()==0) throw new IllegalMonitorStateException();
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }
        //判断是否处于独占状态
        @Override
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }

        ///返回一个Condition，每个condition包含一个condition队列
        Condition newCondition(){return new ConditionObject();}

    }

    //仅需要将操作代理到Sync上即可
    private final Sync sync = new Sync();
    @Override
    public void lock() {
        sync.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1,unit.toNanos(time));
    }

    @Override
    public void unlock() {
        sync.release(1);
    }
    public boolean isLocked() {return sync.isHeldExclusively();}
    public boolean hasQueuedThreads(){return sync.hasQueuedThreads();}

    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }
}
