package solution.solution14;

import cn.hutool.core.util.NumberUtil;

/**
 * 快速幂+递归
 */
public class Solution {
    public static void main(String[] args) {
        System.out.println(myPow(1.00,-2));
    }

    public static double myPow(double x, int n) {
       long N = n;
       return N>0?quickMul(x,N):1/quickMul(x,-N);
    }

    public static double quickMul(double x,long N){
        if(N==0){
            return 1.0;
        }
        double y = quickMul(x,N/2);

        return N%2==0?y*y:y*y*x;
    }
}
