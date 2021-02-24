package com.study.linkedList;

import java.util.Stack;

/**
 * @author LinkWang
 * @projectName demo
 * @description: TODO
 * @date 2021/2/18 11:26
 */
public class Solution {

    /**
     * 合并两个有序链表
     * 思路: 归并排序合并思路
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        //链表头部
        ListNode header = new ListNode(0);

        //链表尾部
        ListNode tail = header;

        while(l1!=null&&l2!=null){

            //如果l1链表小于l2
            if(l1.val<l2.val){
                tail.next=l1;
                l1=l1.next;
                tail=tail.next;
            }else{
                tail.next=l2;
                l2=l2.next;
                tail=tail.next;
            }

        }

        if(l1==null){
            tail.next=l2;
        }

        if(l2==null){
            tail.next=l1;
        }
        return header.next;

    }


    /**
     * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
     * 两趟实现，通过栈，自己尝试
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {

        //定义链表头，用于处理链表删除第一个节点的情况
        ListNode header = new ListNode(0, head);

        //定义栈
        Stack<ListNode> stack = new Stack<>();

        ListNode temp = header;
        while(temp!=null){
            stack.push(temp);
            temp = temp.next;
        }

        for (int i = 0; i < n; i++) {
            stack.pop();
        }
        ListNode node = stack.peek();
        ListNode tail = node.next.next;
        node.next=tail;

        return header.next;
    }

    /**
     * 双指针删除
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd2(ListNode head, int n) {

        //定义链表头
        ListNode header = new ListNode(0, head);

        ListNode pre=header,fast = header;
        //由于需要pre节点在删除节点前一个节点，所以需要让fast指针向后移动n+1步+1步
        for (int i = 0; i < n + 1; i++) {
            fast=fast.next;
        }


        //让pre和fast都后移
        while(fast!=null){
            pre=pre.next;
            fast=fast.next;
        }

        if(fast==null&&pre==header){
            return header.next.next;
        }

        pre.next=pre.next.next;

        return header.next;

    }


    /**
     * 检测链表中是否有环,通过差值指针实现，一个指针一次走一步，一次走两步  2，通过hash实现
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {

        ListNode temp1 = head;
        ListNode temp2 = head;

        while(temp1!=null&&temp2!=null){

            temp1=temp1.next;
            if(temp1==null){
                return false;
            }
            temp2=temp2.next;
            if(temp2==null){
                return false;
            }
            temp2=temp2.next;
            if(temp2==null){
                return false;
            }
            if(temp1==temp2){
                return true;
            }
        }
        return false;
    }

    /**
     * 官方快慢指针
     * @param head
     * @return
     */
    public boolean hasCycle2(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (slow != fast) {
            if (fast == null || fast.next == null) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }


    /**
     * 给定一个头结点为 head 的非空单链表，返回链表的中间结点。
     * 如果有两个中间结点，则返回第二个中间结点。
     *
     * 1.通过快慢指针，一个指针每次走一步，一个每次走两步，当第二个到末尾，第一个必然在中间
     * 2.通过数组存储所有节点，将size%2==0?size/2:size/2+1 返回
     * @param head
     * @return
     */
    public ListNode middleNode(ListNode head) {

        //定义快慢指针
        ListNode slow = head,fast=head;

        while(fast!=null&&fast.next!=null){
            fast=fast.next.next;
            slow=slow.next;
        }
        return slow;
    }


    /**
     * 解数独  char是9*9的数组
     * @param board
     */
    public void solveSudoku(char[][] board) {



    }

}
