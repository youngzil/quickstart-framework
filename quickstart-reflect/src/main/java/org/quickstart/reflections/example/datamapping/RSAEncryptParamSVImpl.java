package org.quickstart.reflections.example.datamapping;

public class RSAEncryptParamSVImpl implements DataCryptoHolder {

  @Override
  public String getName() {
    return "RSA";
  }

  @Override
  public String decrypt(String str, String key) {

    return "";
  }

  @Override
  public String encrypt(String str, String key) {
    return "";
  }

  @Override
  public String getDecryptSecret(AppInfo appinfo) {
    return appinfo.getRsaPrivate();
  }
}
