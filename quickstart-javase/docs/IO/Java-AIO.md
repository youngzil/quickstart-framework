- [JDK中AIO的使用](#JDK中AIO的使用)


-------------------------------------------------------------------------
## JDK中AIO的使用


java7开始提供Aio的实现，BIO、NIO、AIO  
https://blog.csdn.net/anxpp/article/details/51512200  
https://www.cnblogs.com/diegodu/p/6823855.html  
https://www.cnblogs.com/doit8791/p/4951591.html  
    
  
    
  
服务端：  
 AsynchronousServerSocketChannel assc = AsynchronousServerSocketChannel.open().bind(new InetSocketAddress(port));  
        assc.accept();//参数是CompletionHandler，CompletionHandler<AsynchronousSocketChannel, attachment类型>  
        在completed中应该：  
        1、继续调用上面的accept，  
        2、在新连接的AsynchronousSocketChannel中读数据：channel.read(readBuffer, readBuffer, CompletionHandler)//CompletionHandler<Integer, ByteBuffer>，读完处理逻辑，要channel.write写回客户端  
        3、channel.write(writeBuffer, writeBuffer, CompletionHandler)//CompletionHandler<Integer, ByteBuffer>,在completed中判断writeBuffer是否写完，未写完要循环写，写完就切换回读  
    
  
    
  
客户端：  
AsynchronousSocketChannel  asc =  AsynchronousSocketChannel.open();  
    asc.connect(new InetSocketAddress(host, port), attach, CompletionHandler);//CompletionHandler<Void,? super A> handler  
    在completed中应该：  
    1、向Server发送数据，clientChannel.write(writeBuffer, writeBuffer, new WriteHandler(clientChannel, latch));在completed中判断是否发送完，未发送完继续发送，发送完，切换到读Server返回的数据  
    2、clientChannel.read(readBuffer, readBuffer, new ClientReadHandler(clientChannel, latch));在completed中读取数据，逻辑处理  
    
   
