package org.quickstart.javase.jdk.util.regex;

import org.junit.Test;

import java.util.regex.Pattern;

/**
 * @description TODO
 *
 * @author yangzl
 * @createTime 2020/6/12 10:43
 */
public class RegexDemo {

    @Test
    public void testNoRegex(String args[]){
        String str = "1234567890" ;		// 此字符串由数字组成
        boolean flag = true ;			// 定义一个标记变量
        // 要先将字符串拆分成字符数组，之后依次判断
        char c[] = str.toCharArray() ;	// 将字符串变为字符数组
        for(int i=0;i<c.length;i++){	// 循环依次判断
            if(c[i]<'0'||c[i]>'9'){		// 如果满足条件，则表示不是数字
                flag = false ;			// 做个标记
                break ;					// 程序不再向下继续执行
            }
        }
        if(flag){
            System.out.println("是由数字组成！") ;
        }else{
            System.out.println("不是由数字组成！") ;
        }
    }


    @Test
    public void testRegex(String args[]){
        String str = "1234567890" ;		// 此字符串由数字组成
        if(Pattern.compile("[0-9]+").matcher(str).matches()){	// 使用正则
            System.out.println("是由数字组成！") ;
        }else{
            System.out.println("不是由数字组成！") ;
        }
    }

}
