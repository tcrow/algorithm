package org.tcrow.mutiple;

import com.google.common.base.Stopwatch;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author tcrow.luo
 *         无线程调度，实现多线程顺序打印abc字符串
 */
public class MultipleThreadPrint {

    private static AtomicInteger i = new AtomicInteger(0);

    private static StringBuffer validateSb = new StringBuffer();

    private final static int PRINT_TIMES = 50;

    private static class Print implements Runnable {

        @Override
        public void run() {
            for (int j = 0; j < PRINT_TIMES; j++) {
                print();
            }

        }
    }

    private synchronized static void print() {
        char c = (char) ('a' + i.getAndIncrement() % 3);
        System.out.print(c);
        validateSb.append(c);
    }

    public static void main(String[] args) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        ExecutorService pool = Executors.newFixedThreadPool(3);
        pool.execute(new Print());
        pool.execute(new Print());
        pool.execute(new Print());
        pool.shutdown();
        while (true) {
            if (pool.isTerminated()){
                stopwatch.stop();
                System.out.println("\n" + stopwatch.toString());
                String correct = "abcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabc";
                System.out.println(correct.equals(validateSb.toString()));
                break;
            }
        }
    }
}
