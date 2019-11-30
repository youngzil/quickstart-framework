set命令显示当前shell的变量，包括当前用户的变量;
env命令显示当前用户的变量;
export命令显示当前导出成用户变量的shell变量。

每个shell有自己特有的变量（set）显示的变量，这个和用户变量是不同的，当前用户变量和你用什么shell无关，不管你用什么shell都在，比如HOME,SHELL等这些变量，
但shell自己的变量不同shell是不同的，比如BASH_ARGC， BASH等，这些变量只有set才会显示，是bash特有的，
export不加参数的时候，显示哪些变量被导出成了用户变量，因为一个shell自己的变量可以通过export “导出”变成一个用户变量。

这三个命令都可以用来显示shell变量
env：显示所有的环境变量
set：显示所有本地定义的shell变量
export：把一个变量变作环境变量。export不加参数的时候，显示哪些变量被导出成了用户变量，一个shell自己的变量可以通过export “导出”变成一个用户变量（env和export显示基本一样，env多了一个_=/usr/bin/env）




参考
https://blog.csdn.net/zdwzzu2006/article/details/7678181
http://blog.chinaunix.net/uid-26495963-id-3197215.html
https://www.linuxidc.com/Linux/2011-08/40478.htm


