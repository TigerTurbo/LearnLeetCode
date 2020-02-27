package com.yshuoo.leetcode.queue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 给定一个整数数据流和一个窗口大小，根据该滑动窗口的大小，计算其所有整数的移动平均值。
 * 示例:
 * MovingAverage m = new MovingAverage(3);
 * m.next(1) = 1
 * m.next(10) = (1 + 10) / 2
 * m.next(3) = (1 + 10 + 3) / 3
 * m.next(5) = (10 + 3 + 5) / 3
 * 这个题的意思是用next模拟一个数据流，所以用queue来遍历实现
 * @author yangshuo
 * @date 2020/2/27 10:27
 */
public class MovingAverage {

    private Queue<Integer> queue = new LinkedList<Integer>();
    private int size;


    /** Initialize your data structure here. */
    public MovingAverage(int size) {
        this.size = size;
    }

    public double next(int val) {
        queue.offer(val);
        if (queue.size() > size){
            queue.poll();
        }
        int count = 0;
        for (int value : queue){
            count += value;
        }
        return (double) count / queue.size();
    }

    /**
     * Your MovingAverage object will be instantiated and called as such:
     * MovingAverage obj = new MovingAverage(size);
     * double param_1 = obj.next(val);
     */
    public static void main(String[] args) {
        MovingAverage movingAverage = new MovingAverage(1);
        System.out.println(movingAverage.next(-1));
    }
}
