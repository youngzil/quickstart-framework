localhost:ws yangzl$ wsimport -d /Users/yangzl/ws -keep -verbose http://localhost:8888/ns?wsdl

-d  指定生产的目录
-keep 指定是否生成.java的源文件
-verbose 显示生成的详细过程
最后是网络中的wsdl的文件地址

生成的文件放在quickstart-webservice-client项目中org.quickstart.webservice.jws.server
使用quickstart-webservice-client项目中org.quickstart.webservice.jws.client.ClientTest测试


参考文章
https://blog.csdn.net/zolalad/article/details/31735791

