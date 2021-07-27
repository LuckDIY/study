package com.study.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * 回溯算法练习
 * 通过不断回溯寻找最优解
 */
public class Backtracking {





    //太他妈的简洁啦
    public int maxW = Integer.MIN_VALUE; //存储背包中物品总重量的最大值
    //添加备忘录去重
    private boolean[][] mem = new boolean[5][10];
    // cw表示当前已经装进去的物品的重量和；i表示考察到哪个物品了；
    // w背包重量；items表示每个物品的重量；n表示物品个数
    // 假设背包可承受重量100，物品个数10，物品重量存储在数组a中，那可以这样调用函数：
    // f(0, 0, a, 10, 100)
    public void f(int i, int cw, int[] items, int n, int w) {
        if (cw == w || i == n) { // cw==w表示装满了;i==n表示已经考察完所有的物品
            if (cw > maxW) maxW = cw;
            return;
        }

        //抉择之前判断是都已经有这个操作执行过
        //为true说明已经有人标记执行过
        if(mem[i][cw]) return ;

        mem[i][cw]=true;

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



    public List<String> printVertically(String s) {
        ArrayList<String> result = new ArrayList<>();
        String[] s1 = s.split(" ");
        int maxSize = 0;
        for (String s2 : s1) {
            maxSize = maxSize<s2.length()? s2.length() : maxSize;
        }
        for (int i = 0; i < maxSize; i++) {
            String child = "";
            boolean b = false;
            for (int i1 = s1.length - 1; i1 >= 0; i1--) {
                String s2=s1[i1];
                if(s2.length()>i){
                    child=s2.charAt(i)+child;
                    b=true;
                }else{
                    if(b){
                        child=" "+child;
                    }
                }
            }
            result.add(child);
        }
        return result;
    }


    /**
     * 42 接雨水
     * @param height
     * @return
     */
    public int trap(int[] height) {
        //right
        int r = height.length-1;
        int l = 0;

        int lmax=0;
        int[] lrMax=new int[height.length];

        int max = 0;

        lrMax[0]=height[lmax];

        for (int i = 1; i <= r; i++) {
            //计算结果
            int width = i-lmax-1;
            int min = Math.min(height[i], lrMax[lmax]);
            int result = width*min;
            if(result>0){
                for (int j = lmax+1; j < i; j++) {
                    result-=lrMax[j];
                }
                max+=result;
            }

            //计算结果后
            //如果新值大于左边的值
            if(lrMax[lmax]<=height[i]){
                lmax=i;
                lrMax[lmax]=height[i];
            }


        }
        return 1;
    }

    /**
     * 找出最有最高的墙，存储当前坐标的水量
     * @param height
     * @return
     */
    public int trap1(int[] height){

        int max = 0;

        for (int i = 1; i < height.length - 1; i++) {
            int leftMax=0;
            for (int j = i-1; j >=0; j--) {
                leftMax=Math.max(leftMax,height[j]);
            }
            int rightMax=0;
            for (int j = i+1; j < height.length; j++) {
                rightMax=Math.max(rightMax,height[j]);
            }

            //计算当前坐标蓄水量
            int current = height[i];
            if(current<leftMax&&current<rightMax){
                max+=Math.min(rightMax,leftMax)-current;
            }
        }
        return max;
    }

    /**
     * 双指针解法，数组存储当前位置右边最大值
     * @param height
     * @return
     */
    public int trap2(int[] height){

        int[] rmax=new int[height.length];

        //右边最大值，i的最大值=max(i+1的值,i的值)
        for (int i = height.length - 2; i >= 0; i--) {
            rmax[i]=Math.max(height[i+1],rmax[i+1]);
        }
        //左边最大值
        int lmax=0;

        int max = 0;
        for (int i = 1; i < height.length - 1; i++) {
            lmax=Math.max(lmax,height[i-1]);
            //计算蓄水量
            int current = height[i];
            if(current<lmax&&current<rmax[i]){
                max+=Math.min(lmax,rmax[i])-current;
            }

        }
        return max;
    }

    /**
     * 接雨水，栈实现
     * @param height
     * @return
     */
    public int trap6(int[] height) {
        int sum = 0;
        Stack<Integer> stack = new Stack<>();
        int current = 0;
        while (current < height.length) {
            //如果栈不空并且当前指向的高度大于栈顶高度就一直循环
            while (!stack.empty() && height[current] > height[stack.peek()]) {
                int h = height[stack.peek()]; //取出要出栈的元素
                stack.pop(); //出栈
                if (stack.empty()) { // 栈空就出去
                    break;
                }
                int distance = current - stack.peek() - 1; //两堵墙之前的距离。
                int min = Math.min(height[stack.peek()], height[current]);
                sum = sum + distance * (min - h);
            }
            stack.push(current); //当前指向的墙入栈
            current++; //指针后移
        }
        return sum;
    }

}
