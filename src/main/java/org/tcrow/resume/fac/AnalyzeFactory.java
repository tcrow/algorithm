package org.tcrow.resume.fac;

import org.tcrow.resume.analyze.Analyze;
import org.tcrow.resume.analyze.AnalyzeEnum;

/**
 * @author tcrow.luo
 * 解析工厂
 */
public class AnalyzeFactory {
    public Analyze getInstance(AnalyzeEnum type){
        if(type.equals(AnalyzeEnum.ZHILIAN)){
            return null;
        }

        if(type.equals(AnalyzeEnum.JOB51)){
            return null;
        }

        return  null;
    }
}
