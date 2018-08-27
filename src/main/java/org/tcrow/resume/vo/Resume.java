package org.tcrow.resume.vo;

import lombok.Data;
import lombok.ToString;

/**
 * @author tcrow.luo
 */
@Data
@ToString(exclude = "experience")
public class Resume {
    private String name;
    private String sex;
    private String age;
    private String workTime;
    private String mobile;
    private String email;
    private String address;
    private String hope;
    private String position;
    private String experience;
    private String edu;

}
