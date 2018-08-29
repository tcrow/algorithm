package org.tcrow.sort;

/**
 * @author tcrow.luo
 * @date 2018/8/21
 * @description
 */
public class InsertSort implements SortInterface {
    @Override
    public Comparable[] sort(Comparable[] arr) {
        // 8 2 4 9 3 6
        // 2 8 4 9 3 6
        // 2 4 8 9 3 6
//        arr = new int[]{8, 2, 4 ,9, 3, 6};
        Comparable key;
        for (int i = 1; i < arr.length; i++) {
            key = arr[i];
            int j = i - 1;
            while (j >= 0 && Sort.less(key, arr[j])) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }

        return arr;
    }
}
