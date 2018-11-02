package org.quickstart.crypto;

import java.util.HashMap;
import java.util.Map;

import org.quickstart.crypto.utils.SignUtil;

public class SignTest {

    public static void main(String[] args) {
        Map<String, String> sysParams = new HashMap<String, String>();
        sysParams.put("slbrule", "A1");
        sysParams.put("servicecode", "testOrderApi_ICommonSoCSV_testApi");
        sysParams.put("version", "1.0");
        sysParams.put("appId", "2285");
        sysParams.put("accessToken", "38a49d54-e705-483a-9755-e8828aa5e389");
        sysParams.put("sign", "1jbwKDiBjoLGIZquIKQ6XJ3k7cWHlNAYvl8j3otL9PBij0MPe7/kGwyORnn1psAxNyRWrgHUDPSy8qOLjcmKYUprY4glM+LrbALqi");
        sysParams.put("username", "yuxg");
        sysParams.put("password", "A123456");
        // String busiParams = "{\"username\":\"yuxg\",\"password\":\"A123456\"}";
        String busiParams = "";

        String sign = SignUtil.generateSign(sysParams, busiParams);
        System.out.println(sign);

    }

}
