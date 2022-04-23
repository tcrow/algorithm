package org.tcrow.bilibili.class08;

import lombok.Data;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 图
 */
@Data
public class Graph {
    private Map<String, Node> nodes;
    private Set<Edge> edges;

    public Graph() {
        this.nodes = new HashMap<>();
        this.edges = new HashSet<>();
    }

    public static Graph createGraph(String[][] matrix, int[] weights) {
        Graph graph = new Graph();
        for (int i = 0; i < weights.length; i++) {
            Integer weight = weights[i];
            String from = matrix[i][0];
            String to = matrix[i][1];
            if (!graph.getNodes().containsKey(from)) {
                graph.getNodes().put(from, new Node(from));
            }
            if (!graph.getNodes().containsKey(to)) {
                graph.getNodes().put(to, new Node(to));
            }
            Node fromNode = graph.getNodes().get(from);
            Node toNode = graph.getNodes().get(to);
            Edge newEdge = Edge.builder()
                    .weight(weight)
                    .from(fromNode)
                    .to(toNode)
                    .build();
            fromNode.getNext().add(toNode);
            fromNode.getEdges().add(newEdge);

            // 有向图不加这两行，无向图必须加
            toNode.getNext().add(fromNode);
            toNode.getEdges().add(newEdge);

            fromNode.setIn(fromNode.getIn() + 1);
            fromNode.setOut(fromNode.getOut() + 1);
            graph.getEdges().add(newEdge);
        }
        return graph;
    }
}
