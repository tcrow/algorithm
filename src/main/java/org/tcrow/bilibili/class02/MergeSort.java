package org.tcrow.bilibili.class02;

/**
 * 归并排序 可以用来求小和
 */
public class MergeSort {

    private static void swap(int[] arr, int a, int b) {
        if(arr[a] == arr[b]){
            return;
        }
        arr[a] ^= arr[b];
        arr[b] ^= arr[a];
        arr[a] ^= arr[b];
    }

    public static void main(String[] args) {
        int[] arr = {1, 99};
        swap(arr, 1, 0);
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }
}
