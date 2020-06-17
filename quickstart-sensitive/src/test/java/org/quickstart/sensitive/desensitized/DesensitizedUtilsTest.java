package org.quickstart.sensitive.desensitized;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.quickstart.sensitive.desensitized.utils.DesensitizedUtils;

import static org.junit.Assert.*;

/**
 * @description TODO
 *
 * @author yangzl
 * @createTime 2020/6/17 22:02
 */
public class DesensitizedUtilsTest {

    @Test
    public void testUserInfoDesensitize() {
        UserInfo baseUserInfo = new UserInfo();
        baseUserInfo.setRealName("胡丹尼");
            //.setIdCardNo("158199199013141120")
            //.setMobileNo("13579246810")
            //.setAccount("dannyhoo123456")
            //.setPassword("123456");
        System.out.println("脱敏前：" + JSON.toJSONString(baseUserInfo));
        System.out.println("脱敏后：" + DesensitizedUtils.getJson(baseUserInfo));
    }

}