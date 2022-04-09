package solution.solution22;

public class Solution1 {

    public static void main(String[] args) {
        int[] ints = countBits(0);
        for (int anInt : ints) {
            System.out.printf(anInt+" ");
        }
    }


    public static int[] countBits(int n) {
        if(n==0){
            return new int[]{0};
        }
        int[] dp  = new int[n+1];


        dp[0] = 0;
        dp[1] = 1;
        int size = 2;
        int count = 0;
        for (int i = 2; i <=n; i++) {
            if(count==size){
                size +=count;
                count = 0;
            }
            dp[i] = dp[i%size]+1;
            count++;
        }
        return dp;
    }
}
