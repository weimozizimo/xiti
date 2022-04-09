package solution.solution27;

import java.util.*;
import java.util.stream.Collectors;

public class Solution1 {
    public static void main(String[] args) {

        int[] ints = topKFrequent(new int[]{4,1,-1,2,-1,2,3}, 2);
        for (int anInt : ints) {
            System.out.println(anInt);
        }
    }

    public static int[] topKFrequent(int[] nums, int k) {
        Map<Integer,Integer> map = new TreeMap<>();
        for (int num : nums) {
            Integer count = map.computeIfAbsent(num, key -> 0);
            map.put(num,count+1);
        }

        List<Map.Entry<Integer,Integer>> list = new ArrayList<>(map.entrySet());

        list = list.stream().sorted(new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        }).collect(Collectors.toList());

        return list.stream().mapToInt(o -> o.getKey()).limit(k).toArray();
    }
}
