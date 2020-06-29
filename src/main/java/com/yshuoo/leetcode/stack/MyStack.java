package com.yshuoo.leetcode.stack;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author yangshuo
 * @Date 2020/6/23 21:56
 *
 * 使用队列实现栈的下列操作：
 * push(x) -- 元素 x 入栈
 * pop() -- 移除栈顶元素
 * top() -- 获取栈顶元素
 * empty() -- 返回栈是否为空
 *
 *
 *
 */
public class MyStack {

    private Queue<Integer> queue;
    private Queue<Integer> queueStash = new LinkedList<>();
    private int top;

    /** Initialize your data structure here. */
    public MyStack() {
        queue = new LinkedList<Integer>();
    }

    /** Push element x onto stack. */
    public void push(int x) {
        queue.add(x);
        top = x;
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        int pop = top;
        queue.remove(top);
        while (!queue.isEmpty()){
            top = queue.poll();
            queueStash.add(top);
        }
        while (!queueStash.isEmpty()){
            queue.add(queueStash.poll());
        }
        return pop;
    }

    /** Get the top element. */
    public int top() {
        return top;
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return queue.isEmpty();
    }

    public static void main(String[] args) {
        MyStack myStack = new MyStack();
        myStack.push(1);
        myStack.push(2);
        System.out.println(myStack.top());
        System.out.println(myStack.pop());

    }

}
