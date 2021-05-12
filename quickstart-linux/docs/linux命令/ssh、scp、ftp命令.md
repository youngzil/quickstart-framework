- [SSH使用](#SSH使用)
- [SCP使用](#SCP使用)
- [FTP使用](#FTP使用)



---------------------------------------------------------------------------------------------------------------------

## SSH使用

### 修改SSH端口
在远程连接服务sshd配置文件中进行修改，/etc/ssh/sshd_config文件中修改远程连接端口port，之后重启服务systemctl restart sshd 端口生效。


参考  
[SSH远程登录配置文件sshd_config详解](https://blog.csdn.net/Field_Yang/article/details/51568861)  



### 通过SSH发送Linux命令到远程主机
ssh -t -p 22022 aifgw@192.168.0.1 "mkdir test"


ssh appweb@10.1.36.143 "hostname -I;ps -ef|grep java"




---------------------------------------------------------------------------------------------------------------------

## SCP使用


### scp参数及说明
```
[root@tank test]# scp --help
usage: scp [-1246BCpqrv] [-c cipher] [-F ssh_config] [-i identity_file]
[-l limit] [-o ssh_option] [-P port] [-S program]
[[user@]host1:]file1 [...] [[user@]host2:]file2
-1 强制scp命令使用协议ssh1
-2 强制scp命令使用协议ssh2
-4 强制scp命令只使用IPv4寻址
-6 强制scp命令只使用IPv6寻址
-B 使用批处理模式（传输过程中不询问传输口令或短语）
-C 允许压缩。（将-C标志传递给ssh，从而打开压缩功能）
-p 保留原文件的修改时间，访问时间和访问权限。
-q 不显示传输进度条。
-r 递归复制整个目录。
-v 详细方式显示输出。scp和ssh(1)会显示出整个过程的调试信息。这些信息用于调试连接，验证和配置问题。
-c cipher 以cipher将数据传输进行加密，这个选项将直接传递给ssh。
-F ssh_config 指定一个替代的ssh配置文件，此参数直接传递给ssh。
-i identity_file 从指定文件中读取传输时使用的密钥文件，此参数直接传递给ssh。
-l limit 限定用户所能使用的带宽，以Kbit/s为单位。
-o ssh_option 如果习惯于使用ssh_config(5)中的参数传递方式，
-P port 注意是大写的P, port是指定数据传输用到的端口号
-S program 指定加密传输时所使用的程序。此程序必须能够理解ssh(1)的选项。

```



### scp实例
1，下载目录
```
[root@test test]# scp -r root@172.30.4.42:/tmp/test2 ./
```
将172.30.4.42linux系统中/tmp/test2目录copy到当前目录下面，在这172.30.4.42前面加了root@,提示输入密码，如果不加呢，会提示你输入用户名和密码

2，下载文件
```
[root@test test]# scp 172.30.4.42:/tmp/test2/aaa.php ./
```
将172.30.4.42linux系统中/tmp/test2/aaa.php文件copy到当前目录下面

3，上传目录
```
[root@test test]# scp -r ./mytest 172.30.4.42:/tmp/test2
```
将当前目录中的mytest目录上传到172.30.4.42服务器/tmp/test2目录下面。

4，上传文件
```
[root@test test]# scp ./mytest/password.php 172.30.4.42:/tmp/test2
```
将当前目录中的mytest目录下的 password.php 上传到 172.30.4.42 服务器 /tmp/test2 目录下面。



[使用scp命令在多个Linux系统间进行文件复制](http://blog.itpub.net/31524109/viewspace-2642482/)  
[scp批量上传文件到多台机器上(升级版)](https://developer.aliyun.com/article/447725)  

---------------------------------------------------------------------------------------------------------------------

## FTP使用

FTP命令参考 [FTP命令](ftp命令.md)





[FTP服务器的安装与配置](https://www.cnblogs.com/116970u/p/10381593.html)  




