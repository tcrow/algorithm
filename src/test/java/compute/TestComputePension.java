package compute;

import org.junit.Test;
import org.tcrow.compute.ComputePension;

public class TestComputePension {

    @Test
    public void testPension(){
        System.out.println(ComputePension.compute(29,5,5));
    }
}
