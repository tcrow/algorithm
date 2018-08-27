package resume;

import com.google.common.base.Stopwatch;
import org.junit.Test;
import org.tcrow.resume.fac.AnalyzeFactory;

import java.io.File;

public class TestAnalyzeResume {

    final String filePathDirectory = "D://简历库 - 副本";

    @Test
    public void execute() throws Exception {
        Stopwatch stopwatch = Stopwatch.createStarted();
        AnalyzeFactory.instance.execute(filePathDirectory,filePathDirectory + File.separator + "简历库.txt");
        stopwatch.stop();
        System.out.println("spend time:"+stopwatch.toString());
    }


}
