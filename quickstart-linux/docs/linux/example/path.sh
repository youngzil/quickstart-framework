#!/bin/bash

path1=$(cd `dirname $0`;pwd)
echo $path1

path2=$(dirname $0)
echo $path2


#curPath=$(readlink -f $(dirname $0))
curPath=$(greadlink -f $(dirname $0))
echo $curPath
#或者
#curPath2=$(dirname $(readlink -f $0))
curPath2=$(dirname $(greadlink -f $0))
echo $curPath2

: '
Mac下没有readlink
用greadlink命令替代代readlink命令。

brew install coreutils

greadlink --help

'

