package org.tcrow.leetcode;

public class Test116 {

    class Node {
        int val;
        Node left;
        Node right;
        Node next;
    }

    public Node connect(Node root) {
        dfs(root, null);
        return root;
    }

    private void dfs(Node node, Node next) {
        if (node != null) {
            node.next = next;
            dfs(node.left, node.right);
            dfs(node.right, node.next != null ? node.next.left : null);
        }
    }
}
