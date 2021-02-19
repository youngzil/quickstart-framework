#!/bin/bash

time=$(date "+%Y%m%d%H%M%S")
containerId=$(docker ps -a | grep mysql | grep -v grep | awk '{print $1}')
sudo docker exec -it $containerId /bin/bash -c "mysqldump -u root -h localhost --databases limsdb > /root/mysql/limsdb${time}.sql"


: '
crontab -e
00 03 * * * /root/shell/mysqlbak.sh
crontab -l
'

