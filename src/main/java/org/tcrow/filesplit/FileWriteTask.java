package org.tcrow.filesplit;

import java.io.RandomAccessFile;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author tcrow.luo
 *         写文件线程
 */
public class FileWriteTask implements Runnable {

    private RandomAccessFile file;
    private int begin;
    private int lines;
    private LinkedBlockingDeque<String> queue;


    public FileWriteTask(RandomAccessFile file, LinkedBlockingDeque<String> queue, int begin, int lines) {

    }

    @Override
    public void run() {
//        file.getChannel().map(FileChannel.MapMode.READ_WRITE,0,)
        for (int i = 0; i < lines; i++) {
            queue.poll();
        }

    }
}
