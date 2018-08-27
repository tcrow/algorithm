package org.tcrow.resume.analyze;

import com.google.common.io.FileWriteMode;
import com.google.common.io.Files;
import org.tcrow.datastructure.Trie;
import org.tcrow.resume.vo.Resume;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author tcrow.luo
 *         简历队列消费者，消费完毕后移除队列
 */
public class AnalyzeConsumer implements Runnable {

    private LinkedBlockingQueue<Future<Resume>> msgQueue;

    private String outputFilePath;

    private int maxWaitSeconds;

    /**
     * 字典树用来记录手机号码是否重复，因为要写本地文件，因此消费者暂只支持单线程运行
     */
    private Trie trie;

    /**
     * @param msgQueue       消息队列
     * @param outputFilePath 输出文件路径
     * @param maxWaitSeconds 最大等待时间，如果等待超过最大等待时间则线程会自动关闭，以便程序能正确结束运行
     */
    public AnalyzeConsumer(final LinkedBlockingQueue<Future<Resume>> msgQueue, final String outputFilePath, final int maxWaitSeconds) {
        this.msgQueue = msgQueue;
        this.outputFilePath = outputFilePath;
        this.maxWaitSeconds = maxWaitSeconds;
    }

    /**
     * 输出到文件
     *
     * @param resume
     * @param outputFile
     * @throws ExecutionException
     * @throws InterruptedException
     * @throws IOException
     */
    private void writeFile(Resume resume, File outputFile) throws ExecutionException, InterruptedException, IOException {
        if (trie == null) {
            trie = new Trie();
        }
        try {
            if (trie.countPrefix(resume.getMobile()) == 0) {
                trie.insertStr(resume.getMobile());
                Files.asCharSink(outputFile, Charset.defaultCharset(), FileWriteMode.APPEND).write(resume.toString() + "\n");
            }else {
                trie.insertStr(resume.getMobile());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        File output = new File(outputFilePath);
        int count = 0;
        while (true) {
            Future<Resume> future = this.msgQueue.poll();
            //如果队列没有消息，则等待100毫秒
            if (future == null) {
                //超过最大等待时间则线程直接退出
                if (count > maxWaitSeconds * 1000 / 100) {
                    return;
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                count++;
            } else {
                //获取数据后将等待次数归0
                count = 0;
                while (true) {
                    if (future.isDone()) {
                        try {
                            writeFile(future.get(), output);
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    } else {
                        //未结束则等待20毫秒待简历分析线程执行完毕
                        try {
                            Thread.sleep(20);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }
        }

    }
}
