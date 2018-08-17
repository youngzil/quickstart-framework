/**
 * 项目名称：quickstart-proxy 
 * 文件名：AgentArgs.java
 * 版本信息：
 * 日期：2018年8月17日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.proxy.hot.deploy;
import java.io.File;  
import java.util.HashMap;  
import java.util.Map;  
import java.util.logging.Level;  
  
/**
 * AgentArgs 
 *  
 * @author：yangzl@asiainfo.com
 * @2018年8月17日 下午10:43:25 
 * @since 1.0
 */
/** 
 * Agent参数 
 *  
 * <pre> 
 * 在程序启动时，命令行指定参数。 
 * 例： 
 * -javaagent:${path}/hotswap-agent-1.0.jar="classes=${classPath}, interval=1000, logLevel=FINE" 
 * hotswap-agent-1.0.jar就是本项目的jar文件 
 * classPath：用于指定要扫描的class文件所在的目录 
 * interval：扫描文件的时间间隔 
 * logLevel：日志级别，级别参照JDK的java.util.logging.Level 
 * </pre> 
 *  
 * @author zyb 
 * 
 * @date 2017年5月26日 下午7:42:19 
 */  
public class AgentArgs {  
  
    /** class文件的存放路径 */  
    private static final String CLASSES_PATH = "classPath";  
  
    /** 扫描间隔时间 */  
    private static final String SCAN_INTERVAL = "interval";  
      
    /** 日志级别 */  
    private static final String LOG_LEVEL = "logLevel";  
  
    private String classPath;  
  
    private int interval;  
  
    private Level logLevel;  
  
    private AgentArgs() {  
        this.classPath = null;  
        this.interval = -1;  
        this.logLevel = Level.WARNING;  
    }  
  
    public AgentArgs(String agentArgs) {  
        this();  
        if (agentArgs != null && agentArgs.length() > 0) {  
            if (agentArgs.indexOf("=") != -1) {  
                initArgs(agentArgs);  
            }  
        }  
    }  
  
    public String getClassPath() {  
        return classPath;  
    }  
  
    public Level getLogLevel() {  
        return logLevel;  
    }  
  
    public int getInterval() {  
        return interval;  
    }  
  
    private void initArgs(String agentArgs) {  
        String[] args = agentArgs.split(",");  
        Map<String, String> argsMap = new HashMap<String, String>();  
        for (String s : args) {  
            String[] param = s.split("=");  
            argsMap.put(param[0].trim(), param[1]);  
        }  
        if (argsMap.containsKey(CLASSES_PATH)) {  
            setClassPath(argsMap.get(CLASSES_PATH));  
        }  
        if (argsMap.containsKey(SCAN_INTERVAL)) {  
            setInterval(argsMap.get(SCAN_INTERVAL));  
        }  
        if (argsMap.containsKey(LOG_LEVEL)) {  
            setLogLevel(argsMap.get(LOG_LEVEL));  
        }  
    }  
  
    public boolean isValid() {  
        return classPath != null;  
    }  
  
    private void setClassPath(String classPath) {  
        this.classPath = parsePath(classPath);  
    }  
  
    private void setLogLevel(String logLevel) {  
        try {  
            this.logLevel = Level.parse(logLevel.trim());  
        } catch (Exception e) {  
            this.logLevel = Level.WARNING;  
        }  
    }  
  
    private void setInterval(String interval) {  
        try {  
            this.interval = Integer.parseInt(interval.trim());  
        } catch (NumberFormatException e) {  
            this.interval = -1;  
        }  
    }  
  
    private static String parsePath(String path) {  
        if (path != null) {  
            String result = path.trim();  
            return result.endsWith(File.separator) ? result : result + File.separator;  
        }  
        return null;  
    }  
  
}  
