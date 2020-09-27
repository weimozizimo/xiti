import com.sun.xml.internal.ws.api.client.WSPortInfo;
import org.omg.CORBA.INTERNAL;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class WildcardTypeTest {


    public static void main(String[] args) {
        int[] arr = new int[]{-1,-1,-1,-1,-1,0};
        int i = pivotIndex(arr);
        System.out.println(i);
    }

    public static int pivotIndex(int[] nums) {
        int result = -1;
        int leftSum = 0;
        int rightSum = 0;
        int lIndex = 0;
        int rIndex = nums.length - 1;
        if(nums.length==1){
            return 0;
        }else if(nums.length==0){
            return -1;
        }else {
            do {
                if (leftSum == 0 && rightSum == 0) {
                    leftSum += nums[lIndex++];
                    rightSum += nums[rIndex--];
                } else {
                    if (leftSum != rightSum) {
                        int cha = leftSum-rightSum;
                        int lAdd = leftSum+nums[lIndex+1]-rightSum;
                        int rAdd = leftSum-(rightSum+nums[rIndex-1]);
                        if(lAdd<rAdd){
                            rightSum += nums[rIndex--];
                        }else {
                            leftSum += nums[lIndex++];
                        }
                        continue;
                    }
                }
                if(leftSum==rightSum){
                    if(lIndex==rIndex){
                        result = lIndex;
                    }else {
                        leftSum += nums[lIndex++];
                        rightSum += nums[rIndex--];
                    }
                }
            }while (lIndex<rIndex);
            return result;
        }
    }
}