package concurrent.base;

import java.util.concurrent.TimeUnit;

/**
*@Description ThreadLocal demo
*@Author weiyifei
*@date 2022/2/5
*/
public class Profiler {

    private static final ThreadLocal<Long> TIME_THREADLOCAL = new ThreadLocal<Long>(){
        protected Long initialValue(){
            return System.currentTimeMillis();
        }
    };

    public static final void start(){
        TIME_THREADLOCAL.set(System.currentTimeMillis());
    }

    public static final Long end(){
        return System.currentTimeMillis()-TIME_THREADLOCAL.get();
    }

    public static void main(String[] args) throws InterruptedException {
       Profiler.start();
       TimeUnit.SECONDS.sleep(2);
        System.out.println("Cost:"+Profiler.end()+"mills");
    }
}
