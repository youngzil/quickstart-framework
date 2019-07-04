2、六大组件（Amber、Msg、Aicache、ET、Log4x、Isee）除Log4x、Isee依赖产品提供时间点，其他四组件集成测试中；
1、微服务管控平台3大核心（门户、CSF、API网关）+7大组件（Amber、Msg、log4x、Isee、Aicache、ET、猴子军团）已完成开发及钱塘云集成测试；
统一门户、API网关、CSF服务管理、Amber、Msg、Aicache、ET、Log4x、Isee






1、消息组件msgframe：RocketMQ、ActiveMQ、kafka、DB


2、缓存aicache：Redis、Memecached

3、amber配置中心:

4、微服务治理框架CSF：RPC框架


5、log4x和isee

6、
uspa
AISchedule(ET)


7、

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



---------------------------------------------------------------------------------------------------------------------
消息组件msgframe：

异步解耦，削峰填谷

主要包含SDK和Console两部分：
客户端：SDK方式提供jar，屏蔽不同消息中间件差异，修复完善功能、定制开发
Console：配置（动态生效）、监控告警、运维、租户


代理或者桥接模式



1、屏蔽不同消息中间件差异：发送、消费接口，broker、队列负载均衡，主题队列模型，集群模型等
2、定制开发：消息压缩，消息加解密，消息轨迹跟踪、消息稽核、监控告警、消息去重、broker故障隔离、消息异常（生产、消费）的持久化、消息分析（业务端开发）
	权限、流控、黑白名单等
	熔断：broker积压之后熔断，消费端消费的mq积压之后，熔断退出由其他消费者消费
3、Console：配置、监控告警、运维、租户、权限



1、屏蔽：统一发送消费接口、对象，屏蔽差异
2、配置的动态刷新（console、amber、local）：console和local提供api，amber使用zookeeper通知
3、熔断：broker和consumer，Activemq集群模型的优化，broker故障隔离；消费端消费的mq积压之后，熔断退出由其他消费者消费
4、连接池的使用：对象、连接统一管控，动态调整（线程池），消息对象使用对象池的技术
5、负载均衡：topic和broker，主题的分片，发送负载路由（提升并发TPS）【activemq对broker的负载、broker故障隔离】
6、权限的控制（发送和消费的权限控制、黑白名单等）


1、消费端去重（Redis的setnx命令，数据库）
2、消息的稽核（按天分库分表，先使用yugong同步数据，然后再定时任务稽核（根据参数分中心，分主题等稽核），或者使用ET框架执行任务）
3、消息轨迹跟踪（ES,Jest(searchbox)作为客户端查询）
4、消息序列化提供：(Kryo和FST)
5、消息压缩（jdk的zip压缩，设置压缩级别）
6、消息加解密的发送
7、消息的监控告警
8、消息分析（业务端开发）
9、消息异常（生产、消费）的持久化


同步、异步发送回调、顺序、事务、事务的统一提交
发送客户端的限流（TTL动态批量统计）
消息对象使用对象池的技术



消息类型：
从通讯方式：
同步、异步（回调）、oneway

普通消息
tag消息
顺序消息
事务消息（appframe事务的统一提交）
延时消息（定时消息）
消息优先级（activemq）


配置文件主要包括：
生产消费的hook
主题、订阅、中心、集群（RocketMQ、Kafka、ActiveMQ、RabbitMQ、DB）
异常：主题、中心、全局



消息选型比较：
功能性、TPS、积压、
社区、文档、语言、持久化、事务、负载均衡、扩缩容、部署、维护


遇到问题：
事务消息错误使用ThreadLocal导致事务消息一直发送到同一个broker上
线程池参数设置的不合理等导致oom
连接出错，然后发送失败打印日志，并发打的时候出错数据太多等待被打印，导致一直占用内存，内存释放不了

检查代码的时候发现，程序有一个模块，功能是从数据库定时查询数据然后数据做处理，模块中把查出来的数据基于log4j写到日志中，实际现场环境有时候定时查询得到的数据有几百兆，打印到日志文件中打印不过来。导致数据在内存中不断积压等待被打印，内存得不到释放。



1、mq版本5.13.2，是3台mq配合zookeeper组成的集群
出现异常之前手动停止了mq集群，停止的时候有生产者在生产消息
启动集群后，有脏数据的mq的leveldb就同步到了整个集群，导致一直出现主从切换，只能删除所有leveldb数据文件才能恢复

1、Rocketmq的队列大小扩缩容：缩容时候，导致部分数据无法消费
2、consumer设置上instanceName后，无法集群消费的问题调查：以为是因为设置的广播消费模式，发现不是，原来是如果设置上这个参数，启动多个jvm进程，则currentCID都一样，而计算rebalance时如下代码导致每次将所有的queue分配到一个consumer上：
int index = cidAll.indexOf(currentCID);
3、Rocketmq的发送消息，同步发送，设置同步刷盘，但是刷盘要保存的commitlog被删除，也会返回成功。





