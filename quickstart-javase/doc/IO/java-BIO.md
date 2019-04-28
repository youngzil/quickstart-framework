https://blog.csdn.net/liupeng_qwert/article/details/74923587
https://blog.csdn.net/reliveit/article/details/40110049


流只能顺序访问，不能像数组那样随机存取
1、根据处理数据类型的不同分为：字符流和字节流 
2、根据数据流向不同分为：输入流和输出流，InputStream或者Reader是从数据源读取数据（到内存中），OutputStream或者Writer是将数据（从内存中）写入到目标媒介
3、组合流：一次读取一块，先读到Buffer缓存，再从Buffer缓存获取，如BufferedInputStream
4、字符流与字节流之间的转换：借助InputStreamReader可以将字节流转换为字符流
转换流也是处理流，他将字节流转换成字符流，其中InputStreamReader是输入转换流，OutputStreamWriter是输出转换流。
InputStreamReader 是字节流通向字符流的桥梁：它使用指定的charset 读取字节并将其解码为字符。
OutputStreamWriter 是字符流通向字节流的桥梁：可使用指定的charset 将要写入流中的字符编码成字节。
例如 BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in)); 
String strLine = bufferedReader.readLine();


区分是字符流还是字节流:以InputStream或OutputStream结尾是字节流,以Reader或Writer结尾是字符流
区分是输入还是输出:以Input或Read开头是输入,以Output或Writer结尾时输出


按照流的流向来分，可以分为输入流和输出流，输入流读取文件内容，输出流向文件写入内容；
按照操作的文件类型来分，分为字节流和字符流，字节流的数据单元是一个字节，而字符流的数据单元是2个字节；
按照流的角色来分，分为节点流和处理流，直接操作一个I/O设备例如磁盘或内存等为节点流，对一个已存在的流进行封装或连接则称为处理流。
字节流的基类为InputStream和OutputStream，字符流的基类为Reader和Writer，
其中InputStream和Reader为输入流，OutputStream和Writer为输出流。


File、
RandomAccessFile：可以跳跃读写


Java RandomAccessFile用法：使用RandomAccessFile构建内存映射文件MappedByteBuffer
https://blog.csdn.net/akon_vm/article/details/7429245


InputStream：FileInputStream、BufferedInputStream、ByteArrayInputStream、DataInputStream、ObjectInputStream
OutputStream：FileOutputStream、BufferedOutputStream、ByteArrayOutputStream、DataOutputStream、ObjectOutputStream

Reader：FileReader、BufferedReader、CharArrayReader、StringReader
Writer：FileWriter、BufferedWriter、CharArrayWriter、StringWriter

过滤功能：FileFilter、FilenameFilter
工具权限：FilePermission


File文件的操作：
file.exists()
file.createNewFile()
.renameTo
file.delete();
destDir.getAbsolutePath()
sourceDir.getName()

Directory文件夹的操作：
dir.isDirectory()
fileDir.mkdir()
fileDirs.mkdirs()
dir.list
dir.listFiles()


jdk7新增的File操作类：
WatchService
Paths、Path
Files
FileSystems



1、流的概念和作用 
流是一组有顺序的，有起点和终点的字节集合，是对数据传输的总称或抽象。即数据在两设备间的传输称为流，流的本质是数据传输，根据数据传输特性将流抽象为各种类，方便更直观的进行数据操作。
注意：流只能顺序访问，不能像数组那样随机存取


2、流的分类 
根据处理数据类型的不同分为：字符流和字节流 
根据数据流向不同分为：输入流和输出流


3、字符流和字节流 
字符流的由来： 因为数据编码的不同，而有了对字符进行高效操作的流对象。本质其实就是基于字节流读取时，去查了指定的码表。 
字节流和字符流的区别： 
读写单位不同：字节流以字节（8bit）为单位，字符流以字符为单位，根据码表映射字符，一次可能读多个字节。 
处理对象不同：字节流能处理所有类型的数据（如图片、avi等），而字符流只能处理字符类型的数据。 

结论：只要是处理纯文本数据，就优先考虑使用字符流。 除此之外都使用字节流。

字符流根据对应的编码表，一次可能读多个字节（字符流以字符为单位）


4、输入流和输出流
输入流可以理解为向内存输入，输出流可以理解为从内存输出 
InputStream或者Reader是从数据源读取数据（到内存中），OutputStream或者Writer是将数据（从内存中）写入到目标媒介

典型的数据源或目标媒介有：文件、管道、网络连接、内存缓存、控制台…

注意：Java IO流通常是基于字节或者基于字符的。字节流通常以“stream”命名，比如InputStream和OutputStream。除了DataInputStream 和DataOutputStream 还能够读写int, long, float和double类型的值以外，其他流在一个操作时间内只能读取或者写入一个原始字节


5、组合流
一次读取一个字节是很慢的，借助缓冲可以从磁盘中一次读取一大块数据，然后从读到的数据块中获取字节。为了实现缓冲，可以把InputStream包装到BufferedInputStream中。例如 InputStream input = new BufferedInputStream(new FileInputStream(“xxx”));


6、字符流与字节流之间的转换
例如 BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in)); 
String strLine = bufferedReader.readLine();
借助InputStreamReader可以将字节流转换为字符流


7、常用的几个类说明
// 区分是字符流还是字节流
// 以InputStream或OutputStream结尾是字节流,以Reader或Writer结尾是字符流
// 区分是输入还是输出
// 以Input或Read开头是输入,以Output或Writer结尾时输出

// byte stream
//input stream
String testStr = "heh";
StringBufferInputStream strBufferInputStream = new StringBufferInputStream(testStr); //从String中读取数据到Stream中
strBufferInputStream.read(b); // 从Stream中逐个字节读取

File testFile = new File("filePath");
FileInputStream fileInputStream = new FileInputStream(testFile); //从文件中读取数据到Stream中
byte[] storeByte = new byte[10];
fileInputStream.read(storeByte);    // 从Stream中逐个字节读取  // 文件结尾返回 -1

byte[] testByte = new byte[10];
ByteArrayInputStream byteArrInputStream = new ByteArrayInputStream(testByte); // 从ByteArray中读取数据到Stream中
byteArrInputStream.read(testByte); // 从Stream中逐个字节读取

// output stream
File testFile2 = new File("filePath");  // 写入到哪个文件中去
FileOutputStream fileOutputStream = new FileOutputStream(testFile2);
byte[] srcByte = new byte[10];
fileOutputStream.write(srcByte); // 将byteArray写入到指定文件中

ByteArrayOutputStream byteArrOutputStream = new ByteArrayOutputStream();
byte[] srcByte2 = new byte[10];
byteArrOutputStream.write(srcByte2); // 将byteArray写入到Stream中

// character stream
String encoding = "utf-8";
File testFile3 = new File("filePath");
FileInputStream fileInputStream = new FileInputStream(testFile3);
InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream,encoding);
char[] characters = new char[1024];
inputStreamReader.read(characters);  // 逐个字符读取,从字节流中读取并根据编码转换成字符,存入字符数组中

BufferedReader bufferReader = new BufferedReader(inputStreamReader);
bufferReader.readLine(); // 按行读取字符  // 文件结尾返回null




