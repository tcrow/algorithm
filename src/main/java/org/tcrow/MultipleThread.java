package org.tcrow;/*
 * Copyright 2015 Administrator.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import com.google.common.base.Stopwatch;
import com.google.common.io.Files;
import org.tcrow.util.RandomUtil;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author pp
 * @date 2018/7/26
 * @description
 * 生成随机字符串大文本，主要用于生成算法性能测试数据
 */
public class MultipleThread {
    private final static String filePath = "D://testData/testData.txt";
    private final static int coreNum = 4;

    static class SbRunerable implements Runnable {

        @Override
        public void run() {
            System.out.println("Thread id " + Thread.currentThread().getId() + "is starting");
            StringBuilder sb = new StringBuilder();
            File file = new File(filePath);
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            int lineNum = 100000;
            for (int i = 0; i < lineNum; i++) {
                sb.append(RandomUtil.getStringByUUID() + "\r\n");
            }
            try {
                writeFile(sb, file);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private static synchronized void writeFile(StringBuilder sb, File file) throws IOException {
        Files.append(sb.toString(), file, Charset.defaultCharset());
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        File file = new File(filePath);
        Files.createParentDirs(file);
        if(!file.exists()){
            file.createNewFile();
        }
        String s = Files.readFirstLine(new File(filePath), Charset.defaultCharset());
        System.out.println(s);

        Stopwatch stopwatch = Stopwatch.createStarted();
        ExecutorService executorService = Executors.newFixedThreadPool(coreNum);
        int i = 1000;
        for (int j = 0; j < i; j++) {
            executorService.execute(new SbRunerable());
        }

        executorService.shutdown();
        while (true) {
            if (executorService.isTerminated()) {
                stopwatch.stop();
                System.out.println(stopwatch.toString());
                break;
            }
            Thread.sleep(RandomUtil.getIntegerBetween(0, 1000));
        }

    }
}
