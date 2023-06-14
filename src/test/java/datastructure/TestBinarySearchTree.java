package datastructure;

import common.Utils;
import org.junit.Assert;
import org.junit.Test;
import org.tcrow.datastructure.BinarySearchTree;

public class TestBinarySearchTree {

    int len = 1000;

    final Integer keys[];
    final Integer values[];

    public TestBinarySearchTree() {
        Comparable[] keyCs = Utils.generateArrayNoRepeat(len);
        Comparable[] valueVs = Utils.generateArray(len);
        keys = new Integer[len];
        for (int i = 0; i < len; i++) {
            keys[i] = (Integer) keyCs[i];
        }

        values = new Integer[len];
        for (int i = 0; i < len; i++) {
            values[i] = (Integer) valueVs[i];
        }
    }

    @Test
    public void test_1() {
        BinarySearchTree<Integer, Integer> binarySearchTree = new BinarySearchTree();
        for (int i = 0; i < len; i++) {
            binarySearchTree.put(keys[i], values[i]);
        }

        for (int i = 0; i < len; i++) {
            Assert.assertTrue(binarySearchTree.get(keys[i]).compareTo(values[i]) == 0);
        }
        Integer max = -1;
        for (int i = 0; i < len; i++) {
            if (keys[i].compareTo(max) > 0) {
                max = keys[i];
            }
        }

        Integer min = len + 1;
        for (int i = 0; i < len; i++) {
            if (keys[i].compareTo(min) < 0) {
                min = keys[i];
            }
        }
        Assert.assertTrue(binarySearchTree.max().compareTo(max) == 0);
        Assert.assertTrue(binarySearchTree.min().compareTo(min) == 0);
        Assert.assertTrue(binarySearchTree.size() == len);
    }

    @Test
    public void test_2(){
        BinarySearchTree<Integer, Integer> binarySearchTree = new BinarySearchTree();
        binarySearchTree.put(5,555);
        binarySearchTree.put(1,111);
        binarySearchTree.put(10,101010);
        Assert.assertTrue(binarySearchTree.floor(3).compareTo(1) == 0);
        Assert.assertTrue(binarySearchTree.ceiling(3).compareTo(5) == 0);
        Assert.assertTrue(binarySearchTree.ceiling(7).compareTo(10) == 0);
        Assert.assertTrue(binarySearchTree.floor(7).compareTo(5) == 0);
    }


    @Test
    public void test_3(){
        BinarySearchTree<Integer, Integer> binarySearchTree = new BinarySearchTree();
        binarySearchTree.put(5,555);
        binarySearchTree.put(1,111);
        binarySearchTree.put(10,101010);
        System.out.println(binarySearchTree.select(0));
        System.out.println(binarySearchTree.rank(11));
    }

}
