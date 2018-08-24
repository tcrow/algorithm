package org.tcrow.resume.analyze;

import com.google.common.base.Strings;
import org.cyberneko.html.parsers.DOMParser;
import org.tcrow.vo.Resume;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.mail.*;
import javax.mail.internet.MimeMessage;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Properties;

/**
 * @author tcrow.luo
 */
public class Analyze51JobImpl implements Analyze{

    private final static String doctype = "<!DOCTYPE html>";

    private String getKey(final NodeList tds,final String type) {
        String result = null;
        for (int i = 0; i < tds.getLength(); i++) {
            switch (type) {
                case ("mobile"): {
                    String keyWord = "手机";
                    if (tds.item(i).getTextContent().indexOf(keyWord) > 0) {
                        result = tds.item(i).getTextContent().replaceAll(" ", "").replaceAll("\n", "").replaceAll("\t", "");
                        int start = result.indexOf(keyWord) + keyWord.length() + 1;
                        int end = result.indexOf(keyWord) + keyWord.length() + 12;
                        if (end > start) {
                            result = result.substring(start, end);
                        } else {
                            result = "";
                        }
                        return result;
                    }
                    break;
                }
                case ("email"): {
                    if (tds.item(i).getTextContent().indexOf("邮箱") > 0) {
                        result = tds.item(i).getTextContent().replaceAll(" ", "").replaceAll("\n", "").replaceAll("\t", "");
                        int start = result.indexOf("邮箱：") + 3;
                        int end = result.indexOf("居住地");
                        if (end > start) {
                            result = result.substring(start, end);
                        } else {
                            result = "";
                        }
                        return result;
                    }
                    break;
                }
                case ("address"): {
                    if (tds.item(i).getTextContent().indexOf("居住地") > 0) {
                        result = tds.item(i).getTextContent().replaceAll(" ", "").replaceAll("\n", "").replaceAll("\t", "");
                        int start = result.indexOf("居住地：") + 4;
                        int end = result.indexOf("求职状态");
                        if (end > start) {
                            result = result.substring(start, end);
                        } else {
                            result = "";
                        }
                        return result;
                    }
                    break;
                }
                case ("hope"): {
                    if (tds.item(i).getTextContent().indexOf("期望薪资") > 0) {
                        result = tds.item(i).getTextContent().replaceAll(" ", "").replaceAll("\n", "").replaceAll("\t", "");
                        int start = result.indexOf("期望薪资：") + 5;
                        int end = result.indexOf("地点");
                        if (end > start) {
                            result = result.substring(start, end);
                        } else {
                            result = "";
                        }
                        return result;
                    }
                    break;
                }
                case ("edu"): {
                    if (tds.item(i).getTextContent().indexOf("学历/学位") > 0) {
                        result = tds.item(i).getTextContent().replaceAll(" ", "").replaceAll("\n", "").replaceAll("\t", "");
                        int start = result.indexOf("学历/学位：") + 6;
                        int end = result.indexOf("学历/学位：") + 8;
                        if (end > start) {
                            result = result.substring(start, end);
                        } else {
                            result = "";
                        }
                        return result;
                    }
                    break;
                }
                case ("position"): {
                    if (tds.item(i).getTextContent().indexOf("职能/职位") > 0) {
                        result = tds.item(i).getTextContent().replaceAll(" ", "").replaceAll("\n", "").replaceAll("\t", "");
                        int start = result.indexOf("职能/职位：") + 6;
                        int end = result.lastIndexOf("行业：") > start ? result.lastIndexOf("行业：") : result.indexOf("自我评价") > start ? result.indexOf("自我评价") : result.lastIndexOf("到岗时间");
                        if (end > start) {
                            if(end - start > 50){
                                end = start + 50;
                            }
                            result = result.substring(start, end);
                        } else {
                            result = "";
                        }
                        return result;
                    }
                    break;
                }
                case ("experience"): {
                    if (tds.item(i).getTextContent().lastIndexOf("工作经验") > 0) {
                        result = tds.item(i).getTextContent().replaceAll(" ", "").replaceAll("\n", "").replaceAll("\t", "");
                        int start = result.lastIndexOf("工作经验") + 4;
                        int end = result.indexOf("教育经历");
                        if (end > start) {
                            result = result.substring(start, end);
                        } else {
                            result = "";
                        }
                        return result;
                    }
                    break;
                }
                default: {
                    return result;
                }
            }

        }
        return result;
    }

