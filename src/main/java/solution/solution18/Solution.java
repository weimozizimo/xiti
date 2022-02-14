package solution.solution18;

import java.util.ArrayList;
import java.util.List;

/**
*@Description 这里使用了动态规划的思想，通过自底而上的思想来获得结果
 * 首先我们设到
*@Author weiyifei
*@date 2022/2/13
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(climbStairs(4));
    }

    public static int climbStairs(int n) {
       if(n==1){
           return 1;
       }
       if(n==2){
           return 2;
       }
        int n1 = 1,n2 = 2;

        int cur=0;
        for(int i = 3 ; i <= n ; i ++ ){
            cur = n1+n2;
            n1 = n2;
            n2 = cur;
        }

        return cur;
    }
}
