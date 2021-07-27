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
        int[] arr = new int[]{4,3,5,6,2,7,1,1,2,2};
        new Demo().sort(arr);

    }

    /**
     * 插入排序
     * @param arr
     */
    public void sort(int[] arr){

        //i --用
        for (int i = 1; i < arr.length; i++) {
            int f = i;
            //int j = f-1;
            while(f>0){
                if(arr[f]<arr[f-1]){
                    int temp = arr[f];
                    arr[f] = arr[f-1];
                    arr[f-1] = temp;
                    //j--;
                    f--;
                }else{
                    f=0;
                }
            }
        }
        System.out.println(Arrays.toString(arr));
    }
}
