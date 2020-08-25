1)/var/log/secure：记录登录系统存取数据的文件;  
例如:pop3，ssh，telnet，ftp等都会记录在此.  
  
2)/ar/log/btmp：记录登录这的信息记录，被编码过，所以必须以last解析;  
例如:lastb | awk '{ print $3}' | sort | uniq -c | sort -nr | more  
  
3)/var/log/message:几乎所有的开机系统发生的错误都会在此记录;  
  
4)/var/log.boot.log：记录一些开机或者关机启动的一些服务显示的启动或者关闭的信息;  
  
5)/var/log/maillog：记录邮件的存取和往来;  
  
6)/var/log/cron：用来记录crontab这个服务的内容;  
  
7)/var/log/httpd，/var/log/mysqld.log等等文件，记录几个不同的网络服务的记录文件;  
  
8)/var/log/acpid ,   ACPI - Advanced Configuration and Power Interface，表示高级配置和电源管理接口。  
后面的 d 表示 deamon 。 acpid 也就是 the ACPI event daemon 。 也就是 acpi 的消息进程。用来控制、获取、管理 acpi 的状态的服务程序。  
  
9)/var/run/utmp 记录着现在登录的用户;  
10)/var/log/lastlog 记录每个用户最后的登录信息;  
11)/var/log/btmp 记录错误的登录尝试;  
12)/var/log/dmesg内核日志;  
13)/var/log/cpus CPU的处理信息；  
14)/var/log/syslog 事件记录监控程序日志；  
15)/var/log/auth.log 用户认证日志；  
16)/var/log/daemon.log 系统进程日志；  
17)/var/log/mail.err 邮件错误信息；  
18)/var/log/mail.info 邮件信息；  
  
19)/var/log/mail.warn 邮件警告信息；  
20)/var/log/daemon.log 系统监控程序产生的信息;  
21)/var/log/kern 内核产生的信息;  
22)/var/log/lpr   行打印机假脱机系统产生的信息;  
  
  
  
  
参考  
https://blog.csdn.net/oxford_d/article/details/51820031  
  
