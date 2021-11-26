package solution.solution4;

class Solution1 {
    public static void main(String[] args) {
        int[] num1 = {1,2};
        int[] num2 = {3,4};
        System.out.println(findMedianSortedArrays(num1,num2));
    }
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] mNums = new int[nums1.length+nums2.length];
        int s =  mNums.length;

        int i1=0,i2=0;

        for(int i = 0 ; i < s ; i ++){

            if(i1<nums1.length&&i2<nums2.length) {

                if (nums1[i1] < nums2[i2]) {
                    mNums[i] = nums1[i1];
                    i1++;
                } else if (nums1[i1] > nums2[i2]) {
                    mNums[i] = nums2[i2];
                    i2++;
                } else {
                    mNums[i] = nums1[i1];
                    i1++;
                }
            }else{
                if(i1 == nums1.length&&i2< nums2.length){
                    mNums[i] = nums2[i2];
                    i2++;
                }else if(i2== nums2.length&&i1< nums1.length){
                    mNums[i] = nums1[i1];
                    i1++;
                }else {
                    break;
                }
            }
        }
        if(s%2==0){
            return (mNums[(s-1)/2]+mNums[(s-1)/2+1])/2.0d;
        }else{
            return (mNums[(s-1)/2]);
        }
    }
}
