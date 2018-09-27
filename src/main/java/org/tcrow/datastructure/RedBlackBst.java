package org.tcrow.datastructure;

/**
 * @author tcrow.luo
 *         红黑树
 */
public class RedBlackBst<Key extends Comparable<Key>, Value> {

    private RedBlackBst.Node root;

    private class Node {
        private Key key;
        private Value value;
        private boolean color = false;
        private RedBlackBst.Node left, right;
        private int size;

        public Node(Key key, Value value, int size) {
            this.key = key;
            this.value = value;
            this.size = size;
        }
    }

}
