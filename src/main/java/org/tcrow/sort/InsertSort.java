package org.tcrow.sort;

/**
 * @author pp
 * @date 2018/8/21
 * @description
 */
public class InsertSort implements SortInterface{
    @Override
    public int[] sort(int[] arr) {
        // 8 2 4 9 3 6
        // 2 8 4 9 3 6
        // 2 4 8 9 3 6
//        arr = new int[]{8, 2, 4 ,9, 3, 6};
        int key;
        for (int i = 1; i < arr.length; i++) {
            key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key){
                arr[j + 1] = arr[j];
                j -- ;
            }
            arr[j + 1] = key;
        }

        return arr;
    }
}
