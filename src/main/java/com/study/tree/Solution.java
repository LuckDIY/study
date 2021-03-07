package com.study.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 二叉树相关算法题
 */
public class Solution {

    public static List<Integer> beforeTraversal(TreeNode root) {

        ArrayList<Integer> list = new ArrayList<>();
        if(root==null){
            return list;
        }

        List<Integer> left = inorderTraversal(root.left);
        List<Integer> right = inorderTraversal(root.right);
        list.add(root.val);
        list.addAll(left);
        list.addAll(right);
        return list;

    }

    /**
     * 中序遍历
     * @param root
     * @return
     */
    public static List<Integer> inorderTraversal(TreeNode root) {

        ArrayList<Integer> list = new ArrayList<>();
        if(root==null){
            return list;
        }

        List<Integer> left = inorderTraversal(root.left);
        List<Integer> right = inorderTraversal(root.right);
        list.addAll(left);
        list.add(root.val);
        list.addAll(right);
        return list;

    }

    /**
     * 反转二叉树
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {

        if(root==null){
            return root;
        }
        TreeNode temp = root.left;
        root.left=root.right;
        root.right=temp;

        if(root.left!=null){
            invertTree(root.left);
        }
        if(root.right!=null){
            invertTree(root.right);
        }
        return root;

    }


    /**
     * 116. 填充每个节点的下一个右侧节点指针
     * 链接二叉树的所有左右节点
     * @param root
     * @return
     */
    public Node connect(Node root) {

        if(root==null){
            return root;
        }
        //链接左右next
        connectLeftAndRight(root.left,root.right);

        return root;

    }

    private void connectLeftAndRight(Node left, Node right) {
        if(left!=null){
            left.next=right;
            connectLeftAndRight(left.left,left.right);
        }
        if(right!=null){
            connectLeftAndRight(right.left,right.right);
        }
        if(left!=null&&right!=null){
            connectLeftAndRight(left.right,right.left);
        }
    }


    /**
     * 114. 二叉树展开为链表
     * 给你二叉树的根结点 root ，请你将它展开为一个单链表：
     *
     * 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
     * 展开后的单链表应该与二叉树 先序遍历 顺序相同。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param root
     */
    public static void flatten(TreeNode root) {
        if(root==null){
            return ;
        }
        //不为空向下递归
        flatten(root.left);
        flatten(root.right);

        TreeNode temp = root.right;
        root.right=root.left;
        root.left=null;

        TreeNode p = root;
        while (p.right != null) {
            p = p.right;
        }
        p.right = temp;

    }
    public void flatten1(TreeNode root) {
        // base case
        if (root == null) return;

        flatten(root.left);
        flatten(root.right);

        /**** 后序遍历位置 ****/
        // 1、左右子树已经被拉平成一条链表
        TreeNode left = root.left;
        TreeNode right = root.right;

        // 2、将左子树作为右子树
        root.left = null;
        root.right = left;

        // 3、将原先的右子树接到当前右子树的末端
        TreeNode p = root;
        while (p.right != null) {
            p = p.right;
        }
        p.right = right;
    }


    /**
     * 654. 最大二叉树
     * 给定一个不含重复元素的整数数组 nums 。一个以此数组直接递归构建的 最大二叉树 定义如下：
     *
     * 二叉树的根是数组 nums 中的最大元素。
     * 左子树是通过数组中 最大值左边部分 递归构造出的最大二叉树。
     * 右子树是通过数组中 最大值右边部分 递归构造出的最大二叉树。
     * 返回有给定数组 nums 构建的 最大二叉树 。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/maximum-binary-tree
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * 这样频繁copy数组浪费空间，可以通过定义函数 （nums，下标起始，下标结束）在原始数组中操作
     * @param nums
     * @return
     */
    public TreeNode constructMaximumBinaryTree(int[] nums) {

        if(nums==null||nums.length==0){
            return null;
        }
        int max = 0;
        int index = 0;
        //找出数组中的最大值及其下标位置
        for (int i = 0; i < nums.length; i++) {
             if(nums[i]>=max){
                 max=nums[i];
                 index=i;
             }
        }
        TreeNode maxNode = new TreeNode(max);

        int[] leftArr = Arrays.copyOfRange(nums, 0, index);
        TreeNode leftNode = constructMaximumBinaryTree(leftArr);
        maxNode.left=leftNode;

        if(index==nums.length-1){
            return maxNode;
        }
        int[] rightArr = Arrays.copyOfRange(nums, index+1, nums.length);
        TreeNode rightNode = constructMaximumBinaryTree(rightArr);
        maxNode.right=rightNode;

        return maxNode;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode left1 = new TreeNode(2);
        TreeNode right1 = new TreeNode(5);
        TreeNode left1left = new TreeNode(3);
        TreeNode left1right = new TreeNode(4);
        TreeNode right1right = new TreeNode(6);
        root.left=left1;
        root.right=right1;
        left1.left=left1left;
        left1.right=left1right;
        right1.right=right1right;

        flatten(root);
        System.out.println(beforeTraversal(root));

    }

}
