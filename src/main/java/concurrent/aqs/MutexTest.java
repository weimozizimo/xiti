package concurrent.aqs;

import java.util.concurrent.CountDownLatch;

public class MutexTest {
    public static int count = 0;

    public static CountDownLatch start = new CountDownLatch(1);
    public static CountDownLatch end = new CountDownLatch(10);

    static Mutex lock = new Mutex();

    public static void main(String[] args) throws InterruptedException {

        for(int i = 0 ; i < 10 ; i++){
            Thread t = new Thread(new Worker(),"Thread"+i);
            t.start();
            Thread.sleep(300);
        }
        start.countDown();
        end.await();

        System.out.println(count);
    }


    static class Worker implements Runnable{

        @Override
        public void run() {
            try {
                //等待开始
                start.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.lock();
            for(int i = 0 ; i < 30 ; i++){
                count+=i;
                System.out.println(Thread.currentThread().getName()+":+"+i);
            }
            end.countDown();
            lock.unlock();
        }
    }
}


