



---------------------------------------------------------------------------------------------------------------------


grep 更适合单纯的查找或匹配文本
sed 更适合编辑匹配到的文本
awk 更适合格式化文本，对文本进行较复杂格式处理


---------------------------------------------------------------------------------------------------------------------

sed -i "s/待匹配的串/新的串/g" /app/config/application-test.yml



awk是行处理器: 相比较屏幕处理的优点，在处理庞大文件时不会出现内存溢出或是处理缓慢的问题，通常用来格式化文本信息
awk处理过程: 依次对每一行进行处理，然后输出
esbdir=`pwd|awk -F'/' '{print $4}'`
pwd作为输入，-F是以什么为分隔符，然后获取第5个参数，$0 是全行数据，$1 是分割后的第1个数据


---------------------------------------------------------------------------------------------------------------------


意思是提取,分隔的第一列第二列
awk -F ',,' '{print $1$2}'>res.txt


打印分隔的第一列第二列，并用|分隔
awk -F ',,' '{print $1"|"$2}' res.txt>res.txt

cat res.txt | awk -F ',,' '{print $1"|"$2}' >res.txt


cat gatewaytest-service-qa-0f5659cecfdb.txt|grep TotalTime|awk -F ' - ' '{print $2}'>res.txt
cat gatewaytest-service-qa-0f5659cecfdb.txt|grep TotalTime|awk -F ' - ' '{print $2}'|awk -F ',' '{print $1","$2","$3}' >res.txt

cat gatewaytest-service-qa-0f5659cecfdb.txt 读取文件内容
grep TotalTime 筛选有TotalTime的行
awk -F ' - ' '{print $2}' 按照-分隔，提取第二列
awk -F ',' '{print $1","$2","$3}'  按照,分隔，提取123列
>res.txt 重定向到文件res.txt中





[linux awk命令详解](https://www.cnblogs.com/ggjucheng/archive/2013/01/13/2858470.html)  
[linux awk命令统计排名单词出现次数](https://love61v.github.io/2017/07/12/awk%E7%BB%9F%E8%AE%A1%E6%8E%92%E5%90%8D%E5%8D%95%E8%AF%8D%E5%87%BA%E7%8E%B0%E6%AC%A1%E6%95%B0/)  
[awk统计命令(求和、求平均、求最大值、求最小值)](https://blog.csdn.net/csCrazybing/article/details/52594989)  
[awk的sort命令学习一例]()  

---------------------------------------------------------------------------------------------------------------------







