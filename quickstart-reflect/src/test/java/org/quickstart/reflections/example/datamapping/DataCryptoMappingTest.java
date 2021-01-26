package org.quickstart.reflections.example.datamapping;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019-08-02 12:10
 */
public class DataCryptoMappingTest {

  @Test
  public void testLoad() {

    List<String> packages = new ArrayList<>();
    packages.add("org.quickstart.reflections.example");

    DataCryptoMapping dataCryptoMapping = new DataCryptoMapping(packages);
    DataCryptoHolder aesdata = dataCryptoMapping.getDataCryptoHolder("AES");
    DataCryptoHolder rsadata = dataCryptoMapping.getDataCryptoHolder("RSA");

    System.out.println();

  }

}
