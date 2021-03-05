package com.study;

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
            if(!occ.contains(s.charAt(i))){
                occ.add(s.charAt(i));
                r=i;
            }else{
                break;
            }
        }
        int ans = occ.size();

        for(int i=r+1; i< size; i++){
            ans = Math.max(ans,occ.size());
            if(!occ.contains(s.charAt(i))){
                occ.add(s.charAt(i));
            }else {

                occ.remove(s.charAt(l));
                l++;

                if(i!=size-1){
                    i--;
                }
            }
        }
        ans = Math.max(ans,occ.size());

        return ans;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring2("aab"));
    }
}
