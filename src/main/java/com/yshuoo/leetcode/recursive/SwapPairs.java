package com.yshuoo.leetcode.recursive;

/**
 * @Author yangshuo
 * @Date 2020/7/2 21:03
 */
public class SwapPairs {
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null){
            return null;
        }
        ListNode temp = head;
        ListNode n = head.next;
        temp.next = swapPairs(head.next.next);
        n.next = temp;
        return n;
    }

    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
}