package concurrent.volatile1;

/**
*@Description 用来测试volatile 写-读建立的happens-before关系
*@Author weiyifei
*@date 2021/12/26
*/
public class VolatileTest1 {
    int a = 0;

    volatile boolean flag1 = false;

    boolean flag2 = false;


    public static void main(String[] args) throws InterruptedException {
        VolatileTest1 test1 = new VolatileTest1();
        test1.noVolatile();
//        Thread.sleep(1000);
//        test1.hasVolatile();
    }

    //测试有volatile的情况
    public void hasVolatile(){
        a = 0;
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0 ; i < 100 ; i++){
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    writer1();
                }

            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0 ; i < 100 ; i ++){
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    reader1();
                }
            }
        });

        t1.start();
        t2.start();



    }

    //测试无volatile的情况狂
    public void noVolatile(){
        a = 0;
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0 ; i < 100 ; i++){
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    writer2();
                }

            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0 ; i < 100 ; i ++){
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    reader2();
                }
            }
        });

        t1.start();
        t2.start();


    }

    public void writer1(){

        if(flag1){
            a ++;
            flag1 = false;
        }else {
            flag1 = true;
        }
    }

    public void writer2(){

        if(flag2){
            a ++;
            flag2 = false;
        }else {
            flag2 = true;
        }
    }

    public void reader1(){
        if(flag1){
            int i = a;
            System.out.println(i);
        }else {
            System.out.println(false);
        }
    }

    public void reader2(){
        if(flag2){
            int i = a;
            System.out.println(i);
        }else {
            System.out.println(false);
        }
    }
}
