package com.yshuoo.basezcy.class10;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class Code01_UnionFind {

	public static class Node<V> {
		V value;

		public Node(V v) {
			value = v;
		}
	}

	public static class UnionSet<V> {
		// V -> 节点
		public HashMap<V, Node<V>> nodes;
		// 子 -> 父
		public HashMap<Node<V>, Node<V>> parents;
		// 只有一个点，它是代表点，才会有size记录
		public HashMap<Node<V>, Integer> sizeMap;

		public UnionSet(List<V> values) {
			for (V value : values) {
				Node<V> node = new Node<>(value);
				nodes.put(value, node);
				parents.put(node, node);
				sizeMap.put(node, 1);
			}
		}

		// 从点cur开始，一直往上找，找到不能再往上找的代表点，返回
		public Node<V> findFather(Node<V> cur) {
			Stack<Node<V>> path = new Stack<>();
			while (cur != parents.get(cur)) {
				path.push(cur);
				cur = parents.get(cur);
			}
			// cur头节点,扁平化，为了减少遍历链
			while (!path.isEmpty()) {
				parents.put(path.pop(), cur);
			}
			return cur;
		}

		public boolean isSameSet(V a, V b) {
			if (!nodes.containsKey(a) || !nodes.containsKey(b)) {
				return false;
			}
			return findFather(nodes.get(a)) == findFather(nodes.get(b));
		}

		public void union(V a, V b) {
			if (!nodes.containsKey(a) || !nodes.containsKey(b)) {
				return;
			}
			Node<V> aHead = findFather(nodes.get(a));
			Node<V> bHead = findFather(nodes.get(b));
			if (aHead != bHead) {
				int aSetSize = sizeMap.get(aHead);
				int bSetSize = sizeMap.get(bHead);
				if (aSetSize >= bSetSize) {
					parents.put(bHead, aHead);
					sizeMap.put(aHead, aSetSize + bSetSize);
					sizeMap.remove(bHead);
				} else {
					parents.put(aHead, bHead);
					sizeMap.put(bHead, aSetSize + bSetSize);
					sizeMap.remove(aHead);
				}
			}
		}
	}

}
