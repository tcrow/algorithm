package org.tcrow.sort;

/**
 * @author tcrow.luo
 * @date 2018/8/20
 * @description 冒泡排序实现
 */
public class BubbleSort extends AbstractSort {

    @Override
    public Comparable[] sort(Comparable[] arr) {
        Comparable[] result = arr.clone();
        for (int i = 0; i < result.length; i++) {
            for (int j = result.length - 1; j > i; j--) {
                if (less(result[j], result[j - 1])) {
                    exchange(j - 1, j, result);
                }
            }
        }
        return result;
    }

}
