package com.yshuoo.basezcy.class09;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Code05_IPO {

	public static int findMaximizedCapital(int K, int W, int[] Profits, int[] Capital) {
		PriorityQueue<Program> minCostQ = new PriorityQueue<>(new MinCostComparator());
		PriorityQueue<Program> maxProfitQ = new PriorityQueue<>(new MaxProfitComparator());
		// 先根据花费组织小根堆
		for (int i = 0; i < Profits.length; i++) {
			minCostQ.add(new Program(Profits[i], Capital[i]));
		}
		for (int i = 0; i < K; i++) {
			// 将初始资金能支持的项目，放入利润的大根堆
			while (!minCostQ.isEmpty() && minCostQ.peek().c <= W) {
				maxProfitQ.add(minCostQ.poll());
			}
			// K个数量没做够，但是项目做完了，或者启动资金不够解锁项目
			if (maxProfitQ.isEmpty()) {
				return W;
			}
			// 做完一个项目，初始资金加利润，就是下次的启动资金
			W += maxProfitQ.poll().p;
		}
		return W;
	}

	public static class Program {
		public int p;
		public int c;

		public Program(int p, int c) {
			this.p = p;
			this.c = c;
		}
	}

	public static class MinCostComparator implements Comparator<Program> {

		@Override
		public int compare(Program o1, Program o2) {
			return o1.c - o2.c;
		}

	}

	public static class MaxProfitComparator implements Comparator<Program> {

		@Override
		public int compare(Program o1, Program o2) {
			return o2.p - o1.p;
		}

	}

}
