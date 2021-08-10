- [实现使用crontab](#实现使用crontab)
- [使用示例](#使用示例)
- [配置解释](#配置解释)
- [常用命令](#常用命令)
- [使用示例](#使用示例)
- [Crontab的错误排查](#Crontab的错误排查)
- [如何停止crontab中正在运行的job](#如何停止crontab中正在运行的job)




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
echo "${time}" >> /data/program/kafka/date.log

sh hello.sh

#必须的，不然没有执行权限
chmod +x hello.sh

crontab -e
# 每隔1分钟执行
*/1 * * * * /data/program/kafka/hello.sh

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




## 使用示例

linux定时删除N天前的文件（文件夹）

可以使用find命令
find 路径 -mtime +天数 -type f -name "文件名" -exec rm -rf {} ;
```aidl
find /tmp -mtime +30 -type f -name "*" -exec rm -rf {} \;
/tmp --设置查找的目录；
-mtime +30 --设置修改时间为30天前；
-type f --设置查找的类型为文件；其中f为文件，d则为文件夹
-name "*" --设置文件名称，可以使用通配符；
-exec rm -rf --查找完毕后执行删除操作；
 {} \; --固定写法
```

另外的方法大同小异：
find /tmp -mtime +30 -type f | xargs rm -rf

可以吧这个命令写到脚本里cleandata.sh
find /tmp -mtime +30 -type f -name "*" -exec rm -rf {} \;

配置可执行
chmod u+x ./cleandata.sh


配置到crontab
crontab -e
0 0 * * *  /home/username/cleandata.sh > /dev/null 2>&1
```aidl
每天零点自动执行
其中：
第一个*号表示时间中的 分钟  取值范围：0-59
第二个*号表示时间中的 小时  取值范围：0-23
第三个*号表示一个月中的第几天，取值范围：1-31
第四个*号表示一年中的第几个月，取值范围：1-12
第五个*号表示一个星期中的第几天，以星期天开始依次的取值为0～7，0、7都表示星期天
```




参考  
[linux下定时任务以及简单shell脚本的编写](https://blog.csdn.net/qq_39889272/article/details/81215173)  
[shell脚本的定时任务](https://www.jianshu.com/p/69b70f44b372)  





## Crontab的错误排查

运行日志
日志文件位置一般在 /var/spool/cron/ 目录下，这个日志显示的是 crontab 的命令执行日志，并不包括具体的执行情况。



运行详情日志

我遇到的情况就是 crontab 命令其实运行了，有记录，但是没有执行成功，接下来可以配置运行详情日志，这样会把命令执行结果保存下来，更加方便查找失败的原因。
0 6 * * *  /root/script/ss.sh >> /root/for_crontab/mylog.log


不输出内容
*/5 * * * * /root/XXXX.sh &>/dev/null 2>&1

将正确和错误日志都输出到 /tmp/load.log
*/1 * * * * /root/XXXX.sh > /tmp/load.log 2>&1 &

只输出正确日志到 /tmp/load.log
*/1 * * * * /root/XXXX.sh > /tmp/load.log &  等同于   */1 * * * * /root/XXXX.sh 1>/tmp/load.log &

只输出错误日志到 /tmp/load.log
*/1 * * * * /root/XXXX.sh 2> /tmp/load.log &




[crontab 脚本错误日志和正确的输出写入到文件](https://blog.csdn.net/u012129607/article/details/80418149)  
[Crontab 的错误排查的几个步骤](https://www.jianshu.com/p/7430c6ceec8f)  




## 如何停止crontab中正在运行的job

当你发现crontab定时的某个shell运行有问题，但此shell需要运行很长时间时，该如何让此定时任务停止呢？

1. 查到你要停止的那个定时job任务的进程号
   ps aux |grep xx_batch.sh
2. kill-9 进程号。
3. 如果此shell为单任务时，立马ok，搞定，但如果此shell里又调用了其他子shell时，
   则你需要去查到子shell的进程号，再kill掉，这样才能彻底将此定时停止掉。


[如何停止crontab中正在运行的job](https://blog.csdn.net/leys123/article/details/52937175)  
[]()  
[]()  
[]()  
