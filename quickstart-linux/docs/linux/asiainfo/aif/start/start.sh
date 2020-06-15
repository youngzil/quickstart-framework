#!/bin/bash
################
#add by yougw at 2017-7-12
################
time=`date '+%Y%m%d'`
serviceId=tastpost_server_000
user=`whoami`
export BASE_DIR=$(dirname $0)/..

isRun()
{
   pid=`ps -ef | grep $serviceId | grep $user | grep -v grep | awk '{print $2}'`
   if [[ ${pid}X != X ]]; then
         echo "service is ruing."
         echo "exit."
         exit 0
   fi
}
isRun

export JAVA_OPTS="-server -Xmx2048m -Xms1024m -Xmn512m -XX:PermSize=1024m -XX:MaxPermSize=2048m"
export log_path=$HOME/logs/tastpost
export CLASSPATH="${BASE_DIR}/config:${BASE_DIR}/lib"
export JAVA_HOME=/app/jdk

$JAVA_HOME/bin/java $JAVA_OPTS \
        -Dai.service.name=$serviceId \
        com.ai.aifgw.aifgwtastpost.AifgwTastpostApplication  > "${log_path}/tastpost.out" 2>&1 < /dev/null &


tail -f ${log_path}/tastpost.out

