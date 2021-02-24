package com.study.greedy;

/**
 * @author LinkWang
 * @projectName demo
 * @description: TODO 贪心算法练习
 * @date 2021/2/20 15:55
 */
public class Greedy {

    /**
     * 分糖果
     * 我们有 m 个糖果和 n 个孩子。我们现在要把糖果分给这些孩子吃，但是糖果少，孩子多（m<n)
     * 每个糖果的大小不等，这 m 个糖果的大小分别是 s1，s2，s3，……，sm。
     * 除此之外，每个孩子对糖果大小的需求也是不一样的，只有糖果的大小大于等于孩子的对糖果大小的需求的时候，孩子才得到满足。
     * 假设这 n 个孩子对糖果大小的需求分别是 g1，g2，g3，……，gn。
     * @param candyType
     * @return
     */
    public int distributeCandies(int[] candyType) {

        return 0;
    }

    /**
     * 860. 柠檬水找零
     * 硬币找零，手里没硬币，
     * @param bills 排队用户，手里的钱
     * @return
     */
    public boolean lemonadeChange(int[] bills) {
        if (bills[0]!=5) {
            return false;
        }

        //存钱罐
        int five =0;
        int ten = 0;

        for (int bill : bills) {
            if(bill==5){
                five++;
            }
            if(bill==10){
                five--;
                ten++;
            }
            if(bill==20){
                if(ten>0){
                    ten--;
                    five--;
                }else{
                    five = five-3;
                }
            }
            if(five<0){
                return false;
            }
        }
        return true;
    }


    /**
     * 122. 买卖股票的最佳时机 II
     给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
     设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
     注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {

        int count = 0;
        for(int i=1;i<prices.length;i++){
            if(prices[i]>prices[i-1]){
                count+=prices[i]-prices[i-1];
            }
        }
        return count;
    }


}
