package resume;

import com.google.common.base.Stopwatch;
import org.junit.Test;
import org.tcrow.datastructure.Mobile;
import org.tcrow.resume.fac.AnalyzeFactory;

import java.io.File;

public class TestAnalyzeResume {

    final String filePathDirectory = "D://简历库 - 副本";
    final String mobileDictPath = "D://mobile_phone/mobile_phone";

    @Test
    public void execute() throws Exception {
        Stopwatch stopwatch = Stopwatch.createStarted();
        Mobile mobile = new Mobile(mobileDictPath);
        AnalyzeFactory.instance.execute(filePathDirectory, filePathDirectory + File.separator + "简历库.txt", mobile);
        stopwatch.stop();
        System.out.println("spend time:" + stopwatch.toString());
    }


}
