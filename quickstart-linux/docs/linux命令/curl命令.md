




---------------------------------------------------------------------------------------------------------------------

## CURL简介

[CURL官网](https://curl.se/)  
[CURL Github](https://github.com/curl/curl)  
[curl(1) — Linux manual page](https://man7.org/linux/man-pages/man1/curl.1.html)  
[CURL维基百科](https://zh.wikipedia.org/wiki/CURL)  
[Curl Command in Linux with Examples](https://linuxize.com/post/curl-command-examples/)  


cURL是一个开源项目，主要的产品是curl（命令行工具）和libcurl（C语言的API库），两者功能均是：基于网络协议，对指定URL进行网络传输。[2][3]

cURL涉及是任何网络协议传输，不涉及对具体数据的具体处理。（如：html的渲染等）


curl 是常用的命令行工具，用来请求 Web 服务器。它的名字就是客户端（client）的 URL 工具的意思。

它的功能非常强大，命令行参数多达几十种。如果熟练的话，完全可以取代 Postman 这一类的图形界面工具。

我一向以为，curl只是一个编程用的函数库。

最近才发现，这个命令本身，就是一个无比有用的网站开发工具，请看我整理的它的用法。



A command line tool and library for transferring data with URL syntax, supporting DICT, FILE, FTP, FTPS, GOPHER, GOPHERS, HTTP, HTTPS, IMAP, IMAPS, LDAP, LDAPS, MQTT, POP3, POP3S, RTMP, RTMPS, RTSP, SCP, SFTP, SMB, SMBS, SMTP, SMTPS, TELNET and TFTP. libcurl offers a myriad of powerful features


curl supports SSL certificates, HTTP POST, HTTP PUT, FTP uploading, HTTP form based upload, proxies, HTTP/2, HTTP/3, cookies, user+password authentication (Basic, Plain, Digest, CRAM-MD5, SCRAM-SHA, NTLM, Negotiate and Kerberos), file transfer resume, proxy tunneling and more.



---------------------------------------------------------------------------------------------------------------------
## CURL用法


如果要把这个网页保存下来，可以使用 -o 参数：
curl -o [文件名] www.sina.com


有的网址是自动跳转的。使用 -L 参数，curl 就会跳转到新的网址。
curl -L www.sina.com


显示头信息 
-i 参数可以显示 http response 的头信息，连同网页代码一起。
-I 参数则只显示 http response 的头信息。
curl -i www.sina.com


显示通信过程
-v 参数可以显示一次 http 通信的整个过程，包括端口连接和 http request 头信息。
$ curl -v www.sina.com

如果觉得上面的信息还不够，那么下面的命令可以查看更详细的通信过程。
$ curl --trace output.txt www.sina.com

或者

$ curl --trace-ascii output.txt www.sina.com
运行后，打开 output.txt 文件查看。


发送表单信息

发送表单信息有 GET 和 POST 两种方法。GET 方法相对简单，只要把数据附在网址后面就行。
$ curl example.com/form.cgi?data=xxx

POST 方法必须把数据和网址分开，curl 就要用到 --data 或者 -d 参数。
$ curl -X POST --data "data=xxx" example.com/form.cgi

如果你的数据没有经过表单编码，还可以让 curl 为你编码，参数是 --data-urlencode。
$ curl -X POST--data-urlencode "date=April 1" example.com/form.cgi


HTTP动词
curl 默认的 HTTP 动词是 GET，使用 -X 参数可以支持其他动词。
$ curl -X POST www.example.com
$ curl -X DELETE www.example.com


User Agent字段
curl 可以这样模拟：
$ curl --user-agent "[User Agent]" [URL]


cookie
使用 --cookie 参数，可以让 curl 发送 cookie。
$ curl --cookie "name=xxx" www.example.com
至于具体的 cookie 的值，可以从 http response 头信息的 Set-Cookie 字段中得到。

增加头信息--header
有时需要在 http request 之中，自行增加一个头信息。--header 参数就可以起到这个作用。
$ curl --header "Content-Type:application/json" http://example.com


HTTP认证
有些网域需要 HTTP 认证，这时 curl 需要用到 --user 或者 -u 参数。
$ curl --user name:password example.




[curl命令详解](https://blog.csdn.net/mao_xiaoxi/article/details/97764814)  
[curl 的用法指南](https://www.ruanyifeng.com/blog/2019/09/curl-reference.html)  
[curl网站开发指南](https://www.ruanyifeng.com/blog/2011/09/curl.html)  



---------------------------------------------------------------------------------------------------------------------







