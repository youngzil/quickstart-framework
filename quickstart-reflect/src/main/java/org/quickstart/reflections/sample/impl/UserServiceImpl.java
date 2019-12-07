package org.quickstart.reflections.sample.impl;

import org.quickstart.reflections.sample.UserService;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019-08-02 13:47
 */
public class UserServiceImpl implements UserService {

  @Override
  public String getUser() {
    return "UserServiceImpl";
  }
}
