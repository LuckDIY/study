package com.study.bfs;

import com.study.tree.Node;
import com.study.tree.Solution;
import com.study.tree.TreeNode;

import java.util.*;

/**
 * @program: study
 * @description: 广度优先练习
 * @author: WangChaoLei
 * @create: 2021-12-18 19:32
 **/
public class Demo {

    /**
     * 层次遍历
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {

        ArrayList<List<Integer>> lists = new ArrayList<>();

        if(root==null){
            return lists;
        }

        Queue<TreeNode> queue = new ArrayDeque<>();

        //初始化根元素，作为第一层
        queue.add(root);

        while(!queue.isEmpty()){


            List list = new ArrayList<Integer>();

            //不能使用wihle，会把所有层的打印到一个数组里
            int size = queue.size();
            for (int i = 0; i < size; i++) {

                TreeNode poll = queue.poll();
                list.add(poll.val);

                if(poll.left!=null){
                    //将队列的子类放进队列
                    queue.add(poll.left);
                }

                if(poll.right!=null){
                    queue.add(poll.right);
                }

            }

            lists.add(list);

        }

        return lists;

    }


    /**
     * 相同的数
     * @param p
     * @param q
     * @return
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {

        if(p==null && q == null){
            return true;
        }

        if(p==null || q == null){
            return false;
        }

        return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }


    /**
     * 对称树
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {

        //两个中序遍历，一个从左边一个从右边

        if(root==null){
            return true;
        }

        return isReverse(root.left,root.right);

    }


    public boolean isReverse(TreeNode p,TreeNode q){

        if(p==null && q == null){
            return true;
        }

        if(p==null || q == null){
            return false;
        }

        return p.val==q.val && isReverse(p.left,q.right) && isReverse(p.right,q.left);
    }


    /**
     * 103. 二叉树的锯齿形层序遍历 todo 没有写好
     * @param root
     * @return
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {

        List<List<Integer>> lists = new ArrayList<>();

        if(root==null){
            return lists;
        }

        //不为空

        //把层次遍历的队列
        LinkedList<TreeNode> queue = new LinkedList<>();

        //root提前入队列
        queue.add(root);

        //偶数顺序，奇数倒叙
        int count = 0;
        while(!queue.isEmpty()){
            //栈不为空

            int size = queue.size();

            //新栈
            LinkedList<Integer> newQueue = new LinkedList<>();

            for (int i = 0; i < size; i++) {

                TreeNode pop = queue.poll();

                if(count%2==0){
                    //偶数是队列
                    newQueue.offerLast(pop.val);
                }else{
                    //奇数是栈
                    newQueue.offerFirst(pop.val);
                }

                //将他的左右节点入栈
                if(pop.left!=null){
                    queue.add(pop.left);
                }

                if(pop.right!=null){
                    queue.add(pop.right);
                }

            }

            count++;


            lists.add(new ArrayList<>(newQueue));
        }

        return lists;

    }





    /**
     * 自底往上 层次遍历
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {

        if(root==null){
            return new ArrayList<>();
        }

        List<List<Integer>> list = new LinkedList<>();

        LinkedList<TreeNode> queue = new LinkedList<>();

        queue.add(root);

        while(!queue.isEmpty()){

            ArrayList<Integer> minList = new ArrayList<>();
            int size = queue.size();
            int count = 0;
            while(count<size){
                count++;

                TreeNode poll = queue.poll();

                minList.add(poll.val);

                if(poll.left!=null){
                    queue.add(poll.left);
                }
                if(poll.right!=null){
                    queue.add(poll.right);
                }
            }

            list.add(0, minList);

        }

        return list;

    }


    /**
     * 二叉树最小深度
     * 广度
     * @param root
     * @return
     */
    public int minDepth(TreeNode root) {

        if(root==null){
            return 0;
        }

        LinkedList<TreeNode> queue = new LinkedList<>();

        queue.add(root);

        int count = 0;
        while(!queue.isEmpty()){
            count++;

            int size = queue.size();

            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();

                if(poll.left==null && poll.right==null){
                    return count;
                }

                if(poll.left!=null){
                    queue.add(poll.left);
                }

                if(poll.right!=null){
                    queue.add(poll.right);
                }
            }
        }
        return count;


    }

    /**
     * 二叉树最小深度
     * 深度
     * @param root
     * @return
     */
    public int minDepth2(TreeNode root) {

        if(root==null){
            return 0;
        }

        if(root.left==null && root.right==null){
            return 1;
        }

        int min = Integer.MAX_VALUE;

        if(root.left!=null){
            min = Math.min(minDepth2(root.left),min);
        }

        if(root.right!=null){
            min = Math.min(min,minDepth2(root.right));
        }

        return min+1;



    }

    /**
     * 连接完全二叉树左右节点
     *  广度优先
     * @param root
     * @return
     */
    public Node connect(Node root) {
        if(root==null){
            return root;
        }

        LinkedList<Node> queue = new LinkedList<>();

        queue.add(root);

        while (!queue.isEmpty()){

            int size = queue.size();

            for (int i = 0; i < size; i++) {
                Node poll = queue.poll();

                //直接窥探的话会窥探到下一层层的数据
                if(i<size-1){
                    poll.next = queue.peek();
                }

                if(poll.left !=null){
                    queue.add(poll.left);
                }
                if(poll.right !=null){
                    queue.add(poll.right);
                }
            }
        }

        return root;
    }


    /**
     * 被围绕的区域，
     * 方案: 只有外围有O的话，连接他的O才是活得
     *  首先遍历外层，外层发现O的话，
     *  广度优先它四周，把它周围以及他自己的O都标记为#
     *  标记为#代表他是已经遍历过的
     *  并将他周围的O放入队列，进行广度优先
     *
     * [["X","X","X","X"],
     *  ["X","O","O","X"],
     *  ["X","X","O","X"],
     *  ["X","O","X","X"]]
     * @param board
     */
    public void solve(char[][] board) {

        int iSize = board.length;
        int jSize = board[0].length;

        for (int i = 0; i < iSize; i++) {
            //j所有靠边的
            if (board[i][0] == 'O') {
                //查询所有和o相连的所有o
                isChange(board,i,0);
            }

            if (board[i][jSize-1]== 'O') {
                //查询所有和o相连的所有o
                isChange(board,i,jSize-1);
            }
        }

        for (int i = 0; i < jSize; i++) {
            //i所有靠边的
            if (board[0][i] == 'O') {
                //查询所有和o相连的所有o
                isChange(board,0,i);

            }

            if (board[iSize-1][i]== 'O') {
                //查询所有和o相连的所有o
                isChange(board,iSize-1,i);

            }
        }

        //遍历
        for (int i = 0; i < iSize; i++) {
            for (int j = 0; j < jSize; j++) {
                if (board[i][j] == '#') {
                    board[i][j] = 'O';
                }else if(board[i][j] == 'O'){
                    board[i][j] = 'X';
                }
            }
        }


    }

    //是否需要改变,判断所有和边O相连的O
    private void isChange(char[][] board, int i, int j) {

        //
        LinkedList<int[]> queue = new LinkedList<>();
        queue.add(new int[]{i,j});
        board[i][j] = '#';

        while(!queue.isEmpty()){
            //如果队列不为空
            //取出队列中的元素，然后判断他的上下左右是否有O,有的话，把对应元素加入队列
            int[] poll = queue.poll();

            int a = poll[0];
            int b = poll[1];

            //上下左右
            if(a-1>=0 && board[a-1][b] == 'O'){
                //上
                queue.add(new int[]{a-1,b});
                board[a-1][b] = '#';

            }

            if(a+1 < board.length && board[a+1][b] == 'O'){
                //下
                queue.add(new int[]{a+1,b});
                board[a+1][b] = '#';

            }

            if(b-1>=0 && board[a][b-1] == 'O'){
                //左
                queue.add(new int[]{a,b-1});
                board[a][b-1] = '#';

            }

            if(b+1 < board[i].length && board[a][b+1] == 'O'){
                //上
                queue.add(new int[]{a,b+1});
                board[a][b+1] = '#';

            }

        }
    }






    public static void main(String[] args) {
        /*TreeNode root = new TreeNode(1);
        TreeNode treeNode1 = new TreeNode(2);
        TreeNode treeNode2 = new TreeNode(3);
        TreeNode treeNode3 = new TreeNode(4);
        TreeNode treeNode4 = new TreeNode(5);
        root.left=null;
        root.right=treeNode2;
        treeNode1.left=treeNode3;
        treeNode1.right=null;
        treeNode2.left=null;
        treeNode2.right=treeNode4;

        System.out.println(new Demo().minDepth2(root));*/

        Graph graph1 = new Graph(1);
        Graph graph2 = new Graph(2);
        Graph graph3 = new Graph(3);
        Graph graph4 = new Graph(4);
        Graph graph5 = new Graph(5);
        Graph graph6 = new Graph(6);

        ArrayList<Graph> graphs = new ArrayList<>();
        graphs.add(graph2);
        graphs.add(graph3);
        graphs.add(graph5);
        graph1.neighbors = graphs;

        ArrayList<Graph> graphs2 = new ArrayList<>();
        graphs2.add(graph2);
        graphs2.add(graph4);
        graphs2.add(graph6);
        graph2.neighbors = graphs2;

        System.out.println(new Demo().bfsGraph(graph1));

    }


    /**
     * 图的广度优先
     * @param node
     * @return
     */
    public List<List<Integer>> bfsGraph(Graph node) {

        HashSet<Graph> set = new HashSet<>();

        List<List<Integer>> list = new ArrayList<>();

        LinkedList<Graph> queue = new LinkedList<>();
        queue.add(node);

        while(!queue.isEmpty()){

            ArrayList<Integer> arrayList = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Graph poll = queue.poll();
                set.add(poll);

                arrayList.add(poll.val);

                List<Graph> neighbors = poll.neighbors;

                for (Graph neighbor : neighbors) {
                    if(!set.contains(neighbor)){
                        queue.add(neighbor);
                    }
                }
            }
            list.add(arrayList);

        }

        return list;


    }


    public Graph cloneGraph(Graph node) {

        //对图进行广度优先遍历，然后将值存入新图
        //key 老图  value 新图
        HashMap<Graph, Graph> map = new HashMap<>();
        map.put(node,new Graph(node.val,new ArrayList<>()));

        LinkedList<Graph> queue = new LinkedList<>();
        queue.add(node);

        while(queue.size()!=0){

            int size = queue.size();

            for (int i = 0; i < size; i++) {
                Graph poll = queue.poll();

                List<Graph> neighbors = poll.neighbors;

                for (Graph neighbor : neighbors) {

                    //将这个顶点放入上一个顶点里

                    if(!map.containsKey(neighbor)){
                        map.put(neighbor,new Graph(neighbor.val,new ArrayList<>()));
                        queue.add(neighbor);
                    }

                    //将新创建的值放入map
                    map.get(poll).neighbors.add(map.get(neighbor));
                }

            }

        }


        return map.get(node);
    }


    /**
     * 右视图 站在树的右侧能看到的东西，很适合 bfs
     * 万年 bfs
     * @param root
     * @return
     */
    public List<Integer> rightSideView(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();

        if(root==null){
            return list;
        }

        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while(queue.size()!=0){

            int size = queue.size();

            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();

                if(i==0){
                    list.add(poll.val);
                }

                //先放右边的
                if(poll.right!=null){
                    //不为空，不使用左边的
                    queue.add(poll.right);
                }
                //再放左边的
                if(poll.left!=null){
                    queue.add(poll.left);
                }

            }
        }
        return list;
    }


    /**
     * 被水包围的岛屿  1 是陆地  0 是水，求陆地有几块
     *   ["1","1","1","0","0"],
     *   ["1","1","0","1","0"],
     *   ["1","1","0","0","0"],
     *   ["0","0","0","0","0"]
     *
     *   2 块岛屿
     *   不知道怎么做，bfs试试
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/number-of-islands
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param grid
     * @return
     */
    public int numIslands(char[][] grid) {

        //遍历先找到第一个岛屿
        LinkedList<int[]> queue = new LinkedList<>();

        int count = 0;


        //查询陆地
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {

                if (grid[i][j] == '1') {
                    //找到一块陆地
                    //放入queue

                    count++;

                    queue.add(new int[]{i,j});

                    //将它旁边所有的陆地都变为0
                    while(queue.size()!=0){

                        int[] poll = queue.poll();
                        int x = poll[0];
                        int y = poll[1];

                        if(grid[x][y] == '1'){

                            grid[x][y] = '0';

                            if(x-1>=0 && grid[x-1][y] == '1'){
                                queue.add(new int[]{x-1,y});
                            }
                            if(x+1<grid.length && grid[x+1][y] == '1'){
                                queue.add(new int[]{x+1,y});
                            }
                            if(y-1>=0 && grid[x][y-1] == '1'){
                                queue.add(new int[]{x,y-1});
                            }
                            if(y+1< grid[x].length && grid[x][y+1] == '1'){
                                queue.add(new int[]{x,y+1});
                            }
                        }
                    }

                    //继续寻找下一个陆地
                }
            }
        }

        return count;


    }


}

class Graph {
    public int val;
    public List<Graph> neighbors;


    public Graph() {
        val = 0;
        neighbors = new ArrayList<Graph>();
    }


    public Graph(int _val) {
        val = _val;
        neighbors = new ArrayList<Graph>();
    }


    public Graph(int _val, ArrayList<Graph> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }

}
