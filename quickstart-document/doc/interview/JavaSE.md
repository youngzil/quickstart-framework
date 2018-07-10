1、Java的简单类型及其封装器类
2、Object类的方法，和Thread类方法的区别
3、String StringBuffer,StringBuilder原理：底层的数据结构是 字符数组 char[]
4、sleep、wait  notyfi都干啥的，sycnized怎么用的，concurrent包下面的锁用过哪些，怎么实现的
5、io  jvm  多线程
6、图的遍历，深度广度啊
7、内存溢出，内存泄露，，怎么调优，类加载
8、
9、
10、
11、
12、
13、
14、
15、
16、
17、
18、
19、
20、
21、
22、
23、
24、
25、





本身都是通过字符数组来存储，对象内部定义字符数组
String：new是放在堆区，+或者substring都是通过改变字符数组生成新的字符数组来实现
一个是非同步的StringBuilder，一个是同步的StringBuffer（synchronized在方法上），都是字符数组，
append时先扩容，把字符数组拷贝到一个新的大的字符数组，再进行拼接，还是拼接拷贝到一个新的字符数组，











