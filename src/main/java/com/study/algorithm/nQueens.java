package com.study.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: study
 * @description:
 * @author: WangChaoLei
 * @create: 2021-04-22 15:51
 **/
public class nQueens {

    public static void main(String[] args) {
        List<List<String>> lists = new nQueens().solveNQueens(4);
        System.out.println(lists);
    }

    public List<List<String>> solveNQueens(int n){
        //0-7 下标是row  value是column
        int[] result = new int[n];
        ArrayList<List<String>> list = new ArrayList<>();
        tianQueen(0,result,list,n);
        return list;
    }

    public void tianQueen(int row,int[] result,ArrayList<List<String>> list,int n){
        if(row==n){
            //printQueens(result);
            //收集
            //打印吧
            //printQueens(result);

            addQueens(result,list);
            return ;
        }
        //不是最后一行后，判断要填在哪一列
        for (int column = 0; column < n; column++) {
            //判断该列是否可填
            if(isTian(row,column,result)){
                result[row]=column;
                tianQueen(row+1,result,list,n);
            }
        }
    }

    private void addQueens(int[] result, ArrayList<List<String>> list) {
        ArrayList<String> arr = new ArrayList<>();
        //每一行
        for (int i = 0; i < result.length; i++) {
            //每一列
            StringBuilder str = new StringBuilder();
            for (int j = 0; j < result.length; j++) {
                if(result[i]==j){
                    str.append("Q");
                }else{
                    str.append(".");
                }
            }
            arr.add(str.toString());
        }
        list.add(arr);
    }



    public boolean isTian(int row,int column,int[] result){
        //当前列左右列
        int left=column-1;
        int right = column+1;

        for (int i = row-1; i >= 0; i--) {
            //说明正上方有值不能放
            if(result[i]==column) return false;

            //左斜线有值不能放
            if(result[i]==left) return false;

            //右斜线有值不能放
            if(result[i]==right) return false;
            left--;
            right++;
        }

        return true;

    }

    private void printQueens(int[] result) {

        //每一行
        for (int i = 0; i < result.length; i++) {
            //每一列
            for (int j = 0; j < result.length; j++) {
                if(result[i]==j){
                    System.out.print("Q ");
                }else{
                    System.out.print("* ");
                }
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
    }
}
