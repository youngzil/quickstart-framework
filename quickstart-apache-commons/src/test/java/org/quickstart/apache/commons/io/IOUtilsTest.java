package org.quickstart.apache.commons.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019/11/9 10:08
 */
public class IOUtilsTest {

  @Test
  public void test() throws IOException {

    // commonsMethod();

    InputStream in = new URL("http://commons.apache.org").openStream();
    try {
      System.out.println(IOUtils.toString(in));
    } finally {
      IOUtils.closeQuietly(in);
    }
  }

  private void commonsMethod() throws IOException {
    InputStream in = new URL("http://commons.apache.org").openStream();

    try {
      InputStreamReader inR = new InputStreamReader(in);
      BufferedReader buf = new BufferedReader(inR);

      String line;
      while ((line = buf.readLine()) != null) {
        System.out.println(line);
      }

    } finally {
      in.close();
    }
  }

}
