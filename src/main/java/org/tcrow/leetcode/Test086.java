package org.tcrow.leetcode;

public class Test086 {

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

    public static void main(String[] args) {
        ListNode head = new ListNode(1, new ListNode(4, new ListNode(3, new ListNode(2, new ListNode(5, new ListNode(2))))));
        print(head);
        System.out.println("================");
        ListNode partition = partition(head, 3);
        print(partition);
    }

    private static void print(ListNode head) {
        ListNode cur = head;
        while (cur != null) {
            System.out.print(cur.val);
            System.out.print(" ");
            cur = cur.next;
        }
        System.out.println();
    }

    public static ListNode partition(ListNode head, int x) {
        if (head == null) {
            return null;
        }
        ListNode small = null;
        ListNode smallHead = null;
        ListNode large = null;
        ListNode largeHead = null;
        ListNode cur = head;
        while (cur.next != null) {
            if (cur.val < x) {
                if (smallHead == null) {
                    smallHead = cur;
                } else {
                    small.next = cur;
                }
                small = cur;
            } else {
                if (largeHead == null) {
                    largeHead = cur;
                } else {
                    large.next = cur;
                }
                large = cur;
            }
            cur = cur.next;
        }
        if (cur.val < x) {
            if (small != null) {
                small.next = cur;
            } else {
                smallHead = cur;
            }
            small = cur;
        } else {
            if (large != null) {
                large.next = cur;
            } else {
                largeHead = cur;
            }
            large = cur;
        }
        if (large != null) {
            large.next = null;
        }
        if (small != null) {
            small.next = largeHead;
        }

        if (smallHead != null) {
            return smallHead;
        }

        return largeHead;
    }
}
