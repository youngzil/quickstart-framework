/**
 * 项目名称：quickstart-json 
 * 文件名：TestPOJO.java
 * 版本信息：
 * 日期：2017年12月15日
 * Copyright yangzl Corporation 2017
 * 版权所有 *
 */
package org.quickstart.json.jackson.v2.annotation;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * TestPOJO http://blog.csdn.net/sdyy321/article/details/40298081
 * 
 * @author：youngzil@163.com
 * @2017年12月15日 下午6:07:51
 * @since 1.0
 */

/*
使用@JsonAutoDetect（作用在类上）来开启/禁止自动检测
fieldVisibility:字段的可见级别
ANY:任何级别的字段都可以自动识别
NONE:所有字段都不可以自动识别
NON_PRIVATE:非private修饰的字段可以自动识别
PROTECTED_AND_PUBLIC:被protected和public修饰的字段可以被自动识别
PUBLIC_ONLY:只有被public修饰的字段才可以被自动识别
DEFAULT:同PUBLIC_ONLY
jackson默认的字段属性发现规则如下：
所有被public修饰的字段->所有被public修饰的getter->所有被public修饰的setter
同理，除了fieldVisibility可以设置外，还可以设置getterVisibility、setterVisibility、isGetterVisibility、creatorVisibility级别

SerializationFeature.WRAP_ROOT_VALUE：是否环绕根元素，默认false，如果为true，则默认以类名作为根元素，
你也可以通过@JsonRootName来自定义根元素名称
*/

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
/*@JsonIgnoreProperties
作用在类上，用来说明有些属性在序列化/反序列化时需要忽略掉，可以将它看做是@JsonIgnore的批量操作，但它的功能比@JsonIgnore要强，比如一个类是代理类，我们无法将将@JsonIgnore标记在属性或方法上，此时便可用@JsonIgnoreProperties标注在类声明上，它还有一个重要的功能是作用在反序列化时解析字段时过滤一些未知的属性，否则通常情况下解析到我们定义的类不认识的属性便会抛出异常。
可以注明是想要忽略的属性列表如@JsonIgnoreProperties({"name","age","title"})，
也可以注明过滤掉未知的属性如@JsonIgnoreProperties(ignoreUnknown=true)*/
@JsonIgnoreProperties({"name", "age", "title"})
// @JsonIgnoreProperties(ignoreUnknown=true) //那么测试用例中在反序列化未知的count属性时便不会抛出异常了
@JsonRootName("myPojo")
public class TestBean {

    public TestBean() {}

    TestBean(String name) {
        this.name = name;
    }

    // @JsonIgnore作用在字段或方法上，用来完全忽略被注解的字段和方法对应的属性，即便这个字段或方法可以被自动检测到或者还有其他的注解，当@JsonIgnore不管注解在getters上还是setters上都会忽略对应的属性
    @JsonIgnore
    private String name;

    // @JsonProperty作用在字段或方法上，用来对属性的序列化/反序列化，可以用来避免遗漏属性，同时提供对属性名称重命名，比如在很多场景下Java对象的属性是按照规范的驼峰书写，但是实际展示的却是类似C-style或C++/Microsolft style
    @JsonProperty // 注意这里必须得有该注解，因为没有提供对应的getId和setId函数，而是其他的getter和setter，防止遗漏该属性
    private int id;

    @JsonProperty("first_name")
    private String firstName;

    public int wahaha() {
        return id;
    }

    public void wahaha(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String toString() {
        return "TestPOJO{" + "name='" + name + '\'' + '}';
    }

}
