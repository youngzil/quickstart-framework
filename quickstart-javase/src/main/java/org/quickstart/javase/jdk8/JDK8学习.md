发布的核心特性，即lambda表达式、函数式接口、流API、默认方法和新的Date以及Time API

Arrays.sort(myArray);
// 而是这么做：
Arrays.parallelSort(myArray);


CompletableFuture：CompletableFuture类实现了CompletionStage和Future接口，是一个接口，从命名上看得知是一个完成的阶段，它里面的方法也标明是在某个运行阶段得到了结果之后要做的事情。
ConcurrentSkipListSet并发集合
LongAdder适合的场景是统计求和计数的场景
StampedLock控制锁有三种模式（写，读，乐观读），一个StampedLock状态是由版本和模式两个部分组成
Math.random()方法是线程安全的。
ThreadLocalRandom 并发随机数
SecureRandom安全随机数
RecursiveTask：Fork/Join模式，任务分解和集中
Optional：Optional<T> 类是一个容器类，代表一个值存在或者不存在，原来用null表示一个值不存在，现在Optional可以更好的表达这个概念。并且可以避免空指针异常。
Function：接受一个参数，返回一个参数。接收一个泛型T，返回泛型R，调用这个函数后，可以改变返回的类型
Consumer：接受一个参数，不返回参数。
Predicate：用于测试是否符合条件,传入一个泛型参数T，返回boolean类型
Supplier：无参数，有返回值
Stream：Stream接口，map操作，filter操作，flatMap操作




Stream
https://blog.csdn.net/qq_28410283/article/details/80633292
流的几种创建方式：
1.Arrays.stream，我们可以通过Arrays的静态方法，传入一个泛型数组，创建一个流
2.Stream.of，我们可以通过Stream的静态方法，传入一个泛型数组，或者多个参数，创建一个流，这个静态方法，也是调用了Arrays的stream静态方法
3.Collection.stream,可以用过集合的接口的默认方法，创建一个流；使用这个方法，包括继承Collection的接口，如：Set，List，Map，SortedSet 等等，详细的，可以看Collection接口上的定义注释
4.Stream.iterate，是Stream接口下的一个静态方法，从名字也可以看出，这个静态方法，是以迭代器的形式，创建一个数据流，具体的静态方法定义
5.Stream.generate，也是stream中的一个静态方法，静态方法定义如下：

Stream中的方法，可以分成两种类型，一种返回类型为接口本身的Stream<T>，另外一种是返回其他对象类型的，
返回接口类型的，我们称这些方法为中间操作，
返回其他具体类型的，我们称为终端操作；

Stream接口，map操作，filter操作，flatMap操作
filter：filter方法的作用，是对这个boolean做判断，返回true判断之后的对象
map：这个接口，接收一个泛型T，返回泛型R，map函数的定义，返回的流，表示的泛型是R对象，这个表示，调用这个函数后，可以改变返回的类型
flatMap：跟map一样，接收一个Fucntion的函数式接口，不同的是，Function接收的泛型参数，第二个参数是一个Stream流；方法，返回的也是泛型R，具体的作用是把两个流，变成一个流返回
//去重复
Stream<T> distinct();
//排序
Stream<T> sorted();
//根据属性排序
Stream<T> sorted(Comparator<? super T> comparator);
//对对象的进行操作
Stream<T> peek(Consumer<? super T> action);
//截断--取先maxSize个对象
Stream<T> limit(long maxSize);
//截断--忽略前N个对象
Stream<T> skip(long n);
  // stream的min，max，findFirst，findAny操作，这4个函数，都是返回的Optional对象，
    // stream的count，anyMatch，allMatch，noneMatch操作





CompletableFuture
CompletableFuture类实现了CompletionStage和Future接口。Future是Java 5添加的类，用来描述一个异步计算的结果，但是获取一个结果时方法较少,要么通过轮询isDone，确认完成后，调用get()获取值，要么调用get()设置一个超时时间。但是这个get()方法会阻塞住调用线程，这种阻塞的方式显然和我们的异步编程的初衷相违背。
为了解决这个问题，JDK吸收了guava的设计思想，加入了Future的诸多扩展功能形成了CompletableFuture。



LongAdder
LongAdder适合的场景是统计求和计数的场景，而且LongAdder基本只提供了add方法，而AtomicLong还具有cas方法(要使用cas，在不直接使用unsafe之外只能借助AtomicXXX了)

