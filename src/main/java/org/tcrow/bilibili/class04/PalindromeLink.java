package org.tcrow.bilibili.class04;

import org.tcrow.util.LinkUtils;
import org.tcrow.util.PrintUtils;

/**
 * 求链表是否是回文 例如 1 --> 2 --> 3 --> 2 --> 1 --> null 是一个回文表
 * https://www.bilibili.com/video/BV13g41157hK?p=5
 */
public class PalindromeLink {
    /**
     * 判断链表是否回文链表，要求算法时间复杂度O(N)，空间复杂度O(1)
     * 思路：把链表改成如下结构两条链表，判断下一跳，再改回去 1 --> 2 --> 3 <-- 2 <-- 1
     * 步骤：
     * 1、利用快慢指针，找到中间节点；
     * 2、然后快指针节点链表反向，直到指向中间节点；
     * 3、判断两条链表的值是否相同，如果相同则回文，否则不是回文
     * 4、把链表改回去
     *
     * @param head 链表头节点
     * @return
     */
    private static boolean isPalindrome(Node head) {
        Node slow = head;
        Node fast = head;

        // 快慢指针遍历链表
        while (fast.next != null) {
            if (fast.next.next == null) {
                break;
            }
            fast = fast.next.next;
            slow = slow.next;
        }

        // 右边链表反转
        Node right = slow.next;
        slow.next = null;
        right = LinkUtils.reverse(right);

        // 遍历右边链表结果与左边链表完全相等，可以认为链表完全相等
        Node cur = right;
        slow = head;
        while (cur != null) {
            if (cur.getValue() != slow.getValue()) {
                reduction(head, right);
                return false;
            }
            cur = cur.next;
            slow = slow.next;
        }
        reduction(head, right);
        return true;
    }

    /**
     * 还原链表状态
     *
     * @param left
     * @param right
     */
    private static void reduction(Node left, Node right) {
        right = LinkUtils.reverse(right);
        Node cur = left;
        while (cur.next != null) {
            cur = cur.next;
        }
        cur.next = right;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 2, 1};
        Node head = LinkUtils.generateLinkByArray(arr);
        PrintUtils.print(head);
        System.out.println();
        System.out.println(isPalindrome(head));
        PrintUtils.print(head);
    }

}
