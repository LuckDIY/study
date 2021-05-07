package com.study.tree;

import java.util.*;

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
     * 中序遍历通过方法栈
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
     * 中序遍历通过栈直接实现
     * @param root
     * @return
     */
    public static List<Integer> inorderTraversal1(TreeNode root){
        ArrayList<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        if(root==null){
            return result;
        }
        TreeNode header = root;

        //依次弹出
        while(header!=null||!stack.isEmpty()){
            //将左节点全部入栈
            while(header!=null){
                stack.push(header);
                header=header.left;
            }

            header = stack.pop();
            result.add(header.val);
            header = header.right;
        }
        return result;

    }

    /**
     * 官方实现，通过迭代
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        Deque<TreeNode> stk = new LinkedList<TreeNode>();
        while (root != null || !stk.isEmpty()) {
            while (root != null) {
                stk.push(root);
                root = root.left;
            }
            root = stk.pop();
            res.add(root.val);
            root = root.right;
        }
        return res;
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


    /**
     * 652. 寻找重复的子树
     * 给定一棵二叉树，返回所有重复的子树。对于同一类的重复子树，你只需要返回其中任意一棵的根结点即可。
     *
     * 两棵树重复是指它们具有相同的结构以及相同的结点值。
     * @param root
     * @return
     */
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {

        Map<String, Integer> map = new HashMap<>();
        List<TreeNode> result = new ArrayList<>();

        findDuplicateSubtrees(map,result,root);

        System.out.println(map);
        return result;
    }

    public String  findDuplicateSubtrees(Map<String, Integer> map,List<TreeNode> result,TreeNode root) {
        //深度优先,中序遍历，存入集合
        if(root==null){
            return "#";
        }

        String leftStr = findDuplicateSubtrees(map, result, root.left);

        String rightStr = findDuplicateSubtrees(map, result, root.right);

        String str = root.val+","+leftStr+","+rightStr;

        map.put(str,map.getOrDefault(str, 0) + 1);

        if(map.get(str)==2){
            result.add(root);
        }
        return str;
    }


    /**
     * 230. 二叉搜索树中第K小的元素
     * 给定一个二叉搜索树的根节点 root ，和一个整数 k ，请你设计一个算法查找其中第 k 个最小元素（从 1 开始计数）。
     *
     * 中序遍历将数据存入list，取k-1的值
     * @param root
     * @param k
     * @return
     */
    public int kthSmallest(TreeNode root, int k) {

        List<Integer> list = inorderTraversal(root);
        return list.get(k-1);
    }




    /*public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode left1 = new TreeNode(2);
        TreeNode right1 = new TreeNode(5);
        TreeNode left1left = new TreeNode(1);
        TreeNode left1right = new TreeNode(4);
        TreeNode right1right = new TreeNode(6);
        root.left=left1;
        root.right=right1;
        left1.left=left1left;
        right1.left=left1right;
        right1.right=right1right;


        inorderTraversal1(root);

    }*/

    /**
     * 剑指 Offer 55 - I. 二叉树的深度
     * 通过深度优先下探获取深度
     * root的深度 = max(root.left的深度,root.right的深度) + 1
     * 递归终止条件 root==null return 0;
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        if(root==null) return 0;
        return Math.max(maxDepth(root.left),maxDepth(root.right))+1;
    }


    public static void main(String[] args) {
        int[] nums = new int[]{-10,-3,0,5,9};
        TreeNode treeNode = new Solution().sortedArrayToBST(nums);
    }

    /**
     *
     * 面试题 04.02. 最小高度树
     * 递归，分治
     * @param nums
     * @return
     */
    public TreeNode sortedArrayToBST(int[] nums) {

        return processor(nums,0,nums.length-1);

    }

    public TreeNode processor(int[] nums,int start,int end){
        if(start>end){
            return null;
        }

        //nums是有序数组
        int n = (end-start) / 2;
        TreeNode root = new TreeNode(nums[n+start]);

        //左边一半
        root.left=processor(nums,start,start+n-1);
        //右边一半
        root.right=processor(nums,start+n+1,end);

        return root;
    }





}
