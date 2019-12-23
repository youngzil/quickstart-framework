内存管理优化总结:ptmalloc、tcmalloc和jemalloc.md

##系统的物理内存介绍
系统的物理内存是有限的，而对内存的需求是变化的, 程序的动态性越强，内存管理就越重要，选择合适的内存管理算法会带来明显的性能提升。


内存管理可以分为三个层次，自底向上分别是：

- 操作系统内核的内存管理
- glibc层使用系统调用维护的内存管理算法
- 应用程序从glibc动态分配内存后，根据应用程序本身的程序特性进行优化， 比如使用引用计数std::shared_ptr，apache的内存池方式等等。
    当然应用程序也可以直接使用系统调用从内核分配内存，自己根据程序特性来维护内存，但是会大大增加开发成本。


一个优秀的通用内存分配器应具有以下特性:
- 额外的空间损耗尽量少
- 分配速度尽可能快
- 尽量避免内存碎片
- 缓存本地化友好
- 通用性，兼容性，可移植性，易调试



##现状
目前大部分服务端程序使用glibc提供的malloc/free系列函数，而glibc使用的ptmalloc2在性能上远远弱后于google的tcmalloc和facebook的jemalloc。 而且后两者只需要使用LD_PRELOAD环境变量启动程序即可，甚至并不需要重新编译。






参考  
https://blog.csdn.net/junlon2006/article/details/77854898  
<https://cyningsun.github.io/07-07-2018/memory-allocator-contrasts.html>  

<http://www.malloc.de/en/>  
<http://goog-perftools.sourceforge.net/doc/tcmalloc.html>  
<https://github.com/jemalloc/jemalloc>  


