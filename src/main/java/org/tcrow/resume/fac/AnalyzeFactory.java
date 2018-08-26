package org.tcrow.resume.fac;

import com.google.common.collect.Lists;
import com.google.common.io.FileWriteMode;
import com.google.common.io.Files;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.tcrow.Trie;
import org.tcrow.resume.analyze.Analyze;
import org.tcrow.resume.analyze.Analyze51JobImpl;
import org.tcrow.resume.analyze.AnalyzeCallable;
import org.tcrow.resume.analyze.AnalyzeEnum;
import org.tcrow.vo.Resume;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author tcrow.luo
 *         解析工厂
 */
public enum AnalyzeFactory {

    instance;

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

    private void writeFile(List<Future<Resume>> futureList, File outputFile) throws ExecutionException, InterruptedException, IOException {
        List<Future<Resume>> doneFutures = Lists.newArrayList();
        //创建字典树用来记录手机号码是否重复
        Trie trie = new Trie();
        for (Future<Resume> future : futureList) {
            while (true) {
                if (future.isDone()) {
                    Resume resume = future.get();
                    if (trie.countPrefix(resume.getMobile()) == 0) {
                        trie.insertStr(resume.getMobile());
                        Files.asCharSink(outputFile, Charset.defaultCharset(), FileWriteMode.APPEND).write(resume.toString() + "\n");
                    }
                    doneFutures.add(future);
                    break;
                } else {
                    Thread.sleep(10);
                }
            }
        }
        if (doneFutures.size() > 0) {
            futureList.removeAll(doneFutures);
        }
    }

    /**
     * 多线程解析简历文件夹
     *
     * @param filePathDirectory
     * @param outputFilePath
     */
    public void excute(String filePathDirectory, String outputFilePath) throws InterruptedException, IOException, ExecutionException {
        File output = new File(outputFilePath);
        File filePaths = new File(filePathDirectory);
        File[] files = new File[0];
        if (filePaths.isDirectory()) {
            files = filePaths.listFiles();
        }
        //手动创建线程池，设置线程池大小最大不超过1024，防止过大导致oom
        ExecutorService pool = new ThreadPoolExecutor(4, 4,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(1024), new ThreadFactoryBuilder().setNameFormat("XX-task-%d").build(), new ThreadPoolExecutor.AbortPolicy());
        List<Future<Resume>> futureList = Lists.newArrayList();
        for (File resumeFile : files) {
            if (resumeFile.getName().indexOf("51job.com") > 0) {
                Future<Resume> future = pool.submit(new AnalyzeCallable(resumeFile.getPath()));
                futureList.add(future);
            }
        }
        pool.shutdown();
        while (true) {
            if (!pool.isTerminated()) {
                Thread.sleep(100);
                writeFile(futureList, output);
            } else {
                writeFile(futureList, output);
                break;
            }
        }

    }
}
