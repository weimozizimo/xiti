package concurrent.threadpoolDemo;


import java.util.concurrent.TimeUnit;

public class TestThreadPool {

    static ThreadPool<Runner> pool = new DefualtThreadPool<>(3);

    public static void main(String[] args) {
        for (int i = 0; i <20 ; i++) {
            pool.execute(new Runner());
        }
    }



    static class Runner implements Runnable{

        @Override
        public void run() {
            System.out.println( Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
