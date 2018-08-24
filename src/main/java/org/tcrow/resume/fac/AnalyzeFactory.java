package org.tcrow.resume.fac;

import org.tcrow.resume.analyze.Analyze;
import org.tcrow.resume.analyze.Analyze51JobImpl;
import org.tcrow.resume.analyze.AnalyzeEnum;

/**
 * @author tcrow.luo
 *         解析工厂
 */
public class AnalyzeFactory {

    public static Analyze getInstance(AnalyzeEnum type) {
        switch (type) {
            case JOB51: {
                return new Analyze51JobImpl();
            }
            case ZHILIAN:
                break;
            default: {

            }
        }
        return null;
    }
}
