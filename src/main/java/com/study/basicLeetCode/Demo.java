package com.study.basicLeetCode;

import java.util.Arrays;

/**
 * @program: study
 * @description:
 * @author: WangChaoLei
 * @create: 2021-06-03 17:40
 **/
public class Demo {

    public static void main(String[] args) {

        //int[] arr = new int[]{-2,1,-3,4,-1,2,1,-5,4};
        //System.out.println(new Demo().maxSubArray2(arr));
        int[] arr = new int[]{1,2,5};
        new Demo().change(5,arr);


    }

    /**
     * leetcode 53. 最大子序和
     * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
     * dp解法自己的思路，比葫芦画瓢
     * 到某个位置结尾的最大值
     * max[i]=max(max[i-1]+muns[i],nums[i])
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {

        //定义dp数组,到某个位置结尾的最大值
        int[] dp = new int[nums.length];
        dp[0]=nums[0];

        //记录总体最大值
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
           dp[i]= Math.max(dp[i-1]+nums[i],nums[i]);
           max=max>dp[i]?max:dp[i];
        }
        return max;
    }


    public int maxSubArray2(int[] nums) {

        //定义二维dp数组 2的数组存放放与不放的结果
        int[][] dp = new int[nums.length][2];
        dp[0][0]=nums[0];
        dp[0][1]=nums[0];

        //记录总体最大值
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            //连接的话就连接前面一个元素放与不放的最大值
            int max1 = Math.max(dp[i - 1][0], dp[i - 1][1]);
            dp[i][0]= Math.max(max1+nums[i],nums[i]);
            dp[i][1] = nums[i];
            int max2 = Math.max(dp[i][0], dp[i][1]);
            max=max>max2?max:max2;
        }
        //print(dp);
        return max;
    }

    public void print(int[][] dp){
        for (int i = 0; i < dp.length; i++) {
            System.out.println(Arrays.toString(dp[i]));
        }
    }


    /**
     * 322. 零钱兑换
     * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
     *  你可以认为每种硬币的数量是无限的。
     *
     *  coins = [1, 2, 5], amount = 11   11 = 5 + 5 + 1
     *
     *  dp[] 存放总金额为i的最少的硬币个数
     *  dp[i] = min(dp[amount-coins[1-n]]+1的最小值)
     *
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange(int[] coins, int amount) {

        //存放总金额为下标的最少硬币个数
        int[] dp = new int[amount+1];
        dp[0]=0;

        //dp数组存值
        for (int i = 1; i < dp.length; i++) {
            int min = amount+1;
            for (int coin : coins) {
                //每个硬币种类
                if(i-coin<0){
                    //说明该币种太大
                    continue;
                }else{
                    int pre = dp[i - coin];
                    if(pre!=-1){
                        min=Math.min(pre,min);
                    }
                }
            }
            dp[i]=min==amount+1?-1:min+1;
        }
        return dp[amount];
    }

    /**
     * 518. 零钱兑换 II 零钱兑换的组合方式
     * amount = 5, coins = [1, 2, 5]
     * 5=1+1+1+1+1
     * 5=2+1+1+1
     * 5=2+2+1
     * 5=5
     * 4种，返回4
     *
     * i 兑换为i的金额的组合方式
     * j 零钱的种类
     * dp[j][i]=dp[j-1][i-j]+dp[j-1][i]
     *
     * @param amount
     * @param coins
     * @return
     */
    public int change(int amount, int[] coins) {

        int[][] dp = new int[coins.length+1][amount+1];
        dp[0][0] = 1;
        for (int i = 1; i < amount + 1; i++) {
            dp[0][i] = 0;
        }

        //金币个数
        for (int j = 1; j < coins.length + 1; j++) {
            //组合数
            for (int i = 0; i < amount + 1; i++) {
                if(i<coins[j-1]){
                    dp[j][i]=dp[j-1][i];
                }else{
                    dp[j][i]= dp[j-1][i]+dp[j-1][i-coins[j-1]];
                }
            }
        }
        return dp[coins.length][amount];
    }


    /**
     * 打卡 leetcode
     * @param s1
     * @param s2
     * @return
     */
    public boolean CheckPermutation(String s1, String s2) {
        if(s1.length()!=s2.length()){
            return false;
        }

        int[] arr = new int[128];


        for (int i = 0; i < s1.length(); i++) {
            arr[s1.charAt(i)]++;
        }

        for (int i = 0; i < s2.length(); i++) {
            arr[s2.charAt(i)]--;

            //小于0 肯定不匹配
            if(arr[s2.charAt(i)]<0){
                return false;
            }
        }
        //因为数量相同，所以没有减为负数就代表成功
        return true;
    }
}
