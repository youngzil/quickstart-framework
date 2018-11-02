package org.quickstart.crypto.sign;

import java.util.Map;

public interface ISignEngine {

    public String generateSign(Map<String, String> paramsMap) throws Exception;
}
