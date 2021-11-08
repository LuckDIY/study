package com.study.javaConcurrent.pool;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @program: study
 * @description: 使用forkJoin实现归并排序
 *  todo 失败
 *
 * @author: WangChaoLei
 * @create: 2021-11-05 17:58
 **/
public class ForkJoinPoolDemo2 {

    private ForkJoinPool pool;

    public ForkJoinPoolDemo2(){
        //空参数创建，使用cpu可用核心数创建固定线程
        pool = new ForkJoinPool();
    }


    public static class MergeDemo extends RecursiveTask<int[]> {

        private int[] numbers;

        //头
        private int start;

        //尾
        private int end;

        @Override
        protected int[] compute() {
            //实现归并排序

            if(start<=end){

                //中间值
                int mid = start + ((end-start)>>1);

                //合并两个有序数组

                MergeDemo leftMergeDemo = new MergeDemo(numbers,start,mid);
                MergeDemo rightMergeDemo = new MergeDemo(numbers,mid+1,end);

                //分叉执行
                leftMergeDemo.fork();
                rightMergeDemo.fork();

                //分叉执行完毕，合并两个有序数组
                leftMergeDemo.join();
                rightMergeDemo.join();

                int index = 0;
                int leftArrStart=start,rightArrStart = mid+1;
                int leftArrEnd = mid,rightArrEnd = end;

                int[] newArr = new int[numbers.length];

                //合并两个有序数组
                while(leftArrStart<=leftArrEnd&&rightArrStart<=rightArrEnd){

                    if (numbers[leftArrStart]<numbers[rightArrStart]) {
                        //左加一
                        newArr[index++]=numbers[leftArrStart++];

                    }else{
                        //小于
                        newArr[index++] = numbers[rightArrStart++];
                    }

                }

                //两个数组都不为空
                while(rightArrStart<=rightArrEnd){
                    newArr[index++] = numbers[rightArrStart++];
                }

                while (leftArrStart<=leftArrEnd){
                    newArr[index++] = numbers[leftArrStart++];
                }

                index = 0;

                while(start<=end){
                    numbers[start++] = newArr[index++];
                }

            }



            return numbers;
        }

        public MergeDemo(int[] numbers,int start,int end){
            this.numbers = numbers;
            this.start = start;
            this.end = end;
        }
    }

    public int[] sort(int[] numbers) {
        return pool.invoke(new MergeDemo(numbers,0,numbers.length-1));
    }

    public static void main(String[] args) {
        int[] arr = new int[]{5, 2, 67, 1, 9, 3};
        int[] sort = new ForkJoinPoolDemo2().sort(arr);
        System.out.println(Arrays.toString(sort));
    }
}
