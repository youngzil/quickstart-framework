1、使用SSHpass设置多机信任
2、SSH(SecureShell)讲解
SSH1和SSH2的区别
安全套接字层（SecureSocketsLayer（SSL））和 安全套接层协议（SSL，SecuritySocketLayer）
点对点协议（英语：Point-to-Point Protocol，缩写：PPP）
POP3，IMAP4，SMTP
SMTP 的全称是“Simple Mail Transfer Protocol”，即简单邮件传输协议
邮局协议（英语：Post Office Protocol，缩写：POP），
POP3是Post Office Protocol 3的简称，即邮局协议的第3个版本
IMAP全称是Internet Mail Access Protocol，即交互式邮件存取协议
3、SSH、SCP、SFTP、FTP、Telnet定义和使用
SSH、SCP、SFTP、FTP、Telnet常用的java类库






---------------------------------------------------------------------------------------------------------------------

下载sshpass
https://sourceforge.net/projects/sshpass/

Client免登陆到Server端
1、在Client端生成公钥和私钥，此时会生成两个密码文件：id_rsa 私钥文件、id_rsa.pub 公钥文件
ssh-keygen  -t  rsa

2、把 Client机器的公钥拷贝到 Server机器 的 ~/.ssh/authorized_keys 文件里。
方法一 使用ssh-copy-id
ssh-copy-id -i ./id_rsa.pub aideploy@20.26.37.177

方法二 手动创建authorized_keys文件 或者 往里面追加id_rsa.pub 公钥文件内容
mkdir -p ~/.ssh
vi ~/.ssh/authorized_keys

3、设置权限
在Server机器上设置权限
chmod 700 ~/.ssh
chmod 600 ~/.ssh/authorized_keys


4、在Client上ssh aideploy@20.26.37.177


---------------------------------------------------------------------------------------------------------------------
https://blog.csdn.net/liuchangjie0112/article/details/80972887


SSH(SecureShell)讲解:SSH的英文全称是SecureShell，（SSH:SecureShellProtocol）

传统的网络服务程序，如：ftp、pop和telnet在本质上都是不安全的，因为它们在网络上用明文传送口令和数据，别有用心的人非常容易就可以截获这些口令和数据。

通过使用SSH，你可以把所有传输的数据进行加密，这样“中间人”这种攻击方式就不可能实现了，而且也能够防止DNS和IP欺骗。还有一个额外的好处就是传输的数据是经过压缩的，所以可以加快传输的速度。SSH有很多功能，它既可以代替telnet，又可以为ftp、pop、甚至ppp提供一个安全的“通道”。

OpenSSH是SSH的替代软件，而且是免费的，可以预计将来会有越来越多的人使用它而不是SSH。


SSH1和SSH2的区别:
SSH(Secure SHell)到目前为止有两个不兼容的版本——SSH1和SSH2。

SSH1又分为1.3和 1.5两个版本。SSH1采用DES、3DES、Blowfish和RC4等对称加密算法保护数据安全传输，而对称加密算法的密钥是通过非对称加密算法（RSA）来完成交换的。SSH1使用循环冗余校验码（CRC）来保证数据的完整性，但是后来发现这种方法有缺陷。

SSH2避免了RSA的专利问题，并修补了CRC的缺陷。SSH2用数字签名算法（DSA）和Diffie-Hellman（DH）算法代替RSA来完成对称密钥的交换，用消息证实代码（HMAC）来代替CRC。同时SSH2增加了AES和Twofish等对称加密算法。



安全套接字层（SecureSocketsLayer（SSL））和 安全套接层协议（SSL，SecuritySocketLayer）：

安全套接字层（SecureSocketsLayer（SSL）），SSL是一种安全协议，它为网络（例如因特网）的通信提供私密性。SSL使应用程序在通信时不用担心被窃听和篡改。SSL实际上是共同工作的两个协议：“SSL记录协议”（SSLRecordProtocol）和“SSL握手协议”（SSLHandshakeProtocol）。“SSL记录协议”是两个协议中较低级别的协议，它为较高级别的协议，例如SSL握手协议对数据的变长的记录进行加密和解密。SSL握手协议处理应用程序凭证的交换和验证。

安全套接层协议（SSL，SecuritySocketLayer）是网景（Netscape）公司提出的基于WEB应用的安全协议，它包括：服务器认证、客户认证（可选）、SSL链路上的数据
完整性和SSL链路上的数据保密性。对于电子商务应用来说，使用SSL可保证信息的真实性、完整性和保密性。但由于SSL不对应用层的消息进行数字签名，因此不能提供交易的不可否认性，这是SSL在电子商务中使用的最大不足。有鉴于此，网景公司在从Communicator4.04版开始的所有浏览器中引入了一种被称作“表单签名（FormSigning）”的功能，在电子商务中，可利用这一功能来对包含购买者的订购信息和付款指令的表单进行数字签名，从而保证交易信息的不可否认性。综上所述，在电子商务中采用单一的SSL协议来保证交易的安全是不够的，但采用"SSL 表单签名"模式能够为电子商务提供较好的安全性保证。


