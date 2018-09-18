package org.tcrow.sort;

/**
 * @author tcrow.luo
 * @date 2018/8/20
 * @description 冒泡排序实现
 */
public class BubbleSort implements SortInterface {

    @Override
    public Comparable[] sort(Comparable[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = arr.length - 1; j > i; j--) {
                if (Sort.less(arr[j], arr[j - 1])) {
                    Sort.exchange(j - 1, j, arr);
                }
            }
        }
        return arr;
    }

    @Override
    public Comparable[] sort(Comparable[] arr, int low, int high) {
        //todo
        return new Comparable[0];
    }
}
