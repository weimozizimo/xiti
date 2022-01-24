package solution.solution9;

/**
*@Description  二分查找+双指针
*@Author weiyifei
*@date 2022/1/5
*/
public class Solution {

    public static void main(String[] args) {
        int[] nums = {1,4};
        int[] search = searchRange(nums, 4);
        for (int i = 0; i < search.length; i++) {
            System.out.println(search[i]);
        }
    }


    public static int[] searchRange(int[] nums, int target) {
        if(nums.length==0){
            return new int[]{-1,-1};
        }
        if(nums.length==1){
            return nums[0]==target?new int[]{0,0}:new int[]{-1,-1};
        }

        int left = 0;
        int right = nums.length-1;
        int p1=-1,p2=-1;

        while (left<=right){
            int mid = (left+right)/2;
            int m = nums[mid];

            if(m<target){
                left = mid+1;
            }else if(m>target){
                right = mid-1;
            }else {
                p1 = mid;
                p2 = mid;
                boolean f1 = true;
                boolean f2 = true;
                while (f1||f2){
                    if(p1>0&&nums[p1-1]==m){
                        p1--;
                    }else {
                        f1 = false;
                    }
                    if(p2<nums.length-1&&nums[p2+1]==m){
                        p2++;
                    }else {
                        f2 =false;
                    }
                }
                return new int[]{p1,p2};
            }
        }
        return new int[]{p1,p2};
    }
}
