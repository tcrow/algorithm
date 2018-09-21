package datastructure;

import common.Utils;
import org.junit.Assert;
import org.junit.Test;
import org.tcrow.datastructure.MaxPq;
import org.tcrow.datastructure.MinPq;

public class TestMaxPQ {

    int len = 10;

    final MaxPq pq = new MaxPq(len);
    final MinPq minPq = new MinPq(len);

    final Comparable arr[] = Utils.generateArray(len);


    @Test
    public void test() {
        Assert.assertTrue(pq.isEmpty());
        for (Comparable v : arr) {
            pq.insert(v);
        }
        Assert.assertTrue(pq.size() == len);
        System.out.println("min number is :" + pq.max());
        Assert.assertTrue(pq.size() == len);
        System.out.println(pq.toString());
        System.out.println("del min number is:" + pq.delMax());
        Assert.assertTrue(pq.size() == --len);
        System.out.println(pq.toString());
    }

    @Test
    public void testMin() {
        Assert.assertTrue(minPq.isEmpty());
        for (Comparable v : arr) {
            minPq.insert(v);
        }
        Assert.assertTrue(minPq.size() == len);
        System.out.println("min number is :" + minPq.min());
        Assert.assertTrue(minPq.size() == len);
        System.out.println(minPq.toString());
        System.out.println("del min number is:" + minPq.delTop());
        Assert.assertTrue(minPq.size() == --len);
        System.out.println(minPq.toString());
    }

}
