package org.quickstart.reflections.example.datamapping;

public class AESEncryptParamSVImpl implements DataCryptoHolder {

  @Override
  public String getName() {
    return "AES";
  }

  /*
   * AES解密
   */
  @Override
  public String decrypt(String data, String key) {
    return "";
  }

  /*
   * AES加密
   */
  @Override
  public String encrypt(String data, String key) {
    return "";

  }

  @Override
  public String getDecryptSecret(AppInfo appinfo) {
    return appinfo.getAppSecretKey();
  }

}
