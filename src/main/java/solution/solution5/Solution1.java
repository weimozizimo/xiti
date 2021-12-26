package solution.solution5;

/**
*@Description 二分查找法
*@Author weiyifei
*@date 2021/11/28
*/
public class Solution1 {

    public static void main(String[] args) {
        int i = mySqrt(2147395599);
        System.out.println(i);
    }

    public static int mySqrt(int x) {
        long l = 0;
        long r = x;
        int ans=-1;

        while(l<=r){
            long mid = (l+r)/2;
            if(mid*mid<=x){
                ans = (int)mid;
                l=mid+1;
            }else{
                r = mid-1;
            }
        }

        return ans;
    }
}
