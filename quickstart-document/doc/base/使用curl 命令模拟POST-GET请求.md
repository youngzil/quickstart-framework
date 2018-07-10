curl命令是一个利用URL规则在命令行下工作的文件传输工具。它支持文件的上传和下载。curl支持包括HTTP、HTTPS、ftp等众多协议，还支持POST、cookies、认证、从指定偏移处下载部分文件、用户代理字符串、限速、文件大小、进度条等特征。

在进行web后台程序开发测试过程中，常常会需要发送url进行测试，使用curl可以方便地模拟出符合需求的url命令

假设目标url 为：127.0.0.1:8080/login

使用curl发送GET请求：curl protocol://address:port/url?args
curl http://127.0.0.1:8080/login?admin&passwd=12345678  

使用curl发送POST请求：curl -d "args" protocol://address:port/url
curl -d "user=admin&passwd=12345678" http://127.0.0.1:8080/login  

这种方法是参数直接在header里面的，如需将输出指定到文件可以通过重定向进行操作.
curl -H "Content-Type:application/json" -X POST -d 'json data' URL
curl -H "Content-Type:application/json" -X POST -d '{"user": "admin", "passwd":"12345678"}' http://127.0.0.1:8000/login  


