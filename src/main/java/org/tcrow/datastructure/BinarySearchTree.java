package org.tcrow.datastructure;

/**
 * @author tcrow.luo
 *         二叉树
 */
public class BinarySearchTree<Key extends Comparable<Key>, Value> {
    private Node root;

    private class Node {
        private Key key;
        private Value value;
        private Node left, right;
        private int size;

        public Node(Key key, Value value, int size) {
            this.key = key;
            this.value = value;
            this.size = size;
        }
    }

    /**
     * 向上取整
     */
    public Key ceiling(Key key) {
        Node node = ceiling(root, key);
        if (node == null) {
            return null;
        }
        return node.key;
    }

    private Node ceiling(Node node, Key key) {
        if (node == null) {
            return null;
        }
        int cmp = node.key.compareTo(key);
        if (cmp == 0) {
            return node;
        } else if (cmp < 0) {
            return ceiling(node.right, key);
        }
        Node t = ceiling(node.left, key);
        if (t != null) {
            return t;
        }
        return node;
    }

    /**
     * 向下取整
     */
    public Key floor(Key key) {
        Node node = floor(root, key);
        if (node == null) {
            return null;
        }
        return node.key;
    }

    private Node floor(Node node, Key key) {
        if (node == null) {
            return null;
        }

        int cmp = node.key.compareTo(key);
        if (cmp == 0) {
            return node;
        } else if (cmp > 0) {
            return floor(node.left, key);
        }
        Node t = floor(node.right, key);
        if (t != null) {
            return t;
        }
        return node;
    }

    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(Node node, Key key) {
        if (node == null) {
            return null;
        }
        int cmp = node.key.compareTo(key);
        if (cmp == 0) {
            return node.value;
        } else if (cmp < 0) {
            return get(node.right, key);
        } else {
            return get(node.left, key);
        }
    }

    public void put(Key key, Value value) {
        root = put(root, key, value);
    }

    private Node put(Node node, Key key, Value value) {
        if (node == null) {
            return new Node(key, value, 1);
        }
        int cmp = node.key.compareTo(key);
        if (cmp == 0) {
            node.value = value;
        } else if (cmp < 0) {
            node.right = put(node.right, key, value);
        } else {
            node.left = put(node.left, key, value);
        }
        node.size = size(node.left) + size(node.right) + 1;
        return node;
    }

    /**
     * 返回比key小的节点数量
     * @param key
     * @return
     */
    public int rank(Key key) {
        return rank(root, key);
    }

    private int rank(Node node, Key key) {
        if (node == null) {
            return 0;
        }
        int cmp = node.key.compareTo(key);
        if (cmp == 0) {
            return size(node.left);
        } else if (cmp < 0) {
            return 1 + size(node.left) + rank(node.right, key);
        }
        return rank(node.left, key);
    }

    public Key min() {
        return min(root).key;
    }

    private Node min(Node node) {
        if (node.left == null) {
            return node;
        }
        return min(node.left);
    }

    private Node max(Node node) {
        if (node.right == null) {
            return node;
        }
        return max(node.right);
    }

    public Key max() {
        return max(root.right).key;
    }

    /**
     * 返回排名第k的节点
     * @param k
     * @return
     */
    public Key select(int k) {
        return select(root, k).key;
    }

    private Node select(Node node, int k) {
        if (node == null) {
            return null;
        }
        int t = size(node.left);
        if (t == k) {
            return node;
        } else if (t < k) {
            return select(node.right, k - t - 1);
        }
        return select(node.left, k);
    }

    public int size() {
        return size(root);
    }

    private int size(Node node) {
        if (node == null) {
            return 0;
        }
        return node.size;
    }


}
