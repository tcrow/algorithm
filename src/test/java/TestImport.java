import org.cyberneko.html.parsers.DOMParser;
import org.junit.Test;
import org.tcrow.vo.Resume;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Properties;

public class TestImport {
    String filePath = "D://简历库/resume.eml";

    @Test
    public void importFile() throws Exception {
        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);
        InputStream inMsg = new FileInputStream(filePath);
        Message msg = new MimeMessage(session, inMsg);

        String resume = "";
        String type = "";

        if (msg.isMimeType("multipart/*") || msg.isMimeType("MULTIPART/*")) {
            type = "51job";
            System.out.println("multipart");
            Multipart mp = (Multipart) msg.getContent();

            int totalAttachments = mp.getCount();
            if (totalAttachments > 0) {
                for (int i = 0; i < totalAttachments; i++) {
                    Part part = mp.getBodyPart(i);
                    String s = getMailContent(part);
                    String attachFileName = part.getFileName();
                    String disposition = part.getDisposition();
                    String contentType = part.getContentType();
                    if ((attachFileName != null && attachFileName
                            .endsWith(".ics"))
                            || contentType.indexOf("text/calendar") >= 0) {
                        String[] dateHeader = msg.getHeader("date");
                    }
                    if(s.startsWith("<html>")){
                        resume = s.substring(s.indexOf("<!DOCTYPE"),s.lastIndexOf("</html>") + 7);
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

        name = array[0].trim().replaceAll("\t","").replaceAll(" ","");
        sex = array[1].trim().replaceAll("\t","").replaceAll(" ","");
        age = array[2].trim().replaceAll("\t","").replaceAll(" ","");
        worktime = array[3].trim().replaceAll("\t","").replaceAll(" ","");

        resumeVo.setName(name);
        resumeVo.setAge(age);
        resumeVo.setSex(sex);
        resumeVo.setWorkTime(worktime);

        System.out.println(resumeVo.toString());
    }

    public static String getMailContent(Part part) throws Exception {
        String contenttype = part.getContentType();
        int nameindex = contenttype.indexOf("name");
        boolean conname = false;
        if (nameindex != -1) {
            conname = true;
        }
        StringBuilder bodytext = new StringBuilder();
        if (part.isMimeType("text/plain") && !conname) {
            bodytext.append((String) part.getContent());
        } else if (part.isMimeType("text/html") && !conname) {
            bodytext.append((String) part.getContent());
        } else if (part.isMimeType("multipart/*")) {
            Multipart multipart = (Multipart) part.getContent();
            int counts = multipart.getCount();
            for (int i = 0; i < counts; i++) {
                getMailContent(multipart.getBodyPart(i));
            }
        } else if (part.isMimeType("message/rfc822")) {
            getMailContent((Part) part.getContent());
        } else {
        }
        return bodytext.toString();
    }

}
