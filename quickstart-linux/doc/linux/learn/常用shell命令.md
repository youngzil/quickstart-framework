https://www.cnblogs.com/xiaochaohuashengmi/archive/2011/10/08/2203153.html

RPM是RedHat Package Manager（RedHat软件包管理工具）类似Windows里面的“添加/删除程序”

rpm 执行安装包
二进制包（Binary）以及源代码包（Source）两种。二进制包可以直接安装在计算机中，而源代码包将会由RPM自动编译、安装。源代码包经常以src.rpm作为后缀名。

常用命令组合：
－ivh：安装显示安装进度--install--verbose--hash
－Uvh：升级软件包--Update；
－qpl：列出RPM软件包内的文件信息[Query Package list]；
－qpi：列出RPM软件包的描述信息[Query Package install package(s)]；
－qf：查找指定文件属于哪个RPM软件包[Query File]；
－Va：校验所有的RPM软件包，查找丢失的文件[View Lost]；
－e：删除包

netstat -an|grep 8080


war、jar打包、解压：
直接用unzip，unzip test.jar -d dest/
打包
jar cvfm0 EtnetChinaApplication.jar META-INF/MANIFEST.MF .
jar -cvfM0 Test.jar ./
jar -cvf project.war /project_a
-c   创建war包
-v   显示过程信息
-f    
-M
-0   这个是阿拉伯数字，只打包不压缩的意思

#压缩文件为jar包
jar cvfm0 EtnetChinaApplication.jar META-INF/MANIFEST.MF .

解压包：先cp到特定包，再解压
jar -xvf game.war
解压到当前目录


 ssh -p 22022 puaiuc@112.35.58.21
 
 su - root
 
 userdel -r ddmp

id ddemp
useradd ddmp && echo 'Asia1nf0.' | passwd --stdin ddmp

useradd ddmptest && echo 'ddmptest' | passwd --stdin ddmptest


tar -xzvf nmon16e_mpginc.tar.gz

chmod +x nmon_x86_64_centos7

vi /etc/sysctl.conf 


非输入状态（i）
dd 删除整行
0 调到行首
$ 调到行尾

:set number 显示行数
:q 退出
:q! 强制退出
:wq 保存并退出



linux如何查看端口被哪个进程占用的方法：
1、lsof -i:端口号
2、netstat -tunlp|grep 端口号