---------------------------------------------------------------------------------------------------------------------
杭州消息msgframe升级改造记录

发送端消息接口：
普通发送：public void send(String subject, MsgFMessage message) throws Exception
顺序发送：public void sendOrderMsg(String subject, MsgFMessage message,String key) throws Exception
事务发送：MfProducerTxClient client1 = ClientFactory.createTxClient();


消费接口都是MfConsumerClient.main()
参数支持三种：
1、空参数，
2、主题列表，并且配置文件配置的订阅主题tag为*，
3、主题列表，并且配置文件配置的订阅主题tag不是*，通过参数-p可以指定消费的物理队列名称


测试功能点：
1、普通消息（发送和消费）
2、顺序消息（发送和消费）
3、事务消息（发送和消费）
4、tag消息（发送和消费）
5、消费tag消息：配置文件方式、方法参数方式
6、amber配置托管、修改动态生效
7、消息配置记录ES拦截器
8、消息稽核记录：发送和消费记录消息
9、消息离线发送记录
10、broker宕机：全部宕机，部分宕机，全部宕机恢复，部分宕机恢复（发送和消费）
11.兼容性测试（1.0版本发2.0版本消费，2.0版本发1.0版本消费，2.0版本发2.0版本消费）
12.负载均衡（同一个主题不同broker之间的负载均衡；同一个broker，同一个主题的不同物理分片之间的负载均衡）


路由类
com.ai.aif.msgframe.common.route.impl.CustomDestinationRule
改为
com.ai.aif.msgframe.common.ex.MyDestinationRule


支持warmUpper和prodInjection的配置
<warmUpper>com.ai.aif.msgframe.example.consumer.WarmUpperImplTest</warmUpper>
<prodInjection>com.ai.aif.msgframe.example.producer.ProInjectionImpl</prodInjection>


消息：1.0和2.0的互相发送和订阅
msgframe2.0消费端支持消费msgframe1.0和msgframe2.0发送的消息
msgframe1.0消费端支持消费msgframe1.0和msgframe2.0发送的消息


消费端的消息属性获取，消费死信使用
消息的属性移植
消息新增版本号
text和map类型的消息的适配


从中心配置表中找出所有的中心：
根据中心找出中心下的资源：
查询资源上的所有的主题

从资源上获取所有的队列的信息：

物理队列信息表
物理队列名称、消费组、入队tps、出队tps、积压量、中心名称、中心id，broker名称（服务器上的），消息数量


broker信息表
broker名称、入队tps、出队tps、积压量、中心名称


主题信息表
队列名称、入队tps、出队tps、积压量


异常入离线表---修改
去重功能修改---


1、消息轨迹:发送消息时发送一个ES报文到es中，消费消息时无论成功还是失败都需要入一个报文到ES中，发送和消费时候消息入ES，需要配置生产拦截类和消费拦截类，控制台可以查询消息，可以查看消息的生产、消费次数等
2、消息去重:消息再pull到消息时,先把消息插入到稽核消费表中，如果主键冲突，则不消费，如果成功则继续处理业务，发生主键冲突之后发送消息到ES中
3、稽核消息:发送消息和消费消息都同步存入一条稽核信息到稽核表中，通过yugong数据同步工具同步到平台库中进行稽核，稽核为已丢失的消息可以在控制台进行重发和删除，重发和删除后的信息入到历史表中
4、死信消息: 起多个死信消费进程把broker中的消息消费到数据库中，可以在控制台中进行修改、重发、删除等工作，重发和删除后的消息进入死信历史表
5、兼容测试：2.0版本发送2.0版本消费、1.0版本发送2.0版本消费、2.0版本发送1.0版本消费--目前只能发送text消息和map消息
6、容灾：发送异常、离线模式时消息存入到离线&异常表MSG_BROKER_CRASH，离线发送只支持普通消息，事务消息，支持顺序消息



首页区分消费主题和生产主题
资源：分组，分集群，在集群内唯一
主题可以关联资源、关系生产中心、消费中心、按照不同的中心有读写权限，也就是发送和消费的权限
broker监控：分组查询
主题监控：使用主题编码和主题名称查询
消息轨迹、死信管理

中心的：入队tps、出队tps、积压
中心：包含多个资源的ids，一个资源（broker）总的tps
主题：根据主题表主题编码for（主题编码）{1、不带tag，直接sum，2、带tag，拼接tag，再循环sum}
主题积压：根据主题表主题编码for（主题编码）{1、不带tag，直接sum，2、带tag，拼接tag，再循环sum}
主题趋势计算：根据主题--》查询出资源ids，for(资源ids){1、不带tag，资源id+主题直接sum，2、带tag，资源id+拼接主题tag，再循环sum}


---------------------------------------------------------------------------------------------------------------------
amber配置中心：

