package org.tcrow.od;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * 内存池
 * 题目描述
 * 有一个简易内存池，内存按照大小粒度分类，每个粒度有若干个可用内存资源。
 * 用户会进行一系列内存申请，需要按需分配内存池中的资源，返回申请结果成功失败列表。
 * 分配规则如下：
 * <p>
 * 分配的内存要大于等于内存的申请量
 * 存在满足需求的内存就必须分配
 * 优先分配粒度小的，但内存不能拆分使用
 * 需要按申请顺序分配
 * 先申请的先分配，有可用内存分配则申请结果为true
 * 没有可用则返回false
 * 注释：不考虑内存释放
 * 输入描述
 * 输入为两行字符串:
 * 第一行为内存池资源列表，
 * 包含内存粒度数据信息，粒度数据间用逗号分割，
 * 一个粒度信息内用冒号分割，冒号前为内存粒度大小，冒号后为数量，
 * 资源列表不大于1024
 * 每个粒度的数量不大于4096
 * 第二行为申请列表，
 * 申请的内存大小间用逗号分割，申请列表不大于100000
 * 如
 * 64:2,128:1,32:4,1:128
 * 50,36,64,128,127
 * <p>
 * 输出描述
 * 输出为内存池分配结果
 * 如true,true,true,false,false
 */
public class Test031 {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            String input = scanner.nextLine();
            String alloc = scanner.nextLine();
            solution(input, alloc);
        }
    }

    private static void solution(String input, String alloc) {
        List<Integer[]> memList = Arrays.stream(input.split(","))
                .map(o -> {
                    String[] split = o.split(":");
                    Integer[] array = new Integer[2];
                    array[0] = Integer.parseInt(split[0]);
                    array[1] = Integer.parseInt(split[1]);
                    return array;
                })
                .sorted(Comparator.comparingInt(o -> o[0]))
                .collect(Collectors.toList());

        List<Integer> allocList = Arrays.stream(alloc.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        for (Integer integer : allocList) {
            if (allocProcess(memList, integer)) {
                System.out.print("true ");
            } else {
                System.out.print("false ");
            }
        }
        System.out.println();

    }

    private static boolean allocProcess(List<Integer[]> memList, Integer integer) {
        for (int i = 0; i < memList.size(); i++) {
            Integer[] integers = memList.get(i);
            if (integer <= integers[0] && integers[1] > 0) {
                integers[1] = integers[1] - 1;
                return true;
            }
        }
        return false;
    }
}