LongAdder中会维护一个或多个变量，这些变量共同组成一个long型的“和”。当多个线程同时更新（特指“add”）值时，为了减少竞争，可能会动态地增加这组变量的数量。“sum”方法（等效于longValue方法）返回这组变量的“和”值。
当我们的场景是为了统计技术，而不是为了更细粒度的同步控制时，并且是在多线程更新的场景时，LongAdder类比AtomicLong更好用。 在小并发的环境下，论更新的效率，两者都差不多。但是高并发的场景下，LongAdder有着明显更高的吞吐量，但是有着更高的空间复杂度。

LongAdder有两大方法，add和sum。其更适合使用在多线程统计计数的场景下，在这个限定的场景下比AtomicLong要高效一些，下面我们来分析下为啥在这种场景下LongAdder会更高效。

LongAdder减少冲突的方法以及在求和场景下比AtomicLong更高效的原因
首先和AtomicLong一样，都会先采用cas方式更新值
在初次cas方式失败的情况下(通常证明多个线程同时想更新这个值)，尝试将这个值分隔成多个cell（sum的时候求和就好），让这些竞争的线程只管更新自己所属的cell（因为在rehash之前，每个线程中存储的hashcode不会变，所以每次都应该会找到同一个cell），这样就将竞争压力分散了




SecureRandom
https://www.jianshu.com/p/2f6acd169202
Math.random()方法是线程安全的。
ThreadLocalRandom 并发随机数
SecureRandom安全随机数

ThreadLocalRandom 是 JDK 7 之后提供，也是继承至 java.util.Random。
每一个线程有一个独立的随机数生成器，用于并发产生随机数，能够解决多个线程发生的竞争争夺。效率更高！
ThreadLocalRandom 不是直接用 new 实例化，而是第一次使用其静态方法 current() 得到 ThreadLocal<ThreadLocalRandom> 实例，然后调用 java.util.Random 类提供的方法获得各种随机数。

java.Security.SecureRandom
也是继承至 java.util.Random。




StampedLock
https://my.oschina.net/benhaile/blog/264383
StampedLock控制锁有三种模式（写，读，乐观读），一个StampedLock状态是由版本和模式两个部分组成，锁获取方法返回一个数字作为票据stamp，它用相应的锁状态表示并控制访问，数字0表示没有写锁被授权访问。在读锁上分为悲观锁和乐观锁。

所谓的乐观读模式，也就是若读的操作很多，写的操作很少的情况下，你可以乐观地认为，写入与读取同时发生几率很少，因此不悲观地使用完全的读取锁定，程序可以查看读取资料之后，是否遭到写入执行的变更，再采取后续的措施（重新读取变更信息，或者抛出异常） ，这一个小小改进，可大幅度提高程序的吞吐量！！



RecursiveTask：Fork/Join模式
当我们需要执行大量的小任务时，有经验的Java开发人员都会采用线程池来高效执行这些小任务。然而，有一种任务，例如，对超过1000万个元素的数组进行排序，这种任务本身可以并发执行，但如何拆解成小任务需要在任务执行的过程中动态拆分。这样，大任务可以拆成小任务，小任务还可以继续拆成更小的任务，最后把任务的结果汇总合并，得到最终结果，这种模型就是Fork/Join模型。

Java7引入了Fork/Join框架，我们通过RecursiveTask这个类就可以方便地实现Fork/Join模式。
Fork/Join框架提供了执行返回一个结果的任务的能力。这些任务的类型是实现了RecursiveTask类。这个类继承了ForkJoinTask类和实现了执行者框架提供的Future接口。




Function：接受一个参数，返回一个参数。
Consumer：接受一个参数，不返回参数。
Predicate：用于测试是否符合条件。

该接口目前发布在 java.util.function 包中。接口中主要有方法：
R apply(T t);
将Function对象应用到输入的参数上，然后返回计算结果。

Consumer 接口的使用:该接口表示接受单个输入参数并且没有返回值的操作。接口里面重要方法为：
void accept(T t);

Consumer 和 Function 的区别主要就是 Consumer 接口没有返回值， Function 接口有返回值。

Predicate 接口的使用:Predicate 方法 表示 判断 输入的对象是否 符合某个条件。主要方法如下：
boolean test(T t);




参考
https://wizardforcel.gitbooks.io/java8-tutorials/content/
http://www.infoq.com/cn/articles/Java-8-Quiet-Features
https://blog.csdn.net/jiangzhexi/article/details/54236376

https://www.jianshu.com/p/5b800057f2d8
http://www.runoob.com/java/java8-new-features.html
http://www.importnew.com/11908.html
https://blog.csdn.net/pzxwhc/article/details/48314039


https://my.oschina.net/benhaile?tab=newest&catalogId=410404
https://wizardforcel.gitbooks.io/java8-tutorials/content/index.html
https://github.com/eugenp/tutorials
https://www.baeldung.com/java-tutorial
https://github.com/takipi







