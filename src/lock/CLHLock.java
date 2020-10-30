package lock;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
*@description 基于链表的可扩展、高性能、公平的自旋锁，申请线程直在本地变量上自旋，他不断轮询前驱的状态，如果发现前驱
 * 释放了锁就结束自旋，获得锁
*@author weiyifei
*@date 2020/10/30
*/
public class CLHLock {

    /**
     * 定义一个节点，默认lock状态为true
     */
    public static class CLHNode{
        private volatile boolean isLocked = true;
    }

    /**
     * 尾部节点，只用一个节点即可
     */
    private volatile CLHNode tail;
    private static final ThreadLocal<CLHNode> LOACL = new ThreadLocal<>();
    private static final AtomicReferenceFieldUpdater<CLHLock,CLHNode> UPDATER = AtomicReferenceFieldUpdater.newUpdater(CLHLock.class,CLHNode.class,"tail");


}
