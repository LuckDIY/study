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
}
