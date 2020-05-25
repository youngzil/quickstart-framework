package org.quickstart.javase.jdk.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2020/5/21 19:51
 */
public class TXTParseUtils {

  private static final Integer ONE = 1;

  public static void main(String[] args) {

    Map<String, Integer> map = new HashMap<String, Integer>();

    /* 读取数据 */
    try {
      BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File("/Users/yangzl/Documents/token.txt")),
          "UTF-8"));
      String lineTxt = null;

      Set<String> tokenSet = new HashSet<>();

      while ((lineTxt = br.readLine()) != null) {//数据以逗号分隔
        System.out.println(lineTxt);

        String token = lineTxt.substring(lineTxt.length()-36,lineTxt.length());
        System.out.println(token);
        tokenSet.add(token);

        /*String[] names = lineTxt.split(",");
        for (String name : names) {
          if (map.keySet().contains(name)) {
            map.put(name, (map.get(name) + ONE));
          } else {
            map.put(name, ONE);
          }
        }*/
      }
      br.close();

      System.out.println(tokenSet.size());

      System.out.println("yang");

    } catch (Exception e) {
      System.err.println("read errors :" + e);
    }

    /* 输出数据 */
    try {
      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("E:/value_map.txt")),
          "UTF-8"));

      for (String name : map.keySet()) {
        bw.write(name + " " + map.get(name));
        bw.newLine();
      }
      bw.close();
    } catch (Exception e) {
      System.err.println("write errors :" + e);
    }
  }
}
