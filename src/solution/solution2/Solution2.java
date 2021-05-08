package solution.solution2;

import other.Solution;

/**
 * @author weiyifei
 * @description 数组的异或操作
 * @date 2021/5/7
 * <p>
 * 第二种方法，使用数学的方式，可以让时间复杂度减少到O(1)
 */
public class Solution2 {
    public static void main(String[] args) {
        int i = xorOperation(4, 3);
        System.out.println(i);
    }

    public static int xorOperation(int n, int start) {
        int s = start >> 1; //这里>>代表二进制向右移动一位，由于是二进制，所以向右移动一位相当于除以2.对应公式里面的 s = start/2
        /**
         * 这里start & 1是为了将奇数的1提出来，之所以 & n 使用因为如果n是奇数的话，所有数的末尾的1两两相消还会留一个1出来，所以为了这里是根据是奇数还是偶数来判断
         * e是0还是1
         */
        int e = n & start & 1;
        //这一步不用多说，看题解的公式
        int ret = sumXor(s - 1) ^ sumXor(s + n - 1);
        return ret << 1 | e;
    }

    //这个方法是模拟sumXor方法,根据性质5，可以被4整除的根据公式最后会是0，那么我们只需要处理余数即可
    public static int sumXor(int x) {
        if (x % 4 == 0) {
            return x;
        }
        if (x % 4 == 1) {
            return 1;
        }
        if (x % 4 == 2) {
            return x + 1;
        }
        return 0;
    }

}
