package org.quickstart.crypto.utils;

public class AppkeyUtil {

    // private static transient Log log = LogFactory.getLog(AppkeyUtil.class);

    private static String appkey = null;

    static {
        initAppkey();
    }

    private static void initAppkey() {
        if (Constants.KEY_ACQUIRE_TYPE.LOCAL.equalsIgnoreCase(ZJWGSDKConfig.getKeyAcquireType())) {
            if (Constants.SIGN_METHOD.SHA.equalsIgnoreCase(ZJWGSDKConfig.getSignMethod())) {
                appkey = ZJWGSDKConfig.getAppKey();
            } else {
                appkey = ZJWGSDKConfig.getRsaKey();
            }
            return;
        }

        // TODO 添加远程调用获取秘钥

        // HttpClient httpClient = new HttpClient();
        // String url = getAppkeyUrl();
        // GetMethod method = new GetMethod(url);
        // try {
        // httpClient.executeMethod(method);
        // String response = new String(method.getResponseBody(), "UTF-8");
        // if (StringUtils.isNotBlank(response)){
        // JSONObject json = JSONObject.parseObject(response);
        // if (AIESBConstants.MSG_ID.SUCCESS.equals(json
        // .get(AIESBConstants.RESP_CODE) != null ? json.get(
        // AIESBConstants.RESP_CODE).toString() : "")) {
        // appkey = json.getString(AIESBConstants.RESULT);
        // if (log.isDebugEnabled()){
        // log.debug("获取到最新的密钥：" + appkey);
        // }
        // } else {
        // log.error("获取最新密钥失败：" + json.getString(AIESBConstants.RESP_DESC));
        // }
        // }
        // } catch (Exception e) {
        // log.error("获取密钥失败!", e);
        // }finally{
        // method.releaseConnection();
        // }
    }

    public static String getAppkey() {
        if (appkey == null) {
            initAppkey();
        }

        return appkey;
    }

    public static void syncAppkey(String oldAppkey) {
        if (oldAppkey == appkey) {
            synchronized (AppkeyUtil.class) {
                if (oldAppkey == appkey) {
                    initAppkey();
                }
            }
        }
    }

    /*
    private static String getAppkeyUrl(){
    	StringBuilder urlbuff = new StringBuilder();
    	urlbuff.append(AIESBConfig.getAppkeyUrl()).append(AIESBConfig.getAppId());
    	if (Constants.SIGN_METHOD.SHA.equalsIgnoreCase(AIESBConfig.getSignMethod())){
    		urlbuff.append("&action=get_appkey");
    	} else {
    		if (Constants.RSA_ENCRYPT_TYPE.ENCRYPT_PUBLIC.equalsIgnoreCase(AIESBConfig.getRsaEncryptType())){
    			urlbuff.append("&action=get_rsapublic");
    		} else {
    			urlbuff.append("&action=get_rsaprivate");
    		}
    	}
    	
    	return urlbuff.toString();
    }*/
}
