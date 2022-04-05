package org.tcrow.bilibili.class04;

import org.tcrow.util.RandomUtil;

/**
 * 链表辅助结构
 */
public class Node {
    /**
     * 下一跳节点
     */
    public Node next;

    /**
     * 随机节点
     */
    public Node rand;

    private int value;

    private String hash;

    public Node() {
        this.next = null;
        this.value = 0;
    }

    public Node(int value) {
        this.next = null;
        this.value = value;
    }

    public Node(Node next, int value) {
        this.next = next;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public String getHash() {
        return hash;
    }

    public Node copy() {
        Node newNode = new Node(this.next, this.value);
        newNode.rand = this.rand;
        newNode.hash = RandomUtil.getStringByUUID();
        return newNode;
    }
}
