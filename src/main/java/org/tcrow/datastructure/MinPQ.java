package org.tcrow.datastructure;

import java.util.Arrays;

/**
 * @author tcrow.luo
 */
public class MinPq<Key extends Comparable<Key>> extends Pq<Key> {


    public MinPq(int maxN) {
        super(maxN);
    }

    public Key min() {
        return top();
    }


    @Override
    protected boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) > 0;
    }

    @Override
    public String toString() {
        return "MinPQ{" +
                "size=" + size +
                ", pq=" + Arrays.toString(pq) +
                '}';
    }
}
