#!/bin/bash

set -e

mytest()
{

#  接受的是传递给函数的参数
  echo $1
  echo $2
  
  PROJECT_VERSION=$(mvn org.apache.maven.plugins:maven-help-plugin:3.2.0:evaluate -Dexpression=project.version -q -DforceStdout)
  echo $PROJECT_VERSION
  B="ERROR"
  if [[ $PROJECT_VERSION == *$B* ]]
  then
      echo "包含$B"
  else
      echo "不包含$B"
  fi
}



mytest2()
{

#  接受的是传递给函数的参数
  echo $1
  echo $2

  PROJECT_VERSION=$(mvn org.apache.maven.plugins:maven-help-plugin:3.2.0:evaluate -Dexpression=project.version -q -DforceStdout)
  echo $PROJECT_VERSION
  B="ERROR"
  if [[ $PROJECT_VERSION == *$B* ]]
  then
      # shellcheck disable=SC2152
      return "包含$B";
  else
      # shellcheck disable=SC2152
      return '不包含$B';
  fi
}

get_str()
{
	# shellcheck disable=SC2152
	return "string"
}

echo $1
echo $2

echo `get_str` #写法一
echo $(get_str) #写法二


result=`mytest 'dddd' 'fffffff'`
result2=`mytest2 'dddd' 'fffffff'`

echo 'TTTTTT'
echo `result` #写法一
echo $(result) #写法二

echo 'GGGGGG'
echo `result2` #写法一
echo $(result2) #写法二
