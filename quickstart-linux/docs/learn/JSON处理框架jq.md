- [JSON处理框架jq](#JSON处理框架jq)
  - [jq官网文档](#官网文档)
  - [jq命令简介](#命令简介)
  - [安装jq](#安装jq)
    - [macos](#macos)
    - [linux](#linux)
    - [centos](#centos)
    - [ubuntu](#ubuntu)
  - [jq表达式](#表达式)
    - [举个栗子](#举个栗子)
    - [串行操作](#串行操作)
    - [数组操作](#数组操作)
  - [参考](#参考)

# JSON处理框架jq


## 官网文档
## jq官网文档

[jq官网](https://stedolan.github.io/jq/)  
[更多用法可以查看 jq 的手册](https://stedolan.github.io/jq/manual/)  
[jq 中文手册(v1.5)](https://alingse.github.io/jq-manual-cn/manual/v1.5/)  




## 命令简介
## jq命令简介

jq is like sed for JSON data - you can use it to slice and filter and map and transform structured data with the same ease that sed, awk, grep and friends let you play with text.

jq 与 sed 类似，但是对于处理 JSON 更加友好、方便。由于是第三方库，所以需要单独安装或添加二进制文件到 PATH 路径下。不过 jq 本身是可以跨平台并且单文件即可运行，也很方便。

jq 是一款命令行下处理 JSON 数据的工具。其可以接受标准输入，命令管道或者文件中的 JSON 数据，经过一系列的过滤器(filters)和表达式的转后形成我们需要的数据结构并将结果输出到标准输出中。jq 的这种特性使我们可以很容易地在 Shell 脚本中调用它。



## 安装jq

### macos
```
brew install jq
```

### linux
```
wget https://github.com/stedolan/jq/releases/download/jq-1.6/jq-linux64
chmod a+x jq-linux64 && mv jq-linux64 /usr/bin/jq
```


### centos
```
yum install epel-release
yum install jq
```

### ubuntu
```
apt update
apt install -y jq
```




## 表达式
## jq表达式
### 举个栗子
```
echo '{"name":"voidking"}' | jq .
echo '{"name":"voidking"}' | jq .name
echo '{"name":"voidking"}' | jq -r .name
```


### 串行操作

jq表达式支持串行化操作。一个复杂的表达式可以由多个简单的表达式组成，以管道符号 | 分割，串行化执行。管道前面表达式的输出，是管道后面表达式的输入。

逗号 , 表示对同一个输入应用多个表达式。
```
echo '{"name":{"firstname":"Void","lastname":"King"}}' | jq .name.firstname
echo '{"name":{"firstname":"Void","lastname":"King"}}' | jq '.name | .firstname'
echo '{"name":{"firstname":"Void","lastname":"King"}}' | jq '.name | .firstname,.lastname'
echo '[{"firstname":"Void","lastname":"King"},{"firstname":"Hao","lastname":"Jin"}]' | jq '.[] | .firstname,.lastname' | sed -n "N;s/\n/ /p"
```


### 数组操作

jq 提供三种基础表达式来操作数组：
- 迭代器操作.[]，该表达式的输入可以是数组或者JSON对象，输出的是基于数组元素或者JSON对象属性值的迭代器（iterator）。
- 访问特定元素的操作.[index]或.["attributename"]。用来访问数组元素或者JSON对象的属性值，输出是单个值。
- 数组切片操作.[startindex:endindex]'，其行为类似于 python 语言中数组切片操作。

一个表达式产生的结果是迭代器时，迭代器的每一个值会分别作为的输入，传给后面的表达式。

```
echo '[{"name":"voidking"},{"name":"haojin"}]' | jq .
echo '[{"name":"voidking"},{"name":"haojin"}]' | jq '.[]'
echo '[{"name":"voidking"},{"name":"haojin"}]' | jq '.[0:2]'
echo '[{"name":"voidking"},{"name":"haojin"}]' | jq '.[0,1]'
echo '[{"name":"voidking"},{"name":"haojin"}]' | jq '.[].name'
echo '[{"name":"voidking"},{"name":"haojin"}]' | jq '.[] | .name'
echo '[{"name":"voidking"},{"name":"haojin"}]' | jq '.[] | .["name"]'

```




参考  
[jq命令的安装使用](https://www.voidking.com/dev-jq-command/)  
[使用 Shell 脚本来处理 JSON](https://www.tomczhen.com/2017/10/15/parsing-json-with-shell-script/)  
[jq 常用操作](https://mozillazg.com/2018/01/jq-use-examples-cookbook.html)  


