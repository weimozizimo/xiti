package solution.solution8;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
*@Description 使用双指针
*@Author weiyifei
*@date 2021/12/30
*/
public class Solution2 {

    public static void main(String[] args) {
        int[] nums1 = {1,2,2,3};
        int[] nums2 = {1,2,4};
        int[] intersection = intersect(nums1, nums2);
        for (int i : intersection) {
            System.out.println(i);
        }
    }

    public static int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int len1 = nums1.length;
        int len2 = nums2.length;
        int a=0;
        int b=0;
        int[] reNum = new int[len1+len2];
        int length = 0;
        while(a<len1&&b<len2){
            int n1 = nums1[a];
            int n2 = nums2[b];
            if(n1==n2){
                reNum[length++] = n1;
                a++;
                b++;
            }else if(n1<n2){
                a++;
            }else {
                b++;
            }
        }

        return Arrays.copyOfRange(reNum,0,length);
    }
}
