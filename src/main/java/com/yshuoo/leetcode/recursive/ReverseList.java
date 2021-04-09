package com.yshuoo.leetcode.recursive;

/**
 * @Author yangshuo
 * @Date 2020/7/4 10:32
 */
public class ReverseList {

    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null){
            return head;
        }
        ListNode reverse = reverse(head, head.next);
        head.next = null;
        return reverse;
    }

    private ListNode reverse(ListNode node, ListNode next){
        if (next == null){
            return node;
        }
        ListNode head = reverse(node.next, next.next);
        next.next = node;
        return head;
    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

}
