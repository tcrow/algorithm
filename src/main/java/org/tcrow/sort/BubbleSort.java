package org.tcrow.sort;

/**
 * @author tcrow.luo
 * @date 2018/8/20
 * @description 冒泡排序实现
 */
public class BubbleSort implements SortInterface {

    @Override
    public int[] sort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = arr.length - 1; j > i; j--) {
                if (arr[j - 1] > arr[j]) {
                    Sort.swap(j - 1, j, arr);
                }
            }
        }
        return arr;
    }
}
