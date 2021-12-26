package solution.solution5;

/**
*@Description 牛顿迭代法
*@Author weiyifei
*@date 2021/12/4
*/
public class Solution2 {
    public static void main(String[] args) {


        int i = mySqrt(3);
        System.out.println(i);

    }

    public static int mySqrt(int x) {

        double s = x;
        double pre = -1;
        while(pre!=s&&(long)Math.pow(s,2)!=x){
            pre = s;
            s = getNextx(s,x);
        }

        return (int)s;
    }

    /**
     * 获取下一个x的迭代的位置
     * @param x
     * @param c
     * @return
     */
    public static double getNextx(double x,int c){
        double newX = x - ((Math.pow(x,2)-c)/(2*x));

        return newX;
    }
}
