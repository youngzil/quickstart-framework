- [Netstat命令详解](#Netstat命令详解)
- [Linux如何查看端口被哪个进程占用的方法](#Linux如何查看端口被哪个进程占用的方法)
- [查询统计网络状态](#查询统计网络状态)


---------------------------------------------------------------------------------------------------------------------
### Netstat命令详解


Netstat 命令用于显示各种网络相关信息，如网络连接，路由表，接口状态 (Interface Statistics)，masquerade 连接，多播成员 (Multicast Memberships) 等等。
常见参数
-a (all)显示所有选项，默认不显示LISTEN相关
-t (tcp)仅显示tcp相关选项
-u (udp)仅显示udp相关选项
-n 拒绝显示别名，能显示数字的全部转化成数字。
-l 仅列出有在 Listen (监听) 的服務状态

-p 显示建立相关链接的程序名
-r 显示路由信息，路由表
-e 显示扩展信息，例如uid等
-s 按各个协议进行统计
-c 每隔一个固定时间，执行该netstat命令。

提示：LISTEN和LISTENING的状态只有用-a或者-l才能看到


列出所有 tcp 端口 netstat -at
列出所有 udp 端口 netstat -au



方法2: netstat命令
netstat -tunpl | grep 端口号
netstat -natp | grep 1521
netstat -nlp|grep :80
netstat -tunlp|grep port


netstat -anp|grep 8080


---------------------------------------------------------------------------------------------------------------------
### Linux如何查看端口被哪个进程占用的方法
1、lsof -i:端口号
2、netstat -tunlp|grep 端口号




### 查询统计网络状态
   - netstat -nat | awk '{print $6}' TCP各种状态列表
   - netstat -nat | awk '{print $6}'|sort|uniq -c 先把状态全都取出来,然后使用uniq -c统计，之后再进行排序。

[Linux netstat命令详解](https://www.cnblogs.com/ggjucheng/archive/2012/01/08/2316661.HTML)  
[linux netstat 统计连接数查看外部](https://blog.csdn.net/pengyouchuan/article/details/6546179)  
[netstat监控大量ESTABLISHED连接数和TIME_WAIT连接数题解决](https://blog.csdn.net/bluetjs/article/details/80965967)  
[查看linux中的TCP连接数](https://blog.csdn.net/he_jian1/article/details/40787269)




netstat 查看网络状况

linux系统查看网络连接情况
命令：netstat -n | awk '/^tcp/ {++S[$NF]} END {for(a in S) print a, S[a]}'



参考
https://www.cnblogs.com/ccit/p/10065804.html
https://www.cnblogs.com/ggjucheng/archive/2012/01/08/2316661.html

---------------------------------------------------------------------------------------------------------------------







