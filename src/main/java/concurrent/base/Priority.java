package concurrent.base;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
*@Description 线程优先级测试
*@Author weiyifei
*@date 2022/1/12
*/
public class Priority {
    private static volatile boolean notStart = true;

    private static volatile boolean notEnd = true;

    public static void main(String[] args) throws InterruptedException {
        List<Job> jobs = new ArrayList<>();
        for(int i = 0 ; i < 10 ; i++){
            int priority = i>5?10:1;
            Job job = new Job(priority);
            Thread thread = new Thread(job);
            thread.start();
            jobs.add(job);
        }

        notStart = false;

        TimeUnit.SECONDS.sleep(10);

        notEnd = false;

        for (Job job : jobs) {
            System.out.println("Job Priority is "+job.priority+"; Count:"+job.jobCount);
        }


    }

    static class Job implements Runnable{

        //优先级
        private int priority;
        //计数
        private long jobCount;

        public Job(int priority) {
            this.priority = priority;
        }

        @Override
        public void run() {
            while (notStart){
                Thread.yield();
            }

            while (notEnd){
                //让线程从运行变成就绪，重新竞争CPU使用权
                Thread.yield();
                jobCount++;
            }
        }
    }


}
