WebServiceCaller：WebService可视化界面调用客户端
https://github.com/iTanken/TestProjects/tree/master/WebServiceCaller
https://github.com/duqian42707/wscaller


api接口、RPC、WebService分别解决什么问题

RPC：所谓的远程过程调用 (面向方法)
SOA：所谓的面向服务的架构(面向消息)
REST：所谓的 Representational state transfer (面向资源)
RPC 即远程过程调用, 很简单的概念, 像调用本地服务(方法)一样调用服务器的服务(方法).
通常的实现有 XML-RPC , JSON-RPC , 通信方式基本相同, 所不同的只是传输数据的格式.

REST 的三个要素是 唯一的资源标识, 简单的方法 (此处的方法是个抽象的概念), 一定的表达方式.
重要的特性：无状态
个人也比较喜欢REST,目前基本主流开放平台都是使用这个（淘宝开放平台／人人网／facebook...)

Web Service 已经是过时之物，平常基本使用甚少，建议不要在折腾它了


API只是一个实现方式，Web Service属于架构里的Web服务，RPC属于Web Service的一种使用方式。
Web Service：
从使用方式上，分为RPC，SOAP，REST；
从数据格式上，分为XML，JSON；
其中，RPC和SOAP的使用在减少，Restful架构占到了主导地位；XML格式的使用在减少，json等轻量级格式的使用在增多。




webservice之实现一个基于JWS的webservice项目
https://blog.csdn.net/zolalad/article/details/31735791

