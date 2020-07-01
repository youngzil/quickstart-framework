package org.quickstart.crypto.utils;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quickstart.crypto.sign.SignEngineFactory;

public class SignUtil {

    private static transient Log log = LogFactory.getLog(SignUtil.class);

    public static String generateSign(Map<String, String> sysParams, String busiParams) {
        Map<String, String> paramsMap = new HashMap<String, String>();
        paramsMap.putAll(sysParams);
        if (StringUtils.isNotBlank(busiParams)) {
            paramsMap.put(Constants.CONTENT, busiParams);
        }
        try {
            String sign = SignEngineFactory.getSignEngine().generateSign(paramsMap);
            if (log.isDebugEnabled()) {
                log.debug("根据请求报文生成的sign：" + sign);
            }
            return sign;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
