package com.study.controller;

/**
 * @author LinkWang
 * @projectName demo
 * @description: TODO
 * @date 2020/12/22 11:04
 */
public class Example {

    // = new String("good");
    StringBuilder str  = new StringBuilder("good");
    //Object str = new Object();
    char[ ] ch = { 'a' , 'b' , 'c' };
    int i = 1;
    public static void main(String args[]){
        Example ex = new Example();
        ex.change(ex.str,ex.ch,ex.i);
        System.out.print(ex.str + " and ");
        System.out.print(ex.ch);
        System.out.println(ex.i);
    }
    public void change(StringBuilder str,char ch[],int i){
       str = new StringBuilder("sjfklsjdkl");
       // System.out.println(str.hashCode());
       ch[0]='4';
       i++;
       this.i = i++;
    }


}
