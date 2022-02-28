package solution.solution17;

import org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy;

import java.util.ArrayList;
import java.util.List;

/**
*@Description 动态规划解法
*@Author weiyifei
*@date 2022/2/12
*/
public class Solution2 {



    public static void main(String[] args) {
        String str = "10+2-2*5";
        List<Integer> list = diffWaysToCompute(str);
        list.forEach(System.out::println);
    }

    public static List<Integer> diffWaysToCompute(String expression) {


        //将数字和操作符分别按顺序存储在两个list中
        List<Character> chars = new ArrayList<>();
        List<Integer> nums = new ArrayList<>();
        int index = 0;
        int num = 0;
        while (index<expression.length()){
            if(isOperation(expression.charAt(index))){
                chars.add(expression.charAt(index));
                nums.add(num);
                num = 0;
            }else {
                num = num*10 + expression.charAt(index)-'0';
            }
            index++;
        }
        nums.add(num);

        int N = nums.size();//数字的个数
        List<Integer>[][] dp = new ArrayList[N][N];
        for(int i = 0 ; i < N ; i++){
            List<Integer> result = new ArrayList<>();
            result.add(nums.get(i));
            dp[i][i] = result;
        }
        for(int n = 2 ; n <= N ; n++){
            //开始下标
            for(int i = 0 ; i <  N ;i++){
                //结束下标
                int j = i+n -1;
                if(j<=N){
                    break;
                }
                List<Integer> result = new ArrayList<>();
                //分成i~s和s+1~j两部分
                for(int s = i ; s < j ; s++){
                    List<Integer> result1 = dp[i][s];
                    List<Integer> result2 = dp[s+1][j];
                    for(int x = 0; x<result1.size();x++){
                        for (int y = 0; y < result2.size(); y++) {
                            char op = chars.get(s);
                            result.add(cal(result1.get(x),op,result.get(y)));
                        }
                    }
                }
                dp[i][j] =result;
            }
        }

        return dp[0][N-1];
    }

    public static int cal(int num1, char opt, int num2) {
        switch (opt) {
            case '+':
                return num1 + num2;
            case '-':
                return num1 - num2;
            case '*':
                return num1 * num2;
        }
        return -1;
    }

    private static boolean isOperation(char c) {
        return c == '+' || c == '-' || c == '*';
    }

}
