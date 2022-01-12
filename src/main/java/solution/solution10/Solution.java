package solution.solution10;

/**
*@Description  使用二分查找，将矩阵转化为数组即可
*@Author weiyifei
*@date 2022/1/5
*/
public class Solution {

    public static void main(String[] args) {
        int[][] nums = {{1,2},{3,4}};
        System.out.println(searchMatrix(nums, 4));

    }


    public static boolean searchMatrix(int[][] matrix, int target) {
        if(matrix.length==0){
            return false;
        }
        if(matrix.length==1&&matrix[0].length==1){
            return matrix[0][0]==target?true:false;
        }

        //获取矩阵总元素数量
        int length = matrix[0].length*matrix.length;
        int left = 0;
        int right = length-1;
        int xlen = matrix[0].length;

        while (left<=right){
            int mid =  left + (right-left)/2;
            int[] mLoc = getRealLoc(mid,xlen);
            int m = matrix[mLoc[0]][mLoc[1]];
            if(m<target){
                left = mid+1;
            }else if(m>target){
                right = mid - 1;
            }else {
                return true;
            }
        }

        return false;

    }

    public static int[] getRealLoc(int index,int xlen){
        int[] loc = new int[2];

        loc[0] = index/xlen;
        loc[1] = index%xlen;

        return loc;
    }
}
