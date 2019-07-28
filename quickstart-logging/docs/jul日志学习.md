1、Logger中有2个比较重要的概念，分别是记录器(Logger)与处理器(Handler)
2、Logger的级别
3、Handler相关
4、Logger的Formatter
 
 
 ---------------------------------------------------------------------------------------------------------------------
 Logger中有2个比较重要的概念，分别是记录器(Logger)与处理器(Handler)，二者分别完成以下功能：
（1）Logger：记录日志，设置日志级别等。
（2）Handler：确定输出位置等。



Logger相关

Logger的级别：

SEVERE	严重（最高值）
WARNING	警告
INFO	信息
CONFIG	配置
FINE	良好
FINER	较好
FINEST	最好（最低值）
ALL	开启所有级别日志记录
OFF	关闭所有级别日志记录

Logger的默认级别定义是在jre安装目录的lib下面的logging.properties。
jdk启动参数指定-Djava.util.logging.config.file=logging.properties



Handler相关
（1）Handler 对象从 Logger 中获取日志信息，并将这些信息导出。例如，它可将这些信息写入控制台或文件中，也可以将这些信息发送到网络日志服务中，或将其转发到操作系统日志中。
（2）可通过执行 setLevel(Level.OFF) 来禁用 Handler，并可通过执行适当级别的 setLevel 来重新启用。
（3）默认情况下，使用ConsoleHandler，即将日志输出至控制台。可通过FileHandler，SocketHandler等，将日志导向其它地方。

Logger的Handler
(1) java.util.logging.ConsoleHandler 以System.err输出日志。
(2) java.util.logging.FileHandler 将信息输出到文件。
(3) java.util.logging.StreamHandler以指定的!OutputStream实例输出日志。
(4) java.util.logging.SocketHandler将信息通过Socket传送至远程主机。
(5) java.util.logging.MemoryHandler将信息暂存在内存中。



Logger的Formatter
java.util.logging.Formatter 
  java.util.logging.SimpleFormatter 
  java.util.logging.XMLFormatter
Formatter 为格式化 LogRecords 提供支持。 
一般来说，每个日志记录 Handler 都有关联的 Formatter。Formatter 接受 LogRecord，并将它转换为一个字符串。 
以上默认设置即为：fileHandler.setFormatter(new XMLFormatter());
fileHandler.setFormatter(new SimpleFormatter());对应于：





参考
https://blog.csdn.net/x_iya/article/details/70568647
https://blog.csdn.net/u012422446/article/details/51199066
https://www.cnblogs.com/xingele0917/p/4120320.html



