package com.study.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 回溯算法练习
 * 通过不断回溯寻找最优解
 */
public class Backtracking {





    //太他妈的简洁啦
    public int maxW = Integer.MIN_VALUE; //存储背包中物品总重量的最大值
    // cw表示当前已经装进去的物品的重量和；i表示考察到哪个物品了；
    // w背包重量；items表示每个物品的重量；n表示物品个数
    // 假设背包可承受重量100，物品个数10，物品重量存储在数组a中，那可以这样调用函数：
    // f(0, 0, a, 10, 100)
    public void f(int i, int cw, int[] items, int n, int w) {
        if (cw == w || i == n) { // cw==w表示装满了;i==n表示已经考察完所有的物品
            if (cw > maxW) maxW = cw;
            return;
        }
        //当前这个物体不放
        f(i+1, cw, items, n, w);

        // 已经超过可以背包承受的重量的时候，就不要再装了
        //要放的物品放入小于或等于背包总重量
        if (cw + items[i] <= w) {
            //放入
            f(i+1,cw + items[i], items, n, w);
        }
    }


    public static void main(String[] args) {

        char[][] chars = new char[][]{
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'}, 
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'}, 
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'}, 
                {'4', '.', '.', '8', '.', '3', '.','.', '1'}, 
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'}, 
                {'.', '6', '.','.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};
        new Backtracking().solveSudoku(chars);

        //System.out.println((char)(1+48));
    }


    /**
     * 解数独
     * @param board
     */
    public void solveSudoku(char[][] board) {

        sudoku(board,0,0);


    }


    public boolean sudoku(char[][] board,int row,int column){
        if(row>=board.length){
            //说明数独已经放完
            for (int i = 0; i < board.length; i++) {
               System.out.println(Arrays.toString(board[i]));

            }
            return true;
        }

        for (int i = 1; i < 10; i++) {
            //有三种结果，1。当前位置有棋子，无需放置， 2 当前位置没棋子，能放设置棋子  3 当前位置没棋子，不能放设置棋子
            //1 和 2 都是true  3是false
            //boolean ok = isOk(board,(char)(i+48), row, column);
            //TODO 无法知道上一步放入的棋子是什么，从而不知道怎么删除，如果不删除就会进入死循环，
            // isok判断有值，进入下一层，
            // 下一层无字可放，返回上一层
            // 上一层没有删除上一步放入的元素，

            int ok = isOk(board,(char)(i+48), row, column);
            boolean b = false;
            if(ok==1||ok==2){
                //当列还没放完，则放列
                if(column<board.length-1){
                    b = sudoku(board,row,column+1);
                }else{
                    //列放完了，则放下一行第一列
                    b = sudoku(board,row+1,0);
                }
                if(ok==1||b){
                    return b;
                }
                if(ok==2&&!b){
                    //为2将上部的值删除
                    board[row][column]='.';
                }
            }
        }
        return false;

    }

    //判断此位置是否可以放
    private int isOk(char[][] board,char zi, int row, int column) {

        if(board[row][column]!='.'){
            //说明有值，直接返回
            return 1;
        }

        //没值，查看是否能放
        //判断这一行是否能放这个值
        //判断这一列是否支持放入
        for (int i = 0; i < board.length; i++) {
            if(board[row][i]==zi){
                //有这个元素直接false
                return 3;
            }
            if(board[i][column]==zi){
                return 3;
            }
        }

        //判断小方格是否支持
        int bei = column/3;
        int minColumn = bei*3;

        int minRow = row/3*3;

        for (int i = minRow; i < minRow+3; i++) {

            for (int j = minColumn; j < minColumn + 3; j++) {
                if(board[i][j]==zi){
                    return 3;
                }
            }
        }

        //走到这里说明可以放
        board[row][column]=zi;
        return 2;
    }

}
