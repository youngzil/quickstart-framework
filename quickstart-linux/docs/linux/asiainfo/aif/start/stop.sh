#!/bin/bash
################
#add by yougw at 2017-7-12
################

serviceId='com.si.aifgw.Applicaion'

user=`whoami`

isRun()
{
   pid=`ps -ef | grep $serviceId | grep $user | grep -v grep | awk '{print $2}'`
   if [[ ${pid}X == X ]]; then
         echo "No web service."
         exit 0
   else
     msg="Are you sure you want to stop? (y/n default:y)"
     temp=`check "$msg"`
     if [[ ${temp}X = X || ${temp} = 'Y' ]]; then
        kill -9 $pid
        echo "stop done."
     fi
   fi
}

check()
{
    opt=$(echo $1 | tr '[a-z]' '[A-Z]')
    if [[ $opt = "RESTART" ]]; then
        echo Y
    else
        read -p "$1" answer
        temp=$(echo $answer | tr '[a-z]' '[A-Z]')
        if [[ ${temp} != "" && ${temp} != "Y" && ${temp} != "N" ]]; then
            #statements
            check "$1"
        fi
        echo $temp
    fi
}

isRun

