package resume;

import com.google.common.io.Files;
import org.junit.Test;
import org.tcrow.Trie;
import org.tcrow.resume.analyze.Analyze;
import org.tcrow.resume.analyze.AnalyzeEnum;
import org.tcrow.resume.fac.AnalyzeFactory;
import org.tcrow.vo.Resume;

import java.io.File;
import java.nio.charset.Charset;

public class TestAnalyzeResume {
    final String filePathDirectory = "D://简历库";

    final File file = new File(filePathDirectory + File.separator + "简历库.txt");

    static Trie trie = new Trie();

    @Test
    public void excute() throws Exception {
        File filePaths = new File(filePathDirectory);
        File[] files = new File[0];
        if (filePaths.isDirectory()) {
            files = filePaths.listFiles();
        }
        Analyze analyze51Job = AnalyzeFactory.getInstance(AnalyzeEnum.JOB51);
        for (File resumeFile : files) {
            if (resumeFile.getName().indexOf("51job.com") > 0) {
                Resume resume = analyze51Job.analyze(resumeFile.getPath());
                if (trie.countPrefix(resume.getMobile()) == 0) {
                    trie.insertStr(resume.getMobile());
                    Files.append(resume.toString() + "\n",file,Charset.defaultCharset());
                }
            }
        }
    }


}
