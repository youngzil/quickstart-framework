package org.quickstart.apache.commons.configuration2;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.ReloadingFileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.builder.fluent.FileBasedBuilderParameters;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.builder.fluent.PropertiesBuilderParameters;
import org.apache.commons.configuration2.convert.DefaultListDelimiterHandler;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.commons.configuration2.io.ClasspathLocationStrategy;
import org.apache.commons.configuration2.io.CombinedLocationStrategy;
import org.apache.commons.configuration2.io.FileLocationStrategy;
import org.apache.commons.configuration2.io.FileSystemLocationStrategy;
import org.apache.commons.configuration2.io.ProvidedURLLocationStrategy;
import org.apache.commons.configuration2.reloading.PeriodicReloadingTrigger;
import org.junit.jupiter.api.Test;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019/11/9 10:46
 */
public class PropertiesTest {

  @Test
  public void test() {

    // 在获取properties文件的内容时:
    // 如果key不存在，且获取的类型为String类型时，那么返回值为null;
    // 如果key不存在，且获取的类型为非String类型时，那么将抛出一个Exception: java.util.NoSuchElementException
    // 注意:我们还可以指定一个默认的值，在找不到指定key的时候，Configuration将使用这个默认值, Configuration为每个取值的方法都提供了重载的方法。

    Configurations configs = new Configurations();
    try {
      Configuration config = configs.properties(new File("config.properties"));
      // access configuration properties

      String dbHost = config.getString("database.host");
      String dbPassword = config.getString("database.password", "secret"); // provide a default
      int dbPort = config.getInt("database.port");
      long dbTimeout = config.getLong("database.timeout");

    } catch (ConfigurationException cex) {
      // Something went wrong
    }

  }

  // properties文件编码

  @Test
  public void test3() {
    try {
      Configurations configs = new Configurations();
      // setDefaultEncoding是个静态方法,用于设置指定类型(class)所有对象的编码方式。
      // 本例中是PropertiesConfiguration,要在PropertiesConfiguration实例创建之前调用。
      FileBasedConfigurationBuilder.setDefaultEncoding(PropertiesConfiguration.class, "UTF-8");
      PropertiesConfiguration propConfig = configs.properties(this.getClass().getClassLoader().getResource("log4j.properties"));
      System.out.println(propConfig.getString("log4j.appender.CONSOLE.Target"));
      System.out.println(propConfig.getBoolean("log4j.appender.LOGFILE.Append"));
      System.out.println(propConfig.getString("test"));
    } catch (Throwable e) {
      e.printStackTrace();
    }
  }

  public void basicDemo() throws Exception {

    Parameters params = new Parameters();
    // Read data from this file
    File propertiesFile = new File("commons.properties");

    // Use PropertiesConfiguration to read file
    ReloadingFileBasedConfigurationBuilder<PropertiesConfiguration> builder =
        new ReloadingFileBasedConfigurationBuilder<PropertiesConfiguration>(PropertiesConfiguration.class)
            .configure(params.fileBased().setFile(propertiesFile));

    // check the file per second
    PeriodicReloadingTrigger trigger = new PeriodicReloadingTrigger(builder.getReloadingController(), null, 1, TimeUnit.SECONDS);

    // start trigger
    trigger.start();

    // check changes
    checkReloadResult(builder);

  }

  private void checkReloadResult(ReloadingFileBasedConfigurationBuilder<PropertiesConfiguration> builder) {
    // 检查文件是否重新加载
  }

