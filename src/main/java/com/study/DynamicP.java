package com.study;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author LinkWang
 * @projectName demo
 * @description: 动态规划
 * @date 2021/2/20 18:59
 */
public class DynamicP {


    // 回溯算法实现。注意：我把输入的变量都定义成了成员变量。
    private int maxW = Integer.MIN_VALUE; // 结果放到maxW中
    private int[] weight = {2, 2, 4, 6, 3};  // 物品重量
    private int n = 5; // 物品个数
    private int w = 9; // 背包承受的最大重量

    public void f(int i, int cw) { // 调用f(0, 0)
        if (cw == w || i == n) { // cw==w表示装满了，i==n表示物品都考察完了
            if (cw > maxW) maxW = cw;
            return;
        }
        f(i + 1, cw); // 选择不装第i个物品
        if (cw + weight[i] <= w) {
            f(i + 1, cw + weight[i]); // 选择装第i个物品
        }
    }


    /**
     * 3. 无重复字符的最长子串
     * 输入: s = "abcabcbb"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {

        // 哈希集合，记录每个字符是否出现过
        Set<Character> occ = new HashSet<Character>();
        int n = s.length();
        // 右指针，初始值为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动
        int rk = -1, ans = 0;
        for (int i = 0; i < n; ++i) {
            if (i != 0) {
                // 左指针向右移动一格，移除一个字符
                occ.remove(s.charAt(i - 1));
            }
            while (rk + 1 < n && !occ.contains(s.charAt(rk + 1))) {
                // 不断地移动右指针
                occ.add(s.charAt(rk + 1));
                ++rk;
            }
            // 第 i 到 rk 个字符是一个极长的无重复字符子串
            ans = Math.max(ans, rk - i + 1);
        }
        return ans;

    }


    /**
     * 3. 无重复字符的最长子串
     * 输入: s = "abcabcbb"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
     *
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring2(String s) {

        //存放最长子串
        HashSet<Character> occ = new HashSet<>();


        int size = s.length();

        char l = 0;
        int r = 0;
        //遍历字符串
        for (int i = 0; i < size; i++) {
            //no contain insert
            if (!occ.contains(s.charAt(i))) {
                occ.add(s.charAt(i));
                r = i;
            } else {
                break;
            }
        }
        int ans = occ.size();

        for (int i = r + 1; i < size; i++) {
            ans = Math.max(ans, occ.size());
            if (!occ.contains(s.charAt(i))) {
                occ.add(s.charAt(i));
            } else {

                occ.remove(s.charAt(l));
                l++;

                if (i != size - 1) {
                    i--;
                }
            }
        }
        ans = Math.max(ans, occ.size());

        return ans;
    }

    public static void main(String[] args) {
        //System.out.println(lengthOfLongestSubstring2("aab"));
        int[] a = new int[]{-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(maxSubArray(a));
    }


    /**
     * 322. 零钱兑换
     * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。
     * 如果没有任何一种硬币组合能组成总金额，返回 -1。
     * 你可以认为每种硬币的数量是无限的。
     * <p>
     * dp写法 自底向上
     * dp数组定义  dp[]
     *
     * @param coins
     * @param amount
     * @return
     */
    static int[] arr = null;
    public static int coinChange(int[] coins, int amount) {
        arr = new int[amount+1];
        Arrays.fill(arr,amount+1);

        //数组下标代表amount为此值时的最小结果
        arr[0] = 0;

        //外层填充dp数组arr
        for (int i = 0; i < arr.length; i++) {
            for (int coin : coins) {
                //如果要求的总金额amount==》i 小于coin 则跳过此循环
                if(i-coin<0) continue;
                //如果满足上面的条件则 arr[i] = 当前i减去coin的数组+1
                arr[i]=Math.min(arr[i], arr[i-coin]+1);
            }
        }
        return arr[amount]== amount+1?-1:arr[amount];

    }

    /**
     * 322. 零钱兑换
     * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。
     * 如果没有任何一种硬币组合能组成总金额，返回 -1。
     * 你可以认为每种硬币的数量是无限的。
     * <p>
     * min(amount) = min(amount-coins[i])+1
     * 回溯算法，统计所有结果集取最小
     * 会出现重复问题，通过加备忘录实现
     *
     * arr = new int[amount+1];
     *         Arrays.fill(arr,-666);
     *         return huisu(coins,amount);
     * @param coins
     * @param amount
     * @return
     */
    public static int huisu(int[] coins, int amount) {

        int temp = Integer.MAX_VALUE;
        if (amount == 0) {
            return 0;
        }
        if (amount < 0) {
            return -1;
        }

        if(arr[amount]!=-666){
            return arr[amount];
        }

        for (int i = 0; i < coins.length; i++) {
            //当前amount的找零个数等于
            int i1 = huisu(coins, amount - coins[i]);
            if (i1 == -1) {
                continue;
            }
            temp = Math.min(i1, temp);
        }
        arr[amount] = temp == Integer.MAX_VALUE ? -1 : temp + 1;
        return arr[amount];
    }


