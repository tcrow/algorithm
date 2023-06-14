package org.tcrow.leetcode;

import java.util.LinkedList;

public class Test117 {

    static class Node {
        int val;
        Node left;
        Node right;
        Node next;

        Node(int val) {
            this.val = val;
        }
    }

    private static void print(Node root) {

    }

    public static void main(String[] args) {
        Node root = new Node(1);
        Node b = new Node(2);
        Node c = new Node(3);
        Node d = new Node(4);
        Node e = new Node(5);
        Node f = new Node(6);
        Node g = new Node(7);
        root.left = b;
        root.right = c;
        b.left = d;
        b.right = e;
        c.left = null;
        c.right = g;
        Test117 test117 = new Test117();
        Node connect = test117.connect(root);
        print(connect);
    }

    public Node connect(Node root) {
        LinkedList<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int n = queue.size();

            Node pre = null;
            for (int i = 0; i < n; i++) {
                Node node = queue.poll();

                if(pre != null){
                    pre.next = node;
                }

                pre = node;
                if (node.left != null) {
                    queue.add(node.left);
                }

                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }
        return root;
    }

}
