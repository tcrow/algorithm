package sort;

import com.google.common.base.Stopwatch;
import org.junit.Assert;
import org.junit.Test;
import org.tcrow.sort.Sort;
import org.tcrow.util.RandomUtil;

import java.math.BigDecimal;


/**
 * @author tcrow.luo
 * @date 2018/8/20
 * @description
 */
public class TestSort {

    //    final Integer[] arr = {39, 12, 46, 42, 19, 32, 68, 58, 11, 11, 33, 70, 47, 40, 79, 37, 50, 60, 67, 37, 64, 66, 91, 41, 62, 87, 88, 71, 8, 22, 74, 27, 27, 90, 41, 24, 50, 53, 92, 42, 33, 52, 74, 90, 7, 58, 32, 20, 25, 85, 5, 53, 34, 29, 75, 61, 74, 1, 0, 14, 99, 42, 18, 57, 38, 80, 54, 2, 48, 28, 76, 27, 95, 7, 36, 36, 48, 87, 53, 4, 0, 86, 76, 41, 20, 90, 40, 88, 22, 29, 62, 9, 48, 22, 19, 11, 30, 30, 97, 82};
    final Comparable[] arr = generateArray();
    final int len = 10000000;

    /**
     * 生成符合正态分布的数组
     *
     * @return
     */
    private Comparable[] gaussianRandom() {
        Comparable[] arr = new Comparable[len];
        for (int i = 0; i < len; i++) {
            arr[i] = new BigDecimal(getNumberInNormalDistribution(180, 10)).intValue();
        }
        return arr;
    }

    private double getNumberInNormalDistribution(double mean, double std_dev) {
        return mean + (randomNormalDistribution() * std_dev);
    }

    private double randomNormalDistribution() {
        double u, v, w, c;
        do {
            //获得两个（-1,1）的独立随机变量
            u = Math.random() * 2 - 1.0;
            v = Math.random() * 2 - 1.0;
            w = u * u + v * v;
        } while (w == 0.0 || w >= 1.0);
        //这里就是 Box-Muller转换
        c = Math.sqrt((-2 * Math.log(w)) / w);
        //返回2个标准正态分布的随机数，封装进一个数组返回
        //当然，因为这个函数运行较快，也可以扔掉一个
        //return [u*c,v*c];
        return u * c;
    }

    private Comparable[] generateArray() {
        Comparable[] arr = new Comparable[len];
        for (int i = 0; i < len; i++) {
            arr[i] = Integer.valueOf(RandomUtil.getIntegerBetween(0, len));
        }
        return arr;
    }

//    @Test
    public void testBubble() {
        Stopwatch stopwatch = Stopwatch.createStarted();
        Comparable[] result = Sort.sort(arr, null, "bubble");
        stopwatch.stop();
        System.out.println("bubble sort spend time:" + stopwatch.toString());
        Assert.assertTrue(Sort.isSorted(result));
    }

//    @Test
    public void testSimple() {
        Stopwatch stopwatch = Stopwatch.createStarted();
        Comparable[] result = Sort.sort(arr, null, "simple");
        stopwatch.stop();
        System.out.println("simple sort spend time:" + stopwatch.toString());
        Assert.assertTrue(Sort.isSorted(result));
    }

//    @Test
    public void testInsert() {
        Stopwatch stopwatch = Stopwatch.createStarted();
        Comparable[] result = Sort.sort(arr, null, "insert");
        stopwatch.stop();
        System.out.println("insert sort spend time:" + stopwatch.toString());
        Assert.assertTrue(Sort.isSorted(result));
    }

    @Test
    public void testShell() {
        Stopwatch stopwatch = Stopwatch.createStarted();
        Comparable[] result = Sort.sort(arr, null, "shell");
        stopwatch.stop();
        System.out.println("shell sort spend time:" + stopwatch.toString());
        Assert.assertTrue(Sort.isSorted(result));
    }

    @Test
    public void testQuick() {
        Stopwatch stopwatch = Stopwatch.createStarted();
        Comparable[] result = Sort.sort(arr, null, "quick");
        stopwatch.stop();
        System.out.println("quick sort spend time:" + stopwatch.toString());
        Assert.assertTrue(Sort.isSorted(result));
    }

    @Test
    public void testMerge() {
        Stopwatch stopwatch = Stopwatch.createStarted();
        Comparable[] result = Sort.sort(arr, null, "merge");
        stopwatch.stop();
        System.out.println("merge sort spend time:" + stopwatch.toString());
        Assert.assertTrue(Sort.isSorted(result));
    }

    @Test
    public void testMergeBu() {
        Stopwatch stopwatch = Stopwatch.createStarted();
        Comparable[] result = Sort.sort(arr, null, "mergeBu");
        stopwatch.stop();
        System.out.println("merge sort spend time:" + stopwatch.toString());
        Assert.assertTrue(Sort.isSorted(result));
    }

}
