package solution.solution4;

class Solution {


    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] mNums = new int[nums1.length+nums2.length];
        int s =  mNums.length;

        int i1=0,i2=0;

        for(int i = 0 ; i < s ; i ++){

            if(i1 == nums1.length-1){
                mNums[i] = nums2[i2];
            }
            if()

            if(nums1[i1]<nums2[i2]){

            }else if(nums1[i1]>nums2[i2]){

            }
        }
    }
}