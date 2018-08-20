package org.tcrow.sort;

import com.google.common.base.Strings;

/**
 * @author pp
 * @date 2018/8/20
 * @description
 */
public class Sort {

    public static int[] sort(int[] arr ,String sortType ,String type){

        if(Strings.isNullOrEmpty(sortType)){
            sortType = "asc";
        }

        int[] result = arr;

        if("bubble".equals(type)){
            result = new BubbleSort().sort(arr);
        }else if("simple".equals(type)){
            result = new SimpleChoiceSort().sort(arr);
        }else if("insert".equals(type)){
            result = new InsertSort().sort(arr);
        }

        if(!"asc".equals(sortType.toLowerCase())){
            return Sort.reverse(arr);
        }
        return result;
    }

    /**
     * 倒序数组
     * @param result
     * @return
     */
    public static int[] reverse(int[] result){
        int [] tmp = new int[result.length];
        for (int i = 0; i < result.length; i++) {
            tmp[result.length - i - 1] = result[i];
        }
        return tmp;
    }

    public static void swap(int a ,int b ,int[] arr){
        int tmp = arr[b];
        arr[b] = arr[a];
        arr[a] = tmp;
    }
}
