package solution.solution2;

/**
*@description 数组的异或操作
*@author weiyifei
*@date 2021/5/7
 *
 * 该题较简单，使用模拟一下即可，一个简单的循环或则递归就可以解决
*/
public class Solution1 {
    public static void main(String[] args) {
        int i = xorOperation(3, 1);
        System.out.println(i);
    }

    public static int xorOperation(int n, int start) {

        if(n==0){
            return 0;
        }

        int val = (2*(n-1)+start);

        int val2 = xorOperation(n-1,start);


        return val^val2;
    }
}
