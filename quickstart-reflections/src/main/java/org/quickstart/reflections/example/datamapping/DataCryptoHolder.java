package org.quickstart.reflections.example.datamapping;

public interface DataCryptoHolder {
  public String encrypt(String str, String key);

  public String decrypt(String str, String key);

  public String getDecryptSecret(AppInfo appinfo);

  public String getName();

}
