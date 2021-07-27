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



    /**
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
    public int knapsack2(int[] weight, int n, int w) {
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
