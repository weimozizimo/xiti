package concurrent.aqs;

import com.sun.corba.se.spi.orbutil.threadpool.Work;
import concurrent.base.SleepUtil;
import org.junit.Test;

import java.util.concurrent.locks.Lock;

public class TwinLockTest {
    @Test
    public void test1(){
         final Lock lock = new TwinsLock();
         class Worker extends Thread{
             @Override
             public void run() {
                 while (true){
                     try {
                         lock.lock();
                         SleepUtil.sleep(1);
                         System.out.println(Thread.currentThread().getName());
                         SleepUtil.sleep(1);
                     } finally {
                         lock.unlock();
                     }
                 }
             }
         }
         for(int i = 0 ; i < 10 ; i++){
             Worker worker = new Worker();
             worker.start();
         }
         for (int i = 0 ; i <  10 ; i++){
             SleepUtil.sleep(1);
             System.out.println();
         }
    }
}
