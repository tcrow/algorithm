package org.tcrow.mutiple;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class MultipleThreadPrint {

    private static AtomicInteger i = new AtomicInteger(0);

    private static class Print implements Runnable {

        @Override
        public void run() {
            for (int j = 0; j < 50; j++) {
                System.out.println((char)('a' + i.getAndAdd(1) % 3));
            }

        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.execute(new Print());
        executorService.execute(new Print());
        executorService.execute(new Print());
        executorService.shutdown();
    }
}
