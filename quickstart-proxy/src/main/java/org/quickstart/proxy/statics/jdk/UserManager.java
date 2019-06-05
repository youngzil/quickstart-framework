/**
 * 项目名称：quickstart-proxy 
 * 文件名：UserManager.java
 * 版本信息：
 * 日期：2018年4月17日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.proxy.statics.jdk;

/**
 * UserManager 
 *  
 * @author：youngzil@163.com
 * @2018年4月17日 下午5:40:00 
 * @since 1.0
 */
public interface UserManager {  
    
    public void addUser(String userId,String userName);  
      
    public void delUser(String userId);  
      
    public void modifyUser(String userId,String userName);  
      
    public String findUser(String userId);  
      
}  