  /**
   * @author Dragon
   *
   *         <codeInfo> : 增加文件扫描策略
   *
   *         <Date> Jan 14, 2018
   *
   * @throws Exception
   */
  public void extend() throws Exception {

    //  @formatter:off
    Parameters params = new Parameters();

    // init file location strategy
    List<FileLocationStrategy> subs = Arrays.asList(
        new ProvidedURLLocationStrategy(),
        new FileSystemLocationStrategy(),
        new ClasspathLocationStrategy());// 此条被应用
    FileLocationStrategy strategy = new CombinedLocationStrategy(subs);

    // init BuilderParameters
    PropertiesBuilderParameters propertiesBuilderParameters = params.properties()
        .setEncoding("UTF-8")
        // Read data from this file
        // locate by FileSystemLocationStrategy
        //          .setFile(new File("/Users/Dragon/developCode/eclipse-workspace-javaee/java8/src/main/resources/commons.properties"))

        // locate by ClasspathLocationStrategy
        .setFile(new File("commons.properties"))
        .setLocationStrategy(strategy)
        .setListDelimiterHandler(new DefaultListDelimiterHandler(','))
        .setReloadingRefreshDelay(2000L)
        .setThrowExceptionOnMissing(true);
    //  @formatter:on

    // Use PropertiesConfiguration to read file
    ReloadingFileBasedConfigurationBuilder<PropertiesConfiguration> builder =
        new ReloadingFileBasedConfigurationBuilder<PropertiesConfiguration>(PropertiesConfiguration.class).configure(propertiesBuilderParameters);

    // check the file per second
    PeriodicReloadingTrigger trigger = new PeriodicReloadingTrigger(builder.getReloadingController(), null, 1, TimeUnit.SECONDS);

    // start trigger
    trigger.start();

    checkReloadResult(builder);
  }

  /**
   * @author Dragon
   *
   *         <codeInfo> : 正确的初始化参数
   *
   *         <Date> Jan 14, 2018
   *
   * @throws Exception
   */
  public void parameterInit() throws Exception {
    //  @formatter:off
    Parameters params = new Parameters();

    // Read data from this file
    File propertiesFile = new File("commons.properties");

    // 参数在这里初始化是不起作用的,或者说暂时还不会用,要想此处配置生效,要使用这个流式配置的返回值然后传入到configure()方法中
    // params.fileBased()
    //      .setFile(propertiesFile)
    //      .setEncoding("UTF-8")
    //      .setListDelimiterHandler(new DefaultListDelimiterHandler(','))
    //      .setThrowExceptionOnMissing(true);

    // 详细参考BuilderParameters的子接口和实现类
    // 用其返回值作为configure()方法的参数[使用FileBasedBuilderParameters]
    FileBasedBuilderParameters fileBasedBuilderParameters = params.fileBased()
        .setFile(propertiesFile)
        .setEncoding("UTF-8")
        .setListDelimiterHandler(new DefaultListDelimiterHandler(','))
        .setThrowExceptionOnMissing(true);

    // 用其返回值作为configure()方法的参数[使用PropertiesBuilderParameters]
    //      PropertiesBuilderParameters propertiesBuilderParameters = params.properties()
    //              .setFile(propertiesFile)
    //              .setEncoding("UTF-8")
    //              .setListDelimiterHandler(new DefaultListDelimiterHandler(','))
    //              .setThrowExceptionOnMissing(true);

    // Use PropertiesConfiguration to read file
    // 要想params配置的属性生效一定是要在configure()中配置的才可以原因是configure()方法要的参数是BuilderParameters[],但是原生的Parameters并非为该类型或者其子类,所以之前配置的params的相关设置不生效
    ReloadingFileBasedConfigurationBuilder<PropertiesConfiguration> builder = new ReloadingFileBasedConfigurationBuilder<PropertiesConfiguration>(
        PropertiesConfiguration.class)
        // 使用FileBasedBuilderParameters
        .configure(fileBasedBuilderParameters);
    // 使用PropertiesBuilderParameters
    // .configure(propertiesBuilderParameters);
    //  @formatter:on

    // check the file per second
    PeriodicReloadingTrigger trigger = new PeriodicReloadingTrigger(builder.getReloadingController(), null, 1, TimeUnit.SECONDS);

    // start trigger
    trigger.start();

    // get original value from file
    checkReloadResult(builder);

  }

}