SSL/TLS协议在传输层（TCP/IP）之上、但是在应用层之下工作的。因此，它可以很容易在诸如HTTP，Telnet，POP3，IMAP4，SMTP和FTP等应用层协议上实现。SSL安全扩展至少有两种不同的初始化方法：显式安全和隐式安全。 
显示安全:为了建立SSL连接，显式安全要求FTP客户端在和FTP服务器建立连接后发送一个特定的命令给FTP服务器。客户端使用服务器的缺省端口。 
隐式安全: 当FTP客户端连接到FTP服务器时，隐式安全将会自动和SSL连接一起开始运行。在隐式安全中服务器定义了一个特定的端口（TCP端口990）让客户端来和其建立安全连接。



点对点协议（英语：Point-to-Point Protocol，缩写：PPP）
点对点协议（英语：Point-to-Point Protocol，缩写：PPP）工作在数据链路层（以OSI参考模型的观点）。它通常用在两节点间创建直接的连接，并可以提供连接认证、传输加密（使用ECP，RFC 1968）以及压缩。

PPP被用在许多类型的物理网络中，包括串口线、电话线、中继链接、移动电话、特殊无线电链路以及光纤链路（如SONET）。

PPP还用在互联网接入连接上（现在称作宽带）。互联网服务提供商（ISP）使用PPP为用户提供到Internet的拨号接入，这是因为IP报文无法在没有数据链路协议的情况下通过调制解调器线路自行传输。PPP的两个派生物PPPoE和PPPoA被ISP广泛用来与用户创建数字用户线路（DSL）Internet服务连接。

PPP被广泛用作连接同步和异步电路的数据链路层协议，取代了陈旧的串行线路IP协议（SLIP）以及电话公司的拥有的标准（如 X.25协议族中的LAPB。PPP被设计用来与许多网络层协议协同工作，包括网际协议（IP）、TRILL、Novell的互联网分组交换协议（IPX）、NBF以及AppleTalk。



OpenSSH：
https://www.openbsd.org/
https://www.openssh.com/


---------------------------------------------------------------------------------------------------------------------

POP3，IMAP4，SMTP
https://www.zhihu.com/question/24605584

简单地说，SMTP管‘发’， POP3/IMAP管‘收’。


SMTP
SMTP 的全称是“Simple Mail Transfer Protocol”，即简单邮件传输协议。它是一组用于从源地址到目的地址传输邮件的规范，通过它来控制邮件的中转方式。SMTP 协议属于 TCP/IP 协议簇，它帮助每台计算机在发送或中转信件时找到下一个目的地。SMTP 服务器就是遵循 SMTP 协议的发送邮件服务器。 
　　SMTP 认证，简单地说就是要求必须在提供了账户名和密码之后才可以登录 SMTP 服务器，这就使得那些垃圾邮件的散播者无可乘之机。 
　　增加 SMTP 认证的目的是为了使用户避免受到垃圾邮件的侵扰。

POP3
POP3是Post Office Protocol 3的简称，即邮局协议的第3个版本,它规定怎样将个人计算机连接到Internet的邮件服务器和下载电子邮件的电子协议。它是因特网电子邮件的第一个离线协议标准,POP3允许用户从服务器上把邮件存储到本地主机（即自己的计算机）上,同时删除保存在邮件服务器上的邮件，而POP3服务器则是遵循POP3协议的接收邮件服务器，用来接收电子邮件的。(与IMAP有什么区别？)


IMAP
IMAP全称是Internet Mail Access Protocol，即交互式邮件存取协议，它是跟POP3类似邮件访问标准协议之一。不同的是，开启了IMAP后，您在电子邮件客户端收取的邮件仍然保留在服务器上，同时在客户端上的操作都会反馈到服务器上，如：删除邮件，标记已读等，服务器上的邮件也会做相应的动作。所以无论从浏览器登录邮箱或者客户端软件登录邮箱，看到的邮件以及状态都是一致的。（与POP3有什么区别？）


邮局协议（英语：Post Office Protocol，缩写：POP）是TCP/IP协议族中的一员，由RFC 1939定义。此协议主要用于支持使用客户端远程管理在服务器上的电子邮件。最新版本为POP3，全名“Post Office Protocol - Version 3”，而提供了SSL加密的POP3协议被称为POP3S。

