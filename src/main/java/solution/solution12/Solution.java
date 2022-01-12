package solution.solution12;

/**
*@Description 二分法，但是需要考虑两端元素相同，由于这里没有target，所以需要考虑 左右指针相等或者左指针大于有指针的问题
*@Author weiyifei
*@date 2022/1/5
*/
public class Solution {

    public static void main(String[] args) {
        int[] nums = {3,1};

        int search = findMin(nums);
        System.out.println(search);
    }


    public static int findMin(int[] nums) {
        if(nums.length==1) {
            return nums[0];
        }
        int l = 0;
        int r = nums.length-1;

        int min = Integer.MAX_VALUE;
        while(l<=r){
            int mid = l+ (r-l)/2;

            if(l==r){
                return min>nums[l]?nums[l]:min;
            }

            //排除左右相同的情况，因为两个会留一个，所以没必要担心会把最小元素过滤掉的问题
            if(nums[l]==nums[mid]&&nums[r]==nums[mid]){
                r--;
                l++;
                if(l>r){
                    return min>nums[r]?nums[r]:min;
                }
            }else if(nums[l]<=nums[mid]){ //分情况讨论，mid左边有序的情况
                if(min>nums[l]){
                    min = nums[l];
                }
                l = mid+1;
            }else {
                if(min>nums[mid]){
                    min = nums[mid];
                }
                r = mid-1;
            }
        }
        return min;
    }
}
