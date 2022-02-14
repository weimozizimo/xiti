package concurrent.threadpoolDemo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @Description 手写简易线程池
 * @Author weiyifei
 * @date 2022/2/7
 */
public class DefualtThreadPool<Job extends Runnable> implements ThreadPool<Job> {

    //线程池最大限制数
    private static final int MAX_WORKER_NUMBERS = 10;
    //线程池默认的数量
    private static final int DEFAULT_WORKER_NUMBERS = 5;
    //线程池最小的数量
    private static final int MIN_WORKER_NUMBERS = 1;
    //这是一个工作列表，将会向里面插入工作
    private final LinkedList<Job> jobs = new LinkedList<>();
    //工作者列表
    private final List<Worker> workers = Collections.synchronizedList(new ArrayList<>());
    //工作者线程的数量
    private int workerNum = DEFAULT_WORKER_NUMBERS;
    //线程编号生成
    private AtomicLong threadNum = new AtomicLong();

    public DefualtThreadPool() {
        initializeWorkers(DEFAULT_WORKER_NUMBERS);
    }

    public DefualtThreadPool(int num) {
        workerNum = num > MAX_WORKER_NUMBERS ? MAX_WORKER_NUMBERS : num < MIN_WORKER_NUMBERS ? MIN_WORKER_NUMBERS : num;
        initializeWorkers(workerNum);
    }


    //初始化线程工作者
    private void initializeWorkers(int num) {
        for (int i = 0; i < num; i++) {
            Worker worker = new Worker();
            workers.add(worker);
            Thread thread = new Thread(worker, "ThreadPool-Worker-" + threadNum.incrementAndGet());
            thread.start();
        }
    }


    @Override
    public void execute(Job job) {
        if (job != null) {
            synchronized (jobs){
                //添加一个工作，然后进行通知
                jobs.addLast(job);
                jobs.notify();
            }
        }
    }

    @Override
    public void shutdown() {
        for (Worker worker : workers) {
            worker.shudown();
        }
    }

    /**
     * 新增工作者和移除的时候，不允许消费任务
     * @param num
     */
    @Override
    public void addWorkers(int num) {
        synchronized (jobs) {
            //限制新增加的Worker不能超过最大值
            if (num + this.workerNum > MAX_WORKER_NUMBERS) {
                num = MAX_WORKER_NUMBERS - this.workerNum;
            }
            initializeWorkers(num);
            this.workerNum += num;
        }
    }

    @Override
    public void removeWorkers(int num) {
        synchronized (jobs){
            if(this.workerNum-num<=MIN_WORKER_NUMBERS){
                throw new IllegalArgumentException("beyond workNum");
            }
            int count = 0;
            while (count>num){
                Worker worker = workers.get(count);
                //将worker移除工作者队列，然后停止该工作线程（安全停止）
                if(workers.remove(worker)){
                    worker.shudown();
                    count++;
                }
            }
            this.workerNum -=count;
        }
    }

    //获取任务size
    @Override
    public int getJobSize() {
        return jobs.size();
    }

    class Worker implements Runnable {

        private volatile boolean running = true;

        @Override
        public void run() {
            while (running) {
                Job job = null;
                synchronized (jobs) {
                    while (jobs.isEmpty()) {
                        try {
                            jobs.wait();
                        } catch (InterruptedException e) {
                            //感知到外部对workerThread的中断炒作，返回
                            Thread.currentThread().interrupt();
                            return;
                        }

                    }
                    //取出一个job
                    job = jobs.removeFirst();
                }
                if (job != null) {
                    try {
                        job.run();
                    } catch (Exception e) {
                        //忽略Job执行期间的异常
                    }
                }
            }
        }

        public void shudown() {
            running = false;
        }
    }
}
