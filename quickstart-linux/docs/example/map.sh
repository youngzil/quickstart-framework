#!/bin/sh

echo "一、定义Map:declare -A myMap=([\"myMap00\"]=\"00\" [\"myMap01\"]=\"01\")"
declare -A myMap=(["my00"]="00" ["my01"]="01")
myMap["my02"]="02"
myMap["my03"]="03"

echo "二、输出所有的key:"
# shellcheck disable=SC2068
echo ${!myMap[@]}

echo "三、输出所有value:"
echo ${myMap[@]}

echo "四、输出map的长度:"
echo ${#myMap[@]}

echo "五、遍历，根据key找到对应的value:"
for key in ${!myMap[*]};do
 echo "key:"$key
 echo "value:"${myMap[$key]}
done

echo "六、遍历所有的key:"
for key in ${!myMap[@]};do
 echo "key:"$key
 echo "value:"${myMap[$key]}
done

echo "七、遍历所有value:"
for val in ${myMap[@]};do
 echo "value:"$val
done