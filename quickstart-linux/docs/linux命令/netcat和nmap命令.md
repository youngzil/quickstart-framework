- [Netcat命令](#Netcat命令)
- [NMAP工具](#NMAP工具)

---------------------------------------------------------------------------------------------------------------------


## Netcat命令




Netcat 是一个用于 TCP/UDP 连接和监听的工具，主要用于网络传输和调试。本文主要介绍如何使用 Netcat 测试 Windows/Linux 虚拟机 TCP/UDP 端口的连通性。主要包含以下内容：

端口是与 Linux 操作系统上的应用或进程的通讯端点的逻辑实体。在使用之前，了解目标机器上哪些端口是打开并正在运行服务是非常有用的。


我们可以使用 netstat 或其他几个 Linux 命令如 NMAP 在本地机器上轻松地列出 Linux 中的打开端口。

使用简单的 netcat（简称 nc）命令来确定远程主机上的端口是否可访问/打开。

netcat（或简称 nc）是一个功能强大且易于使用的程序，可用于 Linux 中与 TCP、UDP 或 UNIX 域套接字相关的任何事情。

我们可以使用它：打开 TCP 连接、侦听任意 TCP 和 UDP 端口、发送 UDP 数据包、在 IPv4 和 IPv6 进行端口扫描。

使用 netcat，你可以检查单个或多个或一段打开的端口范围




安装
# yum install nc                  [在 CentOS/RHEL 中]
# dnf install nc                  [在 Fedora 22+ 中]
$ sudo apt-get install netcat     [在 Debian/Ubuntu 中]

Mac上安装nc
brew install netcat

命令行输入 /usr/bin/ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)"

ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)" < /dev/null 2> /dev/null