Client启动的时候通过配置注解或者直接注入回调类方式
Client请求配置中心服务（内部的注册中心保证配置中心高可用），获取配置，并放入Redis缓存。
在amber配置中心更新配置的时候，使用ZK（CenterCode+AppCode+Key）通知客户端配置有变化，客户端根据ZK的通知去Redis缓存或者amber配置中心服务（数据库）获取配置
更新配置：1、更新到数据，2、刷新Redis缓存，3、Zk通知客户端

消息地址在Client通过获取配置的时候获取，客户端不需要知道，后面消息地址可以随时修改
改进：配置中心做Redis缓存，第一次启动从配置中心获取，配置中心缓存到Redis缓存，配置修改通过消息通知，Cient做持久化和内存两种缓存。
Client启动的时候，首先从配置中心，再到本地获取配置

---------------------------------------------------------------------------------------------------------------------
微服务治理框架CSF：RPC框架

Provider和Consumer都从Zookeeper获取服务路由信息，
1、  使用Zookeeper做服务注册中心
2、实现服务间的调用、负载均衡、降级、熔断、重试、限流、跟踪等机制
负载均衡：轮训、随机、一致性哈希、最小连接数，加权轮训、加权随机等
超时控制等降级：报错，降级函数
熔断：通过Zookeeper通知熔断
失败重试
通过控制并发线程数的Semaphore，控制并发
跟踪使用log4X

微服务：服务熔断、降级、限流
服务端做、客户端做
服务熔断、降级：超时、错误、请求并发数过大
限流手法：线程数+队列，信号量、令牌桶算法Guava的RateLimiter


查看/Users/yangzl/git/oss-example/docs

1、CSF Provider 启动，注册到zookeeper路径/busidomain/csf/租户/微服务编码/clusters下面，cluster下面再分各种集群，如/busidomain/csf/TenantA/XIESR/clusters/defaultCluster/normal/
2、Registry工具导出CSF Provider的服务，在Web端导入进去（注册、上线（下线、注销）、开放，订阅流程），服务上线注册到zookeeper路径/busidomain/csf/租户/微服务编码/services下面
3、CSF Consumer在Web页面上新增应用，订阅已经开放的API服务

1、API接入层：流控和权限校验（token、sign处理和校验）等，例如从Redis中校验应用是否订阅了某个已经开放的API服务（订阅信息缓存在Redis中），使用nginx+lua实现
2、网关分发层：处理路由到集群、负载到节点，处理服务的超时、降级、熔断等，网关监听ZK上csf/租户/中心下面的三个固定路径下面信息（服务、集群、配置、策略等），集成CSF Client



---------------------------------------------------------------------------------------------------------------------


查看/Users/yangzl/git/oss-example/aicache-example/aicache-example-static/doc

缓存aicache：Redis、Memecached

1、SDK使用，作为Redis和Memcached的通用客户端来使用
2、静态缓存刷新：admin控制台，aicache-loader、zookeeper

1、admin上点击刷新，不传或传入cluster参数
2、aicache-loader根据data中托管的app的刷新逻辑，相当于刷新模板刷新缓存，此处的缓存刷新一定执行的
3、通知ZK缓存有变动
4、app启动的时候订阅ZK，获取到通知就重新获取缓存，aicache-client-2.6.4.jar中根据admin中传来的cluster参数跟本应用的系统参数（JDK参数）appframe.server.name比较，符合就更新本地缓存，否则就忽略


控制台功能：
在根目录下有data目录，然后下面分中心、项目的路径：lib和config目录分别存放jar和配置文件，根据上述规则找到目录和根据配置文件cache.xml中配置的缓存id（类路径）作为唯一标示，在页面展示缓存列表，可以进行刷新，按集群刷新、批量刷新等

中心、项目路径在页面是下拉选的方式展示，自动根据服务器上的路径获取

缓存列表、刷新、批量刷新、按集群刷新
查看刷新日志：是否刷新成功，是否报错
版本查看、版本切换
缓存配置查看：查看托管的项目的配置，并且可以修改，更新
定时刷新的开启、关闭
缓存数据查询：根据key查询value
集群的监控：内存使用率、缓存Key数量、客户端连接数、缓存命中率、网络流量统计



---------------------------------------------------------------------------------------------------------------------

log4x和isee


javaagent + 字节码修改工具


---------------------------------------------------------------------------------------------------------------------





---------------------------------------------------------------------------------------------------------------------




---------------------------------------------------------------------------------------------------------------------





---------------------------------------------------------------------------------------------------------------------




---------------------------------------------------------------------------------------------------------------------





---------------------------------------------------------------------------------------------------------------------




---------------------------------------------------------------------------------------------------------------------







---------------------------------------------------------------------------------------------------------------------




---------------------------------------------------------------------------------------------------------------------





---------------------------------------------------------------------------------------------------------------------




---------------------------------------------------------------------------------------------------------------------




