package org.quickstart.apache.commons.pool2;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019/11/9 09:34
 */
public class User {
  private String name;
  private long time;

  public User() {
    super();
  }

  public User(String name, long time) {
    super();
    this.name = name;
    this.time = time;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public long getTime() {
    return time;
  }

  public void setTime(long time) {
    this.time = time;
  }

  @Override
  public String toString() {
    return "User [name=" + name + ", time=" + time + "]";
  }
}
