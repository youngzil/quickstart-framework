#!/bin/bash


#循环遍历文本
for line in `cat line.txt`
do
  echo $line
done

#循环文件夹
for file in /data/program/logs/com.xxx.middleware/app_log/*
do
  if [ -d "$file" ]
  then
    echo "$file is directory"
  elif [ -f "$file" ]
  then
    echo "$file is file"
    `grep $line $file | grep 'start to upload' >> aa.txt`
  fi
done

