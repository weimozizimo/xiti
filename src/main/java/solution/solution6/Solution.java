package solution.solution6;

class Solution {

    public static void main(String[] args) {
        int[] numbers = {3,24,50,79,88,150,345};
        int target = 200;
        for (int i : twoSum(numbers, target)) {
            System.out.println(i);
        }
    }

    public static int[] twoSum(int[] numbers, int target) {
        int len = numbers.length;
        int left = 0;
        int right = len-1;

        int[] reNum = new int[2];

        while(true){
            int l = numbers[left];
            int r = numbers[right];
            if(l+r>target){
                right-=1;
            }else if(l+r<target){
                left+=1;
            }else {
                reNum[0] = left+1;
                reNum[1] = right+1;
                break;
            }
        }
        return reNum;
    }


}