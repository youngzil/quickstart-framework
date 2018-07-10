开启远程debug
JAVA_OPT="${JAVA_OPT} -Xdebug -Xrunjdwp:transport=dt_socket,address=9555,server=y,suspend=n"


JMX设置
JMX settings
if [ -z "$KAFKA_JMX_OPTS" ]; then
  KAFKA_JMX_OPTS="-Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.authenticate=false  -Dcom.sun.management.jmxremote.ssl=false "
fi

# JMX port to use
if [  $JMX_PORT ]; then
  KAFKA_JMX_OPTS="$KAFKA_JMX_OPTS -Dcom.sun.management.jmxremote.port=$JMX_PORT "
fi

在tomcat上测试的，理论上试用于任何JAVA进程，以及任何实现JMX规范的程序.
修改$CATALINA_HOME/bin/catalina.sh文件，添加如下JVM参数
-Dcom.sun.management.jmxremote.port=18100  //指定jmx连接端口
-Dcom.sun.management.jmxremote.authenticate=false  //This configuration is insecure. Any remote user who knows (or guesses) your JMX port number and host name will be able to monitor and control your Java application and platform. While it may be acceptable for development, it is not recommended for production systems. 这个参数默认是enabled  
-Dcom.sun.management.jmxremote.pwd.file=/usr/lib/jvm/java-1.6.0-openjdk-1.6.0.0.x86_64/jre/lib/management/jmxremote.password 
-Dcom.sun.management.jmxremote.ssl=false  //默认是true,需要指定
-Dcom.sun.management.jmxremote.pwd.file 这个参数指定的file路径，默认是在$JRE_HOME/lib/management下，默认是只有一个jmxremote.password.template文件，把这个文件拷贝成jmxremote.password,修改最后2行指定用户名密码，如：monitorRole  123456（用jconsole连接的时候就用这对用户名和密码）当然，JVM参数
-Dcom.sun.management.jmxremote.authenticate=false 的时候，不用密码就可以连接
启用JMX还有很多另外的JVM参数可以配置，配置使用密码连接以及使用SSL安全连接等等，不一个个去尝试了，方法见http://docs.oracle.com/javase/6/docs/technotes/guides/management/agent.html。
过程中还需要修改机器的hosts，
# vi /etc/hosts 
127.0.0.1   (修改成机器ip)                   localhost localhost.localdomain localhost
修改机器主机名的文件（可不修改，但是要跟hosts匹配）：/etc/sysconfig/network
注意：在catalina.sh文件中添加上面这些JVM参数后，运行shutdown.sh的时候，会提示jmxremote端口被占用,原因是运行shutdown.sh脚本的时候会启动一个JVM，又会尝试绑定jmxremote的端口，导致这个问题。见https://issues.apache.org/bugzilla/show_bug.cgi?id=36976。暂时没想办法去解决它，直接kill进程了（应该可以通过配置解决）。


JVM的GC参数设置可以参考kafka和Rocketmq的设置
kafka JVM的GC设置
JAVA_MAJOR_VERSION=$($JAVA -version 2>&1 | sed -E -n 's/.* version "([^.-]*).*"/\1/p')
  if [[ "$JAVA_MAJOR_VERSION" -ge "9" ]] ; then
    KAFKA_GC_LOG_OPTS="-Xlog:gc*:file=$LOG_DIR/$GC_LOG_FILE_NAME:time,tags:filecount=10,filesize=102400"
  else
    KAFKA_GC_LOG_OPTS="-Xloggc:$LOG_DIR/$GC_LOG_FILE_NAME -verbose:gc -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+PrintGCTimeStamps -XX:+UseGCLogFileRotation -XX:NumberOfGCLogFiles=10 -XX:GCLogFileSize=100M"
  fi
  
  # JVM performance options
if [ -z "$KAFKA_JVM_PERFORMANCE_OPTS" ]; then
  KAFKA_JVM_PERFORMANCE_OPTS="-server -XX:+UseG1GC -XX:MaxGCPauseMillis=20 -XX:InitiatingHeapOccupancyPercent=35 -XX:+ExplicitGCInvokesConcurrent -Djava.awt.headless=true"
fi

Rocketmq JVM的GC设置
JAVA_OPT="${JAVA_OPT} -XX:+UseG1GC -XX:G1HeapRegionSize=16m -XX:G1ReservePercent=25 -XX:InitiatingHeapOccupancyPercent=30 -XX:SoftRefLRUPolicyMSPerMB=0 -XX:SurvivorRatio=8"
-XX:+PrintGCApplicationStoppedTime -XX:+PrintAdaptiveSizePolicy


JVM GC参数解释
https://www.cnblogs.com/redcreen/archive/2011/05/04/2037057.html
https://blog.csdn.net/turkeyzhou/article/details/7619472

让JVM在遇到OOM(OutOfMemoryError)时生成Dump文件
-XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/path/heap/dump

保存错误日志或者数据到文件中
-XX:ErrorFile=./hs_err_pid<pid>.log

输出GC日志文件
-Xloggc:$LOG_DIR/$GC_LOG_FILE_NAME -verbose:gc





