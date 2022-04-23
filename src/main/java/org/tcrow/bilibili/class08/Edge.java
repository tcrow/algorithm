package org.tcrow.bilibili.class08;

import lombok.Builder;
import lombok.Data;

/**
 * 图边
 */
@Data
@Builder
public class Edge {
    private int weight;
    private Node from;
    private Node to;
}
