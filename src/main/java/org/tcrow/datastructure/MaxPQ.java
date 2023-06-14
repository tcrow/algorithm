package org.tcrow.datastructure;

import java.util.Arrays;

/**
 * @author tcrow.luo
 *         优先队列，用于快速获取和删除最大值
 */
public class MaxPQ<Key extends Comparable<Key>> extends PQ<Key> {

    public MaxPQ(int maxN) {
        super(maxN);
    }


    public Key max() {
        return top();
    }

    public Key delMax() {
        return delTop();
    }


    @Override
    protected boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    @Override
    public String toString() {
        return "MaxPQ{" +
                "size=" + size +
                ", pq=" + Arrays.toString(pq) +
                '}';
    }
}
