#!/bin/bash

PORT=2181

count=0

for i in $(cat ip_list.txt)
do
    ((count++))
    echo "count=$count"
        # 关键代码，1s自动结束telnet
    (sleep 1;) | telnet $i $PORT >>telnet_result.txt
done

# 根据结果判断出正常可以ping通的ip
#cat telnet_result.txt | grep -B 1 \] | grep [0-9] | awk '' | cut -d '.' -f 1,2,3,4 >telnet_alive.txt
cat telnet_result.txt | grep -B 1 \] | grep Connected | grep [0-9] | awk '{ print $(NF) }' | cut -d '.' -f 1,2,3,4 >telnet_alive.txt

# 差集，得到ping不同的ip
cat ip_list.txt telnet_alive.txt | sort | uniq -u >telnet_die.txt



#执行步骤：
#
#1、在Linux环境中运行，首先建文件夹如cheng，然后vim创建telnet.sh文件将上述代码复制进去，修改XXXX为实际端口号例如linux的远程端口22，保存退出。
#
#2、在cheng文件夹中创建ip_list.txt的文件，并输入要检查的服务器IP地址，每行一个地址。
#
#3、执行telnet.sh文件，等待结果。
#
#4、cheng文件夹目录文件介绍
#
#shell文件夹中会新出现3个新的文件，如下：
#
#telnet_alive.txt#端口通的地址集合
#
#telnet_die.txt#端口不通的地址集合
#
#telnet_result.txt#所有地址检查结果
