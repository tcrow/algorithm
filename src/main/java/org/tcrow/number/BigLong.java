package org.tcrow.number;

/**
 * @author tcrow.luo
 *         10进制大数类型处理类
 */
public class BigLong {

    /**
     * 表示正负，1表示正数，-1表示负数，0表示0
     */
    final int signum;

    /**
     * 拆解大数字符保存在整形数组中
     */
    final int[] mag;

    /**
     * 无符号数的处理常量
     */
    final static long LONG_MASK = 0xffffffffL;

    /**
     * 十进制分组数值位数
     */
    final static int DIGIT_DEC = 9;

    /**
     * 十进制转二进制最佳位数乘以*1024向上取证，具体算法x=log2(10) * 1024
     * 也可以用公示计算其他进制数转二进制，算法 x = log2(radix) * 1024
     * 这里为简单只支持十进制，主要用来计算数组初始化值，乘以1024是可以用整数标识，比较好看而已
     */
    final static int BIT_OPTIMUM_LEN = 3402;

    /**
     * 十进制每组数据进位差值10^9
     */
    final static int DEC_STEP = 0x3b9aca00;

    /**
     * 用于表示十进制
     */
    final static int DEFAULT_RADIX = 10;

    BigLong(int[] mag, int signum) {
        this.mag = mag;
        this.signum = signum;
    }

    public BigLong(String val) {
        int cursor = 0, numDigits;
        final int len = val.length();

        if (len == 0) {
            throw new NumberFormatException("Zero length BigInteger");
        }

        // 检查大数符号
        int sign = 1;
        int index1 = val.lastIndexOf('-');
        int index2 = val.lastIndexOf('+');
        if (index1 >= 0) {
            if (index1 != 0 || index2 >= 0) {
                throw new NumberFormatException("Illegal embedded sign character");
            }
            sign = -1;
            cursor = 1;
        } else if (index2 >= 0) {
            if (index2 != 0) {
                throw new NumberFormatException("Illegal embedded sign character");
            }
            cursor = 1;
        }
        if (cursor == len) {
            throw new NumberFormatException("Zero length BigInteger");
        }

        //去掉头部的0
        while (cursor < len && Character.digit(val.charAt(cursor), DEFAULT_RADIX) == 0) {
            cursor++;
        }

        //没有发现有效数字则直接返回一个值为0的对象
        if (cursor == len) {
            signum = 0;
            mag = new int[0];
            return;
        }

        //计算有效长度
        numDigits = len - cursor;
        signum = sign;

        //计算初始化数组长度
        int numBits = (int) (((numDigits * BIT_OPTIMUM_LEN) >>> 10) + 1);
        int numWords = (numBits + 31) / 32;
        int[] magnitude = new int[numWords];

        int firstGroupLen = numDigits % DIGIT_DEC;
        if (firstGroupLen == 0) {
            firstGroupLen = DIGIT_DEC;
        }
        String group = val.substring(cursor, cursor += firstGroupLen);
        magnitude[numWords - 1] = Integer.parseInt(group);

        //对剩余数据赋值数组
        int groupVal;
        while (cursor < len) {
            group = val.substring(cursor, cursor += DIGIT_DEC);
            groupVal = Integer.parseInt(group);
            if (groupVal < 0){
                throw new NumberFormatException("Illegal digit");
            }
            destructiveMulAdd(magnitude,DEC_STEP,groupVal);
        }
        mag = trustedStripLeadingZeroInts(magnitude);
    }

    /**
     * 加法运算
     *
     * @param val
     * @return
     */
    public BigLong add(BigLong val) {
        if (val.signum == 0) {
            return this;
        }
        if (this.signum == 0) {
            return val;
        }

        if (this.signum != val.signum) {
            BigLong thiB = new BigLong(this.toString().replaceAll("-", ""));
            BigLong valB = new BigLong(val.toString().replaceAll("-", ""));
            int compare = compareMagnitude(thiB, valB);
            int sign;
            if (compare == 0) {
                return new BigLong(new int[0], 0);
            }

            if (compare > 0) {
                sign = signum;
            } else {
                sign = val.signum;
            }
            return new BigLong(sub(mag, val.mag), sign);
        }

        return new BigLong(add(mag, val.mag), signum);
    }

    /**
     * 无符号数相加
     *
     * @param x
     * @param y
     * @return
     */
    private int[] add(int[] x, int[] y) {
        boolean cb = false;
        StringBuffer result = new StringBuffer();

        int len = x.length > y.length ? x.length : y.length;

        for (int i = 0; i < len; i++) {
            //短数组高位补0
            int thiV = i < x.length ? x[i] : 0;
            int valV = i < y.length ? y[i] : 0;

            result.append((thiV + valV + (cb ? 1 : 0)) % 10);

            if (thiV + valV > 9 || (thiV + valV + (cb ? 1 : 0)) == 10) {
                cb = true;
            } else {
                cb = false;
            }
        }
        if (cb) {
            result.append(1);
        }
        return new BigLong(result.reverse().toString()).mag;
    }

