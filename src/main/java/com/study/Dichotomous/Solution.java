package com.study.Dichotomous;

import com.alibaba.fastjson.serializer.EnumSerializer;

/**
 * 二分算法题练习
 */
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int length1 = nums1.length, length2 = nums2.length;
        // nums1 and nums2 sum size
        int totalLength = length1 + length2;

        if (totalLength % 2 == 1) {
            int midIndex = totalLength / 2;
            double median = getKthElement(nums1, nums2, midIndex + 1);
            return median;
        } else {
            int midIndex1 = totalLength / 2 - 1, midIndex2 = totalLength / 2;
            double median = (getKthElement(nums1, nums2, midIndex1 + 1) + getKthElement(nums1, nums2, midIndex2 + 1)) / 2.0;
            return median;
        }
    }

    /**
     * 求两个数组中 第k小的数，从1开始
     * @param nums1
     * @param nums2
     * @param k
     * @return
     */
    public int getKthElement(int[] nums1, int[] nums2, int k) {
        /* 主要思路：要找到第 k (k>1) 小的元素，那么就取 pivot1 = nums1[k/2-1] 和 pivot2 = nums2[k/2-1] 进行比较
         * 这里的 "/" 表示整除
         * nums1 中小于等于 pivot1 的元素有 nums1[0 .. k/2-2] 共计 k/2-1 个
         * nums2 中小于等于 pivot2 的元素有 nums2[0 .. k/2-2] 共计 k/2-1 个
         * todo k/2-1+k/2+1=k
         * 取 pivot = min(pivot1, pivot2)，两个数组中小于等于 pivot 的元素共计不会超过 (k/2-1) + (k/2-1) <= k-2 个
         * 这样 pivot 本身最大也只能是第 k-1 小的元素
         * 如果 pivot = pivot1，那么 nums1[0 .. k/2-1] 都不可能是第 k 小的元素。把这些元素全部 "删除"，剩下的作为新的 nums1 数组
         * 如果 pivot = pivot2，那么 nums2[0 .. k/2-1] 都不可能是第 k 小的元素。把这些元素全部 "删除"，剩下的作为新的 nums2 数组
         * 由于我们 "删除" 了一些元素（这些元素都比第 k 小的元素要小），因此需要修改 k 的值，减去删除的数的个数
         */

        int length1 = nums1.length, length2 = nums2.length;
        int index1 = 0, index2 = 0;
        int kthElement = 0;

        while (true) {
            // 边界情况
            if (index1 == length1) {
                return nums2[index2 + k - 1];
            }
            if (index2 == length2) {
                return nums1[index1 + k - 1];
            }
            if (k == 1) {
                return Math.min(nums1[index1], nums2[index2]);
            }
            
            // 正常情况
            int half = k / 2;
            int newIndex1 = Math.min(index1 + half, length1) - 1;
            int newIndex2 = Math.min(index2 + half, length2) - 1;
            int pivot1 = nums1[newIndex1], pivot2 = nums2[newIndex2];
            if (pivot1 <= pivot2) {
                k -= (newIndex1 - index1 + 1);
                index1 = newIndex1 + 1;
            } else {
                k -= (newIndex2 - index2 + 1);
                index2 = newIndex2 + 1;
            }
        }
    }

    /**
     * 求两个数组中第k小的数，从1开始
     * 二分法
     * @param nums1
     * @param nums2
     * @param k 慢慢递减，到结果0
     * @return
     */
    public int arrMin(int[] nums1,int[] nums2,int k){

        //两个数组的长度
        int length1 = nums1.length, length2 = nums2.length;

        //比较起始下标
        int index1 = 0, index2 = 0;

        //总长度
        int total = length1+length2;

        while(true){
            //如果 下标等于长度，说明该数组无可比较的
            if(index1==length1){
                return nums2[index2+k-1];
            }

            if(index2==length2){
                return nums1[index1+k-1];
            }

            //如果k为1，取第一个
            if(k==1){
                return Math.min(nums1[index1],nums2[index2]);
            }

            int temp = k/2;

            //第一个数组比较下标
            int comp1 = Math.min(index1+temp,length1)-1;
            int comp2 = Math.min(index2+temp,length2)-1;

            if(nums1[comp1]<nums2[comp2]){
                //上面对k等于1已经做了判断，这里对k进行 减
                index1 = comp1+1;

                k -= (comp1 - index1 + 1);

            }else{
                index2 = comp2+1;

                k -= (comp2 - index2 + 1);

            }


        }

    }


    /**
     * 33. 搜索旋转排序数组
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {

        int length = nums.length;
        //找到循环点
        int k = 0;
        for (int i = 0; i < nums.length-1; i++) {
            if (nums[i]>nums[i+1]) {
                k = i+1;
                break;
            }
        }

        //nums分为两部分 0 k-1   and k  length-

        if(k==0){
            return baseMidSearch(nums,0,length-1,target);
        }

        //判断target在哪部分
        if(nums[0]<=target && nums[k-1]>=target){
            return baseMidSearch(nums,0,k-1,target);
        }

        if(nums[k]<=target&& nums[length-1]>=target){
            return baseMidSearch(nums,k,length-1,target);
        }

        return -1;


    }

    public static int baseMidSearch(int[] nums,int low,int high,int value){

        while(low<=high){
            //中间值
            int mid = low + ((high - low) >> 1);

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
     * 33. 搜索旋转排序数组 2 直接二分
     * @param nums
     * @param target
     * @return
     */
    public int search2(int[] nums, int target) {

        int low = 0;
        int high = nums.length-1;

        while(low <= high){

            //中间值
            int mid = low + ((high-low)>>1);

            if (nums[mid]==target) {
                return mid;
            }

            //判断哪边是有序的
            if(nums[low]<=nums[mid]){
                //low mid 有序 左边有序

                //判断目标值是否在左边,low <= target < mid
                if(target>=nums[low]&&target<=nums[mid]){
                    //说明目标值在左边
                    //将最大值减小到有序数组中
                    high = mid -1;
                }else{
                    //在右边无序数组中
                    //排除掉左边
                    low = mid +1;
                }

            }else{
                // mid high 有序 右边有序

                if(target>=nums[mid]&&target<=nums[high]){
                    low = mid +1;
                }else{
                    //不在右边
                    high = mid -1;
                }

            }
        }

        return -1;

    }



    public static void main(String[] args) {
        Solution solution = new Solution();

        int[] nums1 = new int[]{4,4};
        int[] nums2 = new int[]{1,3};

        System.out.println(solution.getKthElement(nums1, nums2, 2));
    }


}
