package org.tcrow.resume.fac;

import com.google.common.collect.Lists;
import com.google.common.io.Files;
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
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author tcrow.luo
 *         解析工厂
 */
public class AnalyzeFactory {

    static Trie trie = new Trie();

    public static Analyze getInstance(AnalyzeEnum type) {
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

    private static void writeFile(List<Future<Resume>> futureList, File outputFile) throws ExecutionException, InterruptedException, IOException {
        System.out.println(futureList.size());
        List<Future<Resume>> doneFutures = Lists.newArrayList();
        for (Future<Resume> future : futureList) {
            while (true) {
                if (future.isDone()) {
                    Resume resume = future.get();
                    if (trie.countPrefix(resume.getMobile()) == 0) {
                        trie.insertStr(resume.getMobile());
                        Files.append(resume.toString() + "\n", outputFile, Charset.defaultCharset());
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
    public static void excute(String filePathDirectory, String outputFilePath) throws InterruptedException, IOException, ExecutionException {
        File output = new File(outputFilePath);
        File filePaths = new File(filePathDirectory);
        File[] files = new File[0];
        if (filePaths.isDirectory()) {
            files = filePaths.listFiles();
        }
        ExecutorService pool = Executors.newFixedThreadPool(4);
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
