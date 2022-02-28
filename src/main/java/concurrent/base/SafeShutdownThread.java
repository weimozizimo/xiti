package concurrent.base;

import java.util.concurrent.TimeUnit;

/**
 *@Description 安全的终止线程，使用标识符和中断命令
 *@Author weiyifei
 *@date 2022/2/2
 */
public class SafeShutdownThread {
    public static void main(String[] args) throws InterruptedException {
        Thread one = new Thread(new Runner(), "Thread-one");
        one.start();
        TimeUnit.SECONDS.sleep(1);
        one.interrupt();

        Runner runnerTwo = new Runner();
        Thread two = new Thread(runnerTwo, "Thread-two");
        two.start();
        TimeUnit.SECONDS.sleep(1);
        runnerTwo.cancel();
    }

    private static class Runner implements Runnable{

        private volatile boolean on = true;
        private long i;

        @Override
        public void run() {
            while (on&&!Thread.currentThread().isInterrupted()){
                i++;
            }
            System.out.println("i is "+i);
        }


        public void cancel(){
            on = false;
        }
    }
}
