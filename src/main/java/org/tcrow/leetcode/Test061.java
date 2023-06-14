package org.tcrow.leetcode;

public class Test061 {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode rotateRight(ListNode head, int k) {
        ListNode cur = head;
        int count = 1;
        while (cur.next != null) {
            cur = cur.next;
            count++;
        }

        k = k % count;

        if (k - count == 0) {
            return head;
        }

        cur.next = head;

        for (int i = 0; i < count - k - 1; i++) {
            head = head.next;
        }
        ListNode newHead = head.next;
        head.next = null;

        return newHead;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5, null)))));
        Test061 test = new Test061();
        ListNode listNode = test.rotateRight(head, 2);
        System.out.println(listNode.val);
    }
}
