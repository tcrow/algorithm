package org.tcrow.leetcode;

import java.util.*;

public class FreqStack {

    private Map<Integer, Integer> map = new HashMap<>();
    private List<Stack<Integer>> stackList = new ArrayList<>();

    public FreqStack() {

    }

    public void push(int val) {
        int times = map.getOrDefault(val, 0) + 1;
        if (stackList.size() < times) {
            Stack<Integer> stack = new Stack<>();
            stack.push(val);
            stackList.add(stack);
        } else {
            Stack<Integer> stack = stackList.get(times - 1);
            stack.push(val);
        }
        map.put(val, times);
    }

    public int pop() {
        Stack<Integer> stack = stackList.get(stackList.size() - 1);
        Integer res = stack.pop();
        if (stack.isEmpty()) {
            stackList.remove(stackList.size() - 1);
        }
        map.put(res, map.get(res) - 1);
        return res;
    }

    public static void main(String[] args) {
        FreqStack freqStack = new FreqStack();
//        freqStack.push(5);//堆栈为 [5]
//        freqStack.push(7);//堆栈是 [5,7]
//        freqStack.push(5);//堆栈是 [5,7,5]
//        freqStack.push(7);//堆栈是 [5,7,5,7]
//        freqStack.push(4);//堆栈是 [5,7,5,7,4]
//        freqStack.push(5);//堆栈是 [5,7,5,7,4,5]
//        System.out.println(freqStack.pop());//返回 5 ，因为 5 出现频率最高。堆栈变成 [5,7,5,7,4]。
//        System.out.println(freqStack.pop());//返回 7 ，因为 5 和 7 出现频率最高，但7最接近顶部。堆栈变成 [5,7,5,4]。
//        System.out.println(freqStack.pop());//返回 5 ，因为 5 出现频率最高。堆栈变成 [5,7,4]。
//        System.out.println(freqStack.pop());//返回 4 ，因为 4, 5 和 7 出现频率最高，但 4 是最接近顶部的。堆栈变成 [5,7]。
        freqStack.push(1);
        freqStack.push(1);
        freqStack.push(1);
        freqStack.push(2);
        System.out.println(freqStack.pop());
        System.out.println(freqStack.pop());
        freqStack.push(2);
        freqStack.push(2);
        freqStack.push(1);
        System.out.println(freqStack.pop());
        System.out.println(freqStack.pop());
        System.out.println(freqStack.pop());
    }
}
