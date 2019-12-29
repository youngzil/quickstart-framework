#!/bin/bash

NODE_PORT=41301

#有问题，第一个host执行完后就退出了，是telnet导致的，原因不知道

function install()
{
  NODE_COUNT=10
  for((i=1;i<=$NODE_COUNT;i++));
  do
      echo $host
      echo $NODE_PORT
      telnet $host $NODE_PORT
      NODE_PORT=$(($NODE_PORT+1))
  done
}

cat hostlist|while read line
   do
          host=`echo "$line"|awk '{print $1}'`
          username=`echo "$line"|awk '{print $2}'`
          password=`echo "$line"|awk '{print $3}'`
          echo $host
          install
   done
