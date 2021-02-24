package com.study.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
public class AController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Ab ab;


    @GetMapping("ok")
    public String get(){
        System.out.println(ab.a);
        return "ok";
    }


    public static void main(String[] args) {
        System.out.println(1 << 30);
        //System.out.println(lengthOfLongestSubstring(" "));
       // a("pwwkew");
    }

    //(2 -> 4 -> 3) + (5 -> 6 -> 4) = 7 -> 0 -> 8
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode header = new ListNode(0);
        ListNode listNode=header;
        int jin = 0;
        while(l1!=null||l2!=null||jin!=0){
            int one = l1 != null ? l1.val : 0;
            int two = l2 != null ? l2.val : 0;
            int temp = one + two + jin;
            jin = temp / 10;
            ListNode listNode1 = new ListNode(temp % 10);
            listNode.next=listNode1;
            listNode=listNode1;
            if(l1!=null) l1=l1.next;
            if(l2!=null) l2=l2.next;
        }
        return header.next;
    }

    //""pwwkew""
    public static int lengthOfLongestSubstring(String s) {
        ArrayList<Character> list = new ArrayList<>();

        new HashMap<>();
       /* int max = 0;
        int index = 0;
        for (char c : s.toCharArray()) {
            if (list.contains(c)) {
                int i = list.indexOf(c);
                Math.max(max,list.size()-index);
                list.add(c);
                index=i;
                list.remove(i);
            }else{
                list.add(c);
            }
        }
        System.out.println(integers);
        Integer max = Collections.max(integers);
        return max;*/
        return 1;
    }

    //pwwkew
    public static int a(String s){
        // 记录字符上一次出现的位置
        int[] last = new int[128];
        for(int i = 0; i < 128; i++) {
            last[i] = -1;
        }
        int n = s.length();

        int res = 0;
        int start = 0; // 窗口开始位置
        for(int i = 0; i < n; i++) {
            int index = s.charAt(i);
            start = Math.max(start, last[index] + 1);
            res   = Math.max(res, i - start + 1);
            last[index] = i;
        }

        return res;
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}
