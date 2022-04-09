package solution.solution26;

import java.util.Arrays;

public class Solution1 {
    public static void main(String[] args) {
        System.out.println(multiply("909","132"));
    }

    public static String multiply(String num1, String num2) {
        int[] res = new int[num1.length()+num2.length()];

        if("0".equals(num1)||"0".equals(num2)){
            return "0";
        }

        for (int i = num1.length()-1; i >= 0 ; i--) {
            int n1 = num1.charAt(i)-'0';
            for (int j = num2.length()-1; j >=0; j--) {
                int n2 = num2.charAt(j)-'0';
                int sum = res[i+j+1]+n1*n2;
                res[i+j+1] = sum%10;
                res[i+j] += sum/10;
            }
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < res.length; i++) {
            if(res[0]==0&&i==0) continue;
            builder.append(res[i]);
        }
        return builder.toString();
    }


}
