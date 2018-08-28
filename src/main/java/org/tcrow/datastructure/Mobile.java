package org.tcrow.datastructure;

import com.google.common.io.FileWriteMode;
import com.google.common.io.Files;

import javax.annotation.concurrent.ThreadSafe;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.regex.Pattern;

/**
 * @author tcrow.luo
 * @date 2018/8/27
 * @description BitMap处理手机号去重，支持海量手机号数据去重，处理时间毫秒级，理论上经过改造可以支持更大的整数去重运算，但是初始化需要占用更多的存储空间
 */
@ThreadSafe
public class Mobile {

    private final static int INIT_BUFFER_SIZE = 1024 * 1024;
    /**
     * 正则表达式：验证手机号
     */
    private final static String REGEX_MOBILE = "^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";

    /**
     * 二进制1~8位分别为1的值，与原值进行或操作即可完成在号码库的新增操作
     */
    private final static byte[] ARRAY_BYTE = {0b00000001, 0b00000010, 0b00000100, 0b00001000, 0b00010000, 0b00100000, 0b01000000, -0b10000000};


    /**
     * 二进制掩码，-1 用二进制表示 为 11111111
     * 因此任何字节异或掩码后可以获得取反值，例如 00000001 ^ 11111111 = 11111110
     */
    private final static byte MASK_BYTE = -1;

    /**
     * 用于存储手机号码是否存在
     * 因为中国手机号码都是1开头，所以第一位省略
     * 我们需要表示最大9999999999个号码是否存在
     * 1字节 = 8 bit 最多可以表示8个号码
     * 因此需要空间 9999999999 / 8 = 1249999999.875 约等于 125 * 10 ^ 7 字节 约为 1.165 G 空间
     * 直接加载到内存中比较浪费，因此可以创建一个二进制文件直接表示，然后通过RandomAccessFile类读文件相应的位
     */
    private File dictFile;

    private RandomAccessFile file;

    /**
     * 读写全局锁，保证 读读共享, 读写互斥, 写写互斥
     */
    private final static ReadWriteLock LOCK = new ReentrantReadWriteLock();

    public Mobile(final String filePath) throws FileNotFoundException {
        dictFile = new File(filePath);
        file = new RandomAccessFile(dictFile, "rw");
        if (!dictFile.exists()) {
            try {
                init();
                return;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void init() throws IOException {
        LOCK.writeLock().lock();
        try {
            Files.createParentDirs(dictFile);
            int loop = 1250000000 / INIT_BUFFER_SIZE + 1;
            byte[] buffer = new byte[INIT_BUFFER_SIZE];
            for (int i = 0; i < loop; i++) {
                Files.asByteSink(dictFile, FileWriteMode.APPEND).write(buffer);
            }
        } finally {
            LOCK.writeLock().unlock();
        }
    }

    /**
     * 新增电话号码到字典中
     *
     * @param mobile
     */
    public void insert(String mobile) throws IOException {
        if (!isMobile(mobile)) {
            throwExcption(mobile);
        }
        if (hasMobile(mobile)) {
            return;
        }
        long no = Long.parseLong(mobile) - 10000000000L;
        int byteNum = (int) (no / 8);
        int bit = (int) (no % 8);
        byte b = read(byteNum);
        //对应位或操作 e.g. 11100000 | 00000001 == 11100001 则成功插入了标志位
        b = (byte) (b | ARRAY_BYTE[bit]);
        write(byteNum, b);
    }

    /**
     * 从字典中删除电话号码
     * @param mobile
     * @throws IOException
     */
    public void delete(String mobile) throws IOException {
        if (!isMobile(mobile)) {
            throwExcption(mobile);
        }
        if (!hasMobile(mobile)) {
            return;
        }
        long no = Long.parseLong(mobile) - 10000000000L;
        int byteNum = (int) (no / 8);
        int bit = (int) (no % 8);
        byte b = read(byteNum);
        //字节与操作 e.g. 00010001 & 11111110 == 00010000 则成功删除了标志位，对应标志位异或掩码得对应的补码 e.g. 00000001 ^ 11111111 == 11111110
        b = (byte) (b & (ARRAY_BYTE[bit] ^ MASK_BYTE));
        write(byteNum, b);
    }

    private void throwExcption(String mobile) {
        throw new RuntimeException("The string \"" + mobile + "\" is not the mobile number.");
    }

    private void throwUnknowExcption() {
        throw new RuntimeException("read data unknown exception");
    }

    public boolean hasMobile(String mobile) throws IOException {
        if (!isMobile(mobile)) {
            throwExcption(mobile);
        }
        long no = Long.parseLong(mobile) - 10000000000L;
        int byteNum = (int) (no / 8);
        int bit = (int) (no % 8);
        byte b = read(byteNum);
        // 01000111 & 00000010 == 00000010   01000101 & 00000010 == 0
        // 10111111 | 11111101 == 11111111 == -1  10111101 | 11111101 == 11111101 != -1
        // 两种判断方式都可以实现，取简单的方式直接按位与操作后等于判断位则表示标志位有值
        if (ARRAY_BYTE[bit] == (byte) (b & ARRAY_BYTE[bit])) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isMobile(String mobile) {
        return Pattern.matches(REGEX_MOBILE, mobile);
    }

    private byte read(int byteNum) throws IOException {
        LOCK.readLock().lock();
        byte[] buffer = new byte[1];
        try {
            file.seek(byteNum);
            int read = file.read(buffer);
            if (read <= 0) {
                throwUnknowExcption();
            }
        } finally {
            LOCK.readLock().unlock();
        }
        return buffer[0];
    }

    private void write(int byteNum, byte b) throws IOException {
        LOCK.writeLock().lock();
        try {
            file.seek(byteNum);
            byte[] buffer = new byte[1];
            buffer[0] = b;
            file.write(buffer);
        } finally {
            LOCK.writeLock().unlock();
        }
    }

}
