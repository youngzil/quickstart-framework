- [Java中的二进制](#Java中的二进制)
- [简单运算](#简单运算)
- [复合运算](#复合运算)
- [进制转换](#进制转换)
- [1、Java位运算符](#1、Java位运算符)
- [2、Java算术运算符](#2、Java算术运算符)
- [3、Java关系运算符](#3、Java关系运算符)
- [4、Java逻辑运算符](#4、Java逻辑运算符)


---------------------------------------------------------------------------------------------------------------------
## Java中的二进制

在Java7版本以前，Java是不支持直接书写除十进制以外的其它进制字面量。但这在Java7以及以后版本就允许了：
+ 二进制：前置0b/0B
+ 八进制：前置0
+ 十进制：默认的，无需前置
+ 十六进制：前置0x/0X



如何证明Long是64位的？  
有个最简单的方法：拿到Long类型的最大值，用2进制表示转换成字符串看看长度就行了  

说明：在计算机中，负数以其正值的补码的形式表达。因此，用同样的方法你可以自行证明Integer类型是32位的（占4个字节）。




Java语言支持的位运算符还是非常多的，列出如下：
- &：按位与
- |：按位或
- ^：按位异或
- ～：按位取反
- <<：左位移运算符
- \>>：右位移运算符
- \>>>：无符号右移运算符

除～以 外，其余均为二元运算符，操作的数据只能是整型（长短均可）或者char字符型。针对这些运算类型，下面分别给出示例，一目了然。

既然是运算，依旧可以分为简单运算和复合运算两大类进行归类和讲解。  
小贴士：为了便于理解，字面量例子我就都使用二进制表示了，使用十进制（任何进制）不影响运算结果



## 简单运算  
简单运算，顾名思义，一次只用一个运算符。

正数高位全是0，负数高位全是1
所以右移，正数高位补0，负数高位补1
左移低位都是补0
无符号右移，高位统统补0


<<左移用得非常多，理解起来并不费劲。x左移N位，效果同十进制里直接乘以2的N次方就行了，但是需要注意值溢出的情况，使用时稍加注意。

\>>：按位右移
操作规则：把一个数的全部位数都向右移动若干位。

右移用得也比较多，也比较理解：操作其实就是把二进制数右边的N位直接砍掉，然后正数右移高位补0，负数右移高位补1。


\>>>：无符号右移
注意：没有无符号左移，并没有<<<这个符号的

它和>>有符号右移的区别是：无论是正数还是负数，高位通通补0。所以说对于正数而言，没有区别；那么看看对于负数的表现：



## 复合运算

广义上的复合运算指的是多个运算嵌套起来，通常这些运算都是同种类型的。这里指的复合运算指的就是和=号一起来使用，类似于+= -=。

混合运算：指同一个算式里包含了bai多种运算符，如加减乘除乘方开du方等。




位运算除了高效的特点，还有一个特点在应用场景下不容忽视：计算的可逆性。通过这个特点我们可以用来达到隐蔽数据的效果，并且还保证了效率。

位运算使用场景示例:
1. 判断两个数字符号是否相同
2. 判断一个数的奇偶性
3. 交换两个数的值（不借助第三方变量）
4. 位运算用在数据库字段上（重要）
5. 流水号生成器（订单号生成器）



参考  
https://developer.aliyun.com/article/771042  
https://xie.infoq.cn/article/6cd52956d6a50f8f92e68e7b6  


---------------------------------------------------------------------------------------------------------------------  
## 进制转换


十进制（Decimal）：由0，1，…，9组成，整数默认为十进制。
二进制（Binary）：由0，1组成，以0b开头。二进制变量的声明以0b为前缀（b大小写都行）
八进制（Octal）：由0，1，…，7组成，以0开头。八进制变量的声明以0为前缀
十六进制（简写为hex或下标16）：由0，1，…，9，a，b，c，d，e，f组成（大小写均可），以0x开头。十六进制变量的声明以0x为前缀


2进制、8进制和16进制之间转换一般通过10进制来做中间转换


1、N进制之间直接转换
将目标数据i 转换成radix进制的字符：Integer.toString(int i, int radix) 
将目标字符串转换成radix进制的整型数据：Integer.parseInt(String str,int radix) 


2、十进制转成二进制、八进制、十六进制
Java中十进制转其它进制： 
十进制转二进制：Integer.toBinaryString(i); 
十进制转八进制：Integer.toOctalString(i); 
十进制转十六进制：Integer.toHexString(i);
 
 
3、二进制、八进制、十六进制--->十进制
直接将2,8,16进制直接转换为10进制
Integer.valueOf("0101",2).toString() //二进制转十进制 
Integer.valueOf("876",8).toString() //八进制转成十进制 
Integer.valueOf("FFFF",16).toString() //十六进制转成十进制


进制码
在计算机内，有符号数有3种表示法：原码、反码和补码。所有数据的运算都是采用补码进行的。        

原码：就是二进制定点表示法，即最高位为符号位，“0”表示正，“1”表示负，其余位表示数值的大小。
反码：正数的反码与其原码相同；负数的反码是对其原码逐位取反，但符号位除外。
补码：正数的补码与其原码相同；负数的补码是在其反码的末位加1。



可以将运算符分为四大类：算数运算符、位运算符、关系运算符、逻辑运算符。


## 1、Java位运算符
Java 定义的位运算（bitwise operators ）直接对整数类型的位进行操作，这些整数类型包括long，int，short，char，and byte ，用于操作二进制。包括有“&，|，^，~，<<，>>，>>>”

&：按位与，“只有两个都是1，结果才是1。”
|：安位或，“只要有一个1，结果就是1。”
^：按位异或，“相同的为0，不同的为1。”
~：按位取反，“1就是0,0就是1。”
<<：按位左移，“表示将二进制向左位移，右边填0。”
\>>：按位右移，“表示将二进制向右位移，左边填符号位。”
\>>>：无符号右移动，“表示将二进制向右位移，左边填0。”

总结
(1)移位运算符适用类型有byte、short、char、int、long 
(2)对低于int型的操作数将先自动转换为int型再移位。
(3)对于int型整数移位a>>b，系统先将b对32取模，得到的结果才是真正移位的位数。例如：a>>33和a>>1结果是一样的，a>>32的结果还是a原来的数字。
(4)对于long型整数移位时a>>b ，则是先将移位位数b对64取模。移位不会改变变量本身的值。如a>>1；在一行语句中单独存在，毫无意义。
(5)x>>1的结果和x/2的结果是一样的（因此以后要 求解n除以2，可以用n>>1），x<<2和x*4的结果也是一样的。总之，一个数左移n位，就是等于这个数乘以2的n次方，一个数右移n位，就是等于这个数除以2的n次方。


## 2、Java算术运算符
算术运算符他就是数学中的 + - * / 除了这些之外Java中还有一种算术运算符：% 取余(取模) 

双目运算符（加）+，（减）-，（乘）*，（除）/，（求余）%
1. 这些运算符的运算对象可以是byte、short、int、long、float、double、char类型，其中char类型在运算时被自动转为int型。
2. 在Java中，整数被0除或对0取余属于非法运算，将抛出AtrthemticExcerption。
3. 求余运算（%）的两个运算对象不但可以是整数，也可以是浮点类型；不但可以是正整数，也可以是负整数，其计算结果的符号与求余运算福左侧的运算对象符号一致。
4. 如果参与除法运算的两个运算对象都属于整型，则运算为整除运算，若希望得到小数部分的商值则需要对其中一个运算对象的类型作强制转换。
5. 运算符“+”的运算对象可以使String，它的操作含义是将两个字符串连接。如果其中一个对象为其他类型，则会自动将这个运算对象转换成字符串，然后再进行字符串的连接。

单目运算符+（正），-（负），++（自增），--（自减）
自增自减这个东西，老是容易混淆，其实我们在编程时大可不必用这些容易混淆的东西。不过还是来区分一下。简单的区分方法就是，看++、--在前面还是在后面，在前面则先自增、自减，在后面则先参与运算，再自增自减。

赋值运算符：+=、-=、*=、/=、%=


## 3、Java关系运算符
java里的关系运算符有这么几种：大于（>）、小于（<）、等于（==）、不等于（!=）、大于等于（>=）、小于等于（<=），他们运算的结果是个boolean值，关系式成立为true，不成立为false。

例子
12.3 > 4.5;  //结果是true
'a' <= 'k'; //结果是true ,char字符型比较的是ASCII码，a~z的ASCII码值是97~122，A~Z的ASCII码值是65~90 
'A' < 'G; //结果是true 
'a' == 'm'; //结果是false 再来看看布尔类型的比较吧，boolean 型数据只能比较= =或!=，不能比较大小。 


## 4、Java逻辑运算符

Java逻辑运算符包含下面6中符号：
&& 与 ；&& 与  前后两个操作数必须都是true才返回true,否则返回false
& 不短路与 ； & 不短路与 表达式都会执行到
|| 或； || 或 只要两个操作数中有一个是true，就返回true，否则返回false
|不短路或 ；| 不短路 或 表达式都会执行到
!非；! 非 ，如果操作数为true，返回false，否则，返回true
^异或；^ 异或 ，当两个操作数不相同时返回true，返回false
==等于：
!=不等于：
?:三元运算符：


赋值运算符（=）是最常见的了，它将右边的值赋给左边，它的运算优先级是最低的。除了我们最熟悉的=以外，还有复合赋值+=、-=、*=、/=、%=、&=、|=、^=、<<=、>>=、>>>=。这些赋值语句优先级一样，都是最低的。


特殊运算符
除了上面的三元运算符比较特殊以外，还有类型转换运算符（），instanceof运算符。




https://my.oschina.net/zhanglikun/blog/1921746
https://blog.csdn.net/xwu_09/article/details/78285785
https://blog.csdn.net/zmazon/article/details/8262185
https://my.oschina.net/xianggao/blog/412967
https://www.jianshu.com/p/b677858bc085

https://www.zhihu.com/question/38206659
https://blog.yangx.site/2016/07/06/bit-operation-skills/


---------------------------------------------------------------------------------------------------------------------  