POP支持离线邮件处理。其具体过程是：邮件发送到服务器上，电子邮件客户端调用邮件客户机程序以连接服务器，并下载所有未阅读的电子邮件。这种离线访问模式是一种存储转发服务，将邮件从邮件服务器端送到个人终端机器上，一般是PC机或MAC。一旦邮件发送到PC机或MAC上，邮件服务器上的邮件将会被删除。但当前的POP3邮件服务器大都可以“只下载邮件，服务器端并不删除”，也就是改进的POP3协议。


因特网信息访问协议（英语：Internet Message Access Protocol，缩写：IMAP；以前称作交互邮件访问协议）是一个应用层协议，用来从本地邮件客户端（如Microsoft Outlook、Outlook Express、Foxmail、Mozilla Thunderbird）访问远程服务器上的邮件。

POP3和IMAP区别：
POP3协议允许电子邮件客户端下载服务器上的邮件，但是在客户端的操作（如移动邮件、标记已读等），不会反馈到服务器上，比如通过客户端收取了邮箱中的3封邮件并移动到其他文件夹，邮箱服务器上的这些邮件是没有同时被移动的 。

而IMAP提供webmail 与电子邮件客户端之间的双向通信，客户端的操作都会反馈到服务器上，对邮件进行的操作，服务器上的邮件也会做相应的动作。

同时，IMAP像POP3那样提供了方便的邮件下载服务，让用户能进行离线阅读。IMAP提供的摘要浏览功能可以让你在阅读完所有的邮件到达时间、主题、发件人、大小等信息后才作出是否下载的决定。此外，IMAP 更好地支持了从多个不同设备中随时访问新邮件。


简单地说，SMTP管‘发’， POP3/IMAP管‘收’。

举个例子，你坐在电脑边用mail client写完邮件，点击‘发送’。这时你的mail client会发消息给邮件服务器上的SMTP service。这时有两种情况：
1、如果邮件的收信人也是处于同一个domain，比如从http://163.com发送给163的邮箱，SMTP service只需要转给local的POP3 Service即可
2、如果邮件收信人是另外的domain，比如http://163.com发送给http://sina.com， SMTP service需要通过询问DNS, 找到属于sina的SMTP service的host

SMTP service收到邮件后转给负责接收邮件的POP3 service

POP3 service和IMAP的区别主要是：

POP3是比较老的protocol，主要为了解决本地机器和远程邮件服务器链接的问题，每次邮件会download到本地机器，然后从远程邮件服务器上删掉（当然特殊config除外），然后进行本地编辑。这样的问题是如果从多个终端链接服务器，只有第一个下载的能看到，现在pop4正在讨论中

IMAP是比较新的（好吧，好像也是80年代的产物）protocol，可以将邮件分文件夹整理，然后这些信息也存在远程的邮件服务器上，读取邮件后，服务器上不删除。原理上IMAP应该是相当于oneline编辑，但现在的mail client基本都有在本地存copy的功能

Exchange主要是MS自己的一套东东

Exchange Server是微软公司的一套电子邮件服务组件。除传统的电子邮件的存取、储存、转发作用外，在新版本的产品中亦加入了一系列辅助功能，如语音邮件、邮件过滤筛选、OWA（基于Web的电子邮件存取）。Exchange Server支持多种电子邮件网络协议，如SMTP、POP3、IMAP4。Exchange Server能够与微软公司的活动目录完美结合。Exchange Server是个消息与协作系统，Exchange server可以被用来构架应用于企业、学校的邮件系统甚至于免费邮件系统。也可以用于开发工作流，知识管理系统，Web系统或者是其他消息系统。






---------------------------------------------------------------------------------------------------------------------
https://blog.csdn.net/chenleixing/article/details/46659939
https://zhuanlan.zhihu.com/p/21999778
https://blog.csdn.net/shmilychan/article/details/51848850
https://blog.csdn.net/cuker919/article/details/6403925



SSH、SCP、SFTP、FTP、Telnet定义和使用


【Telnet】著名的终端访问协议，传统的网络服务程序，如FTP、POP和Telnet，其本质上都是不安全的；因为它们在网络上用明文传送数据、用户帐号和用户口令。
【telnet命令】telnet host [port]

【SSH】Secure Shell 的缩写，是建立在传输层基础上的安全协议，它本身属于应用层，同时可以为应用层提供安全传输服务。

SSH 是目前较可靠，专为远程登录会话和其他网络服务提供安全性的协议。利用 SSH 协议可以有效防止远程管理过程中的信息泄露问题。透过 SSH 可以对所有传输的数据进行加密，也能够防止DNS欺骗和IP欺骗。

