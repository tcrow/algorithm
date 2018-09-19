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
        int h = 1;
        while (h < arr.length / STEP) {
            h = h * 3 + 1;
        }
        while (h >= 1) {
            for (int i = h; i < arr.length; i++) {
                for (int j = i; j >= h && less(arr[j], arr[j - h]); j -= h) {
                    exchange(j, j - h, arr);
                }
            }
            h = h / STEP;
        }
        return arr;
    }

}
