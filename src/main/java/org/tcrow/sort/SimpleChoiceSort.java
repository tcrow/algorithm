package org.tcrow.sort;

/**
 * @author tcrow.luo
 * @date 2018/8/20
 * @description 简单选择排序
 */
public class SimpleChoiceSort extends AbstractSort {
    @Override
    public Comparable[] sort(Comparable[] arr) {
        int min;

        for (int i = 0; i < arr.length; i++) {
            min = i;
            for (int j = i; j < arr.length; j++) {
                if (less(arr[j], arr[min])) {
                    min = j;
                }
            }
            if (min != i) {
                exchange(i, min, arr);
            }
        }

        return arr;
    }

}
