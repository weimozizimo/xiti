执行ThreadState,通过jps找到ThreadState的进程的pid
然后使用jstack对进程的堆栈信息进行一个快照

C:\Program Files\Java\jdk1.8.0_211\bin>jps
19924 RemoteMavenServer36
14376 ThreadState
9656
17884 KotlinCompileDaemon
20412 Jps
20796 Launcher

```C:\Program Files\Java\jdk1.8.0_211\bin>jstack 14376
2022-01-12 15:51:32
Full thread dump Java HotSpot(TM) 64-Bit Server VM (25.211-b12 mixed mode):

"DestroyJavaVM" #16 prio=5 os_prio=0 tid=0x0000000003744800 nid=0x3d64 waiting on condition [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"BlockedThread-2" #15 prio=5 os_prio=0 tid=0x00000000208ff000 nid=0x3160 waiting for monitor entry [0x00000000217af000]
   java.lang.Thread.State: BLOCKED (on object monitor)
        at concurrent.base.ThreadState$Blocked.run(ThreadState.java:51)
        - waiting to lock <0x000000076b2485f8> (a java.lang.Class for concurrent.base.ThreadState$Blocked)
        at java.lang.Thread.run(Thread.java:748)

"BlockedThread-1" #14 prio=5 os_prio=0 tid=0x00000000208f6000 nid=0x4308 waiting on condition [0x00000000216af000]
   java.lang.Thread.State: TIMED_WAITING (sleeping)
        at java.lang.Thread.sleep(Native Method)
        at java.lang.Thread.sleep(Thread.java:340)
        at java.util.concurrent.TimeUnit.sleep(TimeUnit.java:386)
        at concurrent.base.SleepUtil.sleep(SleepUtil.java:9)
        at concurrent.base.ThreadState$Blocked.run(ThreadState.java:51)
        - locked <0x000000076b2485f8> (a java.lang.Class for concurrent.base.ThreadState$Blocked)
        at java.lang.Thread.run(Thread.java:748)

"WaitingThread" #13 prio=5 os_prio=0 tid=0x00000000208f5000 nid=0x5540 in Object.wait() [0x00000000215ae000]
   java.lang.Thread.State: WAITING (on object monitor)
        at java.lang.Object.wait(Native Method)
        - waiting on <0x000000076b245bc0> (a java.lang.Class for concurrent.base.ThreadState$Waiting)
        at java.lang.Object.wait(Object.java:502)
        at concurrent.base.ThreadState$Waiting.run(ThreadState.java:35)
        - locked <0x000000076b245bc0> (a java.lang.Class for concurrent.base.ThreadState$Waiting)
        at java.lang.Thread.run(Thread.java:748)

"TimeWaitingThread" #12 prio=5 os_prio=0 tid=0x00000000208c4800 nid=0xbf0 waiting on condition [0x000000002148f000]
   java.lang.Thread.State: TIMED_WAITING (sleeping)
        at java.lang.Thread.sleep(Native Method)
        at java.lang.Thread.sleep(Thread.java:340)
        at java.util.concurrent.TimeUnit.sleep(TimeUnit.java:386)
        at concurrent.base.SleepUtil.sleep(SleepUtil.java:9)
        at concurrent.base.ThreadState$TimeWaiting.run(ThreadState.java:22)
        at java.lang.Thread.run(Thread.java:748)
  ```      
我们可以看到,各个线程的当前状态如我们所预期的结果一直