package concurrent.base;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @Description 等待/通知机制
 * @Author weiyifei
 * @date 2022/2/3
 */
public class WaitNotifyDemo {

    static boolean flag = true;
    static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread waitThread = new Thread(new Wait(), "WaitThread");
        waitThread.start();
        TimeUnit.SECONDS.sleep(1);
        Thread notifyThread = new Thread(new Notify(), "NotifyThread");
        notifyThread.start();
    }

    /**
     * @Description 等待方
     * @Author weiyifei
     * @date 2022/2/3
     */
    static class Wait implements Runnable {

        @Override
        public void run() {
            //加锁，拥有lock的monitor
            synchronized (lock) {
                while (flag) {
                    try {
                        System.out.println(Thread.currentThread() + " flag is true. wait @ " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //条件满足是，完成工作
                System.out.println(Thread.currentThread() + " flag is false. running @ " + new SimpleDateFormat("HH:mm:ss").format(new Date()));


            }
        }
    }

    /**
    *@Description 通知方
    *@Author weiyifei
    *@date 2022/2/3
    */
    static class Notify implements Runnable {

        @Override
        public void run() {
            synchronized(lock){
                System.out.println(Thread.currentThread() + " hold lock. notify @ " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                lock.notifyAll();
                flag = false;
                //等待5秒再释放锁
                SleepUtil.sleep(5);
            }
            //再次加锁
            synchronized (lock){
                System.out.println(Thread.currentThread() + " hold lock again. notify @ " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                SleepUtil.sleep(5);
            }
        }
    }
}
