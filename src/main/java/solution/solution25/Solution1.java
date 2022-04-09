package solution.solution25;

import cn.hutool.core.util.ArrayUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
*@Description
*@Author weiyifei
*@date 2022/2/20
*/
public class Solution1 {
    public static void main(String[] args) {
        List<String> strings = generateParenthesis(4);
        for (String string : strings) {
            System.out.println(string);
        }
    }

    static List<String>[] cache = new ArrayList[100];

    public static List<String> generateParenthesis(int n) {
        return generate(n);
    }

    public static List<String> generate(int n ){
        if(cache[n]!=null){
            return cache[n];
        }
        List<String> ans = new ArrayList<>();
        if(n==0){
            ans.add("");
        }else {
            for (int c = 0 ; c < n ; c++){
                for (String left:generate(c)){
                    for (String right:generate(n-1-c)){
                        ans.add("("+left+")"+right);
                    }
                }
            }
        }
        cache[n] = ans;
        return ans;
    }

}
