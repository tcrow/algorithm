package datastructure;

import org.junit.Assert;
import org.junit.Test;
import org.tcrow.datastructure.Mobile;

import java.io.IOException;

public class TestMobile {

    private final static String mobileFilePath = "D://mobile_phone/mobile_phone";

    @Test
    public void test() throws IOException {
        Mobile mobile = new Mobile(mobileFilePath);
        mobile.delete("13312341234");
        Assert.assertFalse(mobile.hasMobile("13312341234"));
        mobile.insert("13312341234");
        Assert.assertTrue(mobile.hasMobile("13312341234"));
        mobile.delete("13312341234");
    }

}
