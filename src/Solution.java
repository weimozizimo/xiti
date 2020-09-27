import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Solution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        String[] split = s.split(",");
        int[] arr = new int[split.length];
        for(int i = 0 ; i < split.length ; i++){
            arr[i] = Integer.valueOf(split[i]);
        }
        int value = scanner.nextInt();
        Arrays.sort(arr);
        System.out.println(dichotomySort(arr,value));
    }

    //二分查找法
    public static int dichotomySort(int[] arr,int value){
        int last = arr.length-1;
        int lo = 0;
        while(lo<=last){
            int mid = lo + (last-lo)/2;
            if(value > arr[mid]) {lo = mid+1;}
            else if(value < arr[mid]) {last = mid -1;}
            else {return mid;}
        }
        return -1;
    }
}