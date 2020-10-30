package other;

import com.sun.xml.internal.ws.api.client.WSPortInfo;
import org.omg.CORBA.INTERNAL;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class WildcardTypeTest {


    public static void main(String[] args) {
        int[][] arrs = new int[][]{{1,3},{2,6},{8,10},{15,17}};
        int[][] merge = merge(arrs);
        for (int[] ints : merge) {
            System.out.println(ints[0]+","+ints[1]);
        }
    }

//    给定一个整数类型的数组 nums，请编写一个能够返回数组 “中心索引” 的方法。
//
//    我们是这样定义数组 中心索引 的：数组中心索引的左侧所有元素相加的和等于右侧所有元素相加的和。
//
//    如果数组不存在中心索引，那么我们应该返回 -1。如果数组有多个中心索引，那么我们应该返回最靠近左边的那一个。
//
//
//
//    示例 1：
//
//    输入：
//    nums = [1, 7, 3, 6, 5, 6]
//    输出：3
//    解释：
//    索引 3 (nums[3] = 6) 的左侧数之和 (1 + 7 + 3 = 11)，与右侧数之和 (5 + 6 = 11) 相等。
//    同时, 3 也是第一个符合要求的中心索引。
//

    public static int pivotIndex(int[] nums) {
       int sum = 0,leftSum = 0;
       for(int x : nums) sum+=x;
       for(int i = 0 ; i < nums.length;i+=1){
           if(leftSum==(sum-nums[i]-leftSum))return i;
           leftSum +=nums[i];
       }
        return -1;
    }

//    给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
//
//    你可以假设数组中无重复元素。
//
//    示例 1:
//
//    输入: [1,3,5,6], 5
//    输出: 2


    public int searchInsert(int[] nums, int target) {
        int preIndex = -1;
        int nextIndex = -1;
        for(int i = 0 ; i < nums.length ; i++){
            if(nums[i]==target){
                return i;
            }else if(nums[i]<target){
                preIndex = i;
            }
        }
        if(preIndex==-1){
            return 0;
        }else {
            return preIndex+1;
        }
    }

//    给出一个区间的集合，请合并所有重叠的区间。

//    示例 1:
//
//    输入: intervals = [[1,3],[2,6],[8,10],[15,18]]
//    输出: [[1,6],[8,10],[15,18]]
//    解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].

    public static int[][] merge(int[][] intervals) {
        if(intervals.length==0){
            return new int[0][2];
        }
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return  o1[0] - o2[0];
            }
        });
        ArrayList<int[]> merged = new ArrayList<>();
        for(int i = 0 ; i < intervals.length ; i ++){
            int L = intervals[i][0],R = intervals[i][1];
            if(merged.size()==0||merged.get(merged.size()-1)[1]<L){
                merged.add(new int[]{L,R});
            }else{
                merged.get(merged.size()-1)[1] = Math.max(merged.get(merged.size()-1)[1],R);
            }
        }
        return merged.toArray(new int[0][2]);
    }
}