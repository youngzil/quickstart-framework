- [实现使用crontab](#实现使用crontab)
- [使用示例](#使用示例)
- [配置解释](#配置解释)
- [常用命令](#常用命令)




## 实现使用crontab

crontab -e 编辑添加定时任务

crontab  -l 查看该用户下的定时任务

crontab -u 定某个用户的cron服务

crontab -r 删除没个用户的cron服务




## 使用示例
vi hello.sh

#!/bin/bash
echo "Hello word!"
time=$(date "+%Y%m%d-%H%M%S")
echo "${time}" >> /root/date.log

sh hello.sh

#必须的，不然没有执行权限
chmod +x hello.sh

crontab -e
# 每隔1分钟执行
*/1 * * * * /root/hello.sh

# 每天凌晨03点20分执行
20 03 * * * /root/hello.sh



## 配置解释

more /etc/crontab

minute： 表示分钟，可以是从0到59之间的任何整数。

hour：表示小时，可以是从0到23之间的任何整数。

day：表示日期，可以是从1到31之间的任何整数。

month：表示月份，可以是从1到12之间的任何整数。

week：表示星期几，可以是从0到7之间的任何整数，这里的0或7代表星期日。

command：要执行的命令，可以是系统命令，也可以是自己编写的脚本文件。



命令crontab -e进行开始编写自己的定时代码
* * * * * /u01/app/test/bak/bin/bak.sh >> /u01/app/test/bak/log/bak.log  
          分 小时 日 月 周 具体的命令
```
这里的时分秒注意一下，
如果想每隔五分钟执行可以是*/5 * * * * weblogic /u01/app/test/bak/bin/bak.sh >> /u01/app/test/bak/log/bak.log
如果是每天晚上十二点二十分执行20 00 * * * weblogic /u01/app/test/bak/bin/bak.sh >> /u01/app/test/bak/log/bak.log
如果是每个月的一号中午十二点二十执行20 12 1 * * weblogic /u01/app/test/bak/bin/bak.sh >> /u01/app/test/bak/log/bak.log
如果是每个周末的十二点二十执行20 12 * 7 * weblogic /u01/app/test/bak/bin/bak.sh >> /u01/app/test/bak/log/bak.log
```



## 常用命令
其他关于定时任务知识：
1，crontab -e 编辑添加定时任务

2，*/2 * * * * /home/admin/jiaoben/buy/deleteFile.sh 每个两分钟执行一下脚本

3，crontab  -l 查看该用户下的定时任务

4，crontab -u 定某个用户的cron服务

5，crontab -r 删除没个用户的cron服务

6，cron文件语法:

      分     小时    日       月       星期     命令
      0-59   0-23   1-31   1-12     0-6     command     (取值范围,0表示周日一般一行对应一个任务)
     
     记住几个特殊符号的含义:
         “*”代表取值范围内的数字,
         “/”代表”每”,
         “-”代表从某个数字到某个数字,
         “,”分开几个离散的数字

7，
/sbin/service crond start //启动服务
/sbin/service crond stop //关闭服务
/sbin/service crond restart //重启服务
/sbin/service crond reload //重新载入配置




参考  
[linux下定时任务以及简单shell脚本的编写](https://blog.csdn.net/qq_39889272/article/details/81215173)  
[shell脚本的定时任务](https://www.jianshu.com/p/69b70f44b372)  


