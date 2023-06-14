package org.tcrow.datastructure;

/**
 * @author tcrow.luo
 */
public abstract class PQ<Key extends Comparable<Key>> {
    int size = 0;
    protected Key[] pq;

    public PQ(int maxN) {
        pq = (Key[]) new Comparable[maxN + 1];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void insert(Key v) {
        pq[++size] = v;
        swim(size);
    }

    public Key delTop() {
        Key min = top();
        exchange(1, size--);
        pq[size + 1] = null;
        sink(1);
        return min;
    }

    protected void sink(int k) {
        while (2 * k <= size) {
            int j = 2 * k;
            if (j < size && less(j, j + 1)) {
                j++;
            }
            if (!less(k, j)) {
                break;
            }
            exchange(k, j);
            k = j;
        }
    }

    public static Comparable[] sort(Comparable[] arr) {
        int N = arr.length - 1;
        for (int k = N / 2; k >= 0; k--) {
            sink(arr, k, N);
        }
        while (N > 0) {
            exchange(arr, 0, N--);
            sink(arr, 0, N);
        }
        return arr;
    }

    private static void exchange(Comparable[] arr, int i, int j) {
        Comparable t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    private static void sink(Comparable[] arr, int k, int n) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && less(arr, j, j + 1)) {
                j++;
            }
            if (!less(arr, k, j)) {
                break;
            }
            exchange(arr, k, j);
            k = j;
        }
    }

    private static boolean less(Comparable[] arr, int i, int j) {
        return arr[i].compareTo(arr[j]) < 0;
    }

    /**
     * 比较大小
     *
     * @param i
     * @param j
     * @return
     */
    protected abstract boolean less(int i, int j);

    protected void exchange(int i, int j) {
        Key t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }

    protected void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            exchange(k / 2, k);
            k = k / 2;
        }
    }

    protected Key top() {
        return pq[1];
    }
}
