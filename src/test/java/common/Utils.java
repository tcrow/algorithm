package common;

import org.tcrow.util.RandomUtil;

import java.math.BigDecimal;

public class Utils {

    public static Comparable[] generateArray(int len) {
        Comparable[] arr = new Comparable[len];
        for (int i = 0; i < len; i++) {
            arr[i] = Integer.valueOf(RandomUtil.getIntegerBetween(0, len));
        }
        return arr;
    }

    private static boolean isRepeat(Comparable[] arr, int x) {
        for (int i = 0; i < x; i++) {
            if (arr[i].compareTo(arr[x]) == 0) {
                return true;
            }
        }
        return false;
    }

    public static Comparable[] generateArrayNoRepeat(int len) {
        Comparable[] arr = new Comparable[len];
        for (int i = 0; i < len; i++) {
            arr[i] = Integer.valueOf(RandomUtil.getIntegerBetween(0, len));
            while (isRepeat(arr, i)) {
                arr[i] = Integer.valueOf(RandomUtil.getIntegerBetween(0, len));
            }
        }
        return arr;
    }

    private static double getNumberInNormalDistribution(double mean, double std_dev) {
        return mean + (randomNormalDistribution() * std_dev);
    }

    private static double randomNormalDistribution() {
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

    /**
     * 生成符合正态分布的数组
     *
     * @return
     */
    public static Comparable[] gaussianRandom(int len) {
        Comparable[] arr = new Comparable[len];
        for (int i = 0; i < len; i++) {
            arr[i] = new BigDecimal(getNumberInNormalDistribution(180, 10)).intValue();
        }
        return arr;
    }
}
