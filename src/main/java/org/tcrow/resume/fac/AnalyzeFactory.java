package org.tcrow.resume.fac;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.tcrow.datastructure.Mobile;
import org.tcrow.resume.analyze.*;
import org.tcrow.resume.vo.Resume;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.*;

/**
 * @author tcrow.luo
 *         解析工厂（单例对象，通过enum实现，线程安全）
 */
public enum AnalyzeFactory {

    /**
     * 单例对象实例
     */
    instance;

    /**
     * 线程池队列最大长度，防止OOM
     */
    private final static int MAX_THREAD_QUEUE = 1024;

    /**
     * 结束循环单次循环等待时间
     */
    private final static int AWAIT_LOOP_TIME = 100;

    /**
     * 等待队列消息关闭线程时间
     */
    private final static int MAX_WAIT_TIME = 2;

    public Analyze getAnalyze(AnalyzeEnum type) {
        switch (type) {
            case JOB51: {
                return new Analyze51JobImpl();
            }
            case ZHILIAN:
                break;
            default: {

            }
        }
        return null;
    }


    /**
     * 多线程解析简历文件夹
     *
     * @param filePathDirectory
     * @param outputFilePath
     */
    public void execute(final String filePathDirectory, final String outputFilePath, final Mobile mobile) throws InterruptedException, IOException, ExecutionException {
        File filePaths = new File(filePathDirectory);
        File[] files = new File[0];
        if (filePaths.isDirectory()) {
            files = filePaths.listFiles();
        }
        //手动创建线程池，设置线程池大小最大不超过MAX_THREAD_QUEUE + 1，防止过大导致oom，+ 1是因为有1个消费者线程
        ExecutorService pool = new ThreadPoolExecutor(4, 4,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(MAX_THREAD_QUEUE + 1), new ThreadFactoryBuilder().setNameFormat("analyze-task-%d").build(), new ThreadPoolExecutor.AbortPolicy());
        LinkedBlockingQueue<Future<Resume>> msgQueue = new LinkedBlockingQueue<>(MAX_THREAD_QUEUE);
        pool.execute(new AnalyzeConsumer(msgQueue, outputFilePath, MAX_WAIT_TIME, mobile));
        for (File resumeFile : files) {
            if (resumeFile.getName().indexOf("51job.com") > 0) {
                Future<Resume> future = pool.submit(new AnalyzeCallable(resumeFile.getPath()));
                while (true) {
                    if (msgQueue.offer(future)) {
                        break;
                    } else {
                        Thread.sleep(100);
                    }
                }
            }
        }
        pool.shutdown();

        while (!pool.awaitTermination(AWAIT_LOOP_TIME, TimeUnit.MILLISECONDS)) {

        }

    }
}
