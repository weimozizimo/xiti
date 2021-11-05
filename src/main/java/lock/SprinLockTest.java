package lock;

import java.util.concurrent.*;

//测试自旋锁
public class SprinLockTest {

    static int count = 0;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        TikectLock spinLock = new TikectLock();
        CountDownLatch latch = new CountDownLatch(100);

        for (int i = 0; i < 100; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    spinLock.lock();
                    count++;
//                    countMinus();
                    spinLock.unLock();
                    latch.countDown();
                }

//                public void countMinus(){
//                    spinLock.lock();
//                    count--;
//                    spinLock.unLock();
//                }
            });
        }

        latch.await();

        System.out.println(count);
    }
}
