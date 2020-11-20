- [Linux内存清理/释放命令](#Linux内存清理/释放命令)
- [内存清理buffer/cache/swap的区别和清理方法](#内存清理buffer/cache/swap的区别和清理方法)
- [查看cpu的方法](#查看cpu的方法)
- [查找文件夹下某个文件或文件夹](#查找文件夹下某个文件或文件夹)
- [查看磁盘和文件大小](#查看磁盘和文件大小)
- [](#)
- [Linux根据端口号查看进程PID](#Linux根据端口号查看进程PID)
- [解压多个压缩包：.gz或.tar.gz](#解压多个压缩包：.gz或.tar.gz)
- [使用grep命令进行多条件查询（AND，OR，NOT）](#使用grep命令进行多条件查询（AND，OR，NOT）)

---------------------------------------------------------------------------------------------------------------------
## Linux内存清理/释放命令

释放内存的时候，首先执行命令 sync 将所有正在内存中的缓冲区写到磁盘中，其中包括已经修改的文件inode、已延迟的块I/O以及读写映射文件，从而确保文件系统的完整性

1.清理前内存使用情况 
free -m

2.首先写缓存到文件系统：sync

3.开始清理，然后执行下面命令释放内存(页缓存buff/cache)：
echo 1 > /proc/sys/vm/drop_caches
到这里内存就释放完了，现在drop_caches中的值为1，如果现在想让操作系统重新分配内存，那么设置drop_caches的值为0即可：
echo 0 > /proc/sys/vm/drop_caches

4.清理后内存使用情况 
free -m

另外需要注意的是，在生产环境中的服务器我们不要频繁的去释放内存，只在必要时候清理内存即可，更重要的是我们应该从应用程序层面去优化内存的利用和释放，经常清理内存可能只是暂时屏蔽的应用程序中的一些bug，所以更重要的是程序的调优，其他的交给操作系统来管理
https://www.cnblogs.com/freeweb/p/5713513.html




## 查看cpu的方法
1、 cat /proc/cpuinfo  或者 更直观的查看cpu的型号命令：dmesg |grep -i xeon

查看内存的方法
2、 cat /proc/meminfo  或者 更直观的查看内存的命令：free -m

查看硬盘大小
3、df -h
最后用top命令也可以查看到cpu和内存的使用率 在输入top命令之后直接按"1" 就能很清楚的查看到cpu和内存的使用情况。



## 查找文件夹下某个文件或文件夹
查找文件夹下某个文件或文件夹，并执行删除
sudo find /Users/yangzl/git/quickstart-all -name 文件或文件夹名称 |xargs rm -rf



umask:默认值是0022
Linux里的文件权限可分为3组，分别是文件拥有者、同个群组的其他用户、不同群组的其他用户。
每一组又有3种不同权限，分别是可读权限（r）、可写权限（w）、可执行权限（x）。
可读权限用二进制表示的话是100，即十进制的4，
可写权限用二进制表示的话是10，即十进制的2，
可执行权限用二进制表示的话是1，也就是十进制的1。
因此有时候我们也会用3个十进制数字来表示文件的权限，比如777表示的是文件拥有者、同个群组的其他用户和不同群组的其他用户都拥有可读、可写和可执行权限，因为7=4+2+1。要查看文件的权限可以用命令“ls -al”，

对于文件而言，其默认的最大权限就是666，表示对于文件拥有者、同个群组的其他用户和不同群组的其他用户都具有可读和可写权限。
对于目录而言，其默认的最大权限就是777，对于目录而言，可执行权限与用户是否能进入该目录有关，因此默认情况下，目录的所有权限都对外开放，即默认的最大权限为777，表示对于文件拥有者、同个群组的用户和不同群组的其他用户都具有可读、可写和可执行权限。

umask的值表示的是文件或目录的“默认最大值”需要减掉的权限
如umask默认值是0022，则默认创建的文件权限是644，默认创建的目录权限是755

修改默认权限：
umask xyz（umask指定的是默认值需要减掉的权限，x为owner需要去掉的权限，y为group需要去掉的权限，z为other需要去掉的权限）


## 查看磁盘和文件大小
du -sh -m
df -h -m


tar命令：
tar -xzvf test.tar.gz test/
tar -czvf test.tar.gz test/

awk是行处理器: 相比较屏幕处理的优点，在处理庞大文件时不会出现内存溢出或是处理缓慢的问题，通常用来格式化文本信息
awk处理过程: 依次对每一行进行处理，然后输出
esbdir=`pwd|awk -F'/' '{print $4}'`
pwd作为输入，-F是以什么为分隔符，然后获取第5个参数，$0 是全行数据，$1 是分割后的第1个数据



杀死进程
ps -ef|grep /usr/local/tomcat_coachqa/ |grep -v grep |awk '{print $2}'|xargs kill -9

kill -9  ps -ef|grep /usr/local/tomcat_coachqa/ |grep -v grep |awk '{print $2}'




dirname filename  返回filename的路径
basename filename [suffix] 返回文件名字 或者 文件名字去除suffix部分

basename /boot/grub/grub.conf.bak .bak 返回grub.conf
basename /boot/grub/grub.conf.bak .conf.bak  返回grub

for file in `find $APP_HOME -name "start*.sh"`
在$APP_HOME路径下面找start开头的sh文件，返回的是sh文件的全路径


ssh  aiesb@10.76.232.148 "cd ~/esb_1/sbin;start_all.sh"




## 内存清理buffer/cache/swap的区别和清理方法

Swap用途：Swap意思是交换分区，通常我们说的虚拟内存，是从硬盘中划分出的一个分区。当物理内存不够用的时候，内核就会释放缓存区（buffers/cache）里一些长时间不用的程序，然后将这些程序临时放到Swap中，也就是说如果物理内存和缓存区内存不够用的时候，才会用到Swap。
cache是高速缓存，用于CPU和内存之间的缓冲；
buffer是I/O缓存，用于内存和硬盘的缓冲；
buffer是即将要被写入磁盘的，而cache是被从磁盘中读出来的。


swap清理：swapoff -a && swapon -a
注意：这样清理有个前提条件，空闲的内存必须比已经使用的swap空间大



参考
https://www.cnblogs.com/kevingrace/p/5991604.html



---------------------------------------------------------------------------------------------------------------------
## Linux根据端口号查看进程PID

Linux查看端口占用：
方法1: lsof命令,即ls open files
lsof -i :端口号


方法2: netstat命令
netstat -tunpl | grep 端口号
netstat -natp | grep 1521
netstat -nlp|grep :80
 netstat -tunlp|grep port


3、命令ps，可以查看已知进程PID的执行目录的详细信息
ps -ef | grep 8246
ps -x | grep 8246  
ps aux | grep pid




 mkdir -p /data/db
-p, --parents 需要时创建上层目录，如目录早已存在则不当作错误
命令格式：mkdir [-p] DirName
说明：建立一个子目录。
参数：-p 确保目录名称存在，如果目录不存在的就新创建一个。




---------------------------------------------------------------------------------------------------------------------
lsof输出各列信息的意义如下：

COMMAND：进程的名称
PID：进程标识符
USER：进程所有者
FD：文件描述符，应用程序通过文件描述符识别该文件。如cwd、txt等
TYPE：文件类型，如DIR、REG等
DEVICE：指定磁盘的名称
SIZE：文件的大小
NODE：索引节点（文件在磁盘上的标识）
NAME：打开文件的确切名称

lsof ［options］ filename

常用的参数列表：

lsof  filename 显示打开指定文件的所有进程
lsof -a 表示两个参数都必须满足时才显示结果
lsof -c string   显示COMMAND列中包含指定字符的进程所有打开的文件
lsof -u username  显示所属user进程打开的文件
lsof -g gid 显示归属gid的进程情况
lsof +d /DIR/ 显示目录下被进程打开的文件
lsof +D /DIR/ 同上，但是会搜索目录下的所有目录，时间相对较长
lsof -d FD 显示指定文件描述符的进程
lsof -n 不将IP转换为hostname，缺省是不加上-n参数
lsof -i 用以显示符合条件的进程情况
lsof -i[46] [protocol][@hostname|hostaddr][:service|port]
            46 --> IPv4 or IPv6
            protocol --> TCP or UDP
            hostname --> Internet host name
            hostaddr --> IPv4地址
            service --> /etc/service中的 service name (可以不只一个)
            port --> 端口号 (可以不只一个)

lsof | grep 1521
lsof -p 25495
lsof -i -p 25495
lsof -i :1521
lsof -i @20.26.37.179
lsof -i TCP

---------------------------------------------------------------------------------------------------------------------
参考
https://www.cnblogs.com/ggjucheng/archive/2012/01/08/2316661.html

Netstat 命令用于显示各种网络相关信息，如网络连接，路由表，接口状态 (Interface Statistics)，masquerade 连接，多播成员 (Multicast Memberships) 等等。
常见参数
-a (all)显示所有选项，默认不显示LISTEN相关
-t (tcp)仅显示tcp相关选项
-u (udp)仅显示udp相关选项
-n 拒绝显示别名，能显示数字的全部转化成数字。
-l 仅列出有在 Listen (监听) 的服務状态

-p 显示建立相关链接的程序名
-r 显示路由信息，路由表
-e 显示扩展信息，例如uid等
-s 按各个协议进行统计
-c 每隔一个固定时间，执行该netstat命令。

提示：LISTEN和LISTENING的状态只有用-a或者-l才能看到


列出所有 tcp 端口 netstat -at
列出所有 udp 端口 netstat -au




---------------------------------------------------------------------------------------------------------------------
bogon:Documents yangzl$ jar
用法: jar {ctxui}[vfmn0PMe] [jar-file] [manifest-file] [entry-point] [-C dir] files ...
选项:
    -c  创建新档案
    -t  列出档案目录
    -x  从档案中提取指定的 (或所有) 文件
    -u  更新现有档案
    -v  在标准输出中生成详细输出
    -f  指定档案文件名
    -m  包含指定清单文件中的清单信息
    -n  创建新档案后执行 Pack200 规范化
    -e  为捆绑到可执行 jar 文件的独立应用程序
        指定应用程序入口点
    -0  仅存储; 不使用任何 ZIP 压缩
    -P  保留文件名中的前导 '/' (绝对路径) 和 ".." (父目录) 组件
    -M  不创建条目的清单文件
    -i  为指定的 jar 文件生成索引信息
    -C  更改为指定的目录并包含以下文件
如果任何文件为目录, 则对其进行递归处理。
清单文件名, 档案文件名和入口点名称的指定顺序
与 'm', 'f' 和 'e' 标记的指定顺序相同。

示例 1: 将两个类文件归档到一个名为 classes.jar 的档案中:
       jar cvf classes.jar Foo.class Bar.class
示例 2: 使用现有的清单文件 'mymanifest' 并
           将 foo/ 目录中的所有文件归档到 'classes.jar' 中:
       jar cvfm classes.jar mymanifest -C foo/ .


创建jar
jar cvf classes.jar Foo.class Bar.class

更新jar
jar -uvf aiesb.jar com/quickstart/openplatform/isb/restful/server/servlet/AopResourceConfig.class
jar -uvf aop-common-task-jar-with-dependencies.jar log4j.properties

解压
jar -xvf aiosptask_lib.jar
unzip aiosptask_lib.jar

列出文件
jar -tvf aop-common-task-jar-with-dependencies.jar.bak | grep defaults.xml


修改jar包中的配置文件：
方式一 通过vim命令直接修改保存jar。超方便。
方式二 通过jar命令替换jar包中的文件(也可新增)：先提取出文件，然后修改，然后再替换jar包中的文件
方式三 解压jar包，修改后重新打包jar



方式一 通过vim命令直接修改保存jar。超方便。
1.通过vim命令直接编辑jar
vim xxx.jar 该命令首先会列出全部文件，可以通过输入/abc来搜索，定位到对应的abc文件后回车进入配置文件内进行编辑，:wq保存。



方式二 通过jar命令替换jar包中的文件(也可新增)
先提取出文件，然后修改，然后再替换jar包中的文件

1.列出jar包中的文件清单
jar tf genesys_data_etl-0.0.1-SNAPSHOT.jar

2.提取出内部jar包的指定文件
jar xf genesys_data_etl-0.0.1-SNAPSHOT.jar BOOT-INF/classes/realtime/t_ivr_data_bj.json

3.然后可以修改文件
vim BOOT-INF/classes/realtime/t_ivr_data_bj.json

4.更新配置文件到内部jar包.(存在覆盖，不存在就新增)
jar uf genesys_data_etl-0.0.1-SNAPSHOT.jar BOOT-INF/classes/realtime/t_ivr_data_bj.json      

4.1更新内部jar包到jar文件
jar uf genesys_data_etl-0.0.1-SNAPSHOT.jar 内部jar包.jar     

5.可以查看验证是否已经更改
vim genesys_data_etl-0.0.1-SNAPSHOT.jar



方式三 解压jar包，修改后重新打包jar
1.解压
unzip genesys_data_etl-0.0.1-SNAPSHOT.jar 
2.移除jar包,最好备份
rm genesys_data_etl-0.0.1-SNAPSHOT.jar
3.重新打包
jar -cfM0 new-genesys_data_etl-0.0.1-SNAPSHOT.jar *
或者
jar -cvfm0 genesys_data_etl-0.0.1-SNAPSHOT.jar ./META-INF/MANIFEST.MF ./
4.运行
java -jar new-genesys_data_etl-0.0.1-SNAPSHOT.jar


jar命令参数:
-c 创建新的存档
-f 指定存档文件名
-M 不配置配置清单，这样还可以使用maven生成的配置清单也就是MANIFEST.MF
-0 不进行压缩,如果压缩会有问题
-m 指定清单文件
-t 列出归档目录
-x 从档案中提取指定的 (或所有) 文件 
-u 更新现有的归档文件 
-v 在标准输出中生成详细输出 




Linux下如何在不解压jar包查看或修改配置文件
https://jingyan.baidu.com/article/91f5db1b1b66a41c7e05e36c.html
更新jar包里的配置文件
https://www.cnblogs.com/dayou123123/p/6845432.html
修改jar包中的配置文件
https://blog.csdn.net/young_kim1/article/details/50482398 --------------------- 本文来自 daydayupzzc 的CSDN 博客 ，全文地址请点击：https://blog.csdn.net/daydayupzzc/article/details/80816529?utm_source=copy


---------------------------------------------------------------------------------------------------------------------
文件：创建、查看、权限

创建文件
cp /dev/null test.log
'' > test.log
vi test.log

less oppf_task-20190417.log
more oppf_task-20190417.log
vi oppf_task-20190417.log
ctrl + F - 向前移动一屏
ctrl + B - 向后移动一屏


Linux文件权限
https://www.jianshu.com/p/8566a74e77be

第一个字符代表了对象的类型：
- 代表文件
d 代表目录
l 代表链接
c 代表字符型设备
b 代表块设备
n 代表网络设备

r代表对象是可读的
w 代表对象是可写的
x 代表对象是可执行的

u:代表用户
g:代表组
o:代表其他
a:代表上述所有


对于文件 666 表示最大权限 对于目录777表示最大权限

chmod 666 dd //表示所有读写权限都开放
chmod o+r dd //给dd文件其他用户赋予读取权限


改变所属关系
chown options owner[.group] file
chown zhanglinyu dd //属主变成zhanglinyu
chown zhanglinyu.staff dd//属主和属组都改变
chown .staff dd//改变属组
chown test. dd //如果你的Linux系统采用和用户登录名匹配的组名，可以只用一个条目就改变二者。

chown 命令采用一些不同的选项参数。
-R 选项配合通配符可以递归地改变子目录和文件的所属关系。
-h 选项可以改变该文件的所有符号链接文件的所属关系。


查看文件
1.cat 与 tac
2.more和less（常用）
3.head和tail
4、nl
5、vim、vi


more test.log
less test.log
less的功能和more相似，但是使用more无法向前翻页，只能向后翻。

cat test.log
tac的功能是将文件从最后一行开始倒过来将内容数据输出到屏幕上。我们可以发现，tac实际上是cat反过来写。这个命令不常用。


head和tail通常使用在只需要读取文件的前几行或者后几行的情况下使用。head的功能是显示文件的前几行内容


nl的功能和cat -n一样，同样是从第一行输出全部内容，并且把行号显示出来
nl test.log



vim test.log
vi test.log



往文件写入或者追加内容
cat test.log >> test-bak.log
echo bbbbb>a.txt

这里注意 >是覆盖，>>是追加。



文件搜索
grep '要搜索的字符串' ddmp-server1.log 
vi 文件，然后/搜索




搜索文件
find . -type f -perm 644 -exec ls -l {} \;
find /data/work/logs -type f -name ddmp-server1.log -exec  grep -i 'gps' {} \;
find /data/work/logs -type f -name ddmp-server1.log -exec  grep -i 'gps' {} \; -print ;
find / -name docbook.xsl


find . -type f -iname "*template*.xml"
find . -type f -iname "*comment*.xml"
find . -type f -iname "sample.db"
codeCommentsTemplates

find . -type f -iname ".classpath" | xargs rm
find . -type f -iname ".project" | xargs rm
find . -type f -iname ".gitignore" | xargs rm

find . -type d -iname "target" | xargs rm -rf
find . -type d -iname ".settings" | xargs rm -rf



---------------------------------------------------------------------------------------------------------------------
jar命令后面跟参数，--server.port=9995是Sprig中覆盖默认参数的方式

java -jar ddmp-server.jar 9998 9997
java -jar  ddmp-test-0.0.1.jar --mqtt.client.groupId=group22 --server.port=9995 --mqtt.client.subscribe=false



/aifs01/java/jdk1.8.0_151/bin/java -jar -Xms64m -Xmx128m ../deploy/ddmp-http-1.0.war --server.port=9000 > ../logs/ddmp-http.log 2>&1 &
java -jar -Xms256m -Xmx2048m ../deploy/ddmp-mgmt-1.0.war --server.port=9100 > ../logs/ddmp-mgmt.log 2>&1 &

set JAVA_OPTS='-Xms256 -Xmx2048'
/aifs01/java/jdk1.8.0_151/bin/java -jar -Xms256m -Xmx2048m ../deploy/ddmp-server-1.0.jar 1883 9200 > ../logs/ddmp-server1.log 2>&1 &
---------------------------------------------------------------------------------------------------------------------

linux shell中"2>&1"含义

在计划任务中经常可以看到。例如我们公司的计划任务举例：
*/2 * * * * root cd /opt/xxxx/test_S1/html/xxxx/admin; php index.php task testOne >/dev/null 2>&1
*/2 * * * * root cd /opt/xxxx/test_S1/html/xxxx/admin; php index.php task testTwo >/dev/null 2>&1

对于& 1 更准确的说应该是文件描述符 1,而1标识标准输出，stdout。
对于2 ，表示标准错误，stderr。
2>&1 的意思就是将标准错误重定向到标准输出。这里标准输出已经重定向到了 /dev/null。那么标准错误也会输出到/dev/null

可以把/dev/null 可以看作"黑洞". 它等价于一个只写文件. 所有写入它的内容都会永远丢失. 而尝试从它那儿读取内容则什么也读不到.

偶尔也可以把 & 在命令的最后加上，表示让程序后台执行。

为何2>&1要写在后面？

index.php task testOne >/dev/null 2>&1
我们可以理解为，左边是标准输出，好，现在标准输出直接输入到 /dev/null 中，而2>&1是将标准错误重定向到标准输出，所以当程序产生错误的时候，相当于错误流向左边，而左边依旧是输入到/dev/null中。

可以理解为，如果写在中间，那会把隔断标准输出指定输出的文件

你可以用

ls 2>1测试一下，不会报没有2文件的错误，但会输出一个空的文件1；
ls xxx 2>1测试，没有xxx这个文件的错误输出到了1中；
ls xxx 2>&1测试，不会生成1这个文件了，不过错误跑到标准输出了；
ls xxx >out.txt 2>&1, 实际上可换成 ls xxx 1>out.txt 2>&1；重定向符号>默认是1,错误和输出都传到out.txt了。



https://blog.csdn.net/zhaobeibei123/article/details/76602202
Linux--标准输入输出、重定向及管道运用

STDIN             0              标准输入     键盘           命令在执行时所要的输入数据通过它来取得

    STDOUT           1              标准输出      显示器        命令在执行后的输出结果从该端口送出

    STDERR           2              标准错误      显示器         命令执行时的错误信息通过该端口送出

    系统重定向:

     重定向就是不适用系统的标准输入端口，标准输出端口和标准错误输出端口，而进行重新的指定，所以重定向分为输入、输出和错误重定向，通常情况下重定向到一个文件。

重定向符号                     说明

 <                          实现输入重定向。输入重定向不经常使用，因为大多数命令都以参数的形式在命令行上指定输入文件的                                     文件名，尽管如此，当使用一个不接受文件名为输入参数的命令，而需要的输入又是在一个已存在的文                                    件里，就可以使用输入重定向解决问题。 

 >或>>                        输出重定向。相对于输入重定向来说，输出重定向更常用，输出重定向使用户能把一个命令的输出重                                      定向 到一个文件里，而不是显示在屏幕上，这种功能使用于多种情况，例如，如果某个命令的输出很                                     多，在屏幕上不能完全显示，即可把他重定向到一个文件中，稍后在用文本编辑器来打开这个文件  

 2>或>>                         错误重定向

 &>                          同时实现输出重定向和错误重定向        
   


 管道命令"|" 将这些命令前后连接在一起，形成一条管道线，
 例如:
 
      a.分屏显示文件文件/etc/passwd的内容
 
       cat /etc/passwd | more
 
      b.统计文本文件/etc/passwd的行数，字数和字符数
 
        cat /etc/passwd | wc
 
      c. 查找是否存在ceshi 的用户账号
 
        cat /etc/passwd | grep ceshi
 
      d. 查看系统是否安装了apache软件包
 
        rpm -qa | grep http
 
 
---------------------------------------------------------------------------------------------------------------------
https://blog.csdn.net/weixin_37490221/article/details/81539341
nohup和&的区别


Linux信号机制
在计算机科学中，信号是Unix、类Unix以及其他POSIX兼容的操作系统中进程间通讯的一种有限制的方式。它是一种异步的通知机制，用来提醒进程一个事件已经发生。当一个信号发送给一个进程，操作系统中断了进程正常的控制流程，此时，任何非原子操作都将被中断。如果进程定义了信号的处理函数，那么它将被执行，否则就执行默认的处理函数。

Linux信号列表
通过kill -l命令获得


SIGINT信号
程序终止(interrupt)信号, 在用户键入INTR字符(通常是Ctrl-C)时发出，用于通知前台进程组终止进程。

SIGHUP信号
本信号在用户终端连接(正常或非正常)结束时发出, 通常是在终端的控制进程结束时, 通知同一session内的各个作业, 这时它们与控制终端不再关联。登录Linux时，系统会分配给登录用户一个终端(Session)。在这个终端运行的所有程序，包括前台进程组和后台进程组，一般都属于这个Session。当用户退出Linux登录时，前台进程组和后台有对终端输出的进程将会收到SIGHUP信号。这个信号的默认操作为终止进程，因此前台进程组和后台有终端输出的进程就会中止。此外，对于与终端脱离关系的守护进程，这个信号用于通知它重新读取配置文件。

ctrl + c 产生SIGINT信号，关闭shell窗口产生SIGHUP信号


&运行程序时在命令后边加一个”&“符号，&的意思是在后台运行
可以看到即使我们使用了ctrl + c ，storm的nimbus进程也依旧存活，但是当我们关掉当前shell窗口的话，进程就会被杀死。因为&只对SIGINT信号免疫，而对SIGHUP信号不免疫

nohup的意思是忽略SIGHUP信号,但是当我们ctrl + c 之后，storm进程就停止了,可见nohup对对SIGINT信号并不免疫。
                                                 

&只对SIGINT信号免疫，不受用户键入INTR字符(通常是Ctrl-C)时发出程序终止(interrupt)信号的影响
nohup的意思是忽略SIGHUP信,不受关闭终端(Session)shell窗口的影响


所以，要想进程不受shell中Ctrl C和Shell窗口关闭的影响，就将nohup和&指令一起使用吧



---------------------------------------------------------------------------------------------------------------------

ip_array=(
112.35.58.12
112.35.58.14
112.35.58.23
112.35.47.15
112.35.47.17
112.35.47.20
)

for ip in ${ip_array[*]}
	do
	echo "start grep:IP=$ip,query=$1"
	ssh ddmp@$ip "grep $1 /data/work/logs/ddmp-server1.log "
	echo "end grep:IP=$ip,query=$1"
	done  


---------------------------------------------------------------------------------------------------------------------


linux系统配置

ssh 10.112.56.136
echo "kernel.pid_max=409600" >> /etc/sysctl.conf  
sysctl -p  
sysctl kernel.pid_max
ifconfig


系统配置文件：
/proc/sys/kernel/pid_max
/etc/sysctl.conf

cat /proc/sys/net/ipv4/ip_local_port_range

echo "kernel.pid_max=409600" >> /etc/sysctl.conf  
sysctl -p  
sysctl kernel.pid_max


ssh 10.112.56.136
echo "net.ipv4.ip_local_port_range = 1024 64999" >>/etc/sysctl.conf
sysctl -p 
sysctl net.ipv4.ip_local_port_range
ifconfig

sysctl kernel.threads-max

cat /proc/sys/kernel/pid_max
more /proc/sys/kernel/pid_max
vim /proc/sys/kernel/pid_max


---------------------------------------------------------------------------------------------------------------------
## 解压多个压缩包：.gz或.tar.gz


对于解压多个.gz文件的，用此命令：
for gz in *.gz; do gunzip $gz; done

对于解压多个.tar.gz文件的，用下面命令：
for tar in *.tar.gz;  do tar xvf $tar; done


参考
https://www.cnblogs.com/z00377750/p/9202558.html


---------------------------------------------------------------------------------------------------------------------


## 使用grep命令进行多条件查询（AND，OR，NOT）

Linux--使用grep命令进行多条件查询（AND，OR，NOT）

grep -rn "runlog" *
说明：
-r 是递归查找
-n 是显示行号
* : 表示当前目录所有文件，也可以是某个文件名

grep是一个非常好用的内容查询命令。如果使用grep来进行条件查询，你的水平就会提高又一等级。


1.grep的AND，多条件的与查询。
用法：
grep 'pattern1' filename | grep 'pattern2'
使用管道符连接多个grep命令就可以得到多个条件同时满足才能查询出来的结果。

2.grep的OR，多条件的或查询。
用法1：
grep 'pattern1\|pattern2' filename
使用转义字符将管道符转为'或'符号。

用法2：
grep -E 'pattern1|pattern2' filename
使用正则表达式选项。

用法3：
egrep 'pattern1|pattern2' filename
egrep相当于grep -E。

grep -E "[1-9]+"
或
egrep "[1-9]+"


3.grep的NOT，非查询（不满足当前条件的所有内容行）。
用法：
grep -v 'pattern' filename


4.统计文件或者文本中包含匹配字符串的行数 -c 选项
语法：
grep -c "text" file_name


1、或操作
grep -E '123|abc' filename  // 找出文件（filename）中包含123或者包含abc的行
egrep '123|abc' filename    // 用egrep同样可以实现
awk '/123|abc/' filename   // awk 的实现方式

2、与操作
grep pattern1 files | grep pattern2 //显示既匹配 pattern1 又匹配 pattern2 的行。

3、其他操作
grep -i pattern files   //不区分大小写地搜索。默认情况区分大小写，
grep -l pattern files   //只列出匹配的文件名，
grep -L pattern files   //列出不匹配的文件名，
grep -w pattern files  //只匹配整个单词，而不是字符串的一部分（如匹配‘magic’，而不是‘magical’），
grep -C number pattern files //匹配的上下文分别显示[number]行，


参考
https://blog.csdn.net/liupeifeng3514/article/details/79880878
https://blog.csdn.net/mmbbz/article/details/51035401





---------------------------------------------------------------------------------------------------------------------











---------------------------------------------------------------------------------------------------------------------






