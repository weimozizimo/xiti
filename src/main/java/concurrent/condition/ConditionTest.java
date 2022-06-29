package concurrent.condition;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionTest {

    /**
     * 创建锁
     */
    public static Lock lock = new ReentrantLock();


    final static int MAX_COUNT = 10;

    static Queue<Integer> queue = new LinkedList<Integer>();

    public static Condition con = lock.newCondition();
    public static Condition pro = lock.newCondition();

    static CountDownLatch latch = new CountDownLatch(1);

    public static void main(String[] args) throws InterruptedException {

        Thread producer = new Thread(new Producer(latch));
        Thread consumer = new Thread(new Consumer(latch));
        producer.start();
        consumer.start();
        latch.countDown();
    }



    public static class Producer implements Runnable{

        private CountDownLatch latch;

        public Producer(CountDownLatch latch) {
            this.latch = latch;
        }

        int seed = 0;

        @Override
        public void run() {
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            while (true){
                lock.lock();
                if(queue.size()<MAX_COUNT){
                    queue.add(++seed);
                    System.out.println("queue add number:"+seed);
                    pro.signal();
                    lock.unlock();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }else {
                    //队列满了，等待消费，否则不生产
                    try {
                        con.await();
                    } catch (InterruptedException e) {
                        lock.unlock();
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static class Consumer implements Runnable{

        private CountDownLatch latch;

        public Consumer(CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public void run() {
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            while (true){
                lock.lock();
                if(queue.size()>0){
                    Integer number = queue.remove();
                    System.out.println("queue consumer number:"+number);
                    con.signal();
                    lock.unlock();
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else {
                    try {
                        pro.await();
                    } catch (InterruptedException e) {
                        lock.unlock();
                        e.printStackTrace();

                    }
                }

            }
        }
    }
}
