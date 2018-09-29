/**
 * 项目名称：quickstart-javase 
 * 文件名：RepeatingAnnotationsTest.java
 * 版本信息：
 * 日期：2018年9月29日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.jdk8;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * RepeatingAnnotationsTest 
 *  
 * @author：yangzl@asiainfo.com
 * @2018年9月29日 下午4:27:09 
 * @since 1.0
 */
public class RepeatingAnnotationsTest {
    
    @Target( ElementType.TYPE )
    @Retention( RetentionPolicy.RUNTIME )
    public @interface Filters {
        Filter[] value();
    }
    
    @Target( ElementType.TYPE )
    @Retention( RetentionPolicy.RUNTIME )
    @Repeatable( Filters.class )
    public @interface Filter {
        String value();
    };
    
    @Filter( "filter1" )
    @Filter( "filter2" )
    public interface Filterable {        
    }
    
    public static void main(String[] args) {
        for( Filter filter: Filterable.class.getAnnotationsByType( Filter.class ) ) {
            System.out.println( filter.value() );
        }
    }


}
