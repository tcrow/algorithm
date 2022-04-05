package org.tcrow.util;

import org.tcrow.bilibili.class04.Node;

/**
 * 链表工具类
 */
public class LinkUtils {
    /**
     * 链表反向
     * 1 --> 2 --> 3 => 1 <-- 2 <-- 3
     * 步骤：
     * 1、先走两步
     * 2、处理先走的两步链表反向
     * 3、再往前走一步，处理前面一步转向，周而复始，直到完成
     *
     * @param head
     */
    public static Node reverse(Node head) {
        Node cur = head;
        Node perv = null;
        Node next;
        while (cur != null) {
            // 临时保存下一跳节点位置
            next = cur.next;

            // 先把当前节点的下一跳指向上一跳
            cur.next = perv;

            // 上一跳往后走一步
            perv = cur;

            // 当前指针往后走一步
            cur = next;
        }
        return perv;
    }
}
