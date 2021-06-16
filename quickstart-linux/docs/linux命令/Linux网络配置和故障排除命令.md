- [1. ifconfig](#ifconfig)
- [2. PING Command](#PING-Command)
- [3. TRACEROUTE Command](#TRACEROUTE-Command)
- [4. NETSTAT Command](#NETSTAT-Command)
- [5. DIG Command](#DIG-Command)
- [6. NSLOOKUP Command](#NSLOOKUP-Command)
- [7. ROUTE Command](#ROUTE-Command)
- [8. HOST Command](#HOST-Command)
- [9. ARP Command](#ARP-Command)
- [10. ETHTOOL Command](#ETHTOOL-Command)
- [11. IWCONFIG Command](#IWCONFIG-Command)
- [12. HOSTNAME Command](#HOSTNAME-Command)
- [13. GUI tool system-config-network](#GUI-tool system-config-network)



---------------------------------------------------------------------------------------------------------------------

## ifconfig

ifconfig ( interface configurator ) 命令用于初始化接口、为接口分配IP 地址以及按需启用或禁用接口。使用此命令，您可以查看分配给接口的IP 地址和硬件/ MAC 地址以及MTU（最大传输单元）大小。


fconfig with interface ( eth0 ) 命令只显示特定的接口详细信息，如IP 地址、MAC 地址等。如果-a选项也被禁用，则将显示所有可用的接口详细信息。

ifconfig eth0


分配 IP 地址和网关
动态地为接口分配IP 地址和网关。如果系统重新启动，该设置将被删除。
# ifconfig eth0 192.168.50.5 netmask 255.255.255.0


启用或禁用特定接口
要启用或禁用特定接口，我们使用示例命令如下。

启用 eth0
# ifup eth0


禁用 eth0
# ifdown eth0


设置 MTU 大小
默认MTU大小为1500。我们可以使用以下命令设置所需的MTU大小。用大小替换XXXX。
# ifconfig eth0 mtu XXXX



将接口设置为混杂模式
网络接口只接收到属于那个特定网卡的数据包。如果您将接口置于混杂模式，它将收到所有数据包。这对于捕获数据包和稍后分析非常有用。为此，您可能需要超级用户访问权限。

# ifconfig eth0 - promisc




---------------------------------------------------------------------------------------------------------------------
## PING Command

PING（Packet Internet Groper）命令是测试两个节点之间连通性的最佳方式。无论是局域网（LAN）还是广域网（WAN）。Ping 使用ICMP（Internet 控制消息协议）与其他设备通信。您可以使用以下命令ping ip 地址的主机名。

ping 4.2.2.2

ping www.tecmint.com


在Linux 中ping 命令一直执行直到您中断。在N个请求（成功或错误响应）后使用-c选项退出Ping 。

ping -c 5 www.tecmint.com


---------------------------------------------------------------------------------------------------------------------
## TRACEROUTE Command

traceroute是一个网络故障排除实用程序，它显示到达目的地所用的跳数，也决定了数据包的传输路径。下面我们正在跟踪到全球DNS 服务器 IP 地址的路由，并且能够到达目的地也显示了该数据包正在传播的路径。

traceroute 4.2.2.2



---------------------------------------------------------------------------------------------------------------------
## NETSTAT Command

Netstat（网络统计）命令显示连接信息、路由表信息等。要显示路由表信息，请使用-r选项。



netstat -r

Mac：netstat -an | grep 8080
Linux：netstat -anp | grep 8080


[20 Netstat Commands for Linux Network Management](https://www.tecmint.com/20-netstat-commands-for-linux-network-management/)
[Linux 中的 20 个 Netstat 命令示例](https://www.tecmint.com/20-netstat-commands-for-linux-network-management/)


---------------------------------------------------------------------------------------------------------------------


## DIG Command

Dig（域信息搜索器）查询DNS相关信息，如A记录、CNAME、MX记录等。该命令主要用于对DNS相关查询进行故障排除。



dig www.tecmint.com;


[查询 DNS 的 10 个 Linux Dig 命令](https://www.tecmint.com/10-linux-dig-domain-information-groper-commands-to-query-dns/)  


---------------------------------------------------------------------------------------------------------------------



## NSLOOKUP Command

nslookup命令也用于查找与DNS相关的查询。以下示例显示了tecmint.com 的A 记录（IP 地址）。



nslookup www.tecmint.com

[8 个 Linux Nslookup 命令示例](https://www.tecmint.com/8-linux-nslookup-commands-to-troubleshoot-dns-domain-name-server/)  


---------------------------------------------------------------------------------------------------------------------
## ROUTE Command

route命令还显示和操作ip路由表。要查看Linux 中的默认路由表，请键入以下命令。


路由添加
# route add -net 10.10.10.0/24 gw 192.168.0.1


路由删除
# route del -net 10.10.10.0/24 gw 192.168.0.1


添加默认网关
# route add default gw 192.168.0.1



---------------------------------------------------------------------------------------------------------------------
## HOST Command

host命令在IPv4或IPv6 中查找名称到IP或IP到名称并查询DNS记录。

host www.google.com


使用-t选项，我们可以找出 DNS 资源记录，如CNAME、NS、MX、SOA等。
# host -t CNAME www.redhat.com



---------------------------------------------------------------------------------------------------------------------
## ARP Command

ARP（地址解析协议）对于查看/添加内核ARP 表的内容很有用。要查看默认表，请使用命令 as。




---------------------------------------------------------------------------------------------------------------------
## ETHTOOL Command

ethtool是mii-tool的替代品。用于查看、设置网络接口卡( NIC ) 的速度和双工。您可以使用ETHTOOL_OPTS变量在/etc/sysconfig/network-scripts/ifcfg-eth0 中永久设置双工。



---------------------------------------------------------------------------------------------------------------------
## IWCONFIG Command

Linux 中的iwconfig命令用于配置无线网络接口。您可以查看和设置基本的Wi-Fi详细信息，例如SSID通道和加密。您可以参考iwconfig 的手册页以了解更多信息。


---------------------------------------------------------------------------------------------------------------------
## HOSTNAME Command

主机名是在网络中标识的。执行hostname命令以查看您的机器的主机名。您可以在/etc/sysconfig/network 中永久设置主机名。设置正确的主机名后需要重新启动框。

hostname


---------------------------------------------------------------------------------------------------------------------
## GUI tool system-config-network

在命令提示符中键入system-config-network来配置网络设置，您将获得漂亮的图形用户界面( GUI )，它也可用于配置IP 地址、网关、DNS等，如下图所示。





---------------------------------------------------------------------------------------------------------------------

参考  
[ Linux Network Configuration and Troubleshooting Commands](https://www.tecmint.com/linux-network-configuration-and-troubleshooting-commands/)  






