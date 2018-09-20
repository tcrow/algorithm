package org.tcrow.sort;

/**
 * @author tcrow.luo
 * @date 2018/8/21
 * @description 希尔排序
 */
public class ShellSort extends AbstractSort {

    private final static int STEP = 3;

    @Override
    public Comparable[] sort(Comparable[] arr) {
        Comparable[] result = arr.clone();
        int h = 1;
        while (h < result.length / STEP) {
            h = h * 3 + 1;
        }
        while (h >= 1) {
            for (int i = h; i < result.length; i++) {
                for (int j = i; j >= h && less(result[j], result[j - h]); j -= h) {
                    exchange(j, j - h, result);
                }
            }
            h = h / STEP;
        }
        return result;
    }

}
