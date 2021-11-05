package other;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class Solution1 {
    public static void main(String[] args) throws FileNotFoundException {
        String sum = getH1SqlSum(1);
        System.out.println(sum);
    }

    public static String getH1SqlSum(int intensity) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 60; i++) {
            String str;
            if(i<10){
                str = "ISNULL(V0"+i+",0)+";
            }else{
                if(i==59){
                    str = "ISNULL(V"+String.valueOf(i)+",0)";
                }else {
                    str = "ISNULL(V"+String.valueOf(i)+",0)+";
                }
            }
            sb.append(str);
        }
        return sb.toString();
    }

}
