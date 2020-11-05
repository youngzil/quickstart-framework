FastDFS

[FastDFS官网](https://github.com/happyfish100/fastdfs)  
[FastDFS介绍](http://www.oschina.net/p/fastdfs)


FastDFS is an open source high performance distributed file system (DFS). It's major functions include: file storing, file syncing and file accessing, and design for high capacity and load balance. Wechat/Weixin public account (Chinese Language): fastdfs

FastDFS是一种开源高性能分布式文件系统（DFS）。它的主要功能包括：文件存储，文件同步和文件访问以及高容量和负载平衡的设计。微信/微信公众号（中文）：fastdfs



## FastDFS详细介绍

FastDFS是一个开源的分布式文件系统，她对文件进行管理，功能包括：文件存储、文件同步、文件访问（文件上传、文件下载）等，解决了大容量存储和负载均衡的问题。特别适合以文件为载体的在线服务，如相册网站、视频网站等等。

FastDFS服务端有两个角色：跟踪器（tracker）和存储节点（storage）。跟踪器主要做调度工作，在访问上起负载均衡的作用。

存储节点存储文件，完成文件管理的所有功能：存储、同步和提供存取接口，FastDFS同时对文件的meta data进行管理。所谓文件的meta data就是文件的相关属性，以键值对（key value pair）方式表示，如：width=1024，其中的key为width，value为1024。文件meta data是文件属性列表，可以包含多个键值对。