SSH 之另一项优点为其传输的数据是经过压缩的，所以可以加快传输的速度。SSH有很多功能，它既可以代替Telnet，又可以为FTP、POP、甚至为PPP提供一个安全的“通道”。

【ssh命令】linux下，用ssh登录服务器，格式如下：
ssh -l 远程服务器用户名 远程服务器ip地址 -p 远程服务器ssh端口（默认22）


SCP（Secure Copy）——Linux文件传送命令
scp 就是 secure copy, 是用来进行远程文件拷贝的 . 数据传输使用 ssh1, 并且和 ssh1 使用相同的认证方式 , 提供相同的安全保证。与 rcp 不同的是 ,scp 会要求你输入密码，如果需要的话。

scp 本地用户名 @IP 地址 : 文件名 1 远程用户名 @IP 地址 : 文件名 2


【FTP】文件传输协议（英文：File Transfer Protocol，简称为FTP）是用于在网络上进行文件传输的一套标准协议。它属于网络协议组的应用层。

【SFTP】
SSH File Transfer Protocol ，有时也被称作 Secure File Transfer Protocol 或SFTP。
它和SCP的区别是它允许用户中断传输，SCP拷贝速度稍快一些。

【sftp命令】另外，小写字母sftp也是linux下的一个命令，遵从SFTP，示例：
sftp -oPort=22 oracle@162.10.0.6


FTP，SFTP，FTPS：
FTP支持两种模式：Standard (PORT方式，主动方式)，Passive (PASV，被动方式)。
FTPS (一种多传输协议)，一种多传输协议，相当于加密版的FTP。默认端口号是21。
SFTP（安全文件传送协议），sftp是Secure File Transfer Protocol的缩写，安全文件传送协议。可以为传输文件提供一种安全的加密方法。



SSH、SCP、SFTP、FTP、Telnet使用：

ssh -l aiesb 20.26.37.179 -p 22
ssh aiesb@20.26.37.180  -p 22
ssh 20.26.37.180    默认和当前登录名一样，-p默认是22

scp aiesb@20.26.37.179:/app/aiesb/test.txt aiesb@20.26.37.180:/app/aiesb
scp -r aiesb@20.26.37.179:/app/aiesb/test.txt aiesb@20.26.37.180:/app/aiesb  文件夹
scp -P 端口号 本地文件路径 用户名@远程服务器地址:远程路径    -P 参数来设置命令的远程服务器的端口号

FTP标准命令TCP端口号为21，Port方式数据端口为20
端口号默认是22

分别以IP地址的方式和域名的方式登录如下:
sftp lzy@202.206.64.33   
sftp lzy@@www.hebust.edu.cn。
回车提示输入密码。进入提示符

sftp aiesb@20.26.37.180:/app/aiesb  进入到某个目录下
下载
get remote_file_name local_file_name
get remote_file_name local_file_name
get -r remote_file_name local_file_name  文件夹

上传
put local_file_name remote_file_name 
put local_file_name 
put -r local_file_name remote_file_name   文件夹

sftp> get /var/www/fuyatao/index.php  /home/fuyatao/
这条语句将从远程主机的  /var/www/fuyatao/目录下将 index.php 下载到本地  /home/fuyatao/目录下。

sftp> put /home/fuyatao/downloads/Linuxgl.pdf /var/www/fuyatao/
这条语句将把本地 /home/fuyatao/downloads/目录下的 linuxgl.pdf文件上传至远程主机/var/www/fuyatao/ 目录下。


在命令前面加上l表示本地，否则是操作远程主机，如lpwd、lcd 是本机操作，pwd、cd 是远程主机操作
其他命令：ls rm rmdir mkdir 等，要离开sftp，用exit 或quit、 bye 均可。详细情况可以查阅 man  sftp.
sftp> lpwd
Local working directory: /app/aiesb
sftp> pwd
Remote working directory: /app/aiesb
sftp> lcd /app
sftp> lpwd
Local working directory: /app
sftp> cd /app/aiesb/app
Couldn't stat remote file: No such file or directory
sftp> cd /app
sftp> pwd
Remote working directory: /app



Telnet
telnet 101.199.97.65 62715
telnet www.baidu.com



---------------------------------------------------------------------------------------------------------------------
SSH、SCP、SFTP、FTP、Telnet常用的java类库

Java SSH库使用简介：Apache sshd和JSch（Java Secure Channel）、jcabi（封装Jsch）
http://www.jcraft.com/jsch/examples/


---------------------------------------------------------------------------------------------------------------------







