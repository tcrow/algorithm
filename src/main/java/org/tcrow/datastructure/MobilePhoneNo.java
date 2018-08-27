package org.tcrow.datastructure;

import com.alibaba.fastjson.JSONObject;
import com.google.common.io.FileWriteMode;
import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * @author tcrow.luo
 * @date 2018/8/27
 * @description
 * BitMap处理手机号去重
 */
public class MobilePhoneNo {

    /**
     * 用于存储手机号码是否存在
     * 因为中国手机号码都是1开头，所以第一位省略
     * 我们需要表示最大9999999999个号码是否存在
     * 1字节 = 8 bit 最多可以表示8个号码
     * 因此需要空间 9999999999 / 8 = 1249999999.875 约等于 125 * 10 ^ 7 字节 约为 1.165 G 空间
     * 直接加载到内存中比较浪费，因此可以创建一个二进制文件直接表示，然后通过RandomAccessFile类读文件相应的位
     */
//    byte[][] phoneNos = new byte[5][100000];

    public static void main(String[] args) throws IOException {
        byte[] b = new byte[1];
        b[0] = 0b1111111;
        Files.asByteSink(new File("d://mobile_phone"),FileWriteMode.APPEND).write(b);
        RandomAccessFile file = new RandomAccessFile("d://mobile_phone","r");
        byte[] a = new byte[1];
        file.read(a,0,1);
        System.out.println(a[0]);
    }
}
