




---------------------------------------------------------------------------------------------------------------------
linux中find批量删除空文件及空文件夹脚本



find /path/to/files -type f -mtime +10 -delete
find /path/to/files* -mtime +10 -exec rm {} \;

在/ l o g s目录中查找更改时间在7日以前的文件并删除它们：
$ find logs/ -type f -mtime +7 -exec rm -f {} \;

一、和xargs搭配用法
#删除./trace目录下的7天前的文件
find ./trace -type f -mtime +7 | xargs rm -f

#删除7天前的目录
find ./trace -type d -mtime +7 |xargs rm -rf

二、和exec搭配
#删除./trace目录下的7天前的文件
find ./trace -type f -mtime +7 -exec rm -f {} \;

三、和反单引号 搭配使用
rm -f `find ./trace -type f -mtime +7`

find /data/program/logs -type f -mtime +7 -delete
find /data/program/logs -type d -empty -mtime +7 -delete


[find命令删除文件](https://blog.csdn.net/hanglinux/article/details/49925975)  
[rm命令弱爆了！](https://segmentfault.com/a/1190000041006696)  




set -x

find /data/program/logs/com.xxx.middleware/app_log/ -name "app*.log" -mtime 1 -exec grep "into es failed" {} \;|grep "Store file info FileInfo" |awk -F "(" '{print $2}'|awk -F ")" '{print $1}' >> /data/program/logs/com.xxx.middleware/app_log/esfailed.log



---------------------------------------------------------------------------------------------------------------------







