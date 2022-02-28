package concurrent.threadpoolDemo;

public interface ThreadPool<Job extends Runnable> {

    //执行一个Job，这个job需要实现Runnable接口
    void execute(Job job);
    //关闭线程池
    void shutdown();
    //增加工作线程
    void addWorkers(int num);
    //减少工作线程
    void removeWorkers(int num);
    //获取正在的等待执行的任务数量
    int getJobSize();
}
