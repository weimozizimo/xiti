package solution.solution23;

import java.util.OptionalInt;

public class Solution {
    public static void main(String[] args) {
        System.out.println(isSubsequence("abc","\"ahbgdc\""));
    }


    public static boolean isSubsequence(String s, String t) {
        if(s.length()>t.length()){
            return false;
        }
        if(s.length()==0){
            return true;
        }
        if(t.length()==0){
            return false;
        }

        char[] sChar = s.toCharArray();
        char[] tChar = t.toCharArray();
        int tLen = t.length();
        int sLen = s.length();

        int sIndex = 0;
        int cur = sChar[sIndex];
        for (int i = 0; i < tLen; i++) {
            if(cur==tChar[i]){
                sIndex++;
            }
            if(sIndex==sLen){
                return true;
            }
            cur = sChar[sIndex];
        }

        return false;
    }
}
