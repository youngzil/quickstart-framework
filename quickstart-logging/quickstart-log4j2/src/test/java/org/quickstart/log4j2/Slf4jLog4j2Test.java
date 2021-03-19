package org.quickstart.log4j2;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.appender.FileAppender;
import org.apache.logging.log4j.core.config.AppenderRef;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.apache.logging.log4j.core.layout.PatternLayout;
import org.apache.logging.slf4j.Log4jLoggerFactory;
import org.junit.Test;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019-07-28 12:36
 */
public class Slf4jLog4j2Test {

    private static final Logger logger = LoggerFactory.getLogger(Slf4jLog4j2Test.class);

    @Test
    public void testCode2() {

        ILoggerFactory test = LoggerFactory.getILoggerFactory();
        System.out.println(test);
        // org.apache.logging.slf4j.Log4jLoggerFactory@13c3c1e1

       /* Log4jLoggerFactory log4jLoggerFactory = (Log4jLoggerFactory)LoggerFactory.getILoggerFactory();
        LoggerContext loggerContext = (LoggerContext)log4jLoggerFactory.getContext();
        String logLevel = "DEBUG";
        loggerContext.getLogger("org.mybatis").setLevel(Level.valueOf(logLevel));
        loggerContext.getLogger("org.springframework").setLevel(Level.valueOf(logLevel));*/

    }

    // Web项目中使用log4j的变化，因为log4j2可以自动监听log配置文件改动，所以一般使用的spring log4j
    // listener就不再需要了，在servlet3.0中不需要在web.xml加入任何log4j2的配置，如果是servlet2.5就需要加入Log4jServletContextListener和Log4jServletFilter等配置，具体可见log4j2在Web中配置 http://logging.apache.org/log4j/2.x/manual/webapp.html
    @Test
    public void testLog() {

        long beginTime = System.currentTimeMillis();
        try {
            System.out.println("log4j2 test");
        } catch (Exception e) {
            logger.error("发生了异常：", e);
        }

        long endTime = System.currentTimeMillis();

        // Tips：根据官方测试的数据，第一种用法比第二种快47倍！
        logger.info("请求处理结束，耗时：{}毫秒", (endTime - beginTime)); // 第一种用法
        logger.info("请求处理结束，耗时：" + (endTime - beginTime) + "毫秒"); // 第二种用法

        // 注意：在JVM启动参数中增加 -DLog4jContextSelector=org.apache.logging.log4j.core.async.AsyncLoggerContextSelector 开启异步日志
    }

    public static void main(String[] args) {
        if (logger.isDebugEnabled()) {
            logger.debug("slf4j-log4j2 debug message");
        }
        if (logger.isInfoEnabled()) {
            logger.info("slf4j-log4j2 info message");
        }
        if (logger.isTraceEnabled()) {
            logger.debug("slf4j-log4j2 trace message");
        }
    }

    @Test
    public void codelog() {
        for (int i = 0; i < 50000; i++) {
            Logger logger = createLogger(i);
            logger.info("Testing testing testing 111");
            logger.debug("Testing testing testing 222");
            logger.error("Testing testing testing 333");
            stop(i);
        }

    }

    /**
     * 获取Logger
     * <p>
     * 如果不想使用slf4j,那这里改成直接返回Log4j的Logger即可
     *
     * @param jobId
     * @return
     */
    public static Logger createLogger(int jobId) {
        start(jobId);
        return LoggerFactory.getLogger("" + jobId);
    }

    public static void start(int jobId) {
        //为false时，返回多个LoggerContext对象，   true：返回唯一的单例LoggerContext
        final LoggerContext ctx = (LoggerContext)LogManager.getContext(false);
        final Configuration config = ctx.getConfiguration();
        //创建一个展示的样式：PatternLayout，   还有其他的日志打印样式。
        Layout layout = PatternLayout.createDefaultLayout(config);
        //        Layout layout = PatternLayout.createLayout(PatternLayout.DEFAULT_CONVERSION_PATTERN,config, null, null, true, false, null, null);
        //TriggeringPolicy tp = SizeBasedTriggeringPolicy.createPolicy("10MB");
        //Appender appender = RollingFileAppender.createAppender(String.format("
        // logs/test/syncshows-job-%s.log", jobID),
        //       "/logs/test/" + jobID + "/syncshows-job-" + jobID + ".log.gz",
        //       "true", jobID, null, null, null, tp, null, layout, null,
        //       null, null, null, config);
        //  日志打印方式——输出为文件
        Appender appender = FileAppender
            .createAppender(String.format("logs/test/syncshows-job-%s.log", jobId), "true", "false", "" + jobId, null, "true", "true", null, layout,
                null, null, null, config);
        appender.start();
        config.addAppender(appender);
        AppenderRef ref = AppenderRef.createAppenderRef("" + jobId, null, null);
        AppenderRef[] refs = new AppenderRef[] {ref};
        LoggerConfig loggerConfig = LoggerConfig.createLogger("false", Level.ALL, "" + jobId, "true", refs, null, config, null);
        loggerConfig.addAppender(appender, null, null);
        config.addLogger("" + jobId, loggerConfig);
        ctx.updateLoggers();
    }

    public static void stop(int jobId) {
        final LoggerContext ctx = (LoggerContext)LogManager.getContext(false);
        final Configuration config = ctx.getConfiguration();
        config.getAppender("" + jobId).stop();
        config.getLoggerConfig("" + jobId).removeAppender("" + jobId);
        config.removeLogger("" + jobId);
        ctx.updateLoggers();
    }

}
