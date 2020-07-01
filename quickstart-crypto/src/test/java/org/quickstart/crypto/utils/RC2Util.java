package org.quickstart.crypto.utils;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class RC2Util
{
  private static transient Log log = LogFactory.getLog(RC2Util.class);
  
  public static String decryption(String cipher)
  {
    if (StringUtils.isBlank(cipher)) {
      return "";
    }
    String plain = null;
    try
    {
      plain = K.k_s(cipher);
    }
    catch (Exception e)
    {
      log.error("RC2 decryption failed", e);
    }
    return plain;
  }
  
  public static String encryption(String plain)
  {
    if (StringUtils.isBlank(plain)) {
      return "";
    }
    String cipher = null;
    try
    {
      cipher = "{RC2}" + K.j(plain);
    }
    catch (Exception e)
    {
      log.error("RC2 encryption failed", e);
    }
    return cipher;
  }
  
}