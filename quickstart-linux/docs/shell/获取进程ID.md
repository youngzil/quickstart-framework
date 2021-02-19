pid=`ps ax | grep -i 'com.alibaba.rocketmq.broker.BrokerStartup' |grep java |grep msg_broker_master_19 | grep -v grep | awk '{print $1}'`
pid=`ps ax |grep java | grep -v grep | awk '{print $1}'`
if [ -z "$pid" ] ; then
   echo "No mqbroker running."
   exit -1;
fi


ps -ef|grep ${PROCESS_NAME} | grep ${PROCESS_PARM} | grep java | grep -v grep | awk '{print $2}' |while read pid
do
    echo $pid
done


kill -9 `ps -ef|grep ddmp-mgmt|grep server.port=9100|awk '{print $2}'`
kill -9 `ps -ef|grep ddmp-server-1.0.jar|awk '{print $2}'`
kill -9 `ps -ef|grep ddmp-mgmt-1.0.war|awk '{print $2}'`




如杀死Nodejs服务

kill -9 $(ps -ef | grep node | grep -v grep | awk '{print $2}')

解析：

ps （processStatus）：显示所有进程状态；

|：表示将前一个表达式的出参当作下一个表达式的入参

grep node ：过滤node进程；

grep -v grep：过滤掉含有‘grep’字段的条目；

awk'{print $2}'：按行，以空格分段的字符串，显示第二行;

$(expression)：获取表达式返回值，用于给kill命令杀进程




参考  
[Shell获取进程号并杀掉该进程](https://blog.csdn.net/nickDaDa/article/details/86748001)  


