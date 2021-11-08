package com.study.sort;

import java.util.Arrays;
import java.util.List;

/**
 * @program: study
 * @description:
 * @author: WangChaoLei
 * @create: 2021-05-12 12:25
 **/
public class Demo {

    public static void main(String[] args) {
        int[] arr = new int[]{4, 3, 5, 6, 2, 7, 1, 1, 2, 2};
        new Demo().fastSort(arr, 0, arr.length - 1);

    }


    /**
     * 插入排序
     *
     * @param arr
     */
    public void sort(int[] arr) {

        //i --用
        for (int i = 1; i < arr.length; i++) {
            int f = i;
            //int j = f-1;
            while (f > 0) {
                if (arr[f] < arr[f - 1]) {
                    int temp = arr[f];
                    arr[f] = arr[f - 1];
                    arr[f - 1] = temp;
                    //j--;
                    f--;
                } else {
                    f = 0;
                }
            }
        }
        System.out.println(Arrays.toString(arr));
    }


    /**
     * 归并排序
     *
     * @param arr   数组
     * @param left  最左下标
     * @param right 最右下标
     */
    public void mergeSort(int[] arr, int left, int right) {

        //小于
        if (left < right) {
            //中间值
            int mid = left + ((right - left) >> 1);

            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);

            merge(arr, left, mid, right);

        }
        //等于或大于不操作

    }

    /**
     * 归并合并数组
     *
     * @param arr
     * @param left
     * @param mid
     * @param right
     */
    private void merge(int[] arr, int left, int mid, int right) {
        int[] temp = new int[arr.length];

        int index = 0;
        int l1 = left, r1 = mid;
        int l2 = mid + 1, r2 = right;

        while (l1 <= r1 && l2 <= r2) {

            if (arr[l1] <= arr[l2]) {
                temp[index++] = arr[l1++];
            } else {
                //大于
                temp[index++] = arr[l2++];
            }
        }

        while (l1 <= r1) {
            temp[index++] = arr[l1++];
        }

        while (l2 <= r2) {
            temp[index++] = arr[l2++];
        }

        index = 0;
        //让临时数组 拷贝 arr
        while (left <= right) {
            System.out.print(left + " ");
            arr[left++] = temp[index++];
        }
        System.out.println(Arrays.toString(arr));

    }


    /**
     * 快速排序
     *
     * @param arr   数组
     * @param left  左下标
     * @param right 右下标
     */
    public void fastSort(int[] arr, int left, int right) {

        if (left >= right) {
            return;
        }

        //左右排序
        int midIndex = fast(arr, left, right);
        System.out.println("mid:"+midIndex+"快排："+Arrays.toString(arr));


        //左边
        fastSort(arr, left, midIndex - 1);

        //右边
        fastSort(arr, midIndex + 1, right);


    }

    //将小的排左边，大的排右边
    private int fast(int[] arr, int left, int right) {

        int midIndex = right;

        System.out.println(midIndex);



        int l = left, r = right;

        int mid = arr[midIndex];

        while (l < r) {

            while(arr[l]<=mid && l<r){
                //左侧小于中间值，位置不变
                l++;
            }

            while(arr[r]>=mid && l<r){
                //右侧大于等于中间值，位置不变
                r--;
            }

            //找到一对，调换位置
            int temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

        }

        //最后一位中间值，和最小r换位置
        arr[midIndex] = arr[r];
        arr[r] = mid;

        return r;
    }
}
