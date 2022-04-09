package com.study.dp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @program: study
 * @description: dp基础 动态规划: 通过上次结果动态的推断后面的结果
 * @author: WangChaoLei
 * @create: 2021-05-19 14:47
 **/
public class DpBasis {

    public static void main(String[] args) {
        int[] weight = new int[]{2,2,4,6,4};

        System.out.println(new DpBasis().knapsack3(weight,5,13));
    }

    /**
     * 背包问题， 石头不可分割，背包容量x，最大能放进多少
     * 1。回溯解法
     * @param weight 每个石头
     * @param n n个石头
     * @param w 背包可承载重量
     * @return
     */
    public int knapsack1(int[] weight, int n, int w){

        int max = 0;

        int i = 0;
        //回溯，要么装，要么不装

        return zhuang(weight,n,i,w,0,max);

    }

    /**
     * 装石头
     * @param weight 石头
     * @param n 总石头数
     * @param i 该放第几个石头
     * @param w 背包最大承重
     * @param cw 当前重量
     * @param max 当前最大重量
     */
    private Integer zhuang(int[] weight,int n, int i, int w, int cw,int max) {

        if(i==n || cw==w){
            //谁大返回谁
            return Math.max(cw,max);
        }

        //不放
        max = zhuang(weight,n,i+1,w,cw,max);

        //放

        if(cw+weight[i]<=w){
            //代表可以放

            max = zhuang(weight, n, i + 1, w, cw + weight[i], max);
        }

        return max;

    }


    /**
     * 背包问题， 石头不可分割，背包容量x，最大能放进多少
     * 2。动态规划写法
     * @param weight 每个石头
     * @param n n个石头
     * @param w 背包可承载重量
     * @return
     */
    public int knapsack2(int[] weight, int n, int w){

        //创建二维数组,初始值为false  1.第几次放石头  2。放完石头后背包的重量
        boolean[][] dp = new boolean[n][w+1];

        //初始值赋值,第一次放入
        dp[0][0] = true;

        if(weight[0]<=w){
            dp[0][weight[0]] = true;
        }

        for (int i = 1; i < n; i++) {

            //继续放与不放
            for (int j = 0; j <= w; j++) {
                if (dp[i-1][j]) {
                    //不放
                    dp[i][j] = true;

                    //放
                    if (j+weight[i]<=w) {
                        dp[i][j+weight[i]] = true;
                    }
                }
            }
        }

        for (int i = w; i >= 0; i--) {
            if (dp[n-1][i]) {
                return i;
            }
        }
        return 0;
    }


    /**
     * 背包问题， 石头不可分割，背包容量x，最大能放进多少
     * 3。动态规划空间复杂度优化
     * 由于只有最后一行有用，所以二维数组改为一位数组
     * @param weight 每个石头
     * @param n n个石头
     * @param w 背包可承载重量
     * @return
     */
    public int knapsack3(int[] weight, int n, int w){

        //由于只有最后一行有用，所以二维数组改为一位数组
        boolean[] dp = new boolean[w+1];

        //初始值赋值,第一次放入
        dp[0] = true;

        if(weight[0]<=w){
            dp[weight[0]] = true;
        }

        for (int i = 1; i < n; i++) {

            //倒循化 避免重复赋值
            for (int j = w-weight[i] ; j >= 0; j--) {

                //不放就是原来的值，无需改变
                //放
                if(dp[j]){
                    dp[j+weight[i]] = true;
                }
            }
        }


        for (int i = w; i >= 0; i--) {
            if (dp[i]) {
                return i;
            }
        }
        return 0;
    }


    /**
     * 背包问题， 石头不可分割，背包容量x，最大能放进多少
     *
     * 动态规划解法
     * dp[i][j] i第几次抉择放与不放(n)   j 抉择后背包的当前重量(w)
     * @param weight 物品重量
     * @param n 物品个数
     * @param w 背包可承载重量
     * @return
     */
    public int knapsack(int[] weight, int n, int w) {
        boolean[][] states = new boolean[n][w+1]; // 默认值false
        states[0][0] = true;  // 第一行的数据要特殊处理，可以利用哨兵优化
        if (weight[0] <= w) {
            states[0][weight[0]] = true;
        }
        for (int i = 1; i < n; ++i) { // 动态规划状态转移

            //遍历
            // 不把第i个物品放入背包
            for (int j = 0; j <= w; ++j) {
                if (states[i-1][j] == true) states[i][j] = states[i-1][j];
            }

            //把第i个物品放入背包   j<=w-weight[i] 太妙了
            for (int j = 0; j <= w-weight[i]; ++j) {
                if (states[i-1][j]==true) states[i][j+weight[i]] = true;
            }
        }

        for (int i = w; i >= 0; --i) { // 输出结果
            if (states[n-1][i] == true) return i;
        }
        return 0;
    }

    /**
     * 一维数组优化 ，只存储存放的结果，不再存储每次放与不放的状态
     * 不放的结果已经存在不做记录
     * @param weight
     * @param n
     * @param w
     * @return
     */
    public int knapsack0(int[] weight, int n, int w) {
        boolean[] states = new boolean[w+1]; // 默认值false
        states[0] = true;  // 第一行的数据要特殊处理，可以利用哨兵优化
        if (weight[0] <= w) {
            states[weight[0]] = true;
        }


        for (int i = 1; i < n; ++i) { // 动态规划状态转移

            //只考虑放的情况，因为不放的结果已经存在
            //把第i个物品放入背包   j<=w-weight[i] 太妙了
            //倒着遍历的原因是防止正序遍历添加的结果重复添加，导致错误
            for (int j = w-weight[i]; j >=0; --j) {
                if (states[j]==true) states[j+weight[i]] = true;
            }
        }
        for (int i = w; i >= 0; --i) { // 输出结果
            if (states[i] == true) return i;
        }
        return 0;
    }
}
