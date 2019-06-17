tcpdump
https://www.tcpdump.org/


Wireshark监控
https://www.wireshark.org/#download
https://github.com/wireshark/wireshark



tcpdump：
保存到target.cap文件，可以使用Wireshark打开文件
tcpdump -i en4 port 8101 -U -w ./target.cap
tcpdump tcp port 8101 -n -X -s 0


wireshark：
http  and  tcp.port == 8101



tcpdump使用
https://www.cnblogs.com/ggjucheng/archive/2012/01/14/2322659.html


wireshark 实用过滤表达式
https://blog.csdn.net/aflyeaglenku/article/details/50884296
https://blog.csdn.net/panda62/article/details/80279732




