package org.tcrow.bilibili.class04;

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

    public Node copy() {
        Node newNode = new Node(this.next, this.value);
        newNode.rand = this.rand;
        return newNode;
    }
}
