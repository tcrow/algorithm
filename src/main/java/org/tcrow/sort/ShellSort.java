package org.tcrow.sort;

/**
 * @author tcrow.luo
 * @date 2018/8/21
 * @description 希尔排序
 */
public class ShellSort implements SortInterface {

    private final static int STEP = 3;

    @Override
    public Comparable[] sort(Comparable[] arr) {
        int h = 1;
        while (h < arr.length / STEP) {
            h = h * 3 + 1;
        }
        while (h >= 1) {
            for (int i = h; i < arr.length; i++) {
                for (int j = i; j >= h && Sort.less(arr[j], arr[j - h]); j -= h) {
                    Sort.swap(j, j - h, arr);
                }
            }
            h = h / STEP;
        }
        return arr;
    }

    @Override
    public Comparable[] sort(Comparable[] arr, int low, int high) {
        return new Comparable[0];
    }
}
