package solution.solution11;

/**
*@Description 二分法，但是需要考虑两端元素相同，或者中间点和左右边界值相同的问题 TODO 代码结构待优化，有点冗余
*@Author weiyifei
*@date 2022/1/5
*/
public class Solution {

    public static void main(String[] args) {
        int[] nums = {1,1,1,1,1,1,1,1,1,13,1,1,1,1,1,1,1,1,1,1,1,1};

        boolean search = search(nums, 13);
        System.out.println(search);
    }


    public static boolean search(int[] nums, int target) {
        if(nums.length==0){
            return false;
        }
        if(nums.length==1){
            return nums[0]==target?true:false;
        }

        int l = 0;
        int r = nums.length-1;

        while(l<=r){
            int mid = l+ (r-l)/2;

            if(nums[mid]==target){
                return true;
            }

            if(nums[l]==nums[mid]&&nums[r]==nums[mid]){
                r--;
                l++;
            }else if(nums[l]<=nums[mid]){
                if(nums[mid]>target&&nums[l]<=target){
                    r = mid-1;
                }else {
                    l = mid+1;
                }
            }else {
                if(nums[mid]<target&&nums[r]>=target){
                    l = mid+1;
                }else {
                    r = mid-1;
                }
            }
        }
        return false;
    }
}
