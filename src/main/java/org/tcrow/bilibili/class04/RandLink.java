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

        // 判断当前节点是否是镜像节点
        boolean flag = false;
        Node perv = null;
        cur = head;
        Node newHead = head.next;
        while (cur != null) {
            if (flag) {
                flag = false;
                cur.rand = cur.rand.next;
                perv.next = cur.next;
                cur = cur.next;
                continue;
            }
            perv = cur;
            cur = cur.next;
            flag = true;
        }
        return newHead;
    }

    private static void printRand(Node head) {
        Node cur = head;
        while (cur != null) {
            System.out.print(cur.rand != null ? cur.rand.getValue() + " " : "");
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
        printRand(head);
        PrintUtils.print(copyRandLink(head));
        printRand(copyRandLink(head));
        printRand(head);

    }
}
