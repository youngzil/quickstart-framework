

正常关闭
  1. 所有非daemon线程退出
  2. 调用System.exit()
  3. SIGINT(ctrl+c)
  4. SIGTERM(kill -15)

异常关闭
  1. 未捕获的异常
  2. oom

强制关闭
  1. SIGKILL(kill -9)
  2. 应用crash
  3. 机器宕机





参考  
[如何优雅地停止Java进程](https://www.cnblogs.com/nuccch/p/10903162.html)  
[JVM安全退出（如何优雅的关闭java服务）](https://blog.csdn.net/u011001084/article/details/73480432)  
[Java程序优雅关闭的两种方法](https://blog.csdn.net/carlislelee/article/details/52688693)  
[如何优雅地停止Java进程](https://cloud.tencent.com/developer/article/1451633)  
[JVM优雅退出](https://www.jianshu.com/p/e8fba41fa501)  
[]()  


