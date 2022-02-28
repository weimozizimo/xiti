package solution.solution19;

import org.springframework.util.NumberUtils;

import java.util.Arrays;

public class Solution1 {
    public static void main(String[] args) {
        int[] ints = {-2,-1,1,3,4};
        int i = maxSubArray(ints);
        System.out.println(i);
    }


    public static int maxSubArray(int[] nums) {
        int pre = 0,maxAns = nums[0];
        for (int x : nums) {
            pre = Math.max(pre+x,x);
            maxAns = Math.max(maxAns,pre);
        }
        return maxAns;
    }

}
