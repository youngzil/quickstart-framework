1、linux 内存清理/释放命令
2、查看cpu的方法
3、查找文件夹下某个文件

4、查看磁盘和文件大小
  tar命令
  Linux查看端口占用

5、

6、

7、

8、

9、

10、

11、


---------------------------------------------------------------------------------------------------------------------
linux 内存清理/释放命令

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




查看cpu的方法
1、 cat /proc/cpuinfo  或者 更直观的查看cpu的型号命令：dmesg |grep -i xeon

查看内存的方法
2、 cat /proc/meminfo  或者 更直观的查看内存的命令：free -m

查看硬盘大小
3、df -h
最后用top命令也可以查看到cpu和内存的使用率 在输入top命令之后直接按"1" 就能很清楚的查看到cpu和内存的使用情况。



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


查看磁盘和文件大小：
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



Linux查看端口占用：
方法1: lsof命令,即ls open files
lsof -i:端口号

方法2: netstat命令
netstat -tunpl | grep 端口号


