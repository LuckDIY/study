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
        //int[] arr = new int[]{4, 3, 5, 6, 2, 7, 1, 1, 2, 2};
        int[] arr = new int[]{-74,48,-20,2,10,-84,-5,-9,11,-24,-91,2,-71,64,63,80,28,-30,-58,-11,-44,-87,-22,54,-74,-10,-55,-28,-46,29,10,50,-72,34,26,25,8,51,13,30,35,-8,50,65,-6,16,-2,21,-78,35,-13,14,23,-3,26,-90,86,25,-56,91,-13,92,-25,37,57,-20,-69,98,95,45,47,29,86,-28,73,-44,-46,65,-84,-96,-24,-12,72,-68,93,57,92,52,-45,-2,85,-63,56,55,12,-85,77,-39};
        //new Demo().fastSort(arr, 0, arr.length - 1);

        new Demo().heapSort(arr);
        System.out.println(Arrays.toString(arr));
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


    /**
     * 堆排序练习
     * @param nums
     */
    public void heapSort(int[] nums){

        //构建大顶堆
        buildMaxHeap(nums,0,nums.length);

        for (int i = nums.length-1;i>=0;i--){
            //第一个已经是最大了,放到尾部，不再参与构建
            swap(nums,0,i);

            //顶部元素下沉,最大值就是他以前的位置
            nodeSink(nums,0,i);
        }

    }

    //建立大顶堆，通过数组构建
    //最后一个非叶子节点下标: length/2-1
    //对于完全二叉树中的第 i 个数，它的左子节点下标：left = 2i + 1
    public void buildMaxHeap(int[] nums,int start,int end) {

        //最后一个非叶子节点下标: length/2-1
        //从最后一个往前构建大顶堆
        for (int i = end / 2 - 1; i >=start; i--) {
            nodeSink(nums,i, end);
        }
    }

    //节点下沉
    private void nodeSink(int[] nums, int current, int end) {

        //记录最大下标，便于交换
        int maxIndex = current;

        int leftIndex = 2* current +1;
        //右不一定存在，有可能越界
        int rightIndex = leftIndex+1;

        if(leftIndex< end && nums[leftIndex] > nums[maxIndex]){
            maxIndex = leftIndex;
        }

        if(rightIndex< end && nums[rightIndex] > nums[maxIndex]){
            maxIndex = rightIndex;
        }

        //如果最大值不是父亲结点，则交换位置
        if(maxIndex!= current){
            swap(nums, current,maxIndex);
            //更换位置后再比较当前元素是否可以继续下沉
            nodeSink(nums,maxIndex,end);
        }
    }

    public void swap(int[] nums,int i,int maxIndex){
        int temp = nums[i];
        nums[i] = nums[maxIndex];
        nums[maxIndex] = temp;
    }
}
