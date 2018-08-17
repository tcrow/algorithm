package org.tcrow;

/**
 * @author tcrow.luo
 *         10进制大数类型处理类
 */
public class LongLong {

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
     * 用于表示十进制
     */
    final static int DEFAULT_RADIX = 10;

    LongLong(int[] mag, int signum) {
        this.mag = mag;
        this.signum = signum;
    }

    public LongLong(String val) {
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

        mag = new int[numDigits];
        while (cursor < len) {
            mag[--numDigits] = Integer.parseInt(val.substring(cursor, ++cursor));
        }
    }

    /**
     * 加法运算
     *
     * @param val
     * @return
     */
    public LongLong add(LongLong val) {
        if (val.signum == 0) {
            return this;
        }
        if (this.signum == 0) {
            return val;
        }

        if (this.signum != val.signum) {
            LongLong thiB = new LongLong(this.toString().replaceAll("-", ""));
            LongLong valB = new LongLong(val.toString().replaceAll("-", ""));
            int compare = compareMagnitude(thiB, valB);
            int sign;
            if (compare == 0) {
                return new LongLong(new int[0], 0);
            }

            if (compare > 0) {
                sign = signum;
            } else {
                sign = val.signum;
            }
            return new LongLong(sub(mag, val.mag), sign);
        }

        return new LongLong(add(mag, val.mag), signum);
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
        return new LongLong(result.reverse().toString()).mag;
    }

    /**
     * 减法运算
     *
     * @param val
     * @return
     */
    public LongLong sub(LongLong val) {
        if (val.signum == 0) {
            return this;
        }

        if (signum == 0) {
            return val.negate();
        }

        if (this.signum != val.signum) {
            if (this.signum < 0) {
                return new LongLong(val.add(new LongLong(this.toString().replaceFirst("-", ""))).mag, signum);
            }
            return this.add(new LongLong(val.toString().replaceFirst("-", "")));
        }

        int compare = compareMagnitude(this, val);

        if (compare == 0) {
            return new LongLong("0");
        }

        return new LongLong(sub(mag, val.mag), ((compare < 0) ? this.negate().signum : signum));
    }

    /**
     * 无符号数相减，会自动用大数减小数，返回大数减小数的结果
     *
     * @param x
     * @param y
     * @return
     */
    private int[] sub(int[] x, int[] y) {
        int compare = compareMagnitude(new LongLong(x, 1), new LongLong(y, 1));
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
        return new LongLong(result.reverse().toString()).mag;
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
     * 比较两个数大小,x == y 返回0 x > y 返回 1 x<y 返回-1
     *
     * @param x
     * @param y
     * @return
     */
    final int compareMagnitude(LongLong x, LongLong y) {
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
    public LongLong negate() {
        return new LongLong(this.mag, -this.signum);
    }

}