    private String getMailContent(final Part part) throws MessagingException, IOException {
        String contenttype = part.getContentType();
        int nameindex = contenttype.indexOf("name");
        boolean conname = false;
        if (nameindex != -1) {
            conname = true;
        }
        StringBuilder bodytext = new StringBuilder();
        if (part.isMimeType(ContentType.PLAIN.getType()) && !conname) {
            bodytext.append((String) part.getContent());
        } else if (part.isMimeType(ContentType.HTML.getType()) && !conname) {
            bodytext.append((String) part.getContent());
        } else if (part.isMimeType(ContentType.MULTIPART.getType())) {
            Multipart multipart = (Multipart) part.getContent();
            int counts = multipart.getCount();
            for (int i = 0; i < counts; i++) {
                bodytext.append(getMailContent(multipart.getBodyPart(i)));
            }
        } else if (part.isMimeType(ContentType.RFC.getType())) {
            bodytext.append(getMailContent((Part) part.getContent()));
        } else {
        }
        return bodytext.toString();
    }

    private boolean isIcs(final String attachFileName,final String contentType){
        return (attachFileName != null && attachFileName
                .endsWith(".ics"))
                || contentType.indexOf("text/calendar") >= 0;
    }

    @Override
    public Resume analyze(final String filePath) throws IOException, MessagingException, SAXException {
        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);
        InputStream inMsg = new FileInputStream(filePath);
        Message msg = new MimeMessage(session, inMsg);

        String resume = "";
        String type = "";

        if (msg.isMimeType(ContentType.MULTIPART.getType()) || msg.isMimeType(ContentType.MULTIPART.getType().toLowerCase())) {
            type = "51job";
            Multipart mp = (Multipart) msg.getContent();

            int totalAttachments = mp.getCount();
            if (totalAttachments > 0) {
                for (int i = 0; i < totalAttachments; i++) {
                    Part part = mp.getBodyPart(i);
                    String s = getMailContent(part);
                    String attachFileName = part.getFileName();
                    String disposition = part.getDisposition();
                    String contentType = part.getContentType();
                    if (isIcs(attachFileName,contentType)) {
                        String[] dateHeader = msg.getHeader("date");
                    }
                    if (s.startsWith("<html>")) {
                        resume = s.substring(s.indexOf("<!DOCTYPE"), s.lastIndexOf("</html>") + 7);
                    } else {
                        if (Strings.isNullOrEmpty(resume) && s.indexOf("<html>") > 0) {
                            resume = s.substring(s.indexOf("<html>"), s.lastIndexOf("</html>") + 7);
                            resume = doctype + resume.replaceAll("gb2312", "utf-8");
                        }
                    }
                }
                inMsg.close();
            }
        } else {
            type = "zhilian";
            resume = msg.getContent().toString();
        }

        DOMParser parser = new DOMParser();
        ByteArrayInputStream in = new ByteArrayInputStream(resume.getBytes(Charset.defaultCharset()));
        parser.setProperty("http://cyberneko.org/html/properties/default-encoding", Charset.defaultCharset());
        parser.parse(new InputSource(in));
        Document document = parser.getDocument();
        NodeList strongList = document.getElementsByTagName("strong");
        String name;
        String sex;
        String age;
        String worktime;
        String[] array = strongList.item(0).getParentNode().getTextContent().split("\\|");
        Resume resumeVo = new Resume();

        name = array[0].trim().replaceAll("\t", "").replaceAll(" ", "");
        sex = array[1].trim().replaceAll("\t", "").replaceAll(" ", "");
        age = array[2].trim().replaceAll("\t", "").replaceAll(" ", "");
        worktime = array[3].trim().replaceAll("\t", "").replaceAll(" ", "");
        NodeList tds = document.getElementsByTagName("td");
        String mobile = getKey(tds, "mobile");
        String email = getKey(tds, "email");
        String address = getKey(tds, "address");

        resumeVo.setName(name);
        resumeVo.setAge(age);
        resumeVo.setSex(sex);
        resumeVo.setWorkTime(worktime);
        resumeVo.setMobile(mobile);
        resumeVo.setEmail(email);
        resumeVo.setAddress(address);
        resumeVo.setHope(getKey(tds, "hope"));
        resumeVo.setPosition(getKey(tds, "position"));
        resumeVo.setEdu(getKey(tds, "edu"));
        resumeVo.setExperience(getKey(tds, "experience"));
        return resumeVo;
    }
}
