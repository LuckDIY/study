package com.study.basicLeetCode;

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
}
