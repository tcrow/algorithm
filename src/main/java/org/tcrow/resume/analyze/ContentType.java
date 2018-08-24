package org.tcrow.resume.analyze;

/**
 * @author tcrow.luo
 */

public enum ContentType {
    /**
     *
     */
    PLAIN("text/plain"),HTML("text/html"),MULTIPART("multipart/*"),RFC("message/rfc822");

    private String type;

    ContentType(String type) {
        this.type = type;
    }

    public String  getType(){
        return type;
    }


}
