167. 两数之和 II - 输入有序数组

给定一个已按照 非递减顺序排列  的整数数组 numbers ，请你从数组中找出两个数满足相加之和等于目标数 target 。

函数应该以长度为 2 的整数数组的形式返回这两个数的下标值。numbers 的下标 从 1 开始计数 ，所以答案数组应当满足 1 <= answer[0] < answer[1] <= numbers.length 。

你可以假设每个输入 只对应唯一的答案 ，而且你 不可以 重复使用相同的元素。
 

示例 1：

输入：numbers = [2,7,11,15], target = 9
输出：[1,2]
解释：2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。

示例 2：

输入：numbers = [2,3,4], target = 6
输出：[1,3]

示例 3：

输入：numbers = [-1,0], target = -1
输出：[1,2]

 

提示：

    2 <= numbers.length <= 3 * 104
    -1000 <= numbers[i] <= 1000
    numbers 按 非递减顺序 排列
    -1000 <= target <= 1000
    仅存在一个有效答案


#### 题解

方法一：二分查找

在数组中找到两个数，使得它们的和等于目标值，可以首先固定第一个数，然后寻找第二个数，第二个数等于目标值减去第一个数的差。利用数组的有序性质，可以通过二分查找的方法寻找第二个数。为了避免重复寻找，在寻找第二个数时，只在第一个数的右侧寻找。
````
class Solution {
    public int[] twoSum(int[] numbers, int target) {
        for (int i = 0; i < numbers.length; ++i) {
            int low = i + 1, high = numbers.length - 1;
            while (low <= high) {
                int mid = (high - low) / 2 + low;
                if (numbers[mid] == target - numbers[i]) {
                    return new int[]{i + 1, mid + 1};
                } else if (numbers[mid] > target - numbers[i]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
        }
        return new int[]{-1, -1};
    }
}
````

复杂度分析

    时间复杂度：O(nlog⁡n)O(n \log n)O(nlogn)，其中 nnn 是数组的长度。需要遍历数组一次确定第一个数，时间复杂度是 O(n)O(n)O(n)，寻找第二个数使用二分查找，时间复杂度是 O(log⁡n)O(\log n)O(logn)，因此总时间复杂度是 O(nlog⁡n)O(n \log n)O(nlogn)。

    空间复杂度：O(1)O(1)O(1)。

方法二：双指针

初始时两个指针分别指向第一个元素位置和最后一个元素的位置。每次计算两个指针指向的两个元素之和，并和目标值比较。如果两个元素之和等于目标值，则发现了唯一解。如果两个元素之和小于目标值，则将左侧指针右移一位。如果两个元素之和大于目标值，则将右侧指针左移一位。移动指针之后，重复上述操作，直到找到答案。

使用双指针的实质是缩小查找范围。那么会不会把可能的解过滤掉？答案是不会。假设 numbers[i]+numbers[j]=target\textit{numbers}[i]+\textit{numbers}[j]=\textit{target}numbers[i]+numbers[j]=target 是唯一解，其中 0≤i<j≤numbers.length−10 \leq i<j \leq \textit{numbers}.\textit{length}-10≤i<j≤numbers.length−1。初始时两个指针分别指向下标 000 和下标 numbers.length−1\textit{numbers}.\textit{length}-1numbers.length−1，左指针指向的下标小于或等于 iii，右指针指向的下标大于或等于 jjj。除非初始时左指针和右指针已经位于下标 iii 和 jjj，否则一定是左指针先到达下标 iii 的位置或者右指针先到达下标 jjj 的位置。

如果左指针先到达下标 iii 的位置，此时右指针还在下标 jjj 的右侧，sum>target\textit{sum}>\textit{target}sum>target，因此一定是右指针左移，左指针不可能移到 iii 的右侧。

如果右指针先到达下标 jjj 的位置，此时左指针还在下标 iii 的左侧，sum<target\textit{sum}<\textit{target}sum<target，因此一定是左指针右移，右指针不可能移到 jjj 的左侧。

由此可见，在整个移动过程中，左指针不可能移到 iii 的右侧，右指针不可能移到 jjj 的左侧，因此不会把可能的解过滤掉。由于题目确保有唯一的答案，因此使用双指针一定可以找到答案。

````
class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int low = 0, high = numbers.length - 1;
        while (low < high) {
            int sum = numbers[low] + numbers[high];
            if (sum == target) {
                return new int[]{low + 1, high + 1};
            } else if (sum < target) {
                ++low;
            } else {
                --high;
            }
        }
        return new int[]{-1, -1};
    }
}
````
复杂度分析

    时间复杂度：O(n)O(n)O(n)，其中 nnn 是数组的长度。两个指针移动的总次数最多为 nnn 次。

    空间复杂度：O(1)O(1)O(1)。
