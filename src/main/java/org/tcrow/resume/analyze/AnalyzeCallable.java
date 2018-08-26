package org.tcrow.resume.analyze;

import org.tcrow.resume.fac.AnalyzeFactory;
import org.tcrow.vo.Resume;

import java.util.concurrent.Callable;

/**
 * @author tcrow.luo
 *         简历分析线程
 */
public class AnalyzeCallable implements Callable<Resume> {

    private String filePath;

    private final static String WEBSITE_51JOB = "51job.com";

    public AnalyzeCallable(final String filePath) {
        this.filePath = filePath;
    }

    @Override
    public Resume call() throws Exception {
        if (filePath.indexOf(WEBSITE_51JOB) > 0) {
            return AnalyzeFactory.instance.getAnalyze(AnalyzeEnum.JOB51).analyze(filePath);
        } else {
            return null;
        }
    }
}
