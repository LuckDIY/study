package com.study.graph;

import com.alibaba.fastjson.JSON;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 邻接表图
 */
public class Graph { // 无向图
    private int v; // 顶点的个数
    private LinkedList<Integer> adj[]; // 邻接表

    public Graph(int v) {
        this.v = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; ++i) {
            adj[i] = new LinkedList<>();
        }
    }

    public void addEdge(int s, int t) { // 无向图一条边存两次
        adj[s].add(t);
        adj[t].add(s);
    }

    public LinkedList<Integer>[] getAdj(){
        return adj;
    }

    public int getV() {
        return v;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}