package com.study.graph;

import com.alibaba.fastjson.JSON;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @program: study
 * @description: 图练习
 * @author: WangChaoLei
 * @create: 2021-05-22 17:16
 **/
public class Demo {

    public static void main(String[] args) {


        System.out.println(graph);

        //new Demo().bfs(0, 7);

        int[] i = {-1, 0, 1, 0, 1, 4, 4, -1};
        new Demo().print(i,0,6);
    }

    private static Graph graph = new Graph(8);

    static {
        graph.addEdge(0, 3);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(1, 4);
        graph.addEdge(2, 5);
        graph.addEdge(3, 4);
        graph.addEdge(4, 5);
        graph.addEdge(4, 6);
        graph.addEdge(5, 7);
        graph.addEdge(6, 7);
    }

    /**
     * 广度优先
     *
     * @param s 图起点
     * @param t 图终点
     */
    public void bfs(int s, int t) {
        //图顶点的个数
        int v = graph.getV();

        //获取各个顶点及线
        LinkedList<Integer>[] adj = graph.getAdj();

        //起点终点相同
        if (s == t) return;

        //记录节点是否被访问 true 已经访问啦   false 未访问
        boolean[] visited = new boolean[v];

        //将s改为已访问
        visited[s] = true;

        //创建队列存储访问的节点
        Queue<Integer> queue = new LinkedList<>();
        //s已访问放入队列
        queue.add(s);


        int[] prev = new int[v];

        for (int i = 0; i < v; ++i) {
            prev[i] = -1;
        }


        //队列不为空，循环
        while (queue.size() != 0) {
            //取出一个
            int w = queue.poll();

            System.out.println(w);

            if(w == t){
                return ;
            }

            //w 顶点的所有相邻顶点
            LinkedList<Integer> wList = adj[w];

            //访问所有w顶点连接的节点
            for (int i = 0; i < wList.size(); ++i) {

                //连接的节点
                int q = wList.get(i);

                //q 如果没被访问过
                if (!visited[q]) {



                    //q前驱是w
                    prev[q] = w;

                    //将q设置成已访问
                    visited[q] = true;

                    //将q放入队列
                    queue.add(q);


                    //如果q==最后一个顶点，则遍历返回
//                    if (q == t) {
//                        print(prev, s, t);
//                        return;
//                    }


                }
            }
        }
    }

    private void print(int[] prev, int s, int t) { // 递归打印s->t的路径
        if (prev[t] != -1 && t != s) {
            print(prev, s, prev[t]);
        }
        System.out.print(t + " ");
    }




    public void topoSortByKahn() {
        int v = graph.getV();
        LinkedList<Integer>[] adj = graph.getAdj();
        int[] inDegree = new int[v]; // 统计每个顶点的入度
        for (int i = 0; i < v; ++i) {
            for (int j = 0; j < adj[i].size(); ++j) {
                int w = adj[i].get(j); // i->w
                inDegree[w]++;
            }
        }
        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 0; i < v; ++i) {
            if (inDegree[i] == 0) queue.add(i);
        }
        while (!queue.isEmpty()) {
            int i = queue.remove();
            System.out.print("->" + i);
            for (int j = 0; j < adj[i].size(); ++j) {
                int k = adj[i].get(j);
                inDegree[k]--;
                if (inDegree[k] == 0) queue.add(k);
            }
        }
    }
}
