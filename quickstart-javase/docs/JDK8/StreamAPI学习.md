1、使用Stream的基本步骤
2、Stream简介
3、Stream操作
  1、数据源
  2、中间操作
  3、终端操作
4、

---------------------------------------------------------------------------------------------------------------------
在此我们总结一下使用Stream的基本步骤：

1、创建Stream；（数据源）
2、转换Stream，每次转换原有Stream对象不改变，返回一个新的Stream对象（**可以有多次转换**）；
3、对Stream进行聚合（Reduce）操作，获取想要的结果；




一、最常用的创建Stream有两种途径：
1、通过Stream接口的静态工厂方法（注意：Java8里接口可以带静态方法）；
  11. of方法：有两个overload方法，一个接受变长参数，一个接口单一值
  12. generator方法：生成一个无限长度的Stream，其元素的生成是通过给定的Supplier（这个接口可以看成一个对象的工厂，每次调用返回一个给定类型的对象），一般这种无限长度的Stream都会配合Stream的limit()方法来用。
  3. iterate方法：也是生成无限长度的Stream，和generator不同的是，其元素的生成是重复对给定的种子值(seed)调用用户指定函数来生成的。其中包含的元素可以认为是：seed，f(seed),f(f(seed))无限循环
  4、数组创建流
    根据参数的数组类型创建对应的流：
    Arrays.stream(T[ ])
    Arrays.stream(int[ ])
    Arrays.stream(double[ ])
    Arrays.stream(long[ ])
   5、文件生成流， 每个元素是给定文件的其中一行
     Stream<String> stream = Files.lines(Paths.get("data.txt"));
    
    
    
2、通过Collection接口的默认方法（默认方法：Default method，也是Java8中的一个新特性，就是接口中的一个带有实现的方法，后续文章会有介绍）–stream()，把一个Collection对象转换成Stream
  21. 通过Collection子类获取Stream



二、转换Stream
转换Stream其实就是把一个Stream通过某些行为转换成一个新的Stream

性能问题
有些细心的同学可能会有这样的疑问：在对于一个Stream进行多次转换操作，每次都对Stream的每个元素进行转换，而且是执行多次，这样时间复杂度就是一个for循环里把所有操作都做掉的N（转换的次数）倍啊。

其实不是这样的，转换操作都是lazy的，多个转换操作只会在汇聚操作（见下节）的时候融合起来，一次循环完成。
我们可以这样简单的理解，Stream里有个操作函数的集合，每次转换操作就是把转换函数放入这个集合中，在汇聚操作的时候循环Stream对应的集合，然后对每个元素执行所有的函数。



常用方法:
1、distinct: 对于Stream中包含的元素进行去重操作（去重逻辑依赖元素的equals方法），新生成的Stream中没有重复的元素；
2、filter: 对于Stream中包含的元素使用给定的过滤函数进行过滤操作，新生成的Stream只包含符合条件的元素；
3、map: 对于Stream中包含的元素使用给定的转换函数进行转换操作，新生成的Stream只包含转换生成的元素。
mapToInt，mapToLong和mapToDouble。比如mapToInt就是把原始Stream转换成一个新的Stream，这个新生成的Stream中的元素都是int类型。之所以会有这样三个变种方法，可以免除自动装箱/拆箱的额外消耗；
4、flatMap：和map类似，不同的是其每个元素转换得到的是Stream对象，会把子Stream中的元素压缩到父集合中；
  

5、peek: 生成一个包含原Stream的所有元素的新Stream，同时会提供一个消费函数（Consumer实例），新Stream每个元素被消费的时候都会执行给定的消费函数；


6、limit: 对一个Stream进行截断操作，获取其前N个元素，如果原Stream中包含的元素个数小于N，那就获取其所有的元素；
7、skip: 返回一个丢弃原Stream的前N个元素后剩下元素组成的新Stream，如果原Stream中包含的元素个数小于N，那么返回空Stream；
  


三、汇聚（Reduce）Stream
在官方文档中是reduce，也叫fold。

汇聚操作（也称为折叠）接受一个元素序列为输入，反复使用某个合并操作，把序列中的元素合并成一个汇总的结果。
比如查找一个数字列表的总和或者最大值，或者把这些数字累积成一个List对象。
Stream接口有一些通用的汇聚操作，比如reduce()和collect()；
也有一些特定用途的汇聚操作，比如sum(),max()和count()。
注意：sum方法不是所有的Stream对象都有的，只有IntStream、LongStream和DoubleStream是实例才有。


下面会分两部分来介绍汇聚操作：
1、可变汇聚：把输入的元素们累积到一个可变的容器中，比如Collection或者StringBuilder；
2、其他汇聚：除去可变汇聚剩下的，一般都不是通过反复修改某个可变对象，而是通过把前一次的汇聚结果当成下一次的入参，反复如此。比如reduce，count，allMatch；


1、可变汇聚对应的只有一个方法：collect，正如其名字显示的，它可以把Stream中的要有元素收集到一个结果容器中（比如Collection）：三个参数
collect方法另外一个override的版本，其依赖[Collector]
Java8还给我们提供了Collector的工具类–其中已经定义了一些静态工厂方法，
比如：Collectors.toCollection()收集到Collection中, 
Collectors.toList()收集到List中和
Collectors.toSet()收集到Set中。这样的静态方法还有很多


2、其他汇聚

– reduce方法：reduce方法非常的通用，后面介绍的count，sum等都可以使用其实现
– count方法：获取Stream中元素的个数。比较简单，这里就直接给出例子，不做解释了。
-sum方法，对Stream中元素的求和

