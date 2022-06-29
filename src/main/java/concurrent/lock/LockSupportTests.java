package concurrent.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class LockSupportTests {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("123");
                LockSupport.parkNanos(this, TimeUnit.MINUTES.toNanos(10));
            }
        });
        t1.start();
        t1.join();
    }
}