    /**
     * 将剩余数据表示为10^9塞入数组中
     *
     * @param x
     * @param y
     * @param z
     */
    private static void destructiveMulAdd(int[] x, int y, int z) {
        // 将整形转换成长整形
        long ylong = y & LONG_MASK;
        long zlong = z & LONG_MASK;
        int len = x.length;

        long product = 0;
        long carry = 0;
        for (int i = len - 1; i >= 0; i--) {
            product = ylong * (x[i] & LONG_MASK) + carry;
            x[i] = (int) product;
            carry = product >>> 32;
        }

        // Perform the addition
        long sum = (x[len - 1] & LONG_MASK) + zlong;
        x[len - 1] = (int) sum;
        carry = sum >>> 32;
        for (int i = len - 2; i >= 0; i--) {
            sum = (x[i] & LONG_MASK) + carry;
            x[i] = (int) sum;
            carry = sum >>> 32;
        }
    }

    /**
     * 减法运算
     *
     * @param val
     * @return
     */
    public BigLong sub(BigLong val) {
        if (val.signum == 0) {
            return this;
        }

        if (signum == 0) {
            return val.negate();
        }

        if (this.signum != val.signum) {
            if (this.signum < 0) {
                return new BigLong(val.add(new BigLong(this.toString().replaceFirst("-", ""))).mag, signum);
            }
            return this.add(new BigLong(val.toString().replaceFirst("-", "")));
        }

        int compare = compareMagnitude(this, val);

        if (compare == 0) {
            return new BigLong("0");
        }

        return new BigLong(sub(mag, val.mag), ((compare < 0) ? this.negate().signum : signum));
    }

    /**
     * 无符号数相减，会自动用大数减小数，返回大数减小数的结果
     *
     * @param x
     * @param y
     * @return
     */
    private int[] sub(int[] x, int[] y) {
        int compare = compareMagnitude(new BigLong(x, 1), new BigLong(y, 1));
        int[] big;
        int[] little;
        if (compare > 0) {
            big = x;
            little = y;
        } else {
            big = y;
            little = x;
        }

        boolean cb = false;
        StringBuffer result = new StringBuffer();

        int len = big.length > little.length ? big.length : little.length;

        for (int i = 0; i < len; i++) {
            //防止数组溢出
            int thiV = i < big.length ? big[i] : 0;
            int valV = i < little.length ? little[i] : 0;

            int ret = thiV - valV - (cb ? 1 : 0);

            result.append(ret >= 0 ? ret : (ret + 10));

            if (thiV < valV || isLast(ret, cb)) {
                cb = true;
            } else {
                cb = false;
            }
        }
        return new BigLong(result.reverse().toString()).mag;
    }

    private boolean isLast(int ret, boolean cb) {
        return ret == -1 && cb == true;
    }

    /**
     * 转换成字符串
     *
     * @return
     */
    @Override
    public String toString() {
        if (signum == 0) {
            return "0";
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < mag.length; i++) {
            sb.append(mag[i]);
        }
        return signum >= 0 ? sb.reverse().toString() : "-" + sb.reverse().toString();
    }

    /**
     * 去掉数组中多余的0
     * @param val
     * @return
     */
    private static int[] trustedStripLeadingZeroInts(int val[]) {
        int vlen = val.length;
        int keep;

        // Find first nonzero byte
        for (keep = 0; keep < vlen && val[keep] == 0; keep++)
            ;
        return keep == 0 ? val : java.util.Arrays.copyOfRange(val, keep, vlen);
    }

    /**
     * 比较两个数大小,x == y 返回0 x > y 返回 1 x<y 返回-1
     *
     * @param x
     * @param y
     * @return
     */
    final int compareMagnitude(BigLong x, BigLong y) {
        int[] m1 = x.mag;
        int len1 = m1.length;
        int[] m2 = y.mag;
        int len2 = m2.length;
        if (len1 < len2) {
            return -1;
        }

        if (len1 > len2) {
            return 1;
        }

        for (int i = 0; i < len1; i++) {
            int a = m1[m1.length - 1 - i];
            int b = m2[m2.length - 1 - i];
            if (a != b) {
                return ((a & LONG_MASK) < (b & LONG_MASK)) ? -1 : 1;
            }
        }
        return 0;
    }

    /**
     * 反转符号
     *
     * @return
     */
    public BigLong negate() {
        return new BigLong(this.mag, -this.signum);
    }

    public static void main(String[] args) {
        System.out.printf(Integer.toHexString(1000000000));
    }

}
