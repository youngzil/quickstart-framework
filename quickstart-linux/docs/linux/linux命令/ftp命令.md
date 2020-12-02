mac 上安装ftp
1，brew install telnet 
2，brew install inetutils 
3，brew link --overwrite inetutils




---------------------------------------------------------------------------------------------------------------------

ftp 192.168.1.171

然后输入用户名密码

然后就可以执行命令了

put、cd ，分本地和远程（待整理）



---------------------------------------------------------------------------------------------------------------------

ftp批量上传、下载、单个上传、下载、匿名上传

1.ftp自动登录批量下载文件。
#####从ftp服务器上的/home/data 到 本地的/home/databackup####
#!/bin/bash
ftp -n<<!
open 192.168.1.171
user guest 123456
binary
cd /home/data
lcd /home/databackup
prompt
mget *
close
bye
!


2、ftp自动登录批量上传文件。
####本地的/home/databackup to ftp服务器上的/home/data####
#!/bin/bash
ftp -n<<!
open 192.168.1.171
user guest 123456
binary
hash
cd /home/data
lcd /home/databackup
prompt
mput *
close
bye
!

3、ftp自动登录下载单个文件。
####ftp服务器上下载/home/data/a.sh to local /home/databackup####
#!/bin/bash
ftp -n<<!
open 192.168.1.171
user guest 123456
binary
cd /home/data
lcd /home/databackup
prompt
get a.sh a.sh 
close
bye
!

4、ftp自动登录上传单个文件。
####把本地/home/databachup/a.sh up ftp /home/databackup 下####
#!/bin/bash
ftp -n<<!
open 192.168.1.171
user guest 123456
binary
cd /home/data
lcd /home/databackup
prompt
put a.sh a.sh 
close
bye
!

5、以下脚本是匿名上传文件
[root@cucrz-1 test1]# cat ftp_data.sh 
#!/bin/bash
# put_ftp
time=`date`
ftp_dir=/test1/
/usr/bin/ftp -v -n 192.168.20.1 << END   #ftp地址
user anonymous 123
binary
hash
cd pub/
lcd $ftp_dir
prompt
put b.txt
close
bye



1.删除单个文件：delete  file;
2.删除多个文件：mdelete *；mdelete   file* 


小结：把以上脚本另存为文件加入到crontab中即可实现ftp自动上传、下载文件。
注解：
-n 不受.netrc文件的影响。（ftp默认为读取.netrc文件中的设定）
<< 是使用即时文件重定向输入。
!是即时文件的标志它必须成对出现，以标识即时文件的开始和结尾。



ftp命令
http://imhuchao.com/323.html


---------------------------------------------------------------------------------------------------------------------


ascii                以ASCII方式传送文件
binary               以二进制方式传送文件
bell                 每完成一次文件传送,报警提示

ls                   等同于Linux的ls
cd                   等同于Linux的cd
cdup                 返回上一级目录
lcd                  改变当前本地主机的工作目录,如果缺省,就转到当前用户的HOME目录.

get                  下载ftp服务端文件到本地
mget                 下载一批ftp服务端的文件到本地
recv                 等同于get
mput                 上传本地的一批文件到ftp服务端
put                  上传本地的一个文件到ftp服务端
send                 等同于put

mkdir                在ftp服务端建立目录
rename               改变ftp服务端中的文件名
chmod                改变ftp服务端中的文件权限
delete               删除ftp服务端中的文件
rmdir                删除ftp服务端中的目录
mdelete              删除ftp服务端中的一批文件

pwd                  列出当前ftp服务端的目录
status               显示当前ftp的状态
system               显示ftp服务端的系统类型
dir                  列出当前ftp服务端的目录中的文件

open host            重新建立一个新的连接
user user-name       重新以别的用户名登录ftp服务端

bye                  退出FTP
quit                 退出FTP
close                终止远端的FTP进程,返回到FTP命令状态, 所有的宏定义都被删除

help                 帮助,输出命令的解释
?                    等同于help



https://blog.csdn.net/qq_38526635/article/details/82147980





