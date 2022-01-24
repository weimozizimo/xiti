package concurrent.base;

/**
 * 观察线程的各个生命周期的状态
 */
public class ThreadState {

    public static void main(String[] args) {
      new Thread(new TimeWaiting(),"TimeWaitingThread").start();
      new Thread(new Waiting(),"WaitingThread").start();
      new Thread(new Blocked(),"BlockedThread-1").start();
      new Thread(new Blocked(),"BlockedThread-2").start();
    }


    //该线程一直循环去睡眠，陷入超时等待状态
    static class TimeWaiting implements Runnable{

        @Override
        public void run() {
            while (true){
                SleepUtil.sleep(100);
            }
        }
    }

    //该线程使用wait进入WAITING状态
    static class Waiting implements Runnable{

        @Override
        public void run() {
            while (true){
                synchronized (Waiting.class){
                        try {
                            Waiting.class.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                }
            }
        }
    }

    //让执行线程在Blocked.class实例上枷锁后，循环睡眠，不释放锁
    static class Blocked implements Runnable{

        @Override
        public void run() {
            synchronized (Blocked.class){
                while (true){
                    SleepUtil.sleep(100);
                }
            }
        }
    }
}
