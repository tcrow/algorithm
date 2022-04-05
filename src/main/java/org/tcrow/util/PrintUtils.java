package org.tcrow.util;

import org.tcrow.bilibili.class04.Node;

public class PrintUtils {
    public static void print(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static void print(Node head) {
        Node cur = head;
        while (cur != null) {
            System.out.print(cur.getValue() + " ");
            cur = cur.next;
        }
        System.out.println();
    }
}
