package solution.solution20;

import java.util.ArrayList;
import java.util.List;

public class Solution1 {

    public static void main(String[] args) {
        List<List<Integer>> lists = generate(3);
        for (List<Integer> list : lists) {
            list.forEach(System.out::print);
            System.out.println("\n");
        }
    }


    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> dp = new ArrayList<>();

        for (int i = 0; i < numRows; i++) {
            dp.add(new ArrayList<>(i));
        }

        dp.get(0).add(1);
        List<Integer> list, pre;
        for (int i = 1; i < numRows; i++) {
            list = dp.get(i);
            pre = dp.get(i - 1);
            for (int j = 0; j <= i; j++) {
                if(j==0||j==i){
                    list.add(1);
                }else {
                    list.add( pre.get(j - 1) + pre.get(j));
                }
            }
        }

        return dp;
    }
}
