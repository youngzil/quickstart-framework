
本身都是通过字符数组来存储，对象内部定义字符数组
String：new是放在堆区，+或者substring都是通过改变字符数组生成新的字符数组来实现
一个是非同步的StringBuilder，一个是同步的StringBuffer（synchronized在方法上），都是字符数组，
append时先扩容，把字符数组拷贝到一个新的大的字符数组，再进行拼接，还是拼接拷贝到一个新的字符数组，



boolean：只有true和false两个取值。

byte：8位，最大存储数据量是255，存放的数据范围是-128~127之间。
char：16位，存储Unicode码，用单引号赋值。

short：16位，最大数据存储量是65536，数据范围是-32768~32767之间。
int：32位，最大数据存储容量是2的32次方减1，数据范围是负的2的31次方到正的2的31次方减1。
long：64位，最大数据存储容量是2的64次方减1，数据范围为负的2的63次方到正的2的63次方减1。

float：32位，数据范围在3.4e-45~1.4e38，直接赋值时必须在数字后加上f或F。
double：64位，数据范围在4.9e-324~1.8e308，赋值时可以加d或D也可以不加。



简单类型	boolean		byte		char			short	Int		long		float	double	void
二进制位数	1		8		16			16		32		64		32		64		无
封装器类	Boolean		Byte		Character	Short	Integer	Long		Float	Double	Void




String底层的数据结构是 字符数组 char[]
StringBuilder ----线程非安全的
StringBuffer----线程安全的，synchronized在方法上


string+=”hello”的操作，实际上是哟如下三个操作：
StringBuilder str = new StringBuilder(string);  
str.append(“hello”);  
str.toString(); 


StringBuilder和StringBuffer的append方法，每次都会先扩容：ensureCapacityInternal(count + len);
再进行str.getChars(0, len, value, count);----》System.arraycopy(value, srcBegin, dst, dstBegin, srcEnd - srcBegin);



String StringBuffer,StringBuilder 原理
https://blog.csdn.net/wangming520liwei/article/details/79809253