    /**
     * 剑指 Offer 42. 连续子数组的最大和
     * 输入一个整型数组，数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。
     *
     * 要求时间复杂度为O(n)。
     *
     *  
     *
     * 示例1:
     *
     * 输入: nums = [-2,1,-3,4,-1,2,1,-5,4]
     * 输出: 6
     * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
     *
     * 状态定义： 设动态规划列表 dpdp ，dp[i]dp[i] 代表以元素 nums[i]nums[i] 为结尾的连续子数组最大和。
     *
     * 为何定义最大和 dp[i]dp[i] 中必须包含元素 nums[i]nums[i] ：保证 dp[i]dp[i] 递推到 dp[i+1]dp[i+1] 的正确性；如果不包含 nums[i]nums[i] ，递推时则不满足题目的 连续子数组 要求。
     * 转移方程： 若 dp[i-1] \leq 0dp[i−1]≤0 ，说明 dp[i - 1]dp[i−1] 对 dp[i]dp[i] 产生负贡献，即 dp[i-1] + nums[i]dp[i−1]+nums[i] 还不如 nums[i]nums[i] 本身大。
     *
     * 当 dp[i - 1] > 0dp[i−1]>0 时：执行 dp[i] = dp[i-1] + nums[i]dp[i]=dp[i−1]+nums[i] ；
     * 当 dp[i - 1] \leq 0dp[i−1]≤0 时：执行 dp[i] = nums[i]dp[i]=nums[i] ；
     * 初始状态： dp[0] = nums[0]dp[0]=nums[0]，即以 nums[0]nums[0] 结尾的连续子数组最大和为 nums[0]nums[0] 。
     *
     * 返回值： 返回 dpdp 列表中的最大值，代表全局最大值。
     *
     * @param nums
     * @return
     */
    public static int maxSubArray(int[] nums) {
        //定义dp数组，dp[i] 即dp[i]的最大值
        int[] dp = new int[nums.length+1];
        //初始化dp[0] = 0;
        dp[0] = Integer.MIN_VALUE;
        int max = dp[0];
        for (int i = 1; i < dp.length; i++) {
            //当dp[i−1]>0 时：执行 dp[i] = dp[i-1] + nums[i]
            if(dp[i-1]>0){
                dp[i] = dp[i-1]+nums[i-1];
            }else{
                //dp[i−1]≤0 时：执行 dp[i] = nums[i]
                dp[i] = nums[i-1];
            }
            //统计dp数组中的最大值
            max = Math.max(max,dp[i]);
        }
        return max;
    }


    /**
     * 121. 买卖股票的最佳时机
     * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
     *
     * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
     *
     * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
     *
     * dp数组 ==》 i天买入的最大收益
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int len = prices.length;
        // 特殊判断
        if (len < 2) {
            return 0;
        }


        // dp[i][0] 下标为 i 这天结束的时候，不持股，手上拥有的现金数
        // dp[i][1] 下标为 i 这天结束的时候，持股，手上拥有的现金数
        int[][] dp = new int[len][2];

        // 初始化：不持股显然为 0，持股就需要减去第 1 天（下标为 0）的股价
        dp[0][0] = 0;
        dp[0][1] = -prices[0];

        // 从第 2 天开始遍历
        for (int i = 1; i < len; i++) {
            //第i天如果不持股即以前都不持股和以前持股今天卖出  ==》 i-1天不持股和dp[i-1]持股+卖出金额的最大值
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            //第i天持股即以前就持股和今天买入  ==》 dp[i-1]持股，金额不变  买入减去当天金额
            dp[i][1] = Math.max(dp[i - 1][1], -prices[i]);
        }
        return dp[len - 1][0];
    }

    public int maxProfit2(int[] prices) {

        //小于2无法交易
        if(prices.length<2){
            return 0;
        }

        //定义dp数组,1 天数 2 持有状态  结果最佳操作剩余金额
        int[][] dp = new int[prices.length][2];

        //给第一天复初始值 不持有为0 持有为-prices[0]
        dp[0][0] = 0;
        dp[0][1] = -prices[0];

        //计算每一天的持有状态
        for (int i = 1; i < prices.length; i++) {
            //不持有等于前一天不持有和前一天持有今天卖出的最大值
            dp[i][0] = Math.max(dp[i-1][0],dp[i-1][1]+prices[i]);
            //持有 前一天持有 前一天不持有今天买入
            dp[i][1] = Math.max(dp[i-1][1],-prices[i]);
        }

        //最后一天不持有的最大收益
        return dp[prices.length-1][0];



    }
}
