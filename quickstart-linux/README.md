SSH、SCP、SFTP、FTP、Telnet常用的java类库

Java SSH库使用简介：Apache sshd和JSch（Java Secure Channel）、jcabi（封装Jsch）
http://www.jcraft.com/jsch/examples/

ganymed-ssh2





http://www.jcraft.com/jsch/examples/


FTP:commons-net
https://www.jianshu.com/p/44d9b05691a8
https://segmentfault.com/a/1190000017308687
https://blog.csdn.net/jiangyu1013/article/details/75529343
https://happyqing.iteye.com/blog/2317621
https://www.journaldev.com/661/java-ftp-client-upload-example-apache-commons-net
https://github.com/journaldev/journaldev/tree/master/CoreJavaProjects/CoreJavaExamples/src/com/journaldev
https://github.com/OSpoon/MyFtpClient/blob/master/app/src/main/java/com/example/myftpclient/ftp/FTP.java
https://www.cnblogs.com/winorgohome/p/6088013.html


https://commons.apache.org/proper/commons-net/examples/
commons-net-2.0.jar telnet功能的简单使用，
这个包实现了很多基本的基于Intenet协议，下面是 commons 支持的协议：
FTP/FTPS
NNTP
SMTP
POP3
Telnet
TFTP
Finger
Whois
rexec/rcmd/rlogin
Time (rdate) and Daytime
Echo
Discard
NTP/SNTP



https://www.coderanch.com/t/674623/java/Telnet-execution-Java
https://www.programcreek.com/java-api-examples/index.php?api=org.apache.commons.net.telnet.TelnetClient
https://www.programcreek.com/java-api-examples/?class=org.apache.commons.net.telnet.TelnetClient&method=connect
http://www.javased.com/index.php?api=org.apache.commons.net.telnet.TelnetClient
https://my.oschina.net/xuqiang/blog/386549
http://www.java2s.com/Code/Java/Network-Protocol/Telnet.htm


