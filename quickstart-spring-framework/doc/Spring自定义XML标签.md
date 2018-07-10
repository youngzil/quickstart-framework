1、定义解析类DatasourceNamespaceHandlerSupport extends NamespaceHandlerSupport
2、定义xsd文件
3、spring.handlers文件，指定解析类
4、spring.schemas指定xsd文件
5、在xml文件头中引入xmlns和xsi:schemaLocation，就可以使用了

主要是引入w3c的XMLSchema-instance和3个Spring的：beans、aop、context
<?xml version="1.0" encoding="UTF-8"?>  
<beans xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  
 xmlns="http://www.springframework.org/schema/beans"  
 xmlns:aop="http://www.springframework.org/schema/aop" 
 xmlns:context="http://www.springframework.org/schema/context"  
 xsi:schemaLocation="
 http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd 
 http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop.xsd  
 http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd" /> 
 
 
 NamespaceHandler中方法：
 void init();
 BeanDefinition parse(Element element, ParserContext parserContext);
 BeanDefinitionHolder decorate(Node source, BeanDefinitionHolder definition, ParserContext parserContext);
 
 自定义命名空间处理器一般是继承NamespaceHandlerSupport，在init方法里面register  BeanDefinition parse
 public class DatasourceNamespaceHandlerSupport extends NamespaceHandlerSupport {
    @Override
    public void init() {
        registerBeanDefinitionParser("datasource", new DatasourceBeanDefinitionParser());
    }
}
 
 