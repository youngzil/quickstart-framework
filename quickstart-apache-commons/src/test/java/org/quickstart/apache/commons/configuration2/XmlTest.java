package org.quickstart.apache.commons.configuration2;

import org.apache.commons.configuration2.CombinedConfiguration;
import org.apache.commons.configuration2.XMLConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.tree.DefaultExpressionEngine;
import org.apache.commons.configuration2.tree.DefaultExpressionEngineSymbols;
import org.apache.commons.configuration2.tree.xpath.XPathExpressionEngine;
import org.junit.jupiter.api.Test;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019/11/9 10:52
 */
public class XmlTest {
  @Test
  public void test2() {
    try {
      Configurations configs = new Configurations();
      XMLConfiguration config = configs.xml(this.getClass().getClassLoader().getResource("token.xml"));
      {
        // 使用默认的符号定义创建一个表达式引擎
        DefaultExpressionEngine engine = new DefaultExpressionEngine(
            DefaultExpressionEngineSymbols.DEFAULT_SYMBOLS);
        // 指定表达式引擎
        config.setExpressionEngine(engine);
        System.out.println(config.getBoolean("token.device.validate"));
        System.out.println(config.getInt("token.person.expire"));
        System.out.println(config.getString("token.person.expire[@description]"));
      }
      {
        // 使用 XPath表达式引擎
        // 请注意这里路径分隔符和attribute标签与上面使用DefaultExpressionEngine是不同的
        XPathExpressionEngine xpathEngine = new XPathExpressionEngine();
        config.setExpressionEngine(xpathEngine);
        System.out.println(config.getBoolean("token/device/validate"));
        System.out.println(config.getInt("token/person/expire"));
        System.out.println(config.getString("token/person/expire/@description"));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  // 自定义表达式引擎

  @Test
  public void test() {
    try
    {
      DefaultExpressionEngineSymbols symbols =
          new DefaultExpressionEngineSymbols.Builder(
              DefaultExpressionEngineSymbols.DEFAULT_SYMBOLS)
              // 指定属性分隔符或可以用xpath的概念叫路径分隔符
              .setPropertyDelimiter(".")
              // Indices should be specified in curly brackets
              .setIndexStart("{")
              .setIndexEnd("}")
              // 指定@开头就是attribute标志
              .setAttributeStart("@")
              // attribute结尾符为null
              .setAttributeEnd(null)
              // A Backslash is used for escaping property delimiters
              .setEscapedDelimiter("\\/")
              .create();
      // 用自定义的符号DefaultExpressionEngineSymbols对象创建一个表达式引擎
      DefaultExpressionEngine engine = new DefaultExpressionEngine(symbols);
      Parameters params = new Parameters();
      FileBasedConfigurationBuilder<XMLConfiguration> builder =
          new FileBasedConfigurationBuilder<XMLConfiguration>(XMLConfiguration.class)
              .configure(params.xml()
                  .setFileName("token.xml")
                  // 使用自定义的表达式引擎
                  .setExpressionEngine(engine));
      XMLConfiguration config = builder.getConfiguration();
      Configurations configs = new Configurations();
      XMLConfiguration config2 = configs.xml(this.getClass().getClassLoader().getResource("token.xml"));
      config2.setExpressionEngine(engine);
      System.out.println(config.getBoolean("token.device.validate"));
      System.out.println(config.getInt("token.person.expire"));
      System.out.println(config.getProperty("token.person.expire@description"));
    }
    catch(Throwable e)
    {
      e.printStackTrace();
    }
  }

  @Test
  public void test4(){
    try
    {
      DefaultExpressionEngine engine = new DefaultExpressionEngine(DefaultExpressionEngineSymbols.DEFAULT_SYMBOLS);
      Configurations configs = new Configurations();
      // 创建CombinedConfiguration 实例
      CombinedConfiguration config = configs.combined(this.getClass().getClassLoader().getResource("root.xml"));
      config.setExpressionEngine(engine);
      System.out.println(config.getBoolean("token.device.validate"));
      System.out.println(config.getInt("token.person.expire"));
      System.out.println(config.getString("token.person.expire[@description]"));

    }
    catch(Throwable e)
    {
      e.printStackTrace();
    }
  }


}
