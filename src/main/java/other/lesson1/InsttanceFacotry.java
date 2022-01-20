package other.lesson1;

/**
*@author weiyifei
*@description 测试静态内部类的初始化时间点
*@date 2022/1/6
*/
public class InsttanceFacotry
{

    public static String key = "123";
    static {
        System.out.println("InstanceFacotry静态初始化");
    }
    {
        System.out.println("InstanceFacotry初始化");
    }

    private static class InstanceHolder{
        public static Instance instance = new Instance();
        static {
            System.out.println("初始化instance");
        }
    }

    public static Instance getInstance(){
        System.out.println("获取Instance");
        return InstanceHolder.instance;
    }
}
