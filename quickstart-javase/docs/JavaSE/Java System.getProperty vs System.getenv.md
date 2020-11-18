System 类中有两个方法，分别来读取系统属性（system properties）和环境变量（environment variables）

下面我们来看看这两者的区别：
- 使用System.getProperty()  
    Java 平台使用一个 Properties 对象来提供本地系统相关的信息和配置，我们称之为 系统属性。  
    系统属性包括当前用户、当前 Java 运行时版本 以及 文件路径分隔符诸如此类的信息。
- 使用 System.getenv()  
    环境变量是类似 Properties 的一些 键/值 对。许多操作系统都提供环境变量的方式向应用传递配置信息。  
    设置环境变量的方式，各操作系统之间有所不同。例如，Windows 中，我们使用控制面板中的系统工具（System Utility）应用来设置，而 Unix 系统则使用 shell 脚本。


这两者本质上都是提供 字符串类型 键值 信息的映射，区别在于：
1. 我们可以在运行时变更 系统属性（Properties），但是 环境变量（Environment Variables）仅是操作系统环境变量的一个不可变拷贝。
2. 仅 Java 平台包含这个 系统属性 特性，而 环境变量 则是操作系统层面提供，全局可用的 - 运行在同一个机器上的所有应用都可以访问。
3. 系统属性 在打包应用时就必须存在1，而 环境变量 则任意时刻都可以在操作系统中创建。


Java中系统变量和环境变量的区别:
- 系统变量指的是通过-D这种方式给的值，通过System.getProperty()来获取值，默认获取到的系统变量部分以java.开头，还有一些其他系统变量，通过System.getProperties().list(System.out)即可打印出来。
- 环境变量指的是操作系统中配置的环境变量，以windows系统为例，就是高级系统设置里面的环境变量，其中用户变量会覆盖系统环境变量。通过System.getEnv()可以查看。
- 注意：运行java时，每次都可以指定-D来设置属性Property，但是如果你新增了一个环境变量，一定要退出所有java进程，然后才能通过System.getEnv()获得值，否则获取不到。






[Java System.getProperty vs System.getenv](https://www.baeldung.com/java-system-get-property-vs-system-getenv)  
[Java System.getProperty VS. System.getenv（译）](https://blog.xiayf.cn/2019/06/25/java-prop-env/)  
[Java System.getProperty 和 System.getenv 区别](https://blog.csdn.net/neweastsun/article/details/81590821)  
[java获取和设置系统变量(环境变量)](https://blog.csdn.net/u013514928/article/details/78147421)  
[java 获取系统变量(环境变量和设置变量)](https://blog.csdn.net/oscar999/article/details/9713249)  

[Java系统属性和环境变量](https://blog.csdn.net/pursuer211/article/details/82255413)  
[Java中系统变量和环境变量的区别](https://www.jianshu.com/p/ac99ce832d6b)  





