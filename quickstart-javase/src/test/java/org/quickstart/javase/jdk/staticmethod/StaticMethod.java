package org.quickstart.javase.jdk.staticmethod;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019/11/4 20:38
 */
public class StaticMethod {

  public static String getStr(String origin) throws InterruptedException {

    String varStr = origin;

    if ("hehe".equalsIgnoreCase(origin)) {
      TimeUnit.SECONDS.sleep(5);
    }

    if ("test".equalsIgnoreCase(origin)) {
      TimeUnit.SECONDS.sleep(10);
    }

    if ("haha".equalsIgnoreCase(origin)) {
      TimeUnit.SECONDS.sleep(15);
    }

    Long timestamp = Calendar.getInstance().getTimeInMillis();

    return varStr + "-" + origin + "-" + timestamp;
  }





  public static void main(String[] args) {


    new Thread(()->{

      try {
        // TimeUnit.SECONDS.sleep(5);
        System.out.println(StaticMethod.getStr("test"));
        System.out.println(StaticMethod.getStr("haha"));
        System.out.println(StaticMethod.getStr("hehe"));
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

    }).start();

    /*new Thread(()->{

      try {
        System.out.println(new StaticMethod().getStr("test"));
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

    }).start();

    new Thread(()->{

      try {
        System.out.println(new StaticMethod().getStr("haha"));
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

    }).start();
*/


  }

}
