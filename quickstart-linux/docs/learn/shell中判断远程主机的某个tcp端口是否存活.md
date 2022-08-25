Linux shell验证IP 端口是否通并获取验证结果(telnet nc )，linux获取telnet ip port的结果

验证原理就是使用telnet和nc来进行验证，测试下来感觉nc比较好用。
nc -v -w 5 -z 192.168.1.114 2182 &> /dev/null
echo $?
输出结果 1表示不通，0表示通。

nc -v -z -w 5 172.16.48.180 2181; echo $?
nc -v -z -w 5 172.16.48.180 2182; echo $?



[shell中判断远程主机的某个tcp端口是否存活](https://blog.51cto.com/xoyabc/1836353)


参考 [test_ip_port_access](../../docs/example/test_ip_port_access.sh)
