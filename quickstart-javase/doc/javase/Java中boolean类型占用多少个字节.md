布尔类型：布尔数据类型只有两个可能的值：真和假。使用此数据类型为跟踪真/假条件的简单标记。这种数据类型就表示这一点信息，但是它的“大小”并不是精确定义

stackoverflow就有关于boolean占几个字节的讨论。 what-is-the-size-of-a-boolean-variable-in-java[2] 其中有一个高赞回答：

Java虚拟机规范一书提到 :

•在Java虚拟机中没有任何供 boolean值专用的字节码指令,Java语言表达式所操作的 boolean值,在编译之后都使用Java虚拟机中的int数据类型来代替。
•Java虚拟机直接支持 boolean类型的数组,虚拟机的 navarra指令参见第6章的newarray小节可以创建这种数组。boolean类型数组的访问与修改共用byte类型数组的baload和 bastore指令。

•因为在虚拟机规范中说了，boolean值在编译之后都使用Java虚拟机中的int数据类型来代替，而int是4个字节，那么boolean值就是4个字节。
•boolean类型数组的访问与修改共用byte类型数组的baload和 bastore指令，因为两者共用，只有两者字节一样才能通用呀，所以byte数组中一个byte是1个字节，那么boolean数组中boolean是1个字节。


•总结：boolean在数组情况下为1个字节，单个boolean为4个字节。


Java规范中，没有明确指出boolean的大小。在《Java虚拟机规范》给出了单个boolean占4个字节，和boolean数组1个字节的定义，具体 还要看虚拟机实现是否按照规范来，所以1个字节、4个字节都是有可能的[3]



References
[1] 官方文档的描述: http://docs.oracle.com/javase/tutorial/java/nutsandbolts/datatypes.html
[2] what-is-the-size-of-a-boolean-variable-in-java: https://stackoverflow.com/questions/383551/what-is-the-size-of-a-boolean-variable-in-java
[3] 所以1个字节、4个字节都是有可能的: https://blog.csdn.net/makingadream/article/details/53100237


