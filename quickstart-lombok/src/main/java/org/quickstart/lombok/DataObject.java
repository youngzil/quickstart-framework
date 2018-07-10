/**
 * 项目名称：quickstart-lombok 
 * 文件名：DataObject.java
 * 版本信息：
 * 日期：2018年1月21日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.lombok;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * DataObject
 * 
 * @author：yangzl@asiainfo.com
 * @2018年1月21日 下午10:29:21
 * @since 1.0
 */
//@Data//@Data注解的作用相当于 @Getter @Setter @RequiredArgsConstructor @ToString @EqualsAndHashCode的合集。
@Setter  
@Getter  
@ToString  
@EqualsAndHashCode 
public class DataObject {
//    @Setter
//    @Getter
    private String id;
    private String name;
    private String userId;
    private String password;

    /*@Setter
    @Getter
    可以放在POJO类的任意位置，并且会自从重写equal、hashCode、toString（按照属性定义顺序）等
    重写的toString如下所示
    public String toString()
    {
      return "DataObject(id=" + getId() + ", name=" + getName() + ", userId=" + getUserId() + ", password=" + getPassword() + ")";
    }*/
}
