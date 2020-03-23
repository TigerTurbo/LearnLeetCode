package com.yshuoo.leetcode.stack;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yangshuo
 * @date 2020/3/19 16:34
 *
 * 设计一个支持 push，pop，top 操作，并能在常数时间内检索到最小元素的栈。
 *
 *     push(x) -- 将元素 x 推入栈中。
 *     pop() -- 删除栈顶的元素。
 *     top() -- 获取栈顶元素。
 *     getMin() -- 检索栈中的最小元素。
 *
 * 示例:
 *
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin();   --> 返回 -3.
 * minStack.pop();
 * minStack.top();      --> 返回 0.
 * minStack.getMin();   --> 返回 -2.
 */
public class MinStack {

    private List<Integer> list;
    private int min;
    /** initialize your data structure here. */
    public MinStack() {
        list = new ArrayList<>();
        min = Integer.MAX_VALUE;
    }

    public void push(int x) {
        if (min > x){
            min = x;
        }
        list.add(x);
    }

    public void pop() {
        int index = list.size() - 1;
        int num = list.get(index);
        if (num == min){
            min = Integer.MAX_VALUE;
            for (int i = 0; i < index; i++){
                int value = list.get(i);
                min = min > value ? value : min;
            }
        }
        list.remove(list.size() - 1);
    }

    public int top() {
        if (list.size() <= 0){
            return 0;
        }
        return list.get(list.size() - 1);
    }

    public int getMin() {
        return min;
    }

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-1);
        minStack.push(0);
        System.out.println(minStack.top());
        minStack.push(-2);
        System.out.println(minStack.getMin());
        minStack.pop();
        System.out.println(minStack.getMin());
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
