/**
 * 项目名称：quickstart-proxy 
 * 文件名：HotSwapAgent.java
 * 版本信息：
 * 日期：2018年8月17日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.proxy.hot.deploy;


import java.lang.instrument.Instrumentation;  
import java.util.logging.ConsoleHandler;  
import java.util.logging.Logger;  

/**
 * HotSwapAgent 
 *  
 * @author：youngzil@163.com
 * @2018年8月17日 下午10:42:31 
 * @since 1.0
 */
/** 
 * class热替换 
 *  
 * <pre> 
 * 用于在应用程序运行期间，修改代码后，不用重启服务器，就能生效。 
 * 只有在修改方法体里的代码才生效，其他情况都不适用。 
 * </pre> 
 *  
 * @author zyb 
 * 
 * @date 2017年5月26日 下午5:33:15 
 */  
public class HotSwapAgent {  
  
    /** 默认扫描间隔时间 */  
    private static final int DEFAULT_SCAN_INTERVAL = 500;  
  
    private static final Logger log = Logger.getLogger(HotSwapAgent.class.getName());  
  
    private final Instrumentation instrumentation;  
  
    /** 要监视的class文件路径 */  
    private final String classPath;  
  
    public static void premain(String agentArgs, Instrumentation inst) {  
        init(agentArgs, inst);  
    }  
  
    public static void agentmain(String agentArgs, Instrumentation inst) {  
        init(agentArgs, inst);  
    }  
  
    private static void init(String agentArgs, Instrumentation inst) {  
        AgentArgs args = new AgentArgs(agentArgs);  
        if (!args.isValid()) {  
            throw new RuntimeException("args is invalid");  
        }  
        new HotSwapAgent(inst, args);  
    }  
  
    public HotSwapAgent(Instrumentation inst, AgentArgs args) {  
        this.instrumentation = inst;  
        this.classPath = args.getClassPath();  
        int scanInterval = DEFAULT_SCAN_INTERVAL;  
        if (args.getInterval() > scanInterval) {  
            scanInterval = args.getInterval();  
        }  
        log.setUseParentHandlers(false);  
        log.setLevel(args.getLogLevel());  
        ConsoleHandler consoleHandler = new ConsoleHandler();  
        consoleHandler.setLevel(args.getLogLevel());  
        log.addHandler(consoleHandler);  
  
        HotSwapMonitor monitor = new HotSwapMonitor(instrumentation, classPath, args.getInterval());  
        monitor.start();  
          
        log.info("class path: " + classPath);  
        log.info("scan interval (ms): " + scanInterval);  
        log.info("log level: " + log.getLevel());  
    }  
  
}  
