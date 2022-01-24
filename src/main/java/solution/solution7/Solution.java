package solution.solution7;

import cn.hutool.core.util.ArrayUtil;

import java.util.*;

//使用hash表的特性来处理
class Solution {

    public static void main(String[] args) {
        int[] nums1 = {1,2,2,3};
        int[] nums2 = {1,2,4};
        int[] intersection = intersection(nums1, nums2);
        for (int i : intersection) {
            System.out.println(i);
        }
    }

    public static int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> re = new HashSet<>();
        Map<Integer,Integer> map = new HashMap<>();
        for (int i : nums1) {
            map.put(i,0);
        }
        for (int i : nums2) {
            Integer v = map.get(i);
            if(v!=null){
                re.add(i);
            }
        }
        int[] arr = new int[re.size()];
        int ind = 0;
        for (Integer v : re) {
            arr[ind] = v;
            ind++;
        }
        return arr;
    }
}

