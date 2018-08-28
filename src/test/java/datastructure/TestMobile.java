package datastructure;

import org.junit.Assert;
import org.junit.Test;
import org.tcrow.datastructure.Mobile;

import java.io.IOException;

public class TestMobile {

    private final static String mobileFilePath = "D://mobile_phone/mobile_phone";

    @Test
    public void test() throws IOException, InterruptedException {
        Mobile mobile = new Mobile(mobileFilePath);
        mobile.delete("13312341234");
        Assert.assertFalse(mobile.hasMobile("13312341234"));
        mobile.insert("13312341234");
        Assert.assertTrue(mobile.hasMobile("13312341234"));
        mobile.delete("13312341234");

        //按位测试一整个字节是否都能正常保存
        mobile.delete("13312341232");
        mobile.delete("13312341233");
        mobile.delete("13312341234");
        mobile.delete("13312341235");
        mobile.delete("13312341236");
        mobile.delete("13312341237");
        mobile.delete("13312341238");
        mobile.delete("13312341239");
        Assert.assertFalse(mobile.hasMobile("13312341232"));
        Assert.assertFalse(mobile.hasMobile("13312341233"));
        Assert.assertFalse(mobile.hasMobile("13312341234"));
        Assert.assertFalse(mobile.hasMobile("13312341235"));
        Assert.assertFalse(mobile.hasMobile("13312341236"));
        Assert.assertFalse(mobile.hasMobile("13312341237"));
        Assert.assertFalse(mobile.hasMobile("13312341238"));
        Assert.assertFalse(mobile.hasMobile("13312341239"));
        mobile.insert("13312341232");
        mobile.insert("13312341233");
        mobile.insert("13312341234");
        mobile.insert("13312341235");
        mobile.insert("13312341236");
        mobile.insert("13312341237");
        mobile.insert("13312341238");
        mobile.insert("13312341239");
        Assert.assertTrue(mobile.hasMobile("13312341232"));
        Assert.assertTrue(mobile.hasMobile("13312341233"));
        Assert.assertTrue(mobile.hasMobile("13312341234"));
        Assert.assertTrue(mobile.hasMobile("13312341235"));
        Assert.assertTrue(mobile.hasMobile("13312341236"));
        Assert.assertTrue(mobile.hasMobile("13312341237"));
        Assert.assertTrue(mobile.hasMobile("13312341238"));
        Assert.assertTrue(mobile.hasMobile("13312341239"));
        mobile.delete("13312341232");
        mobile.delete("13312341233");
        mobile.delete("13312341234");
        mobile.delete("13312341235");
        mobile.delete("13312341236");
        mobile.delete("13312341237");
        mobile.delete("13312341238");
        mobile.delete("13312341239");
        Assert.assertFalse(mobile.hasMobile("13312341232"));
        Assert.assertFalse(mobile.hasMobile("13312341233"));
        Assert.assertFalse(mobile.hasMobile("13312341234"));
        Assert.assertFalse(mobile.hasMobile("13312341235"));
        Assert.assertFalse(mobile.hasMobile("13312341236"));
        Assert.assertFalse(mobile.hasMobile("13312341237"));
        Assert.assertFalse(mobile.hasMobile("13312341238"));
        Assert.assertFalse(mobile.hasMobile("13312341239"));
    }

}
