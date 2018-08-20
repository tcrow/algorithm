package org.tcrow.sort;

/**
 * @author pp
 * @date 2018/8/20
 * @description
 * 简单选择排序
 */
public class SimpleChoiceSort implements SortInterface{
    @Override
    public int[] sort(int[] arr) {
        int min;

        for (int i = 0; i < arr.length; i++) {
            min = i;
            for (int j = i; j < arr.length; j++) {
                if(arr[min] > arr[j]){
                    min = j;
                }
            }
            if(min != i){
                Sort.swap(i ,min ,arr);
            }
        }

        return arr;
    }
}
