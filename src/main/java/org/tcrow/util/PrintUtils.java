package org.tcrow.util;

import org.tcrow.bilibili.class04.Node;
import org.tcrow.bilibili.class05.BinaryTreeNode;

public class PrintUtils {
    /**
     * 打印数组
     *
     * @param arr
     */
    public static void print(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    /**
     * 打印链表
     *
     * @param head
     */
    public static void print(Node head) {
        Node cur = head;
        while (cur != null) {
            System.out.print(cur.getValue() + " ");
            cur = cur.next;
        }
        System.out.println();
    }

    /**
     * 打印二叉树
     *
     * @param head
     */
    public static void print(BinaryTreeNode head) {

    }
}
