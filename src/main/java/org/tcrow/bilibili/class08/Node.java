package org.tcrow.bilibili.class08;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 图节点
 */
@Data
public class Node {
    private String value;
    private int in;
    private int out;
    private List<Node> next;
    private List<Edge> edges;

    public Node(String value) {
        this.value = value;
        this.in = 0;
        this.out = 0;
        this.next = new ArrayList<>();
        this.edges = new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node node = (Node) o;

        return value.equals(node.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
