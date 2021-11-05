package solution.solution1;

import org.apache.regexp.RE;

import java.util.Arrays;

/**
 * @author weiyifei
 * @description 解决方案2，此处和方案一的不同指出在于判断进行判断的时候使用二分法，降低了时间复杂度
 * @date 2021/4/29
 */
public class Solution2 {
    public static void main(String[] args) {
        int i = shipWithinDays(new int[]{1,2,3,1,1}, 4);
        System.out.println(i);
    }

    public static int shipWithinDays(int[] weights, int D) {
        // 确定二分查找左右边界
        int left = Arrays.stream(weights).max().getAsInt(), right = Arrays.stream(weights).sum();
        while (left < right) {
            int mid = (left + right) / 2;
            // need 为需要运送的天数
            // cur 为当前这一天已经运送的包裹重量之和
            int need = 1, cur = 0;
            for (int weight : weights) {
                if (cur + weight > mid) {
                    ++need;
                    cur = 0;
                }
                cur += weight;
            }
            if (need <= D) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
