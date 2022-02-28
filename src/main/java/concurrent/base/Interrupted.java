package concurrent.base;

import java.util.concurrent.TimeUnit;

/**
*@author weiyifei
*@description 测试中断
*@date 2022/1/24
*/
public class Interrupted {
    public static void main(String[] args) throws InterruptedException {
        //不停的睡眠
        Thread sleepThread = new Thread(new SleepRunner());
        sleepThread.setDaemon(true);
        //不停地运行
        Thread busyThread = new Thread(new BusyRunner());
        busyThread.setDaemon(true);
        sleepThread.start();
        busyThread.start();
        //main线程休眠5秒，让两个线程充分运行
        TimeUnit.SECONDS.sleep(5);
        sleepThread.interrupt();
        busyThread.interrupt();
        System.out.println("sleepThread interrupted is:"+sleepThread.isInterrupted());
        System.out.println("busyThread interrupted is:"+busyThread.isInterrupted());
        //睡眠5秒，防止两个支持线程退出
        TimeUnit.SECONDS.sleep(5);
    }

    static class SleepRunner implements Runnable{

        @Override
        public void run() {
            while (true){
                SleepUtil.sleep(10);
            }
        }
    }

    static class BusyRunner implements Runnable{

        @Override
        public void run() {
            while (true){

            }
        }
    }
}
