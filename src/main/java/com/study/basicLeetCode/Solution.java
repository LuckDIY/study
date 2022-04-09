package com.study.basicLeetCode;

import cn.hutool.core.date.DateUtil;

import java.util.Date;

/**
 * 力扣一些基础的无法分类的算法题
 */
public class Solution {


    /**
     * 11. 盛最多水的容器
     * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0) 。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
     *
     * 此题双指针左右移动
     * @param height
     * @return
     */
    public int maxArea(int[] height) {

        int l = 0;
        int r = height.length-1;
        int max =0;

        while(l<r){
            if(height[l]<height[r]) {
                max = Math.max(height[l]*(r-l),max);
                l++;
            }else{
                max = Math.max(height[r]*(r-l),max);
                r--;
            }
        }
        return max;
    }


    /**
     * URL化。编写一种方法，将字符串中的空格全部替换为%20。假定该字符串尾部有足够的空间存放新增字符，并且知道字符串的“真实”长度。（注：用Java实现的话，请使用字符数组实现，以便直接在数组上操作。）
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/string-to-url-lcci
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param S
     * @param length
     * @return
     */
    public String replaceSpaces(String S, int length) {

        char[] chars=new char[length*3];

        //chars的下标
        int index=0;

        for (int i = 0; i < length; i++) {
            if(S.charAt(i)==' '){
                chars[index++]='%';
                chars[index++]='2';
                chars[index++]='0';
            }else{
                chars[index++]=S.charAt(i);
            }
        }
        return new String(chars,0,index);
    }

    /**
     * 给你一个字符串 s 和一个 长度相同 的整数数组 indices 。
     *
     * 请你重新排列字符串 s ，其中第 i 个字符需要移动到 indices[i] 指示的位置。
     *
     * 返回重新排列后的字符串。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/shuffle-string
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param s
     * @param indices
     * @return
     */
    public String restoreString(String s, int[] indices) {

        char[] chars = new char[s.length()];
        for (int i = 0; i < indices.length; i++) {
            chars[indices[i]]=s.charAt(i);
        }
        return new String(chars);
    }


    /**
     * 10. 正则表达式匹配
     * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
     *
     * '.' 匹配任意单个字符
     * '*' 匹配零个或多个前面的那一个元素
     * 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
     *
     *
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/regular-expression-matching
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch(String s, String p) {

        int m = s.length();
        int n = p.length();

        //f[i][j] s的第i个和p的第j个匹配
        boolean[][] f = new boolean[m + 1][n + 1];

        f[0][0] = true;


        for (int i = 0; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (p.charAt(j - 1) == '*') {
                    f[i][j] = f[i][j - 2];
                    if (matches(s, p, i, j - 1)) {
                        f[i][j] = f[i][j] || f[i - 1][j];
                    }
                } else {
                    if (matches(s, p, i, j)) {
                        f[i][j] = f[i - 1][j - 1];
                    }
                }
            }
        }
        return f[m][n];

    }

    public boolean matches(String s, String p, int i, int j) {
        if (i == 0) {
            return false;
        }
        if (p.charAt(j - 1) == '.') {
            return true;
        }
        return s.charAt(i - 1) == p.charAt(j - 1);
    }


    /**
     * 递归解法
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch2(String s, String p) {

        //如果p等于0，s为0返回true
        if(p.length()==0){
            return s.length()==0;
        }

        //一位一位递归判断
        boolean b = s.length() > 0 && (s.charAt(0)==p.charAt(0) || p.charAt(0)=='.');

        //判断p下一位是不是*

        if(p.length()>1 && p.charAt(1)== '*'){

            //如果p的下一位是*

            //有两条路，1. 省略 p中的 x*  2.使用p[0]和s[0-x] 比较
            //回溯，哪条通走哪条
            /*if(isMatch2(s,p.substring(2))){
                return true;
            }else{
                //首先第一位比较相同
                return b && isMatch2(s.substring(1),p);
            }*/

            //精简后
            return isMatch2(s,p.substring(2)) || ( b && isMatch2(s.substring(1),p));

        }else{
            //下一位不是* 如果第一位为true，两个都比较下一位
            return b && isMatch2(s.substring(1),p.substring(1));
        }
    }


    /**
     * 动态规划
     * 自顶向下
     * 递归
     */
    public boolean isMatch3(String s, String p) {

        Boolean[][] b = new Boolean[s.length()+1][p.length()+1];

        int i = 0 , j = 0;

        return digui(i,j,s,p,b);

    }

    public boolean digui(int i,int j , String s, String p,Boolean[][] b) {

        if(j==p.length()){
            b[i][j] = i == s.length();
            return b[i][j];
        }

        //备忘录，如果有值直接返回，不需要多次计算
        if(b[i][j]!=null){
            return b[i][j];
        }




        //不是最后一位，开始比较

        boolean boo = i<s.length() && (s.charAt(i)==p.charAt(j) || p.charAt(j)=='.');

        if(j+1< p.length() && p.charAt(j+1)=='*'){
            //下一位是*
            //回溯两种

            b[i][j] = digui(i,j+2,s,p,b)|| boo && digui(i+1,j,s,p,b);

            return b[i][j];



        }else{
            //下一位不是*

            b[i][j] = boo && digui(i+1,j+1,s,p,b);
            return b[i][j];
        }


    }




    /**
     * "aaa"
     * "a*a"
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch4(String text, String pattern){

        boolean[][] memo = new boolean[text.length() + 1][pattern.length() + 1];


        memo[text.length()][pattern.length()] = true;

        for (int i = text.length(); i >= 0; i--){
            for (int j = pattern.length() - 1; j >= 0; j--){
                boolean curMatch = (i < text.length() &&
                        (pattern.charAt(j) == text.charAt(i) ||
                                pattern.charAt(j) == '.'));
                if (j + 1 < pattern.length() && pattern.charAt(j+1) == '*'){
                    memo[i][j] = memo[i][j+2] || curMatch && memo[i+1][j];
                } else {
                    memo[i][j] = curMatch && memo[i+1][j+1];
                }
            }
        }
        return memo[0][0];
    }


    public static void main(String[] args) {

    }

}
