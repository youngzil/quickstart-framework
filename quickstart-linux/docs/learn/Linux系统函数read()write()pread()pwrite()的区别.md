Linux系统函数read()/write()/pread()/pwrite()的区别

pread函数用于从打开文件的指定位置开始读取数据
pwrite函数用于从打开文件的指定位置处写入指定字节的数据

函数原型
Ssize_t pread(int fd,void *buf,size_t nbytes,off_t offset);
Ssize_t write(int fd,const void *buf,size_t nbytes,off_t offset);

pread相当于先调用lseek接着调用read。但又不完全是这样：
（1）pread是原子操作，定位和读操作在一个原子操作中完成，期间不可中断。但分开的lseek和read中间可能被其他程序中断。
（2）pread不更改当前文件的指针，也就是说不改变当前文件偏移量。
（3）pread中的offset是一个绝对量，相对于文件开始处的绝对量，与当前文件指针位置无关。


pwrite相当于先调用lseek接着调用write。但又不完全是这样：
（1）pwrite是原子操作，定位和写操作在一个原子操作中完成，期间不可中断。但分开的lseek和write中间可能被其他程序中断。
（2）pwrite不更改当前文件的指针，也就是说不改变当前文件偏移量。
（3）pwrite中的offset是一个绝对量，相对于文件开始处的绝对量，与当前文件指针位置无关。


（1）read()
原因：基本系统调用功能；
实现：文件（由filedes所指）－读nbytes字节－＞内存buf中。
补充：有多种情况可使实际读到的字节数少于要求读的字节数：

当从普通文件读时，在读到要求字节数之前已到达了文件尾端。
当从终端设备读时，通常一次最多读一行。
当从网络读时，网络中缓冲机构可能造成返回值小于所要求读的字节数。
当从管道或FIFO读时，如若管道包含的字节少于所需的数量，那么只返回实际用的字节数。
当从某些面向记录的设备读时，一次最多返回一个记录。
当某一信号造成中断，而已经读了部分数据量时。
读操作从文件的当前偏移量处开始，在成功返回之前，该偏移量将增加实际读到的字节数。常用的unix系统shell都提供一种方法，它在标准输入上打开一个文件，在标准输出上追寻或重写一个文件，这使得程序不必自行打开输入和输出文件。


（2）write()
原因：基本系统调用功能；
实现：文件（由filedes所指）＜－写nbytes字节－内存buf中。
补充：write出错的一个常见的原因是：磁盘已写满，或者超过了一个给定进程的文件长度限制。对于普通文件，写操作从文件的当前偏移量处开始。如果在打开该文件时，指定了O＿APPEND选项，则在每次写操作之前，将文件偏移量设置在文件的当前结尾处。在一次成功写之后，该文件偏移量增加实际写的字节数。


（3）pread()
原因：由于lseek和read 调用之间，内核可能会临时挂起进程，所以对同步问题造成了问题，调用pread相当于顺序调用了lseek 和　read，这两个操作相当于一个捆绑的原子操作。
实现：文件（由filedes所指）－读nbytes字节－＞内存buf中。
补充：调用pread时，无法中断其定位和读操作，另外不更新文件指针。


（4）pwrite()
原因：由于lseek和write 调用之间，内核可能会临时挂起进程，所以对同步问题造成了问题，调用pwrite相当于顺序调用了lseek 和　write，这两个操作相当于一个捆绑的原子操作。
实现：文件（由filedes所指）＜－写nbytes字节－内存buf中。
补充：调用pwrite时，无法中断其定位和读操作，另外不更新文件指针。



参考
https://blog.csdn.net/zhoulaowu/article/details/14046309
https://blog.csdn.net/zhoulaowu/article/details/14045039
https://blog.csdn.net/u013525455/article/details/52661313
https://blog.csdn.net/u011573853/article/details/51852974


