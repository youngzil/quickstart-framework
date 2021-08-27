- [查看进程PID和线程TID](#查看进程PID和线程TID)
- [Shell中转换16进制10进制](#Shell中转换16进制10进制)
- [Linux上查看java最耗时的线程信息](#Linux上查看java最耗时的线程信息)
- [Linux调优之上下文切换](#Linux调优之上下文切换)
  - [Linux上下文切换的理解和原因](#Linux上下文切换的理解和原因)
  - [Linux上下文切换的测试工具和方法](#Linux上下文切换的测试工具和方法)





先在主机上通过top、free、ps、iostat 等常用工具分析了下主机的CPU、内存、IO使用情况
CPU
内存
磁盘IO
网络IO（网卡、带宽）






---------------------------------------------------------------------------------------------------------------------


## 查看进程PID和线程TID


查看进程pid
ps -ef | grep java
ps aux | grep java
pgrep java



查看线程tid
(1) ps -efL | grep prog_name
(2) ls -l  /proc/<pid>/task
ps -T -p <pid> (在top运行时，你也可以通过按“H”键将线程查看模式切换为开或关。)
top -Hp <pid>
pstree -p  <pid>


[Linux查看某个进程的线程](https://www.cnblogs.com/EasonJim/p/8098217.html)
[linux下进程和线程状态查看](https://blog.csdn.net/huangjin0507/article/details/77848386)
[linux中查看进程](https://blog.csdn.net/weixin_34379433/article/details/92753311)





## Shell中转换16进制10进制

#10进制转换成16进制
printf "%x\n" 180
jstack 7 | grep 0xb4

#16进制转换成10进制
printf %d"\n" 0xb4

[shell中转换16进制10进制](https://blog.csdn.net/rheostat/article/details/8057405)





## Linux上查看java最耗时的线程信息

找到JAVA进程pid
ps -ef|grep java或则jps -mlv

找进行下耗时的线程TID
使用top -Hp pid可以查看某个进程的线程信息 -H 显示线程信息，-p指定pid
top -Hp 10906 查看最耗时的 TID即线程id

printf "%x\n" [tid] 转成16进制

java中的线程类相关信息
jstack 线程ID 可以查看某个线程的堆栈情况，特别对于hung挂死的线程
jstack [pid] | grep [tid] 特定线程信息
jstack -l [pid] 列出所有线程信息。

[linux上查看java最耗时的线程信息](https://blog.csdn.net/u013467442/article/details/89511656)







---------------------------------------------------------------------------------------------------------------------

## Linux调优之上下文切换

Linux查看线程切换的主动还是被动


Linux查看：
CPU上下文切换次数
进程上下文切换次数
线程上下文切换次数


- [Linux上下文切换的理解和原因](#Linux上下文切换的理解和原因)
- [Linux上下文切换的测试工具和方法](#Linux上下文切换的测试工具和方法)




## Linux上下文切换的测试工具和方法

vmstat 只给出了系统总体的上下文切换情况，要想查看每个进程的详细情况，就需要使用我们前面提到过的 pidstat 了。给它加上 -w 选项，你就可以查看每个进程上下文切换的情况了。
vmstat 5

这个结果中有两列内容是我们的重点关注对象。一个是 cswch ，表示每秒自愿上下文切换（voluntary context switches）的次数，另一个则是 nvcswch ，表示每秒非自愿上下文切换（non voluntary context switches）的次数。
pidstat -w  5

pidstat -w -I -t -p <pid> 3


查看某个任务切换的次数，可以通过proc接口查看，例如查看任务pid的切换次数命令：
cat    /proc/<pid>/status   |  grep "ctxt_switches"
voluntary_ctxt_switches: 1241 /*进程主动切换的次数*/
nonvoluntary_ctxt_switches: 717 /*进程被动切换的次数*/


#10进制转换成16进制
printf "%x\n" 180
jstack 7 | grep 0xb4



[Linux调优之上下文切换](https://blog.csdn.net/bwt1989/article/details/86661008)  
[Linux中的主动切换和被动切换](http://m.blog.chinaunix.net/uid-30588073-id-5572712.html)  
[linux性能速查-CPU上下文切换（二）](https://blog.csdn.net/javajxz008/article/details/88862875)  
[查看进程运行时间及上下文切换次数](https://garlicspace.com/2019/07/20/%E6%9F%A5%E7%9C%8B%E8%BF%9B%E7%A8%8B%E8%BF%90%E8%A1%8C%E6%97%B6%E9%97%B4%E5%8F%8A%E4%B8%8A%E4%B8%8B%E6%96%87%E5%88%87%E6%8D%A2%E6%AC%A1%E6%95%B0/)  
[进程上下文频繁切换导致load average过高](https://blog.csdn.net/sinat_27143551/article/details/103033602)





## Linux上下文切换的理解和原因

[Linux性能优化篇-了解CPU上下文切换](https://cloud.tencent.com/developer/article/1492104)  
[linux性能优化 - 03、CPU上下文切换一](https://www.cnblogs.com/liang1101/p/13405297.html)  
[linux 性能优化-- cpu 切换以及cpu过高](https://www.jianshu.com/p/27abadcab89f)  
[]()  
[]()  




---------------------------------------------------------------------------------------------------------------------