[The Linux Kernel Archives(Linux官网)](https://www.kernel.org/)  
[Linux的源码地址（Linus Torvalds的github）](https://github.com/torvalds/linux)  

[Linux命令大全搜索工具，内容包含Linux命令手册、详解、学习、搜集](https://github.com/jaywcjlove/linux-command)


Linux和Shell教程
http://www.runoob.com/linux/linux-tutorial.html
https://wizardforcel.gitbooks.io/w3school-linux/content/


UNIX操作系统（尤尼斯），是一个强大的多用户、多任务操作系统，支持多种处理器架构，按照操作系统的分类，属于分时操作系统，只有符合单一UNIX规范的UNIX系统才能使用UNIX这个名称，否则只能称为类UNIX（UNIX-like）。

其实Linux就是类Unix，什么叫类Linux，就是类似于Unix，是Unix的分支，从文件系统的结构、命令等方面比较相似，故此很多人觉得Linux和Unix差不多，实际上差得太多了，从内核、进程管理、设备管理、I/O方式等都不一样，即使是Unix之间，它们的区别也是比较大，更不用说Linux。

Linux的基本思想有两点：第一，一切都是文件；第二，每个软件都有确定的用途。
linux有两种版本，一种是核心版本，一种是发行版本。

Linux Kernel版本:
https://www.kernel.org/
从最初的1.X和2.X发展到现在的3.X、4.X

Linux发行版本：
RHEL（Red Hat Enterprise Linux的缩写）的克隆版本不只CentOS一个，还有White Box Enterprise Linux和TAO Linux 和Scientific Linux。
redhat：
https://www.redhat.com/en
https://www.redhat.com/zh
从2.X到7.X

centos：
https://www.centos.org/
从2.X到7.X，现在最多的是7或者6


用CentOS做服务器,Ubuntu做桌面
Ubuntu：目前的版本是17.x、16.x
https://www.ubuntu.com/index_kylin
Ubuntu（友帮拓、优般图、乌班图）是一个以桌面应用为主的开源GNU/Linux操作系统，Ubuntu 是基于Debian GNU/Linux，支持x86、amd64（即x64）和ppc架构




Linux Deepin 项目现已正式更名为 Deepin 项目，并已成为国内最流行和活跃的Linux 发行版，一直以“免除新手痛苦、节约老手时间”为口号。

[深度操作系统Linux Deepin](https://www.deepin.org/zh/)


linux 中 man 1 man2 man3 ......man N的区别
```
1、Standard commands （标准命令）
2、System calls （系统调用）
3、Library functions （库函数）
4、Special devices （设备说明）
5、File formats （文件格式）
6、Games and toys （游戏和娱乐）
7、Miscellaneous （杂项）
8、Administrative Commands （管理员命令）
9 其他（Linux特定的）， 用来存放内核例行程序的文档。
```


## 文件传输
bye、ftp、ftpcount、ftpshut、ftpwho、ncftp、tftp、uucico、uucp、uupick、uuto、scp

## 备份压缩
ar、bunzip2、bzip2、bzip2recover、compress、cpio、dump、gunzip、gzexe、gzip、lha、restore、tar、unarj、unzip、zip、zipinfo

## 文件管理
diff、diffstat、file、find、git、gitview、ln、locate、lsattr、mattrib、mc、mcopy、mdel、mdir、mktemp、mmove、mread、mren、mshowfat、mtools、mtoolstest、mv、od、paste、patch、rcp、rhmask、rm、slocate、split、tee、tmpwatch、touch、umask、whereis、which、cat、chattr、chgrp、chmod、chown、cksum、cmp、cp、cut、indent

## 磁盘管理
cd、df、dirs、du、edquota、eject、lndir、ls、mcd、mdeltree、mdu、mkdir、mlabel、mmd、mmount、mrd、mzip、pwd、quota、quotacheck、quotaoff、quotaon、repquota、rmdir、rmt、stat、tree、umount

## 磁盘维护
badblocks、cfdisk、dd、e2fsck、ext2ed、fdisk、fsck.ext2、fsck、fsck.minix、fsconf、hdparm、losetup、mbadblocks、mformat、mkbootdisk、mkdosfs、mke2fs、mkfs.ext2、mkfs、mkfs.minix、mkfs.msdos、mkinitrd、mkisofs、mkswap、mpartition、sfdisk、swapoff、swapon、symlinks、sync

## 系统设置
alias、apmd、aumix、bind、chkconfig、chroot、clock、crontab、declare、depmod、dircolors、dmesg、enable、eval、export、fbset、grpconv、grpunconv、hwclock、insmod、kbdconfig、lilo、liloconfig、lsmod、minfo、mkkickstart、modinfo、modprobe、mouseconfig、ntsysv、passwd、pwconv、pwunconv、rdate、resize、rmmod、rpm、set、setconsole、setenv、setup、sndconfig、SVGAText Mode、timeconfig、ulimit、unalias、unset

## 系统管理
adduser、chfn、chsh、date、exit、finger、free、fwhois、gitps、groupdel、groupmod、halt、id、kill、last、lastb、login、logname、logout、logrotate、newgrp、nice、procinfo、ps、pstree、reboot、renice、rlogin、rsh、rwho、screen、shutdown、sliplogin、su、sudo、suspend、swatch、tload、top、uname、useradd、userconf、userdel、usermod、vlock、w、who、whoami、whois

## 文本处理
awk、col、colrm、comm、csplit、ed、egrep、ex、fgrep、fmt、fold、grep、ispell、jed、joe、join、look、mtype、pico、rgrep、sed、sort、spell、tr、uniq、vi、wc

## 网络通讯
dip、getty、mingetty、ppp-off、smbd(samba daemon)、telnet、uulog、uustat、uux、cu、dnsconf、efax、httpd、ip、ifconfig、mesg、minicom、nc、netconf、netconfig、netstat、ping、pppstats、samba、setserial、shapecfg(shaper configuration)、smbd(samba daemon)、statserial(status ofserial port)、talk、tcpdump、testparm(test parameter)、traceroute、tty(teletypewriter)、uuname、wall(write all)、write、ytalk、arpwatch、apachectl、smbclient(samba client)、pppsetup

## 设备管理
dumpkeys、loadkeys、MAKEDEV、rdev、setleds

## 电子邮件与新闻组
archive、ctlinnd、elm、getlist、inncheck、mail、mailconf、mailq、messages、metamail、mutt、nntpget、pine、slrn、X WINDOWS SYSTEM、reconfig、startx(start X Window)、Xconfigurator、XF86Setup、xlsatoms、xlsclients、xlsfonts






## Linux系统调用

System Call

5个系统调用∶open,write,read,close,ioctl


### 系统调用是什么

所谓系统调用，就是内核提供的、功能十分强大的一系列的函数。这些系统调用是在内核中实现的，再通过一定的方式把系统调用给用户，一般都通过门(gate)陷入(trap)实现。系统调用是用户程序和内核交互的接口。

系统调用是一种 程序进入内核执行任务的方式。程序利用系统调用进行一系列操作，例如 创建进程、处理网络、读写文件等等。 通过 syscall(2) man page 可以查看完整的系统调用列表。

应用程序有多种发起系统调用的方式，其中涉及的底层指令会因 CPU 不同而有所 差异。应用开发者通常无需考虑系统调用是如何实现的，只需 include 相应的头文 件，像调用正常函数一样调用系统调用函数即可。glibc 提供的 wrapper 函数封装 了底层代码，如果使用这些 wrapper 函数，只需要传递相应的参数给它就可以进入内核。



