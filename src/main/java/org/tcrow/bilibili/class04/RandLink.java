package org.tcrow.bilibili.class04;

import org.tcrow.util.LinkUtils;
import org.tcrow.util.PrintUtils;

/**
 * rand指针链表问题
 */
public class RandLink {
    /**
     * 深拷贝一个randLink，时间复杂度 O(N)，空间复杂度O(1)
     *
     * @param head
     * @return
     */
    private static Node copyRandLink(Node head) {
        Node cur = head;
        Node next;
        while (cur != null) {
            next = cur.next;
            cur.next = cur.copy();
            cur = next;
        }

        cur = head;
        Node newHead = head.next;
        while (cur != null) {
            next = cur.next;
            if (cur.next != null) {
                cur.next = next.next;
            }
            if (cur.rand != null) {
                cur.rand = cur.rand.next;
            }
            cur = next;
        }
        return newHead;
    }

    private static void printRand(Node head) {
        Node cur = head;
        while (cur != null) {
            System.out.print(cur.getHash() + " ");
            cur = cur.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3};
        Node head = LinkUtils.generateLinkByArray(arr);
        head.rand = head.next.next;
        head.next.rand = head.next;
        head.next.next.rand = head.next.next;
        System.out.print("old link hash:");
        printRand(head);
        for (int i = 0; i < 10; i++) {
            Node node = copyRandLink(head);
            System.out.print("new link value:");
            PrintUtils.print(node);
            System.out.print("new link hash:");
            printRand(node);
            System.out.print("old link hash:");
            printRand(head);
        }

    }
}
