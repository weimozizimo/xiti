package solution.solution14;

public class Solution22 {
    public static void main(String[] args) {

        System.out.println(myPow(3,77));
    }

    public static double myPow(double x, int n) {
        long N = n;
        return N>0?quickMul(x,N):1/quickMul(x,-N);
    }

    public static double quickMul(double x,long N){
        double ans = 1.0;
        //贡献初始值为x
        double x_contirbute = x;
        //在对N进行拆分的同时计算答案
        while (N>0){
            if(N%2==1){
                //如果N二进制表示的最低位为1，那么需要计入贡献
                ans*=x_contirbute;
            }
            //将贡献不断平方
            x_contirbute*=x_contirbute;
            N/=2;
        }
        return ans;
    }
}
