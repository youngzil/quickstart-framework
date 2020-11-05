Elastic-Job


[Elastic-Job介绍](http://www.oschina.net/p/elastic-job)  


http://www.oschina.net/p/echarts-java
http://www.oschina.net/p/disconf
http://www.oschina.net/p/jfinal_weixin
http://www.oschina.net/p/jboot


Elastic-Job 是一个分布式调度解决方案，由两个相互独立的子项目 Elastic-Job-Lite 和 Elastic-Job-Cloud 组成。  
- 1、Elastic-Job-Lite 定位为轻量级无中心化解决方案，使用 jar 包的形式提供分布式任务的协调服务。  
- 2、Elastic-Job-Cloud 使用 Mesos + Docker(TBD) 的解决方案，额外提供资源治理、应用分发以及进程隔离等服务。  

Elastic-Job-Lite 和 Elastic-Job-Cloud 提供同一套 API 开发作业，开发者仅需一次开发，即可根据需要以 Lite 或 Cloud 的方式部署。



