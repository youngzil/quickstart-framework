




---------------------------------------------------------------------------------------------------------------------

最常用的DNS解析命令有：
- dig命令
- host命令
- nslookup命令
- whois命令

dig baidu.com

host baidu.com

nslookup baidu.com

whois baidu.com


dig命令：
- dig math.stackexchange.com 查询DNS寻址过程
- dig +short math.stackexchange.com 直接显示DNS寻址结果
- dig @8.8.8.8 math.stackexchange.com 向特定DNS服务器寻址
- dig ns com 查询com顶级域名的ns记录
- dig a com 查询com顶级域名的a记录
- dig -x 192.30.252.153 用于查询PTR记录（用IP查域名）

host命令：
- host github.com 返回域名的各种记录
- host 192.30.252.153 逆向查询IP地址对应的域名

nslookup命令：
- nslookup github.com 查询域名记录

whois命令：
- whois shuyi.me 查询域名的注册情况


## dig命令

### 查询DNS寻址过程

如果我们要查找某个网址的寻址过程，那么我们可以使用下面语法：dig 域名，例如：

dig math.stackexchange.com


### 直接显示DNS寻址结果

当我们用 dig 域名 查询时，会显示出整个寻址过程。但如果我们只想要看到最终的结果，那么我们可以加上 +short 参数。这样控制台直接返回该域名对应的 IP 地址。例如当我们执行下面的命令，其直接返回了 151.101.65.69 这个 IP 地址。

[~]$ dig +short math.stackexchange.com


### 向特定DNS服务器寻址

当我们使用 dig 域名 进行 DNS 寻址时，其默认向我们的本地 DNS 服务器寻址。如果我们想向特定的 DNS 服务器寻址，我们可以用下面的命令：

dig @8.8.8.8 math.stackexchange.com


### 查询某种解析类型的记录

域名解析有许多种类型，包括：NS类型、A 类型、MX 类型等。而我们可以使用 dig 命令去查询其解析类型的值，其语法为：

dig 解析类型 域名

例如我们查看下 shuyi.me 这个域名的 NS 记录，那么其执行命令及其结果为：

[~]$ dig ns shuyi.me

我们继续看下 shuyi.me 这个域名的 A 记录，其执行结果为：

[~]$ dig a shuyi.me

我们继续看看 shuyi.me 的 MX 记录：

[~]$ dig mx shuyi.me


### 反向查询IP对应的域名

我们上面都是通过域名查询 IP，但其实我们也可以通过 IP 去查询其对应的域名。

[~]$ dig -x 192.30.252.153




## host命令

host 命令可以直接返回域名的各种记录，例如：
[~]$ host www.baidu.com

host 命令也可以通过 IP 地址逆向查询对应的域名，例如：
[~]$ host 192.30.252.153



## nslookup命令

我们也可以用 nslookup 命令查询域名的记录，例如：
[~]$ nslookup shuyi.me
Server:		10.xxx.xxx.253
Address:	10.xxx.xxx.253#53

Non-authoritative answer:
Name:	shuyi.me
Address: 192.30.252.153

上面返回的结果表示，其向 10.xxx.xxx.253 DNS 服务器询问 shuyi.me 域名对应的 IP 地址，结果是：192.30.252.153。



## whois命令查询域名的注册情况

当我们想知道域名的注册情况时，我们可以使用 whois 命令，例如：
whois baidu.com

通过 whois 命令，我们可以知道该域名通过哪个服务商注册以及注册地点等信息。





[帮你记录DNS寻址全过程](https://www.cnblogs.com/chanshuyi/p/dns_dig_command.html)  


---------------------------------------------------------------------------------------------------------------------







