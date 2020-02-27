package com.yshuoo.leetcode.queue;

/**
 * @author yangshuo
 * @date 2019/12/30 17:08
 */
class MyCircularQueue {

    private int[] queue;
    private int head = -1;
    private int tail = -1;
    private int size;

    /** Initialize your data structure here. Set the size of the queue to be k. */
    public MyCircularQueue(int k) {
        queue = new int[k];
        size = k;
    }

    /** Insert an element into the circular queue. Return true if the operation is successful. */
    public boolean enQueue(int value) {

        if (isFull()){
            return false;
        }

        if (isEmpty()){
            head++;
            queue[head] = value;
            tail ++;
            return true;
        }

        tail ++;
        if (tail == size){
            tail = 0;
        }
        queue[tail] = value;
        return true;
    }

    /** Delete an element from the circular queue. Return true if the operation is successful. */
    public boolean deQueue() {
        if (isEmpty()){
            return false;
        }

        if (head == tail){

            head = -1;
            tail = -1;
            return true;
        }
        head ++;
        if (head == size){
            head = 0;
        }
        return true;
    }

    /** Get the front item from the queue. */
    public int Front() {
        if (isEmpty()){
            return -1;
        }
        return queue[head];
    }

    /** Get the last item from the queue. */
    public int Rear() {
        if (isEmpty()){
            return -1;
        }
        return queue[tail];
    }

    /** Checks whether the circular queue is empty or not. */
    public boolean isEmpty() {
        if (head == -1 && tail == -1){
            return true;
        }
        return false;
    }

    /** Checks whether the circular queue is full or not. */
    public boolean isFull() {
        if (head == 0 && tail == size -1){
            return true;
        }
        if (tail == head -1){
            return true;
        }
        return false;
    }

    public static void main(String[] args){
        MyCircularQueue myCircularQueue = new MyCircularQueue(3);
        System.out.println(myCircularQueue.enQueue(3));
        System.out.println(myCircularQueue.enQueue(1));
        System.out.println(myCircularQueue.enQueue(2));
        System.out.println( myCircularQueue.enQueue(3));
        System.out.println(myCircularQueue.Rear());
        System.out.println(myCircularQueue.isFull());
        System.out.println(myCircularQueue.deQueue());
        System.out.println(myCircularQueue.enQueue(4));
        System.out.println(myCircularQueue.Rear());
    }
}

/**
 * Your MyCircularQueue object will be instantiated and called as such:
 * MyCircularQueue obj = new MyCircularQueue(k);
 * boolean param_1 = obj.enQueue(value);
 * boolean param_2 = obj.deQueue();
 * int param_3 = obj.Front();
 * int param_4 = obj.Rear();
 * boolean param_5 = obj.isEmpty();
 * boolean param_6 = obj.isFull();
 */


