package org.tcrow.resume.analyze;

import org.tcrow.vo.Resume;
import org.xml.sax.SAXException;

import javax.mail.MessagingException;
import java.io.IOException;

/**
 * @author tcrow.luo
 */
public interface Analyze {

    /**
     * 分析简历，组装成简历对象文件
     * @param filePath
     * @return
     * @throws IOException
     * @throws MessagingException
     * @throws SAXException
     */
    Resume analyze(String filePath) throws IOException, MessagingException, SAXException;
}
