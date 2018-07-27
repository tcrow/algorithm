package org.tcrow.filesplit;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author tcrow.luo
 *         读文件线程
 */
public class FileReadTask implements Runnable {

    private RandomAccessFile file;
    private int begin;
    private int lines;
    private LinkedBlockingDeque<String> queue;

    public FileReadTask(RandomAccessFile file, LinkedBlockingDeque<String> queue, int begin, int lines) {
        this.file = file;
        this.begin = begin;
        this.lines = lines;
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            file.seek(begin);
            for (int i = 0; i < lines; i++) {
                queue.add(file.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
