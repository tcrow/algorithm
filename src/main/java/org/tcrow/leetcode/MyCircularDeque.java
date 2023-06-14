package org.tcrow.leetcode;

import java.util.LinkedList;
import java.util.NoSuchElementException;

public class MyCircularDeque {
    private int capacity;
    private LinkedList<Integer> queue;

    public MyCircularDeque(int k) {
        this.capacity = k;
        this.queue = new LinkedList<>();
    }

    public boolean insertFront(int value) {
        if (isFull()) {
            return false;
        }
        queue.addFirst(value);
        return true;
    }

    public boolean insertLast(int value) {
        if (isFull()) {
            return false;
        }
        queue.addLast(value);
        return true;
    }

    public boolean deleteFront() {
        try {
            queue.removeFirst();
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean deleteLast() {
        try {
            queue.removeLast();
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public int getFront() {
        try {
            return queue.getFirst();
        } catch (NoSuchElementException e) {
            return -1;
        }
    }

    public int getRear() {
        try {
            return queue.getLast();
        } catch (NoSuchElementException e) {
            return -1;
        }
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public boolean isFull() {
        return queue.size() == capacity;
    }
}
