package solution.solution1;


import cn.hutool.core.util.ArrayUtil;

/**
 * @author weiyifei
 * @description 在D天内送达包裹的能力，解决方案一，循环判断
 * @date 2021/4/26
 */
public class Solution1 {
    public static void main(String[] args) {
        int i = shipWithinDays(new int[]{1,2,3,1,1}, 4);
        System.out.println(i);
    }
    public static int shipWithinDays(int[] weights, int D) {
        int load;
        int sum = 0;
        for (int i = 0; i < weights.length ; i++){
            sum+=weights[i];
        }
        load = sum/D;
        sum = 0;
        loop1:while(true){
            int dc = 1;
            loop2:for(int i = 0 ; i < weights.length ; i++){
                if(sum+weights[i]<=load){
                    sum+=weights[i];
                }else if (load<weights[i]){ //如果当日货物大于载重，就需要跳出循环，否则会死循环
                    sum=0;
                    load++;
                    continue loop1;
                }else {
                    i--;
                    sum=0;
                    dc++;
                }
            }
            if(dc>D){
                sum=0;
                load++;
            }else {
                break loop1;
            }
        }
        return load;
    }
}
