package com.yshuoo.basezcy.class10;

import java.util.HashSet;
import java.util.Stack;

public class Code02_DFS {

	public static void dfs(Node node) {
		if (node == null) {
			return;
		}
		// 其实记录的是当前从头走过的路径
		Stack<Node> stack = new Stack<>();
		HashSet<Node> set = new HashSet<>();
		stack.add(node);
		set.add(node);
		// 打印部分可以替换成之后处理数据的部分
		System.out.println(node.value);
		while (!stack.isEmpty()) {
			// 弹出，相当于回退状态
			Node cur = stack.pop();
			for (Node next : cur.nexts) {
				if (!set.contains(next)) {
					// 维持到达next的路径，保持状态
					stack.push(cur);
					stack.push(next);
					set.add(next);
					System.out.println(next.value);
					break;
				}
			}
		}
	}

}
