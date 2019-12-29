#!/bin/bash  
# author:yangzl


#STD应该就是standard的意思，+in、+out、+err
#STDIN、STDOUT、STDERR

echo " learn shell"

read redirectIn
printf "重定向输入：$redirectIn \n"

#test:
#1、./io.sh > test
#2、./io.sh < test
#3、./io.sh < test > test2


cat << EOF
欢迎来到
菜鸟教程
www.runoob.com
EOF

