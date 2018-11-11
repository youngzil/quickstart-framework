/**
 * 项目名称：quickstart-xml 
 * 文件名：Wisdom.java
 * 版本信息：
 * 日期：2018年5月20日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package com.quickstart.xml.xstream.xmlpull;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Wisdom 
 *  
 * @author：youngzil@163.com
 * @2018年5月20日 下午11:50:26 
 * @since 1.0
 */
@Getter@Setter@ToString
public class Wisdom {
    
    private int id;  
    private String content;  
    private String author;  
  
    public Wisdom() {  
        super();  
    }  
  
    public Wisdom(String content, String author) {  
        super();  
        this.content = content;  
        this.author = author;  
    }  
      
    public Wisdom(int id, String content, String author) {  
        super();  
        this.id = id;  
        this.content = content;  
        this.author = author;  
    }  

}
