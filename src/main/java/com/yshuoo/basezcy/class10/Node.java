package com.yshuoo.basezcy.class10;

import java.util.ArrayList;

public class Node {
	// 值，可以改实际题目的类型
	public int value;
	// 入度
	public int in;
	// 出度
	public int out;
	// 直接邻居，由自己出发能到达的点 out = nexts.size()
	public ArrayList<Node> nexts;
	// 从自己出发的边
	public ArrayList<Edge> edges;

	public Node(int value) {
		this.value = value;
		in = 0;
		out = 0;
		nexts = new ArrayList<>();
		edges = new ArrayList<>();
	}
}
