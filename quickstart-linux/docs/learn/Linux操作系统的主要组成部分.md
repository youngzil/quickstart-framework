Linux操作系统的主要组成部分


操作系统的基本组成部分
1、进程管理(进程调度)
2、存储管理(内存管理)
3、文件管理(文件系统)
4、设备管理(设备驱动)
5、系统调用、


Linux系统一般有4个主要部分：内核、shell、文件系统和应用程序。
内核、shell和文件系统一起形成了基本的操作系统结构，它们使得用户可以运行程序、管理文件并使用系统。

一．Linux内核
   内核是操作系统的核心，具有很多最基本功能，如虚拟内存、多任务、共享库、需求加载、可执行程序和TCP/IP网络功能。
   Linux内核的模块分为以下几个部分：存储管理、CPU和进程管理、文件系统、设备管理和驱动、网络通信、系统的初始化和系统调用等。

二．Linux shell
      shell是系统的用户界面，提供了用户与内核进行交互操作的一种接口。
      它接收用户输入的命令并把它送入内核去执行，是一个命令解释器。
      另外，shell编程语言具有普通编程语言的很多特点，用这种编程语言编写的shell程序与其他应用程序具有同样的效果。

三．Linux文件系统
      文件系统是文件存放在磁盘等存储设备上的组织方法。
      Linux系统能支持多种目前流行的文件系统，如EXT2、EXT3、FAT、FAT32、VFAT和ISO9660。

四．Linux应用程序
     标准的Linux系统一般都有一套都有称为应用程序的程序集，它包括文本编辑器、编程语言、XWindow、办公套件、Internet工具和数据库等。



用户态和内核态

Linux系统将自身划分为两部分，一部分为核心软件，即是kernel，也称作内核空间，另一部分为普通应用程序，这部分称为用户空间。
区分用户空间和内核空间的目的是为确保系统安全。
CPU将指令分为特权指令和非特权指令，对于那些危险的指令，只允许操作系统及其相关模块使用，普通的应用程序只能使用那些不会造成灾难的指令。
Intel的CPU将特权级别分为4个级别：RING0,RING1,RING2,RING3， 内核空间级别为“RING0”， 用户空间级别为RING3。


内核空间和用户空间
    x86 CPU采用了段页式地址映射模型。进程代码中的地址为逻辑地址，经过段页式地址映射后，才真正访问物理内存。
    通常32位Linux内核地址空间划分0~3G为用户空间，3~4G为内核空间。64位内核地址空间划分是不同的。


内核态与用户态
1、用户态Ring3状态不能访问内核态Ring0的地址空间，包括代码和数据。(例如32位Linux进程的4GB地址空间，3G-4G部 分大家是共享的，是内核态的地址空间，这里存放在整个内核的代码和所有的内核模块，以及内核所维护的数据）。
    2、用户运行一个程序，该程序所创建的进程开始是运行在用户态的，如果要执行文件操作，网络数据发送等操作，必须通过write，send等系统调用，这些系统调用会调用内核中的代码来完成操作，
    3、这时，必须切换到Ring0，然后进入内核地址空间去执行这些代码完成操作，完成后，切换回Ring3，回到用户态。这样，用户态的程序就不能 随意操作内核地址空间，具有一定的安全保护作用。


处理器总处于以下状态中的一种：
1、内核态，运行于进程上下文，内核代表进程运行于内核空间；
2、内核态，运行于中断上下文，内核代表硬件运行于内核空间；
3、用户态，运行于用户空间。



从用户空间到内核空间有两种触发手段：
1.系统调用：
2.中断:

1.系统调用：
用户空间的应用程序，通过系统调用，进入内核空间。这个时候用户空间的进程要传递很多变量、参数的值给内核，内核态运行的时候也要保存用户进程的一些寄存器值、变量等。所谓的“进程上下文”，可以看作是用户进程传递给内核的这些参数以及内核要保存的那一整套的变量和寄存器值和当时的环境等。

2.中断:
硬件通过触发信号，导致内核调用中断处理程序，进入内核空间。例如网卡发送一个数据包或硬盘驱动器提供一次 IO 请求等。这个过程中，硬件的一些变量和参数也要传递给内核，内核通过这些参数进行中断处理。所谓的“中断上下文”，其实也可以看作就是硬件传递过来的这些参数和内核需要保存的一些其他环境（主要是当前被打断执行的进程环境）。



参考
https://blog.csdn.net/hguisu/article/details/6122513
https://blog.csdn.net/perfectguyipeng/article/details/76794552


---------------------------------------------------------------------------------------------------------------------


