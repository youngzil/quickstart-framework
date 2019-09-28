mac 上安装ftp
1，brew install telnet 
2，brew install inetutils 
3，brew link --overwrite inetutils



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




---------------------------------------------------------------------------------------------------------------------







