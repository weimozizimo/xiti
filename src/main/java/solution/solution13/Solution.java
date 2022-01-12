package solution.solution13;

import java.util.HashMap;
import java.util.Map;

/**
*@Description
*@Author weiyifei
*@date 2022/1/5
*/
public class Solution {

    public static void main(String[] args) {
        System.out.println(romanToInt("III"));
    }


    public static int romanToInt(String s) {
        int num = 0;
        int[] arr = new int[91];
        arr['I']=1;
        arr['V']=5;
        arr['X']=10;
        arr['L']=50;
        arr['C']=100;
        arr['D']=500;
        arr['M']=1000;

        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            int nc = arr[c];
            if(i<chars.length-1){
                if(c=='I'&&(chars[i+1]=='V'||chars[i+1]=='X')){
                    num += arr[chars[i+1]]-nc;
                    i++;
                }else
                if(c=='X'&&(chars[i+1]=='L'||chars[i+1]=='C')){
                    num += arr[chars[i+1]]-nc;
                    i++;
                }else
                if(c=='C'&&(chars[i+1]=='D'||chars[i+1]=='M')){
                    num += arr[chars[i+1]]-nc;
                    i++;
                }else {
                    num+=nc;
                }
            }else {
                num+=nc;
            }
        }
        return num;
    }
}
