package org.tcrow.bilibili.class03;

import org.tcrow.sort.Sort;
import org.tcrow.util.PrintUtil;
import org.tcrow.util.RandomUtil;

/**
 * 堆排序
 * https://www.bilibili.com/video/BV13g41157hK?p=4
 */
public class HeapSort {
    /**
     * 向堆中插入一个数字
     *
     * @param arr   数组
     * @param index 当前索引位置
     */
    private static void heapInsert(int[] arr, int index) {
        // 子节点比父节点大，则与父节点进行交换，并且索引指向父节点
        while (arr[index] > arr[(index - 1) / 2]) {
            Sort.swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    /**
     * 把堆重新排序成大根堆
     *
     * @param arr
     * @param index
     * @param heapSize
     */
    private static void heapIfy(int[] arr, int index, int heapSize) {
        int left = index * 2 + 1;
        while (left < heapSize) {
            // 子节点找到最大一个，如果有右节点且右节点大于左节点，则右节点大
            int largest = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
            // 比较子节点是否比父节点大，如果子节点大于父节点，则直接交换
            if (arr[largest] > arr[index]) {
                Sort.swap(arr, index, largest);
            }
            // 如果没有子节点，直接跳出循环
            if (index == largest) {
                break;
            }
            index = largest;
            left = index * 2 + 1;
        }
    }

    /**
     * 堆排序：
     * 1、把数组所有节点插入堆中，并排列成大根堆
     * 2、把堆顶数字和堆底数字交换，并将堆缩小1，然后进行heapIfy，重新排序堆组成大根堆
     * 总体思想，利用大根堆堆顶值最大的特性，每次把堆顶最大值换到最后，将堆-1，则堆清空后，数组自然完成排序
     *
     * @param arr
     */
    private static void heapSort(int[] arr) {
        int heapSize = 0;
        for (int i = 0; i < arr.length; i++) {
            heapInsert(arr, i);
            heapSize++;
        }
        while (heapSize > 0) {
            Sort.swap(arr, 0, heapSize - 1);
            heapIfy(arr, 0, --heapSize);
        }
    }

    public static void main(String[] args) {
        int[] array = RandomUtil.getRandomArray(1, 10, 10);
        PrintUtil.print(array);
        for (int i = 0; i < 10; i++) {
            heapSort(array);
            PrintUtil.print(array);
        }
    }

}
