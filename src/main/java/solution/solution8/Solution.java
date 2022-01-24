package solution.solution8;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

//使用hash表的特性来处理
class Solution {

    public static void main(String[] args) {
        int[] nums1 = {1,2,2,3};
        int[] nums2 = {1,2,4};
        int[] intersection = intersect(nums1, nums2);
        for (int i : intersection) {
            System.out.println(i);
        }
    }

    public static int[] intersect(int[] nums1, int[] nums2) {

        Map<Integer, Integer> map = new HashMap<>();
        Map<Integer, Integer> map2 = new HashMap<>();
        for (int i : nums1) {
            Integer n = map.putIfAbsent(i, 1);
            if (n != null) {
                map.put(i, ++n);
            }
        }
        int len = 0;
        for (int i : nums2) {
            Integer a = map.get(i);
            if (a != null) {
                Integer b = map2.putIfAbsent(i, 1);
                if (b != null) {
                    if (++b <= a) {
                        map2.put(i, b);
                        len++;
                    }
                }else {
                    len++;
                }

            }
        }
        int[] reNum = new int[len];
        AtomicInteger index = new AtomicInteger();
        map2.forEach((k, v) -> {
            for (int i = 0; i < v; i++) {
                reNum[index.get()] = k;
                index.getAndIncrement();
            }
        });

        return reNum;
    }
}

