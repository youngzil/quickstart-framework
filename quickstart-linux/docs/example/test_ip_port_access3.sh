#!/bin/bash

nport=`echo ""|telnet 172.16.48.179 2181 2>/dev/null|grep "\^]"|wc -l`
if [ "$nport" -eq "0" ];then
  echo "不通"
else
  echo "open"
fi

nc -zvw3 172.16.48.179 2181

nc -zvw3 172.16.48.179 2182


nmap 172.16.48.179 -p 2181