数值流方法：sum()、average()、max()、min()


– allMatch：是不是Stream中的所有元素都满足给定的匹配条件
– anyMatch：Stream中是否存在任何一个元素满足匹配条件
– findFirst: 返回Stream中的第一个元素，如果Stream为空，返回空Optional
– noneMatch：是不是Stream中的所有元素都不满足给定的匹配条件
– max和min：使用给定的比较器（Operator），返回Stream中的最大|最小值






示例
https://blog.csdn.net/youzhouliu/article/details/51820088
https://www.jianshu.com/p/e429c517e9cb
https://ifeve.com/stream/
https://www.w3cschool.cn/java/codetag-stream-stream_group.html
https://www.jianshu.com/p/e4275cca364c
https://blog.jooq.org/2014/06/13/java-8-friday-10-subtle-mistakes-when-using-the-streams-api/



---------------------------------------------------------------------------------------------------------------------
Stream简介


Stream是java 8中新增加的特性。

Stream 不是集合元素，它不是数据结构并不保存数据，它是有关算法和计算的，它更像一个高级版本的Iterator。原始版本的Iterator，用户只能显式地一个一个遍历元素并对其执行某些操作；高级版本的Stream，用户只要给出需要对其包含的元素执行什么操作，比如 “过滤掉长度大于 10 的字符串”、“获取每个字符串的首字母”等，Stream会隐式地在内部进行遍历，做出相应的数据转换。

Stream就如同一个迭代器（Iterator），单向，不可往复，数据只能遍历一次，遍历过一次后即用尽了，就好比流水从面前流过，一去不复返。

而和迭代器又不同的是，Stream可以并行化操作，迭代器只能命令式地、串行化操作。顾名思义，当使用串行方式去遍历时，每个item读完后再读下一个item。而使用并行去遍历时，数据会被分成多个段，其中每一个都在不同的线程中处理，然后将结果一起输出。Stream的并行操作依赖于Java 7中引入的Fork/Join框架来拆分任务和加速处理过程。

Java的并行API演变历程基本如下：
1.0-1.4 中的 java.lang.Thread
5.0 中的 java.util.concurrent
6.0 中的 Phasers 等
7.0 中的 Fork/Join 框架
8.0 中的 Lambda

Stream 的另外一大特点是，数据源本身可以是无限的。


parallelStream简介
parallelStream其实就是一个并行执行的流。它通过默认的ForkJoinPool，可能提高你的多线程任务的速度。
parallelStream具有平行处理能力，处理的过程会分而治之，也就是将一个大任务切分成多个小任务，这表示每个任务都是一个操作：


Stream中有两个个方法collect和collectingAndThen用于对流中的数据进行处理，可以对流中的数据进行聚合操作，如：

将流中的数据转成集合类型: toList、toSet、toMap、toCollection
将流中的数据(字符串)使用分隔符拼接在一起：joining
对流中的数据求最大值maxBy、最小值minBy、求和summingInt、求平均值averagingDouble
对流中的数据进行映射处理 mapping
对流中的数据分组：groupingBy、partitioningBy
对流中的数据累计计算：reducing
Collectors.collectingAndThen() 收集之后继续做一些处理。
Collectors.mapping 映射：先对集合中的元素进行映射，然后再对映射的结果使用Collectors操作


参考
https://blog.csdn.net/Alice_qixin/article/details/87169586
https://blog.yangx.site/2018/03/28/java-8-stream/

---------------------------------------------------------------------------------------------------------------------
Stream操作
1、数据源
2、中间操作：filter、distinct、skip、limit、map、flatMap、sorted、
3、终端操作：anyMatch、noneMatch、allMatch、findAny、findFirst、forEach、collect、reduce、count


一、Stream操作
Stream操作分为中间操作和终端操作。每个Stream操作由数据源、零个或多个中间操作和一个终端操作组成。

（一）数据源
上文中提到的集合、数组和无限序列生成器（函数式接口）等都可作为数据源。

（二）中间操作
所有中间操作都是懒执行，即只有在终端操作执行时才会执行，执行中间操作实际上并不执行任何操作，而是创建一个新的流，当遍历该流时，它包含与给定谓词匹配的原始流的元素。因此在执行终端操作之前，流的遍历不会开始。
这是非常重要的特性，对于无限流尤其重要——因为它允许我们创建只有在调用终端操作时才实际调用的流。
常用的中间操作有filter()、map()、flatMap()等。

（三）终端操作
终端操作可以遍历流生成最终结果，执行终端操作即可认为消费管道，上文提到Stream的特点只可消费一次，故该管道已不能再被使用。
几乎在所有情况下，终端操作都是立即执行的，这也是无限流的重要基础——我们是否可以限制无限流，例如limit()。
常用的终端操作有forEach()、toArray()、collect()等。



Java 8 Stream：
Java 8 Stream（一、Stream简介及创建方式）
Java 8 Stream（二、Stream操作和无限流）
Java 8 Stream（三、Stream中间操作：filter()、distinct()、skip()、limit()、map()、flatMap()、sorted()、peek()）
Java 8 Stream（四、Stream终端操作：forEach()、Match、find()、max、min()、reduce()、collect()、toArray()、count()）
Java 8 Stream（五、Optional类和Stream调试）




参考
https://blog.csdn.net/qq_38718258/article/details/104696658




---------------------------------------------------------------------------------------------------------------------

