import com.google.common.base.Stopwatch;
import org.junit.Assert;
import org.junit.Test;
import org.tcrow.LongLong;

import java.math.BigInteger;

/**
 * @author tcrow.luo
 * @date 2018/8/17
 * @description
 */
public class TestLongLong {

    @Test
    public void test() throws Exception {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 1000; j++) {
                BigInteger bigInteger = new BigInteger("" + i).add(new BigInteger("" + j));
                LongLong longLong = new LongLong("" + i).add(new LongLong("" + j));
                if (!bigInteger.toString().equals(longLong.toString())) {
                    throw new Exception("i:" + i + "| j:" + j);
                }
                bigInteger = new BigInteger("" + i).subtract(new BigInteger("" + j));
                longLong = new LongLong("" + i).sub(new LongLong("" + j));
                if (!bigInteger.toString().equals(longLong.toString())) {
                    throw new Exception("i:" + i + "| j:" + j);
                }
            }
        }

        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j > -10; j--) {
                BigInteger bigInteger = new BigInteger("" + i).add(new BigInteger("" + j));
                LongLong longLong = new LongLong("" + i).add(new LongLong("" + j));
                if (!bigInteger.toString().equals(longLong.toString())) {
                    throw new Exception("i:" + i + "| j:" + j);
                }
                bigInteger = new BigInteger("" + i).subtract(new BigInteger("" + j));
                longLong = new LongLong("" + i).sub(new LongLong("" + j));
                if (!bigInteger.toString().equals(longLong.toString())) {
                    throw new Exception("i:" + i + "| j:" + j);
                }
            }
        }

    }

    @Test
    public void testBig(){
        Stopwatch stopwatch = Stopwatch.createStarted();
        Assert.assertEquals(new BigInteger("111111111111111111111111111111111111").add(new BigInteger("99999999999999999999999999999")).toString(),new LongLong("111111111111111111111111111111111111").add(new LongLong("99999999999999999999999999999")).toString());
        Assert.assertEquals(new BigInteger("-111111111111111111111111111111111111").add(new BigInteger("99999999999999999999999999999")).toString(),new LongLong("-111111111111111111111111111111111111").add(new LongLong("99999999999999999999999999999")).toString());
        Assert.assertEquals(new BigInteger("-111111111111111111111111111111111111").add(new BigInteger("-99999999999999999999999999999")).toString(),new LongLong("-111111111111111111111111111111111111").add(new LongLong("-99999999999999999999999999999")).toString());
        Assert.assertEquals(new BigInteger("123153534534213414123").add(new BigInteger("-99999999999999999999999999999")).toString(),new LongLong("123153534534213414123").add(new LongLong("-99999999999999999999999999999")).toString());
        Assert.assertEquals(new BigInteger("111111111111111111111111111111111111").subtract(new BigInteger("99999999999999999999999999999")).toString(),new LongLong("111111111111111111111111111111111111").sub(new LongLong("99999999999999999999999999999")).toString());
        Assert.assertEquals(new BigInteger("-111111111111111111111111111111111111").subtract(new BigInteger("-99999999999999999999999999999")).toString(),new LongLong("-111111111111111111111111111111111111").sub(new LongLong("-99999999999999999999999999999")).toString());
        Assert.assertEquals(new BigInteger("111111111111111111111111111111111111").subtract(new BigInteger("-99999999999999999999999999999")).toString(),new LongLong("111111111111111111111111111111111111").sub(new LongLong("-99999999999999999999999999999")).toString());
        Assert.assertEquals(new BigInteger("-111111111111111111111111111111111111").subtract(new BigInteger("99999999999999999999999999999")).toString(),new LongLong("-111111111111111111111111111111111111").sub(new LongLong("99999999999999999999999999999")).toString());
        stopwatch.stop();
        System.out.println(stopwatch.toString());
    }

    @Test
    public void testSingle(){
        LongLong longLong = new LongLong("1");
        System.out.println(new LongLong("-1").sub(new LongLong("2")).toString());
    }
}
