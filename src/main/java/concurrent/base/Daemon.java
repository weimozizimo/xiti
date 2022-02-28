package concurrent.base;

/**
 * @author weiyifei
 * @description 测试Daemon线程
 * @date 2022/1/24
 */
public class Daemon {
    public static void main(String[] args) {
        Thread thread = new Thread(new DaemonRunner());
        thread.setDaemon(true);
        thread.start();
    }

    static class DaemonRunner implements Runnable {

        @Override
        public void run() {
            try {
                SleepUtil.sleep(10);
            } finally {
                System.out.println("DaemonThread finally run.");
            }
        }
    }
}
