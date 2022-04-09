package solution.solution21;

public class Solution1 {
    public static void main(String[] args) {
        int[] prices = new int[]{4,2,1,7};
        int i = maxProfit(prices);
        System.out.println(i);
    }

    public static int maxProfit(int[] prices) {
        int buy = prices[0];
        int maxProfit = 0;
        int sell = prices[0];
        for (int price : prices) {
            if(price>sell){
                sell = price;
                maxProfit = sell-buy>maxProfit?sell-buy:maxProfit;
            }else {
                if(price<buy){
                    buy = price;
                    sell = buy;
                }
            }
        }
        return maxProfit;
    }
}
