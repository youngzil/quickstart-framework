#!/bin/bash

LOCALIP=`ifconfig | grep inet | head -1 | awk '{print $2}' | sed 's/addr\://'`
IPLIST='172.16.48.179 172.16.48.180 172.16.48.181'
PORTLIST='2181 2182'

check_nc(){
for CHECK_IP in $IPLIST
do
    for CHECK_PORT in $PORTLIST
        do
            nc -nz -w 1 $CHECK_IP $CHECK_PORT
            if [ $? -eq 0 ];then
                echo "$LOCALIP result $CHECK_IP $CHECK_PORT Connected"
            else
                echo "$LOCALIP result $CHECK_IP $CHECK_PORT Noconnected"
            fi
        done
done
}

check_nc_7(){
for CHECK_IP in $IPLIST
do
    for CHECK_PORT in $PORTLIST
        do
            nc -n -w 1 $CHECK_IP $CHECK_PORT < /dev/null
            if [ $? -eq 0 ];then
                echo "$LOCALIP result $CHECK_IP $CHECK_PORT Connected"
            else
                echo "$LOCALIP result $CHECK_IP $CHECK_PORT Noconnected"
            fi
        done
done
}

check_telnet(){
for CHECK_IP in $IPLIST
do
    for CHECK_PORT in $PORTLIST
        do
            echo  -e  "\n"| telnet $CHECK_IP $CHECK_PORT | grep Connected
            if [ $? -eq 0 ];then
                echo "$LOCALIP result $CHECK_IP $CHECK_PORT Connected"
            else
                echo "$LOCALIP result $CHECK_IP $CHECK_PORT Noconnected"
            fi
        done
done
}


echo "--------------------------------------"
echo "execution time : `date`"
echo "--------------------------------------"
echo "Home ip : $LOCALIP"
echo "--------------------------------------"
which nc > /dev/null
if [ $? -eq 0 ];then
    echo "------------------nc------------------"
    cat /etc/redhat-release |grep 'release 7' > /dev/null
    if [ $? -eq 0 ];then
        echo "---------------check_nc_7-----------------------"
        # 测试主机是CentOS release 6.8 (Final)，没有测试check_nc_7
        check_nc_7
    else
        echo "-------------------check_nc-------------------"
        check_nc
        check_telnet
    fi
else
    which telnet > /dev/null
    if [ $? -eq 0 ];then
        echo "-----------------telnet----------------"
        # telnet不可用
        check_telnet
    fi
fi
