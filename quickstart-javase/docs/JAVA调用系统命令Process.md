```
在编写Java程序时，有时候我们需要调用其他的诸如exe,shell这样的程序或脚本。

Java虚拟机执行Runtime.getRuntime().exec()方法的过程是：首先克隆一个和当前虚拟机拥有一样环境变量的进程，再用这个进程去执行外部命令，最后再退出这个进程。如果频繁执行这个操作，系统消耗会很大，不仅是CPU，内存负担也很大。

在Java中提供了两种方法来启动其他程序：
1、使用Runtime的exec()方法
2、使用ProcessBuilder的start()方法 。

Runtime和ProcessBulider提供了不同的方式来启动程序，设置启动参数、环境变量和工作目录。但是这两种方法都会返回一个用于管理操作系统进程的Process对象，再调用Process.waitFor()来等待命令执行结束，获取执行结果。




参考
https://my.oschina.net/hzchenyh/blog/727905
https://www.cnblogs.com/wmx3ng/p/4138247.html

