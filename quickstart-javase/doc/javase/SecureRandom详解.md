```
private Random rand = SecureRandom.getInstanceStrong();  // SecureRandom is preferred to Random
rand.nextInt();


SecureRandom.getInstanceStrong(); 是jdk1.8里新增的加强版随机数实现。
如果你的服务器在Linux操作系统上，这里的罪魁祸首是SecureRandom generateSeed（）。
它使用/dev/random生成种子。
但是/dev/random是一个阻塞数字生成器，如果它没有足够的随机数据提供，它就一直等，这迫使JVM等待键盘和鼠标输入以及磁盘活动可以产生所需的随机性或熵。
但在一个服务器缺乏这样的活动，可能会出现问题。



如果是tomcat 环境,有如下解决方式


可以通过配置JRE使用非阻塞的Entropy Source： 
在catalina.sh中加入这么一行：-Djava.security.egd=file:/dev/./urandom 即可。 

加入后再启动Tomcat，整个启动耗时下降到Server startup in 20130 ms。 

这种方案是在修改随机数获取方式，那这里urandom是啥呢？
/dev/random的一个副本是/dev/urandom（“unblocked”，非阻塞的随机数发生器[4]），它会重复使用熵池中的数据以产生伪随机数据。

这表示对/dev/urandom的读取操作不会产生阻塞，但其输出的熵可能小于/dev/random的。

它可以作为生成较低强度密码的伪随机数生成器，不建议用于生成高强度长期密码。- - - wikipedia

在JVM环境中解决 
打开$JAVA_PATH/jre/lib/security/java.security这个文件，找到下面的内容：
securerandom.source=file:/dev/random
替换成
securerandom.source=file:/dev/./urandom



只好修改代码，不采用SecureRandom.getInstanceStrong这个新方法，改成了SecureRandom.getInstance("NativePRNGNonBlocking")。



总结
1.SecureRandom本身并不是伪随机算法的实现，而是使用了其他类提供的算法来获取伪随机数。 

2.如果简单的new一个SecureRandom对象的话，在不同的操作平台会获取到不同的算法，windows默认是SHA1PRNG,Linux的话是NativePRNG。 

3. Linux下的NativePRNG，如果调用generateSeed()方法，这个方法会读取Linux系统的/dev/random文件，这个文件在JAVA_HOME/jre/lib/securiy/java.security里面有默认定义。而/dev/random文件是动态生成的，如果没有数据，就会阻塞。也就造成了第一个现象。 

4.可以使用-Djava.security.egd=file:/dev/./urandom （这个文件名多个u）强制使用/dev/urandom这个文件，避免阻塞现象。中间加个点的解释是因为某个JDK BUG，SO那个帖子有链接。 

5.如果使用SecureRandom.getInstanceStrong()这种方法初始化SecureRandom对象的话,会使用NativePRNGBlocking这个算法，而NativePRNGBlocking算法的特性如下： 

NativePRNGBlocking uses /dev/random for all of the following operations: 
Initial seeding: This initializes an internal SHA1PRNG instance using 20 bytes from /dev/random 
Calls to nextBytes(), nextInt(), etc.: This provides the XOR of the output from the internal SHA1PRNG instance (see above) and data read from /dev/random 
Calls to getSeed(): This provides data read from /dev/random 
可见这个算法完全依赖/dev/random，所以当这个文件随机数不够的时候，自然会导致卡顿了。 

6.如果使用NativePRNGBlocking算法的话，4中的系统参数失效！！！（这个是从http://hongjiang.info/java8-nativeprng-blocking/看到的） 

7.一般使用SecureRandom不需要设置Seed，不需要设置算法，使用默认的，甚至一个静态的即可，如果有需求的话可以在运行一段时间后setSeed一下。

参考
https://mp.weixin.qq.com/s/q2WcDxM-v-gp_tZNe6gcIw
```

