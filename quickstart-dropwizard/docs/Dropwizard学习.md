应用请求8080
管理请求8081
Your Dropwizard application is now listening on ports 8080 for application requests and 8081 for administration requests.



greeter:
  saying: ${GREETER_SAYING:-Guten Tag Dropwizard}
  host: ${GREETER_BACKEND_HOST:-localhost}
  port: ${GREETER_BACKEND_PORT:-8080}
  #export GREETER_BACKEND_HOST="128.0.0.1" && echo $GREETER_BACKEND_HOST
  #export GREETER_BACKEND_PORT=9090 && echo $GREETER_BACKEND_PORT  
  #-Ddw.greeter.host=$GREETER_BACKEND_HOST -Ddw.greeter.port=$GREETER_BACKEND_PORT
可以看到对saying的配置首先回去查看环境变量GREETER_SAYING，如果该环境变量不存在，那么就会使用默认的Guten Tag aus，
默认Dropwizard不会从环境变量中获取配置，如果需要让Dropwizard使用环境变量，需要做一些额外改动。打开HelloWorldApplication，编辑initialize()方法。

1、export GREETER_SAYING="Hello Dropwizard2 @ " && echo $GREETER_SAYING
2、java -jar target/myapp.jar server myapp.yml
有的说如下生效，目前测试不使用VM选项的-D参数也可以生效
2、通过JVM选项来覆盖它，使用-D参数-Ddw.XXX=$HELLOAPP_SAYING
如下启动：java -Ddw.server.connector.bindHost=$OPENSHIFT_DIY_IP -Ddw.server.connector.port=$OPENSHIFT_DIY_PORT -jar target/myapp.jar server myapp.yml



当你启动一个Dropwizard应用，一个Jetty服务就会启动，同时它会创建两个Handler：一个在8080，为你的应用提供服务，另一个在8081，这个提供管理功能。Dropwizard之所以这么做，是因为不想将管理功能通过8080进行暴露，这样你可以把端口隐藏在防火墙后面。诸如Metrics和健康检查，这些也是暴露在管理端口上的，区分的很大原因是考虑安全问题。


Dropwizard与Spring Boot类似，也是构建微服务可选的工具，但是它显得比Spring Boot更加规范一些。它使用的组件一般不会做可选替换，而好处是可以不需要那么多的修饰，比如写基于REST的web服务。比方说，Dropwizard选择使用Jetty作为Servlet容器，REST库使用Jersey，序列化和反序列化使用了Jackson，而想将其中的Jetty替换成Undertow就没有那么容易。


Dropwizard技术栈
Dropwizard在优秀的三方库协助下，提供了不错的抽象层，使之更有效率，更简单的编写生产用途的微服务。
Servlet容器使用Jetty
REST/JAX-RS实现使用Jersey
JSON序列化使用Jackson
集成Hibernate Validator
Guava
Metrics
SLF4J + Logback
数据访问层上使用JDBI


Dropwizard暴露了如下抽象，如果你能掌握这些简单的抽象，你就能很快的使用Dropwizard进行开发了。

Application
包含了public void main()方法

Environment
放置servlet、resources、filters、health checks、task的地方

Configuration
用于改变环境或者系统配置的地方

Commands
当我们启动微服务后，使用它来与微服务交互

Resources
REST/JAX-RS资源

Tasks
对于应用的管理，比如改变日志级别或者暂停数据库连接等



当你启动一个Dropwizard应用，一个Jetty服务就会启动，同时它会创建两个Handler：一个在8080，为你的应用提供服务，另一个在8081，这个提供管理功能。Dropwizard之所以这么做，是因为不想将管理功能通过8080进行暴露，这样你可以把端口隐藏在防火墙后面。诸如Metrics和健康检查，这些也是暴露在管理端口上的，区分的很大原因是考虑安全问题。


暴露应用Metrics和信息
    Dropwizard中做的最好的是将Metrics作为了一等公民，Dropwizard从一开始就考虑了Metrics，而非像其他框架一样事后考虑，所以当一个Dropwizard应用启动的同时，8081管理端口就暴露了Metrics信息。我们只需要在对应的REST资源上增加一些注解就可以做到。

增加注解@Timed，它将跟踪该服务的调用耗时与次数等信息，
@Metered服务调用频率
@ExceptionMetered异常抛出频率
不能都添加上，只能选择一种



学习参考
http://hao.jobbole.com/dropwizard/
https://yq.aliyun.com/articles/231279


示例
https://www.jianshu.com/p/99fd92796799
https://www.dropwizard.io/1.1.0/docs/getting-started.html#gs-maven-setup
https://blog.csdn.net/sf_cyl/article/details/49965411?utm_source=blogxgwz0


