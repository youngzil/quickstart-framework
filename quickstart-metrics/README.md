[Metrics官网](https://metrics.dropwizard.io/)  
[Metrics文档](https://metrics.dropwizard.io/4.1.2/manual/index.html)  
[Metrics操作手册](https://metrics.dropwizard.io/4.1.2/getting-started.html)  

📈 Capturing JVM- and application-level metrics. So you know what's going on.

捕获JVM和应用程序级别的指标。所以你知道发生了什么事。


Metrics is a Java library which gives you unparalleled insight into what your code does in production.

Metrics是一个Java库，可让您无比洞察代码在生产中的功能。


Metrics provides a powerful toolkit of ways to measure the behavior of critical components in your production environment.

度量标准提供了一种功能强大的工具包，用于衡量生产环境中关键组件的行为。

With modules for common libraries like Jetty, Logback, Log4j, Apache HttpClient, Ehcache, JDBI, Jersey and reporting backends like Graphite, Metrics provides you with full-stack visibility.

借助适用于常见库（如Jetty，Logback，Log4j，Apache HttpClient，Ehcache，JDBI，Jersey）的模块以及报告后端（如Graphite），Metrics可为您提供全栈可见性。



它的基本类型有如下几种：


类型      | 解释  
-------- | ---
Meters（度量器） | 度量某个时间段的平均处理次数（request  per second）
Histogram（直方图） | 统计数据的分布情况，最大值、最小值、平均值、中位数，百分比（75%、90%、95%、98%、99%和99.9%）。  
Timers（计时器） | 统计某一块代码段的执行时间以及其分布情况，基于Histograms和Meters来实现的。  
Counter（计数器） | 维护一个计数器。  
Gauge（计量器） | 统计瞬时状态的数据信息。  




Metrics的五种类型

- Meter度量一系列事件发生的速率(rate)，例如TPS。Meters会统计最近1分钟，5分钟，15分钟，还有全部时间的速率。
  
- Histogram统计数据的分布情况。比如最小值，最大值，中间值，还有中位数，75百分位，90百分位，95百分位，98百分位，99百分位，和 99.9百分位的值(percentiles)。
  
- Timer其实是 Histogram 和 Meter 的结合， histogram 某部分代码/调用的耗时， meter统计TPS。
  
- Counter 就是计数器，Counter 只是用 Gauge 封装了 AtomicLong ，我们可以使用如下的方法获得队列大小
  
- Gauges 最简单的度量指标，只有一个简单的返回值，比如返回某个对象的瞬时状态的数据,某个特定瞬时值，例如MQ中消息数量

- Health Checks 集中式检测系统的监控状态，例如数据库连接是否正常。

- Reporting 度量值可以通过 HTTP，SLF4J，JMX，CSV ，Ganglia，Graphite



参考  
[Java Metrics系统性能监控工具](https://www.jianshu.com/p/e5bba03fd64f)  
[Intro to Dropwizard Metrics](https://www.baeldung.com/dropwizard-metrics)  
[性能分析之Java Metrics度量包](https://cloud.tencent.com/developer/article/1465669?from=10680)  
[Java Metrics工具介绍](https://cloud.tencent.com/developer/article/1519497)  
[Metrics —— JVM上的实时监控类库](https://www.jianshu.com/p/e4f70ddbc287)  





metric + influxdb + grafana 组成的监控系统


- Metrics：是一个给Java提供度量工具的包，在JAVA代码中嵌入Metrics代码，可以方便的对业务代码的各个指标进行监控。
- InfluxDB： 是Go语言写的一个时序型数据库。这里我们可以用来存储上报的监控数据。
- Grafana： 是一个非常好看的监控界面。它可以去数据源（influxdb）中load数据进行实时的展示。
- 三者关系见下图

Meter收集监控数据----HTTP----》InfluxDB监控数据存储----load----》Grafana数据展示




容器
- Metric的容器是指：MetricRegistry
- 作用：用来注册具体的Reporter，Reporter定时去记录或者上报日志。提供所有的Metric工具
- Reporter（报表）：Metrics通过报表，将采集的数据展现到不同的位置,这里比如我们注册一个ConsoleReporter到MetricRegistry中，那么console中就会打印出对应的信息。除此之外Metrics还支持JMX、HTTP、Slf4j等等


基本工具
- Meters计算器：Meters工具会帮助我们统计系统中某一个事件的速率。比如每秒请求数（TPS），每秒查询数（QPS）等等。这个指标能反应系统当前的处理能力，帮助我们判断资源是否已经不足。Meters本身是一个自增计数器
- Histogram（直方图数据）：直方图是一种非常常见的统计图表，Metrics通过这个Histogram这个度量类型提供了一些方便实时绘制直方图的数据
- Timer（计时器）：Timer是一个Meter和Histogram的组合。这个度量单位可以比较方便地统计请求的速率和处理时间。对于接口中调用的延迟等信息的统计就比较方便了。如果发现一个方法的RPS（请求速率）很低，而且平均的处理时间很长，那么这个方法八成出问题了
- Counter（计数器）：Counter的本质就是一个AtomicLong实例，可以增加或者减少值，可以用它来统计队列中Job的总数
- Gauges（度量）：除了Metrics提供的几个度量类型，我们可以通过Gauges完成自定义的度量类型。比方说很简单的，我们想看我们缓存里面的数据大小，就可以自己定义一个Gauges


参考  
[基于Metrics的实时监控系统](https://zhuanlan.zhihu.com/p/163246828)  


