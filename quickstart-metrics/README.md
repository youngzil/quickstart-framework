- [Yammer Metrics的使用](#Yammer-Metrics的使用)
- [Dropwizard Metrics介绍](#Dropwizard-Metrics介绍)
- [Metrics + InfluxDB + Grafana 组成的监控系统](#Metrics-InfluxDB-Grafana组成的监控系统)
- [Micrometer介绍](#Micrometer介绍)
- [Alibaba metrics介绍](#Alibaba-metrics介绍)

---------------------------------------------------------------------------------------------------------------------
## Dropwizard Metrics介绍

[Metrics官网](https://metrics.dropwizard.io/)  
[Metrics文档](https://metrics.dropwizard.io/4.1.2/manual/index.html)  
[Metrics操作手册](https://metrics.dropwizard.io/4.1.2/getting-started.html)  
[dropwizard metrics github](https://github.com/dropwizard/metrics)  

[dropwizard metrics 3.X文档](https://metrics.dropwizard.io/3.1.0/)  
[dropwizard metrics 2.X文档](https://metrics.dropwizard.io/2.2.0/)  


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


---------------------------------------------------------------------------------------------------------------------

## Metrics InfluxDB Grafana组成的监控系统
Metrics + InfluxDB + Grafana 组成的监控系统


- Metrics：是一个给Java提供度量工具的包，在JAVA代码中嵌入Metrics代码，可以方便的对业务代码的各个指标进行监控。
- InfluxDB： 是Go语言写的一个时序型数据库。这里我们可以用来存储上报的监控数据。
- Grafana： 是一个非常好看的监控界面。它可以去数据源（influxdb）中load数据进行实时的展示。
- 三者关系见下图

Meter收集监控数据----HTTP----》InfluxDB监控数据存储----load----》Grafana数据展示



Influxdb的Reporte：  
[davidB metrics-influxdb](https://github.com/davidB/metrics-influxdb)  
[iZettle dropwizard-metrics-influxdb](https://github.com/iZettle/dropwizard-metrics-influxdb)  




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
[利用Metrics+influxdb+grafana构建监控平台](https://www.jianshu.com/p/fadcf4d92b0e)  






---------------------------------------------------------------------------------------------------------------------

## Yammer Metrics的使用

基本没见到人使用

Yammer Metrics和dropwizard metrics的关系

猜测Yammer Metrics是之前的老的版本，新的版本就是dropwizard metrics了



[metrics之前的版本](https://github.com/codahale/metrics)  
[dropwizard metrics github](https://github.com/dropwizard/metrics)  



[Yammer Metrics的使用](https://ningg.top/yammehttps://github.com/codahale/metricsr-metrics/)  



---------------------------------------------------------------------------------------------------------------------
## Micrometer介绍

[Micrometer官网](https://micrometer.io/)  
[Micrometer文档](https://micrometer.io/docs)  
[Micrometer Github](https://github.com/micrometer-metrics/micrometer)  
[]()  
[]()  
[]()  
[]()  


Vendor-neutral application metrics facade
供应商中立的应用程序指标外观


从官网介绍，Micrometer是想做一个facade，就好像SLF4J一样

也提供了对Dropwizard Metrics的封装

Micrometer provides a simple facade over the instrumentation clients for the most popular monitoring systems, allowing you to instrument your JVM-based application code without vendor lock-in. Think SLF4J, but for metrics.

Micrometer 为最流行的监控系统的检测客户端提供了一个简单的外观，允许您检测基于 JVM 的应用程序代码而不会被供应商锁定。想想 SLF4J，但对于指标。



An application metrics facade for the most popular monitoring tools. Think SLF4J, but for metrics.

最流行的监控工具的应用程序指标外观。想想 SLF4J，但对于指标。



Contains built-in support for AppOptics, Azure Monitor, Netflix Atlas, CloudWatch, Datadog, Dynatrace, Elastic, Ganglia, Graphite, Humio, Influx/Telegraf, JMX, KairosDB, New Relic, Prometheus, SignalFx, Google Stackdriver, StatsD, and Wavefront.

内置支持的包括：AppOptics, Azure Monitor, Netflix Atlas, CloudWatch, Datadog, Dynatrace, Elastic, Ganglia, Graphite, Humio, Influx/Telegraf, JMX, KairosDB, New Relic, Prometheus, SignalFx, Google Stackdriver, StatsD, and Wavefront.


不过要知道Micrometer与Spring属于同门，都是Pivotal旗下的产品。






## Micrometer使用

Micrometer（译：千分尺）

Micrometer为最流行的监控系统提供了一个简单的仪表客户端外观，允许仪表化JVM应用，而无需关心是哪个供应商提供的指标。它的作用和SLF4J类似，只不过它关注的不是Logging（日志），而是application metrics（应用指标）。简而言之，它就是应用监控界的SLF4J。


不妨看看SLF4J官网上对于SLF4J的说明：Simple Logging Facade for Java (SLF4J)

现在再看Micrometer的说明：Micrometer provides a simple facade over the instrumentation clients for the most popular monitoring systems.



Metrics（译：指标，度量）

Micrometer提供了与供应商无关的接口，包括 timers（计时器）， gauges（量规）， counters（计数器）， distribution summaries（分布式摘要）， long task timers（长任务定时器）。它具有维度数据模型，当与维度监视系统结合使用时，可以高效地访问特定的命名度量，并能够跨维度深入研究。

支持的监控系统：AppOptics ， Azure Monitor ， Netflix Atlas ， CloudWatch ， Datadog ， Dynatrace ， Elastic ， Ganglia ， Graphite ， Humio ， Influx/Telegraf ， JMX ， KairosDB ， New Relic ， Prometheus ， SignalFx ， Google Stackdriver ， StatsD ， Wavefront


为了使用Micrometer，首先要添加你所选择的监视系统的依赖。以Prometheus为例：
```
<dependency>
    <groupId>io.micrometer</groupId>
        <artifactId>micrometer-registry-prometheus</artifactId>
        <version>${micrometer.version}</version>
</dependency>
```



## Micrometer概念


### Registry

Meter是收集关于你的应用的一系列指标的接口。Meter是由MeterRegistry创建的。每个支持的监控系统都必须实现MeterRegistry。

Micrometer中包含一个SimpleMeterRegistry，它在内存中维护每个meter的最新值，并且不将数据导出到任何地方。如果你还没有一个首选的监测系统，你可以先用SimpleMeterRegistry

注意：如果你用Spring的话，SimpleMeterRegistry是自动注入的

Micrometer还提供一个CompositeMeterRegistry用于将多个registries结合在一起使用，允许同时向多个监视系统发布指标。 


### Tag与Meter的命名

Micrometer中，Meter的命名约定使用英文逗号(dot，也就是”.”)分隔单词。但是对于不同的监控系统，对命名的规约可能并不相同，如果命名规约不一致，在做监控系统迁移或者切换的时候，可能会对新的系统造成破坏。

Micrometer中使用英文逗号分隔单词的命名规则，再通过底层的命名转换接口NamingConvention进行转换，最终可以适配不同的监控系统，同时可以消除监控系统不允许的特殊字符的名称和标记等。开发者也可以覆盖NamingConvention实现自定义的命名转换规则：registry.config().namingConvention(myCustomNamingConvention);。

其实NamingConvention已经提供了5种默认的转换规则：dot、snakeCase、camelCase、upperCamelCase和slashes。

在Micrometer中，对一些主流的监控系统或者存储系统的命名规则提供了默认的转换方式，

例如当我们使用下面的命名时候：
MeterRegistry registry = ...
registry.timer("http.server.requests");

对于不同的监控系统或者存储系统，命名会自动转换如下：
Prometheus - http_server_requests_duration_seconds。
Atlas - httpServerRequests。
Graphite - http.server.requests。
InfluxDB - http_server_requests。




### Meters

Micrometer提供一系列原生的Meter，包括Timer , Counter , Gauge , DistributionSummary , LongTaskTimer , FunctionCounter , FunctionTimer , TimeGauge。不同的meter类型导致有不同的时间序列指标值。例如，单个指标值用Gauge表示，计时事件的次数和总时间用Timer表示。

每一项指标都有一个唯一标识的名字和维度。“维度”和“标签”是一个意思，Micrometer中有一个Tag接口，仅仅因为它更简短。一般来说，应该尽可能地使用名称作为轴心。

（PS：指标的名字很好理解，维度怎么理解呢？如果把name想象成横坐标的话，那么dimension就是纵坐标。Tag是一个key/value对，代表指标的一个维度值） 


### Naming meters（指标命名）

Micrometer使用了一种命名约定，用.分隔小写单词字符。不同的监控系统有不同的命名约定。每个Micrometer的实现都要负责将Micrometer这种以.分隔的小写字符命名转换成对应监控系统推荐的命名。你可以提供一个自己的NamingConvention来覆盖默认的命名转换：



### Meter filters

每个registry都可以配置指标过滤器，它有3个方法：

- Deny (or accept) meters from being registered
- Transform meter IDs
- Configure distribution statistics for some meter types.

实现MeterFilter就可以加到registry中

过滤器按顺序应用，所有的过滤器形成一个过滤器链（chain） 


Deny/accept meters 接受或拒绝指标

MeterFilter还提供了许多方便的静态方法用于接受或拒绝指标 





## Micrometer提供一系列原生的Meter

### Counters（计数器）

Counter接口允许以固定的数值递增，该数值必须为正数。 



### Gauges（可任意上升和下降）

gauge是获取当前值的句柄。典型的例子是，获取集合、map、或运行中的线程数等。

MeterRegistry接口包含了用于构建gauges的方法，用于观察数字值、函数、集合和map。



### Timers（计时器）

Timer用于测量短时间延迟和此类事件的频率。所有Timer实现至少将总时间和事件次数报告为单独的时间序列。

例如，可以考虑用一个图表来显示一个典型的web服务器的请求延迟情况。服务器可以快速响应许多请求，因此定时器每秒将更新很多次。




### Long task timers

长任务计时器用于跟踪所有正在运行的长时间运行任务的总持续时间和此类任务的数量。

Timer记录的是次数，Long Task Timer记录的是任务时长和任务数




### Distribution summaries（分布汇总）

distribution summary用于跟踪分布式的事件。它在结构上类似于计时器，但是记录的值不代表时间单位。例如，记录http服务器上的请求的响应大小。



### Histograms and percentiles（直方图和百分比）

Timers 和 distribution summaries 支持收集数据来观察它们的百分比。查看百分比有两种主要方法：

Percentile histograms（百分比直方图）：  Micrometer将值累积到底层直方图，并将一组预先确定的buckets发送到监控系统。监控系统的查询语言负责从这个直方图中计算百分比。目前，只有Prometheus , Atlas , Wavefront支持基于直方图的百分位数近似值，并且通过histogram_quantile , :percentile , hs()依次表示。

Client-side percentiles（客户端百分比）：Micrometer为每个meter ID（一组name和tag）计算百分位数近似值，并将百分位数值发送到监控系统。





参考  
[Micrometer 快速入门](https://www.cnblogs.com/cjsblog/p/11556029.html)  

[基于Micrometer和Prometheus实现度量和监控的方案](https://juejin.cn/post/6847902218910334984#heading-10)

[Spring Boot 使用 Micrometer 集成 Prometheus 监控 Java 应用性能](https://blog.csdn.net/u011250186/article/details/106552199)  
[Micrometer简介及其在SLA指标测量中的使用](https://blog.csdn.net/qiyanli123/article/details/107573921)  
[]()
[]()  
[JVM应用度量框架Micrometer实战](https://zhuanlan.zhihu.com/p/146374529)  
[]()  




## Micrometer使用示例

[Micrometer使用介绍](https://www.tony-bro.com/posts/1386774700/index.html)
[micrometer samples](https://github.com/micrometer-metrics/micrometer/tree/main/samples)
[Micrometer Concepts](https://micrometer.io/docs/concepts)  
[Quick Guide to Micrometer](https://www.baeldung.com/micrometer)





### Cumulate与Step

对于一个完整的监控体系来说，通常至少会有三个部分：应用程序、监控数据存储、监控数据表现

对于一个完整的监控体系来说，通常至少会有三个部分：应用程序、监控数据存储、监控数据表现，而某些框架或者工具会同时包含其中的多个或者多个工具共同组成一个部分，从而产生各种各样的组合。对于速率、平均值、事件分布、延迟等与时间窗口相关的监控指标（Rate aggregation）可以在不同的部分进行处理，例如对于某个接口请求速度的监控，可以在应用层计算好直接发送速度值；也可以直接发送请求数量到存储层然后由表现层来计算速度；又或者是由应用层存储累加值，由其他工具主动来抓取每个时刻的状态。


所以在应用层，有的Meter会有两种类型：累加（Accumulate）与滚动（Step）。以Counter为例，该基接口在core包提供的默认实现中包括：CumulativeCounter和StepCounter，源码并不复杂，直接列出：


此外还有Timer与Distribution Summary（它们两个行为基本一致）。对于它们来说，单纯地使用Cumulative模式基本上没什么意义，因为通常来说事件是频繁的而有价值的是每个时间段范围内的统计，这也导致在初步了解学习过程中可能会对Timer的功能产生疑惑，尤其是和其他没有Step性质的Meter放在一起理解的时候。

这两个概念在官方文档中描述地比较“玄乎”，但这对理解和使用Counter、Timer等是非常重要的，额外注意Counter的使用，因为确实有不少情况是要统计整个生命周期的计数值，这个时候如果你使用的是以Step为实现的Registry，就需要额外处理，避免从StepCounter抓取出局部范围内的累加值。


参考  
[Micrometer使用介绍](https://www.tony-bro.com/posts/1386774700/index.html)  






---------------------------------------------------------------------------------------------------------------------



## Alibaba metrics介绍

[Alibaba metrics Gtihub](https://github.com/alibaba/metrics)  
[Alibaba metrics文档](https://github.com/alibaba/metrics/wiki/demo)  


Dubbo Metrics 的代码是基于 Dropwizard Metrics 衍生而来，版本号是3.1.0，当时决定 fork 到内部进行定制化开发的主要原因有两个。

一是社区的发展非常缓慢，3.1.0之后的第3年才更新了下一个版本，我们担心社区无法及时响应业务需求；

另一个更重要的原因是当时的3.1.0还不支持多维度的 Tag，只能基于 a.b.c 这样传统的指标命名方法，这就意味着 Dropwizard Metrics 只能在单维度进行度量。然后，在实际的业务过程中，很多维度并不能事先确定，而且在大规模分布式系统下，数据统计好以后，需要按照各种业务维度进行聚合，例如按机房、分组进行聚合，当时的 Dropwizard 也无法满足，种种原因使得我们做了一个决定，内部fork一个分支进行发展。




[如何使用](https://github.com/alibaba/metrics/wiki/quick-start)

使用方式很简单，和日志框架的Logger获取方式一致。

Counter hello = MetricManager.getCounter("test", MetricName.build("test.my.counter"));
hello.inc();



支持的度量器包括：
- Counter（计数器）
- Meter（吞吐率度量器）
- Histogram（直方分布度量器）
- Gauge(瞬态值度量器)
- Timer（吞吐率和响应时间分布度量器）
- Compass(吞吐率， 响应时间分布， 成功率和错误码度量器)
- FastCompass(一种快速高效统计吞吐率，平均响应时间，成功率和错误码的度量器)
- ClusterHistogram(集群分位数度量器)






参考  
[Dubbo Metrics 发布新版本 2.0.1 | Dubbo 的度量统计基础设施](https://www.oschina.net/news/105163/dubbo-metrics-2-0-1-released)  




---------------------------------------------------------------------------------------------------------------------


[微服务架构之 Metrics 监控：Prometheus](https://xinlichao.cn/back-end/java/prometheus/)  
[Metrics：让微服务运行更透明](https://cloud.tencent.com/developer/article/1084291)  
[论分布式系统中Metric框架的设计](https://blog.csdn.net/Androidlushangderen/article/details/100752425)  

[Linux内核TCP Metrics框架](https://segmentfault.com/a/1190000020473127)  
[Apache Flink 进阶（八）：详解 Metrics 原理与实战](https://www.infoq.cn/article/ujnzzimkdyif5rxwke7i)  
[]()  
[]()  







