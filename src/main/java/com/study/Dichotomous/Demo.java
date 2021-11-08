package com.study.Dichotomous;

import java.util.ArrayList;

/**
 * @program: study
 * @description: 王争二分算法学习
 * @author: WangChaoLei
 * @create: 2021-10-21 17:53
 **/
public class Demo {



    /**
     * 二分基础代码
     * @param nums 有序数组
     * @param high 查询的最大下标
     * @param value 查询的值
     * @return 返回此值的下标
     */
    public int baseMidSearch(int[] nums,int high,int value){

        int low = 0;

        while(low<=high){
            //中间值
            int mid = (low+high)/2;

            if(nums[mid]==value){
                return mid;
            }
            if(nums[mid]>value){
                //当前值大于指定值
                high = mid-1;
            }else{
                //当前值 小于 指定值
                low = mid+1;
            }
        }
        return -1;
    }


    /**
     * 二分变种
     *  有序数组种
     *  查询第一个value值
     * @param a
     * @param n
     * @param value
     * @return
     */
    public int firstSearch(int[] a, int n, int value) {
        int low = 0;
        int high = n - 1;

        while (low <= high) {

            //中间值，最大-最小 除2 +low
            int mid = low + ((high - low) >> 1);

            //[1,2,3,3,3] 1 3==3 high = 1 low = 0  2<3 low = 2 high = 1 结束循环，return 2
            if (a[mid] >= value) {
                //a[mid]大于或等于value

                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        if (low < n && a[low]==value) return low;


        else return -1;
    }


    /**
     * 变种自己实现
     * @param a
     * @param n
     * @param value
     * @return
     */
    public int firstSearch2(int[] a, int n, int value) {
        int low = 0;
        int high = n-1;

        while(low <= high){

            int mid = low + ((high-low)>>1);

            if(a[mid]<value){
                low = mid + 1;
            }else if(a[mid]>value){
                high = mid -1;
            }else{
                //等于
                //判断是否是第一个
                if (mid==0||a[mid-1]!=value) {
                    return mid;
                }else{
                    //不是第一个
                    high = mid -1;
                }
            }

        }
        return -1;
    }


    /**
     * 二分变种
     *   查询最后一个等于value的值
     *
     * @param a
     * @param n
     * @param value
     * @return
     */
    public int lastSearch(int[] a, int n, int value) {
        int low = 0;
        int high = n-1;

        while(low<=high){

            int mid = low + ((high-low)>>1);

            if(a[mid]<value){
                low = mid +1;
            }else if(a[mid]>value){
                high = mid -1;
            }else{
                //等于，判断是否是最后一个

                if (mid==n-1||a[mid+1]!=value) {
                    return mid;
                }else{
                    low = mid +1;
                }
            }

        }
        return -1;
    }

    /**
     * 二分变种3 查询第一个大于或等于的值
     * @param a
     * @param n
     * @param value
     * @return
     */
    public static int greaterEqualSearch(int[] a, int n, int value) {
        int low = 0;
        int high = n-1;

        while(low<=high){

            int mid = low + ((high-low)>>1);

            if(a[mid]<value){
                low = mid +1;
            }else if(a[mid]>=value){

                high = mid -1;
            }

        }

        if(low<n && a[low]>=value) return low;
        else return -1;
    }

    /**
     * 二分变种3 查询第一个大于或等于的值
     * @param a
     * @param n
     * @param value
     * @return
     */
    public static int greaterEqualSearch2(int[] a, int n, int value) {
        int low = 0;
        int high = n-1;

        while(low<=high) {

            int mid = low + ((high - low) >> 1);

            if (a[mid] < value) {
                low = mid + 1;
            } else {
                //大于或大于等于
                if (mid == 0 || a[mid - 1] < value) {
                    return mid;
                } else {
                    high = mid - 1;
                }
            }
        }
        return -1;
    }





        public static void main(String[] args) {
            int[] arr = new int[]{1,2,2,4};
            System.out.println(greaterEqualSearch(arr, 4, 3));
        }

}
