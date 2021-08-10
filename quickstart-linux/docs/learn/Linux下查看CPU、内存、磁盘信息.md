Linux下查看CPU、内存、磁盘信息.md


1、查看CPU信息  

其实就是cat /proc/cpuinfo然后根据一些关键字进行排序（sort）、去重（uniq）、统计（wc）等  
关键字比如："physical id"、"cpu cores"、"processor"等  

# 总核数 = 物理CPU个数 X 每颗物理CPU的核数 
# 总逻辑CPU数 = 物理CPU个数 X 每颗物理CPU的核数 X 超线程数

# 查看物理CPU个数
查看 CPU 颗数
实际Server中插槽上的CPU个数, 物理cpu数量，可以数不重复的 physical id个数。
cat /proc/cpuinfo | grep 'physical id' | sort | uniq | wc -l


# 查看每个物理CPU中core的个数(即核数)
查看 CPU 核数
一颗CPU上面能处理数据的芯片组的数量。
cat /proc/cpuinfo| grep "cpu cores"| uniq
cat /proc/cpuinfo |grep "cores"|uniq|awk '{print $4}'

# 查看逻辑CPU的个数
top命令查询出来的就是逻辑CPU的数量。
cat /proc/cpuinfo | grep "processor" | wc -l


# 查看CPU信息（型号）
cat /proc/cpuinfo | grep name | cut -f2 -d: | uniq -c
cat /proc/cpuinfo | grep 'model name' | sort | uniq


逻辑 CPU 核数
一般情况，我们认为一颗cpu可以有多核，加上intel的超线程技术(HT), 可以在逻辑上把一个物理线程模拟出两个线程来使用，使得单个核心用起来像两个核一样，以充分发挥CPU的性能，

逻辑CPU数量=物理cpu数量 x cpu cores 这个规格值 x 2(如果支持并开启超线程)。


参考  
[在Linux中查询CPU的核数](https://colobu.com/2019/02/22/how-to-find-cpu-cores-in-linux/)  
[Linux查看物理CPU个数、核数、逻辑CPU个数](https://www.cnblogs.com/emanlee/p/3587571.html)  
[linux 查看机器cpu核数](https://www.cnblogs.com/hurry-up/p/9564064.html)  
[linux下怎么查看cpu核数](https://blog.csdn.net/fox_wayen/article/details/80642718)  




#查看CPU的负载
平均负载是指上一分钟同时处于就绪状态的平均进程数。在CPU中可以理解为CPU可以并行处理的任务数量，就是CPU个数X核数。
如果CPU Load等于CPU个数乘以核数，那么就说CPU正好满负载，再多一点，可能就要出问题了，有些任务不能被及时分配处理器，那要保证性能的话，最好要小于CPU个数X核数X0.7。
Load Average是指CPU的Load。它所包含的信息是在一段时间内CPU正在处理及等待CPU处理的进程数之和的统计信息，也就是CPU使用队列的长度的统计信息。
Load Average的值应该小于CPU个数X核数X0.7，Load Average会有3个状态平均值，分别是1分钟、5分钟和15分钟平均Load。
如果1分钟平均出现大于CPU个数X核数的情况，还不需要担心；如果5分钟的平均也是这样，那就要警惕了；15分钟的平均也是这样，就要分析哪里出现问题，防范未然。
#CPU负载信息，使用top 命令
top - 15:50:31 up 4 days, 23:43,  1 user,  load average: 0.51, 0.29, 0.37




2、查看内存信息
1）、cat /proc/meminfo
2）、free 命令
             total       used       free     shared    buffers     cached
Mem:      65973912   32496232   33477680          0     906932    6452984
-/+ buffers/cache:   25136316   40837596
Swap:     41943032      13204   41929828




3、查看磁盘信息
1）fdisk -l
2）iostat -x 10    查看磁盘IO的性能
[root@xdpp02 bin]# iostat -x 10
Linux 2.6.32-358.el6.x86_64 (xdpp02)    11/30/2016      _x86_64_        (16 CPU)
avg-cpu:  %user   %nice %system %iowait  %steal   %idle
           3.86    0.00    0.17    0.06    0.00   95.90
Device:         rrqm/s   wrqm/s     r/s     w/s   rsec/s   wsec/s avgrq-sz avgqu-sz   await  svctm  %util
sda               0.77    84.89    0.62    3.11    15.72   703.97   193.03     0.12   31.10   2.86   1.07
dm-0              0.00     0.00    0.15    0.22     2.93     1.78    12.75     0.00   12.42   3.77   0.14
dm-1              0.00     0.00    0.09    0.10     0.75     0.83     8.00     0.00   14.53   0.28   0.01




参考
https://www.cnblogs.com/gytbolg/p/11013159.html



