/**
 * 项目名称：quickstart-webservice 
 * 文件名：IMyService.java
 * 版本信息：
 * 日期：2018年11月2日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.webservice.jws.server;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

/**
 * IMyService 
 *  
 * @author：yangzl@asiainfo.com
 * @2018年11月2日 下午4:55:11 
 * @since 1.0
 */
@WebService()
public interface IMyService {
    
    @WebResult(name="addResult")
    public int add(@WebParam(name="a")int a,@WebParam(name="b")int b);
    
    @WebResult(name="minusResult")
    public int minus(@WebParam(name="a")int a,@WebParam(name="b")int b);
    
    @WebResult(name="loginUser")
    public User login(@WebParam(name="username")String username,@WebParam(name="password")String password);

}
