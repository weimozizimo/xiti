package concurrent.condition;

import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionTest {

    /**
     * 创建锁
     */
    public Lock readLock = new ReentrantLock();

    /**
     * 创建条件
     */
    public Condition condition = readLock.newCondition();

    public static void main(String[] args) {
        ConditionTest conditionTest = new ConditionTest();
        Executors.newFixedThreadPool(2);
    }
}
