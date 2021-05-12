


[linux的set,env和export的区别](linux的set,env和export的区别.md)



## Linux下设置和查看环境变量

### Linux的变量种类
按变量的生存周期来划分，Linux变量可分为两类：
1 永久的：需要修改配置文件，变量永久生效。
2 临时的：使用export命令声明即可，变量在关闭shell时失效。



### 设置变量的三种方法
1. 在/etc/profile文件中添加变量【对所有用户生效(永久的)】
2. 在用户目录下的.bash_profile文件中增加变量【对单一用户生效(永久的)】
3. 直接运行export命令定义变量【只对当前shell(BASH)有效(临时的)】



### 环境变量的查看
1. 使用echo命令查看单个环境变量
2. 使用env查看所有环境变量(或者export)



### 删除指定的环境变量
1. unset  TEST #删除环境变量TEST



### 常用的环境变量
- PATH 决定了shell将到哪些目录中寻找命令或程序
- HOME 当前用户主目录
- HISTSIZE　历史记录数
- LOGNAME 当前用户的登录名
- HOSTNAME　指主机的名称
- SHELL 当前用户Shell类型
- LANGUGE 　语言相关的环境变量，多语言可以修改此环境变量
- MAIL　当前用户的邮件存放目录
- PS1　基本提示符，对于root用户是#，对于普通用户是$




[Linux下设置和查看环境变量](https://blog.csdn.net/u010758410/article/details/80545563)


