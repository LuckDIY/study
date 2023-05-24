package com.study.dp;

/**
 * 新动态规划练习
 */
public class NewDp {

    public static void main(String[] args) {
        NewDp newDp = new NewDp();
        //System.out.println(newDp.uniquePathsByDp(3, 2));
        int[] weights = {3,2,4,7};
        int[] values = {5,6,3,19};
        System.out.println(newDp.knapsackByDp(weights, values, 11));
    }


    /**
     * 62. 不同路径
     * https://leetcode.cn/problems/unique-paths/
     * @param m
     * @param n
     * @return 暴力递归解题
     *
     *
     */
    public int uniquePaths(int m, int n) {

        int x = 0;
        int y = 0;

        return recursion(x,y,m,n);

    }

    /**
     *
     * @param x 当前位置坐标x
     * @param y 当前位置坐标y
     * @param m 地图x
     * @param n 地图y
     * @return 从xy到 m-1，n-1有多少种走法
     */
    private int recursion(int x, int y, int m, int n) {

        if(x==m-1 && y == n-1){
            return 1;
        }

        int a1 = 0;
        int a2 = 0;
        if(x+1<m){
            a1 = recursion(x + 1, y, m, n);
        }
        if(y+1<n){
            a2 = recursion(x, y + 1, m, n);
        }

        return a1+a2;
    }


    /**
     * dp解法，dp图 62. 不同路径dp.png
     * @param m
     * @param n
     * @return
     */
    public int uniquePathsByDp(int m, int n) {

        int[][] dp = new int[m+1][n+1];
        dp[1][1]=1;

        for (int i = 2; i <=n; i++) {
            dp[1][i] = dp[1][i-1];
        }

        for (int i = 2; i <=m; i++) {
            dp[i][1] = dp[i-1][1];
        }

        for (int i = 2; i <=m; i++) {
            for (int j = 2; j <= n; j++) {
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }

        return dp[m][n];
    }


    /**
     * 背包问题
     * @param weight 重量
     * @param v 价值
     * @param w 总容量
     * @return
     */
    public int knapsack(int[] weight, int[] v, int w){

        if(weight.length!=v.length || w<0){
            return 0;
        }

        return knapsack(weight,v,w,0);

    }

    /**
     * index~weight.length 的物品，remainder的最大容量
     * @param weight
     * @param v
     * @param remainder 剩余容量
     * @param index 物品下标
     * @return
     */
    private int knapsack(int[] weight, int[] v, int remainder, int index) {

        if(remainder<0){
            return -1;
        }

        if(index==weight.length){
            return 0;
        }



        //不放
        int value = knapsack(weight, v, remainder, index + 1);

        //放
        int knapsack = knapsack(weight, v, remainder - weight[index], index + 1);
        int value1 = 0;
        if(knapsack!=-1){
            value1 =v[index] + knapsack;
        }

        return Math.max(value1,value);
    }


    /**
     * 动态规划解法
     * 图： 背包问题dp图.jpeg
     * @param weight
     * @param v
     * @param w
     * @return
     */
    public int knapsackByDp(int[] weight, int[] v, int w){

        if(weight.length!=v.length || w<0){
            return 0;
        }

        //横坐标 背包剩余容量  纵坐标 挑选物品下标
        int[][] dp = new int[w + 1][weight.length+1];

        //默认值就是0，没必要，为了逻辑清晰
        for (int i = 0; i < w; i++) {
            dp[i][weight.length] = 0;
        }

        for (int i=weight.length-1; i >=0 ; i--){

            for (int j = 0; j < w + 1; j++) {
                int max = 0;

                int p1 = dp[j][i + 1];

                int knapsack = j - weight[i] <0? -1 : dp[j - weight[i]][i + 1];
                int p2 = 0;
                if(knapsack!=-1){
                    p2 =v[i] + knapsack;
                }

                dp[j][i] = Math.max(p1,p2);
            }

        }

        return dp[w][0];
    }

}
