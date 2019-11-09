package org.quickstart.apache.commons.pool2;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.pool2.KeyedPooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019/11/9 09:34
 */
public class MyKeyedPooledObjectFactory implements KeyedPooledObjectFactory<String, User> {

  private static final User USER = new User("aa",System.currentTimeMillis());

  private static final Map<String,User> USER_OBJECT = new ConcurrentHashMap<String, User>();

  @Override
  public PooledObject<User> makeObject(String key) throws Exception {
    User user = null;
    if(USER_OBJECT.containsKey(key)){
      user = USER_OBJECT.get(key);
    }else{
      synchronized(USER_OBJECT){
        user = new User(key,System.currentTimeMillis());
        USER_OBJECT.put(key, user);
      }
    }
    //return new DefaultPooledObject<User>(new User(key,System.currentTimeMillis()));
    return new DefaultPooledObject<User>(user);
  }

  @Override
  public void destroyObject(String key, PooledObject<User> p)
      throws Exception {
    print("destroyObject", p);
  }

  @Override
  public boolean validateObject(String key, PooledObject<User> p) {
    print("validateObject", p);
    return true;
  }

  @Override
  public void activateObject(String key, PooledObject<User> p)
      throws Exception {
    print("activateObject", p);
  }

  @Override
  public void passivateObject(String key, PooledObject<User> p)
      throws Exception {
    print("passivateObject", p);
  }

  private void print(String str,PooledObject<User> p){
    DefaultPooledObject<User> pooled = (DefaultPooledObject<User>)p;
    System.out.println(str + " >> info : " + p + " getBorrowedCount : " + pooled.getBorrowedCount());
  }

}
