package org.tcrow.bilibili.class08;

import com.google.common.base.Stopwatch;

import java.util.*;

/**
 * Dijkstra 算法求最短距离
 */
public class Dijkstra {

    private static int getDistance(Node from, Node to) {
        List<Edge> edges = from.getEdges();
        for (Edge edge : edges) {
            if (edge.getFrom().equals(from) && edge.getTo().equals(to)) {
                return edge.getWeight();
            }
        }
        return Integer.MAX_VALUE;
    }

    private static Node getUnselectedMinDistanceNode(Set<Node> selectedNodes, Map<Node, Integer> distanceMap) {
        Node minNode = null;
        int minDistance = Integer.MAX_VALUE;
        for (Node node : distanceMap.keySet()) {
            if (selectedNodes.contains(node)) {
                continue;
            }
            Integer distance = distanceMap.get(node);
            if (minDistance > distance) {
                minDistance = distance;
                minNode = node;
            }
        }
        return minNode;
    }

    public static Map<Node, Integer> dijkstra(Node head) {
        Map<Node, Integer> distanceMap = new HashMap<>();
        Set<Node> selectedNodes = new HashSet<>();
        distanceMap.put(head, 0);
        Node cur = getUnselectedMinDistanceNode(selectedNodes, distanceMap);
        while (cur != null) {
            List<Node> next = cur.getNext();
            Integer distance = distanceMap.get(cur);
            for (Node node : next) {
                if (!distanceMap.containsKey(node)) {
                    distanceMap.put(node, getDistance(cur, node));
                    continue;
                }
                if (selectedNodes.contains(node)) {
                    continue;
                }
                distanceMap.put(node, Math.min(distanceMap.get(node), distance + getDistance(cur, node)));
            }
            selectedNodes.add(cur);
            cur = getUnselectedMinDistanceNode(selectedNodes, distanceMap);
        }
        return distanceMap;
    }

    public static Map<Node, Integer> dijkstra2(Node head) {
        Map<Node, Integer> distanceMap = new HashMap<>();
        Set<Node> selectedNodes = new HashSet<>();
        distanceMap.put(head, 0);
        Node cur = getUnselectedMinDistanceNode(selectedNodes, distanceMap);
        while (cur != null) {
            List<Edge> edges = cur.getEdges();
            Integer distance = distanceMap.get(cur);
            for (Edge edge : edges) {
                Node to = edge.getTo();
                if (!distanceMap.containsKey(to)) {
                    distanceMap.put(to, distance + edge.getWeight());
                    continue;
                }
                if (selectedNodes.contains(to)) {
                    continue;
                }
                distanceMap.put(to, Math.min(distanceMap.get(to), distance + edge.getWeight()));
            }
            selectedNodes.add(cur);
            cur = getUnselectedMinDistanceNode(selectedNodes, distanceMap);
        }
        return distanceMap;
    }


    /**
     * /100  B    \
     * /      |5    \1
     * A --7--C--50-- E
     * \      |2    /
     * \1    D    /100
     *
     * @param args
     */
    public static void main(String[] args) {
        String[][] matrix = new String[][]{
                {"A", "B"},
                {"A", "C"},
                {"A", "D"},
                {"B", "A"},
                {"B", "C"},
                {"B", "E"},
                {"C", "A"},
                {"C", "B"},
                {"C", "D"},
                {"C", "E"},
                {"D", "A"},
                {"D", "C"},
                {"D", "E"},
                {"E", "B"},
                {"E", "C"},
                {"E", "D"},
        };
        int[] weights = new int[]{
                100,// A - B
                7,  // A - C
                1,  // A - D
                100, // B - A
                5,  // B -C
                1,  // B - E
                7,  // C - A
                5,  // C - B
                2, // C - D
                50, // C - E
                1, // D - A
                2, // D - C
                100, // D - E
                1, // E - B;
                50, // E - C
                100, // E - D;
        };
        Graph graph = Graph.createGraph(matrix, weights);
        Node head = graph.getNodes().get("A");
        Stopwatch stopwatch = Stopwatch.createStarted();
        Map<Node, Integer> distanceMap = dijkstra(head);
        System.out.println("dijkstra:" + stopwatch.toString());
        stopwatch.reset().start();
        distanceMap = dijkstra2(head);
        System.out.println("dijkstra2:" + stopwatch.toString());
        for (Map.Entry<Node, Integer> entry : distanceMap.entrySet()) {
            String log = "node:%s to %s,min distance:%s";
            System.out.println(String.format(log, head.getValue(), entry.getKey().getValue(), entry.getValue()));
        }
    }
}
