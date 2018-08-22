package org.tcrow.mutiple;

import com.google.common.base.Stopwatch;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author tcrow.luo
 * 实现多线程顺序打印abc字符串
 */
public class MultipleThreadPrint {

    /**
     * 状态位因print方法已加锁，无需使用AtomicInteger类提高处理效率
     */
    private static int i = 0;

    /**
     * 校验字符串因print方法已加锁，无需使用StringBuffer提高处理效率
     */
    private static StringBuilder validateSb = new StringBuilder();

    private final static int PRINT_TIMES = 50;
    private final static int THREAD_NUM = 3;

    private static class Print implements Runnable {
        @Override
        public void run() {
            for (int j = 0; j < PRINT_TIMES; j++) {
                print();
            }
        }
    }

    /**
     * 方法加锁保证打印时其他线程不会抢先打印
     *
     * @return
     */
    private synchronized static char print() {
        char c = (char) ('a' + i++ % 3);
        System.out.print(c);
        validateSb.append(c);
        return c;
    }

    public static void main(String[] args) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        ExecutorService pool = Executors.newFixedThreadPool(THREAD_NUM);
        for (int j = 0; j < THREAD_NUM; j++) {
            new Thread(new Print()).start();
            pool.execute(new Print());
        }
        pool.shutdown();
        while (true) {
            if (pool.isTerminated()) {
                stopwatch.stop();
                System.out.println("\n multiple thread spend time:" + stopwatch.toString());
                StringBuilder correct = new StringBuilder();
                stopwatch.reset();
                stopwatch.start();
                for (int j = 0; j < PRINT_TIMES * THREAD_NUM; j++) {
                    char c = (char) ('a' + j % 3);
                    correct.append(c);
                    System.out.print(c);
                }
                System.out.println("\nsingle thread spend time:" + stopwatch.toString());
                System.out.println(correct.toString().equals(validateSb.toString()));
                break;
            }
        }
    }
}