[Mac上安装nc](https://www.cnblogs.com/ChristianKula/p/9385203.html)  
[netcat Install command](https://formulae.brew.sh/formula/netcat)  



下面的命令将帮助我们查看端口 22 是否在主机 192.168.56.10 上打开：
$ nc -zv 192.168.1.15 22

上面的命令中，这些标志是：
-z – 设置 nc 只是扫描侦听守护进程，实际上不向它们发送任何数据。
-v – 启用详细模式



下面的命令会检查远程主机 192.168.5.10 上是否打开了端口 80、22 和 21（我们也可以使用主机名）：
nc -zv 192.168.56.10 80 22 21


也可以指定端口扫描的范围：
$ nc -zv 192.168.56.10 20-80


nc -nvv 192.168.x.x 80



参考  
[使用 nc 命令检查远程端口是否打开](https://linux.cn/article-8186-1.html)  
[How to Use Netcat Commands: Examples and Cheat Sheets](https://blogvaronis2.wpengine.com/netcat-commands/)  
[Linux命令--nc （测试服务器端口是否打开）](http://blog.itpub.net/26736162/viewspace-2644141/)  
[]()  



---------------------------------------------------------------------------------------------------------------------


## NMAP工具

[nmap的官网](https://nmap.org/)  
[nmap中文网地址](http://www.nmap.com.cn/)  


NMAP是一款流行的网络扫描和嗅探工具，被广泛应用在黑客领域做漏洞探测以及安全扫描，更多的nmap是一个好用的网络工具，在生产和开发中也经常用到，主要做端口开放性检测和局域网信息的查看收集等，不同Linux发行版包管理中一般也带有nmap工具，这里选择去官网下载源码包进行编译安装，nmap的官网是：https://nmap.org/ 由于某城墙的原因，打开会比较慢或者连接失败，所以奉上nmap中文网地址：http://www.nmap.com.cn/ 中文网里面也有比较全的使用手册，这里选择Linux版本的二进制包进行下载


Nmap是一个网络连接端扫描软件，用来扫描网上电脑开放的网络连接端。确定哪些服务运行在哪些连接端，并且推断计算机运行哪个操作系统（这是亦称 fingerprinting）。它是网络管理员必用的软件之一，以及用以评估网络系统安全。



Nmap包含四项基本功能：
- 主机发现 (Host Discovery)：检测网络上的主机
- 端口扫描 (Port Scanning)：检测主机上开放的端口
- 版本侦测 (Version Detection)：检测操作系统，硬件地址，以及软件版本
- 操作系统侦测 (Operating System Detection)：检测脆弱性的漏洞（Nmap的脚本）


Nmap还具备如下功能：
- 主机探测：Nmap可査找目标网络中的在线主机。默认情况下，Nmap通过4种方式—— ICMP echo请求（ping）、向443端口发送TCP	SYN	包、向80端口发送TCP ACK包和ICMP 时间戳请求——发现目标主机。
- 服务/版本检测：在发现开放端口后，Nmap可进一步检查目标主机的检测服务协议、应用 程序名称、版本号等信息。
- 操作系统检测：Nmap	向远程主机发送一系列数据包，并能够将远程主机的响应与操作系统 指纹数据库进行比较。如果发现了匹配结果，它就会显示匹配的操作系统。它确实可能无法 识别目标主机的操作系统；在这种情况下，如果您知道目标系统上使用的何种操作系统，可 在它提供的	URL	里提交有关信息，更新它的操作系统指纹数据库。
- 网络路由跟踪：它通过多种协议访问目标主机的不同端口，以尽可能访问目标主机。Nmap 路由跟踪功能从TTL的高值开始测试，逐步递减TTL，直到它到零为止。
- Nmap脚本引擎：这个功能扩充了Nmap的用途。如果您要使用Nmap实现它（在默认情况 下）没有的检测功能，可利用它的脚本引擎手写一个检测脚本。目前，Nmap可检査网络服务 的漏洞，还可以枚举目标系统的资源。





### 安装

CentOS 7下直接yum安装nmap
# yum install nmap

发现并不是最新版本，而且升级一下看看，发现也不能直接升级。只好再直接yum卸载掉。

按官方文档rpm安装最新版本的nmap
[root@localhost www.linuxidc.com]# rpm -vhU https://nmap.org/dist/nmap-7.80-1.x86_64.rpm

Mac上安装
brew install nmap

可选的
$ brew search nmap
$ brew info nmap


安装成功
namp -v
nmap --version


[下载最新的官方安装程序](https://nmap.org/download.html#macosx)  
[查看此以获取更多信息](https://nmap.osuorg/book/inst-macosx.html)  
[nmap所有的版本](https://nmap.org/dist/)  

[CentOS 7下安装使用最新版本Nmap](https://www.linuxidc.com/Linux/2019-09/160563.htm)  
[Linux下安装及简单使用nmap/zenmap](https://my.oschina.net/u/4518087/blog/4728437)  
[Linux下安装nmap扫描工具](https://www.cnblogs.com/freeweb/p/6903915.html)  




### 使用
查看本机端口开放情况
nmap localhost


nmap -p 端口 IP(域名)，判断ip是否开放指定端口
nmap -p 8080 192.168.31.13 -Pn


也可以增加端口和网段 ：
nmap  -p 22,21,80 192.168.31.13 -Pn
nmap  -p 22,21,80 192.168.31.1-253 -Pn
nmap 192.168.31.13 -Pn

nmap 192.168.31.1/24 扫描整个子网的端口 ，这个过程可能会比较久


nmap -n --open -p 9000 192.168.31.13
nmap 192.168.31.13 -p9000 -Pn

nmap -p 9000 192.168.31.13 -Pn

nmap -sU -p9000 192.168.31.13 -Pn



### 基本扫描技术

扫描单个网络
nmap 192.168.1.1/www.baidu.com

扫描多个网络/目标
nmap 192.168.1.1 192.168.1.2                #将扫描同个网段内不同的ip地址。

扫描连续的IP地址
nmap 192.168.2.1-192.168.2.100                #将扫描同个网段内不同的ip地址。
nmap 192.168.2.1/24                #nmap也可以用使用CIDR（无类别域间路由）表示法整个子网。

扫描目标列表
nmap -iL [LIST.txt]                #如果你有大量的系统进行扫描，就可以在文本文件中输入IP地址（或主机名），并使用该文件作为输入。





[Nmap使用教程（初级篇）](https://www.cnblogs.com/H4ck3R-XiX/p/12231851.html)  
[nmap超详细使用指南](https://crayon-xin.github.io/2018/08/12/nmap%E8%B6%85%E8%AF%A6%E7%BB%86%E4%BD%BF%E7%94%A8%E6%8C%87%E5%8D%97/)  
[Nmap使用教程（进阶篇）](https://bbs.huaweicloud.com/blogs/144203)  
[nmap详细使用教程](https://blog.csdn.net/smli_ng/article/details/105964486)  
[nmap](https://blog.csdn.net/weixin_30339969/article/details/96739534)  
[]()  
[]()  


---------------------------------------------------------------------------------------------------------------------







