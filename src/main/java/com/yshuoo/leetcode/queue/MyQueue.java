package com.yshuoo.leetcode.queue;

import java.util.Stack;

/**
 * @Author yangshuo
 * @Date 2020/6/23 21:45
 *
 * 使用栈实现队列的下列操作：
 * push(x) -- 将一个元素放入队列的尾部。
 * pop() -- 从队列首部移除元素。
 * peek() -- 返回队列首部的元素。
 * empty() -- 返回队列是否为空。
 *
 * MyQueue queue = new MyQueue();
 * queue.push(1);
 * queue.push(2);
 * queue.peek();  // 返回 1
 * queue.pop();   // 返回 1
 * queue.empty(); // 返回 false
 *
 */
public class MyQueue {

    private Stack<Integer> stack;
    private Stack<Integer> stackStash = new Stack<Integer>();

    /** Initialize your data structure here. */
    public MyQueue() {
        stack = new Stack<Integer>();
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        stack.push(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        while (!stack.isEmpty()){
            stackStash.push(stack.pop());
        }
        int value = stackStash.pop();
        while(!stackStash.isEmpty()){
            stack.push(stackStash.pop());
        }
        return value;
    }

    /** Get the front element. */
    public int peek() {
        while (!stack.isEmpty()){
            stackStash.push(stack.pop());
        }
        int value = stackStash.peek();
        while(!stackStash.isEmpty()){
            stack.push(stackStash.pop());
        }
        return value;
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return stack.isEmpty();
    }

}
