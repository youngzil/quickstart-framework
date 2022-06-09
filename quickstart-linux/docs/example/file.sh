#!/bin/bash

#set -x
set -v

for file in /Users/lengfeng/kafka/kafka_2.13-2.8.0/*
do
  if [ -d "$file" ]
  then
    echo "$file is directory"
  elif [ -f "$file" ]
  then
    echo "$file is file"
  fi
done
