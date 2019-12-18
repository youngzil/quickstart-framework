```
<? extends T>限定参数类型的上界：参数类型必须是T或T的子类型,修饰返回数据类型,所以get返回的一定是T和T的子类，所以可以使用T和T的父类接收，
<? super T> 限定参数类型的下界：参数类型必须是T或T的超类型，修饰保存数据类型，add加入的一定是T和T的父类，所以可以加入T和T的子类，返回值是Object接收，再强转
具体示例查看GenericTypesTest.java


总结
限定通配符总是包括自己
上界类型通配符：add方法受限
下界类型通配符：get方法受限
如果你想从一个数据类型里获取数据，使用 ? extends 通配符
如果你想把对象写入一个数据结构里，使用 ? super 通配符
如果你既想存，又想取，那就别用通配符
不能同时声明泛型通配符上界和下界
--------------------- 
作者：zhiboer 
来源：CSDN 
原文：https://blog.csdn.net/claram/article/details/51943742 
版权声明：本文为博主原创文章，转载请附上博文链接！


总结为：
1、<? extends T> 只能用于方法返回，告诉编译器此返参的类型的最小继承边界为T，T和T的父类都能接收，但是入参类型无法确定，只能接受null的传入
2、<? super T>只能用于限定方法入参，告诉编译器入参只能是T或其子类型，而返参只能用Object类接收
3、? 既不能用于入参也不能用于返参


使用大写字母A,B,C,D......X,Y,Z定义的，就都是泛型，把T换成A也一样，这里T只是名字上的意义而已

？ 表示不确定的java类型
T (type) 表示具体的一个java类型
K V (key value) 分别代表java键值中的Key Value
E (element) 代表Element




https://www.zhihu.com/question/20400700
https://www.cnblogs.com/lwbqqyumidi/p/3837629.html
https://www.cnblogs.com/yepei/p/6591289.html
https://blog.csdn.net/claram/article/details/51943742


