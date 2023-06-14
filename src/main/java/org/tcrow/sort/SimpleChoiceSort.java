package org.tcrow.sort;

/**
 * @author tcrow.luo
 * @date 2018/8/20
 * @description 简单选择排序
 */
public class SimpleChoiceSort extends AbstractSort {
    @Override
    public Comparable[] sort(Comparable[] arr) {
        Comparable[] result = arr.clone();

        int min;

        for (int i = 0; i < result.length; i++) {
            min = i;
            for (int j = i; j < result.length; j++) {
                if (less(result[j], result[min])) {
                    min = j;
                }
            }
            if (min != i) {
                exchange(i, min, result);
            }
        }

        return result;
    }

}
