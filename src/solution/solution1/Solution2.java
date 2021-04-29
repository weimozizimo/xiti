package solution.solution1;

import java.util.Arrays;

/**
*@description 解决方案2
*@author weiyifei
*@date 2021/4/29
*/
public class Solution2 {
    public static void main(String[] args) {
        int i = shipWithinDays(new int[]{1,2,3,1,1}, 4);
        System.out.println(i);
    }
    public static int shipWithinDays(int[] weights, int D) {
        int left = Arrays.stream(weights).max().getAsInt(),right = Arrays.stream(weights).sum();
        while(left<right){
            int mid = (left+right/2);
            int need = 1, cur=0;
            for (int weight : weights) {
                if(cur+weight>mid){
                    ++need;
                    cur=0;
                }
                cur+=weight;
            }
            if(need<=D){
                right=mid;
            }else {
                left = mid+1;
            }
        }
        return left;
    }
}
