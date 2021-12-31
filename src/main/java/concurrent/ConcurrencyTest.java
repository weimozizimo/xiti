package concurrent;

import java.util.concurrent.ThreadPoolExecutor;

/**
*@author weiyifei
*@description 测试多线程和单线程的效率差别
*@date 2021/12/21
*/
public class ConcurrencyTest {
    private static long count = 1000000000;

    public static void main(String[] args) throws InterruptedException {
        concurrency();
        serial();
    }

    public static void concurrency() throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int a = 0;
                for (long i = 0 ; i < count; i++){
                    a+=5;
                }
            }
        });
        thread.start();
        int b = 0;
        for(int i = 0 ; i< count ; i ++){
            b--;
        }
        thread.join();
        long time = System.currentTimeMillis()-start;
        System.out.println("concurrency:"+time+"ms,b="+b);
    }

    public static void serial(){
       long start = System.currentTimeMillis();
       long a = 0 ;
       for(long i = 0 ; i < count ; i ++){
           a+=5;
       }
       int b = 0 ;
       for(long i = 0 ; i < count ; i ++){
           b--;
       }
        long time = System.currentTimeMillis()-start;
        System.out.println("serial:"+time+"ms,b="+b);
    }
}
