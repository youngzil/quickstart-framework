#!/bin/bash

var=$1
abc(){
   echo "abc"
}
abb(){
   echo "abb"
}
list_name="abc abb"   ###定义list


if [[ "$list_name" =~ "$var" ]]
then
    ${var}
fi


if [ ! $2 ]; then
  echo "haha"
fi