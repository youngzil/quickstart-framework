




---------------------------------------------------------------------------------------------------------------------

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




---------------------------------------------------------------------------------------------------------------------







