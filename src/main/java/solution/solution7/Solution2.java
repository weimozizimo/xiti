package solution.solution7;

import java.util.*;

/**
*@Description  排序加双指针
*@Author weiyifei
*@date 2021/12/29
*/
public class Solution2 {
    public static void main(String[] args) {
        int[] nums1 = {1,2,2,1};
        int[] nums2 = {2,2};
        int[] intersection = intersection(nums1, nums2);
        for (int i : intersection) {
            System.out.println(i);
        }
    }

    public static int[] intersection(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int[] arrs = new int[nums1.length+nums2.length];

        int index1=0,index2=0,index=0;

        while (index1<nums1.length&&index2<nums2.length){
            int n1 = nums1[index1];
            int n2 = nums2[index2];
            if(n1==n2){
                if(index==0||n1!=arrs[index-1]){
                    arrs[index++]=n1;

                }
                index1++;
                index2++;
            }
            else if(n1<n2){
                index1++;
            }
            else{
                index2++;
            }
        }
        return Arrays.copyOfRange(arrs,0,index);
    }
}
