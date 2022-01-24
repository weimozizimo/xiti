package concurrent.base;

import java.util.concurrent.TimeUnit;

public class SleepUtil {

    public static void sleep(long second){
        try {
            TimeUnit.SECONDS.sleep(second);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
