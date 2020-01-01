

问除了加锁之外有没有其他方法来保证线程安全。楼下很多回答copyonwrite机制。这个问题回答有很多，但是copyonwrite的回答有点误导人。

copyonwrite并不能保证线程安全，主要是为了读写分离，也就是读写锁的功能，并发写的时候，写的时候还是要加锁，还是要处理线程并发安全的问题

写不是线程安全的，不加锁会丢失数据，也可能到之超过capacity




copyonwrite机制
和单词描述的一样，他的实现就是写时复制， 在往集合中添加数据的时候，先拷贝存储的数组，然后添加元素到拷贝好的数组中，然后用现在的数组去替换成员变量的数组（就是get等读取操作读取的数组）。
这个机制和读写锁是一样的，但是比读写锁有改进的地方，那就是读取的时候可以写入的 ，这样省去了读写之间的竞争，看了这个过程，你也发现了问题，同时写入的时候怎么办呢，当然果断还是加锁。




使用场景：读多写少
copyonwrite的机制虽然是线程安全的，但是在add操作的时候不停的拷贝是一件很费时的操作，所以使用到这个集合的时候尽量不要出现频繁的添加操作，而且在迭代的时候数据也是不及时的，数据量少还好说，数据太多的时候，实时性可能就差距很大了。在多读取，少添加的时候，他的效果还是不错的（数据量大无所谓，只要你不添加，他都是好用的）。




java中提供了两个利用这个机制实现的线程安全集合。copyonwritearraylist，copyonwritearrayset。
看名字就大概猜到他们之间的关系，copyonwritearrayset的底层实现是copyonwritearraylist。
CopyOnWriteArraySet的add方法 就是调用 CopyOnWriteArrayList的addIfAbsent方法


HashSet的底层实现是HashMap，只不过key就是set的key，value就是一个Object


CopyOnWriteArrayList代码：底层使用ReentrantLock控制并发写

final transient ReentrantLock lock = new ReentrantLock();
public boolean add(E e) {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            Object[] elements = getArray();
            int len = elements.length;
            Object[] newElements = Arrays.copyOf(elements, len + 1);
            newElements[len] = e;
            setArray(newElements);
            return true;
        } finally {
            lock.unlock();
        }
    }


阿里限流框架sentinel中类似代码：底层使用volatile + Double-Check Locking
private volatile Set<Node> childList = new HashSet<>();
public void addChild(Node node) {
    if (node == null) {
        RecordLog.warn("Trying to add null child to node <{0}>, ignored", id.getName());
        return;
    }
    if (!childList.contains(node)) {//1、判断原hashset不包含node
        synchronized (this) {
            if (!childList.contains(node)) {//2再次判断原hashset不包含node
                Set<Node> newSet = new HashSet<>(childList.size() + 1);//3、新建一个hashset
                newSet.addAll(childList);//4、把原对象addAll到新的hashset
                newSet.add(node);//5、把node add到新的hashset
                childList = newSet;//6、把新的hashset赋值给之前的
            }
        }
        RecordLog.info("Add child <{0}> to node <{1}>", ((DefaultNode)node).id.getName(), id.getName());
    }
 }

这里的要点在于，操作集合要在“开放”or“可见”之前，可以理解为新对象创建过程，一旦开放给其它线程（修改引用变量），该对象就不应该有继续修改的操作，类似immutable对象，而immutable对象都是线程安全的
还要保证，在新对象创建过程中，不要多次使用原对象（childList）



参考
https://www.jianshu.com/p/afc6e0ae08b0



