package solution.solution17;

import cn.hutool.core.lang.copier.Copier;
import cn.hutool.core.util.ArrayUtil;
import org.omg.CORBA.INTERNAL;
import org.springframework.util.NumberUtils;
import other.FindTest;

import java.util.*;

/**
 * @Description 递归分治法，使用递归的方式去计算，将计算符两边分开计算，拆分至只有数字，然后将两边计算的结果集进行
 * 笛卡尔积计算获得所有结果。
 * 使用memoization的方式缓存已有结果，来避免重复计算已经计算过的结果（空间换时间）
 * @Author weiyifei
 * @date 2022/2/12
 */
public class Solution {

    public static void main(String[] args) {
        String str = "2+3-1*4+3";

        List<Integer> list = diffWaysToCompute(str);
        list.forEach(System.out::println);
    }

    static Map<String, List<Integer>> map = new HashMap<>();

    public static List<Integer> diffWaysToCompute(String expression) {

        if (expression.length() == 0) {
            return new ArrayList<>();
        }

        if (map.containsKey(expression)) {
            return map.get(expression);
        }
        List<Integer> result = new ArrayList<>();
        int num = 0;

        //如果字符为全数字
        int index = 0;
        while (index < expression.length() && !isOperation(expression.charAt(index))) {
            //每向后移动一位前面的数字升一位，需要乘以10，通过当前字符的ascii码减去字符0的ascii码可以将字符转换为数字
            num = num * 10 + expression.charAt(index) - '0';
            index++;
        }

        if (index == expression.length()) {
            result.add(num);
            return result;
        }

        for (int i = 0; i < expression.length(); i++) {
            //通过运算符将字符串分割
            if (isOperation(expression.charAt(i))) {
                List<Integer> result1 = diffWaysToCompute(expression.substring(0, i));
                List<Integer> result2 = diffWaysToCompute(expression.substring(i + 1));
                for (int j = 0; j < result1.size(); j++) {
                    for (int k = 0; k < result2.size(); k++) {
                        result.add(cal(result1.get(j), expression.charAt(i), result2.get(k)));
                    }
                }
            }
        }

        map.put(expression, result);
        return result;
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
