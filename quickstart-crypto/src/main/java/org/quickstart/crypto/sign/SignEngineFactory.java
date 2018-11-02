package org.quickstart.crypto.sign;

import java.util.HashMap;
import java.util.Map;

import org.quickstart.crypto.sign.impl.RSASignEngineImpl;
import org.quickstart.crypto.sign.impl.SHASignEngineImpl;
import org.quickstart.crypto.utils.Constants;
import org.quickstart.crypto.utils.ZJWGSDKConfig;

public class SignEngineFactory {

    private static Map<String, ISignEngine> signEngineMap = new HashMap<String, ISignEngine>();

    public static ISignEngine getSignEngine() {
        String signMethod = ZJWGSDKConfig.getSignMethod();
        ISignEngine signEngine = signEngineMap.get(signMethod);
        if (signEngine != null) {
            return signEngine;
        }

        if (Constants.SIGN_METHOD.RSA.equalsIgnoreCase(signMethod)) {
            signEngine = RSASignEngineImpl.getSingleton();
        } else if (Constants.SIGN_METHOD.SHA.equalsIgnoreCase(signMethod)) {
            signEngine = SHASignEngineImpl.getSingleton();
        }
        signEngineMap.put(signMethod, signEngine);
        return signEngine;
    }
}
