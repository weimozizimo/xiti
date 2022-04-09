package solution.solution24;

public class Solution1 {
    public static void main(String[] args) {
        int[] nums = new int[]{2,3,1,1,4};
        System.out.println(canJump(nums));
    }

    public static boolean canJump(int[] nums) {

        int  n = nums.length;
        int rightMost = 0;

        for(int i = 0 ; i < n ; ++i){
            if(i<=rightMost){
                rightMost = Math.max(rightMost,i+nums[i]);
                if(rightMost>=n-1){
                    return true;
                }
            }
        }

        return false;
    }
}
