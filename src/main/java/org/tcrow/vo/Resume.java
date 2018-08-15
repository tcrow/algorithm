package org.tcrow.vo;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

/**
 * @author tcrow.luo
 */
@Data
public class Resume {
    private String name;
    private String sex;
    private String age;
    private String workTime;

    @Override
    public String toString(){
        return JSONObject.toJSONString(this);
    }
}
