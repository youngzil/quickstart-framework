<? extends T>限定参数类型的上界：参数类型必须是T或T的子类型
<? super T> 限定参数类型的下界：参数类型必须是T或T的超类型

总结为：
1、<? extends T> 只能用于方法返回，告诉编译器此返参的类型的最小继承边界为T，T和T的父类都能接收，但是入参类型无法确定，只能接受null的传入
2、<? super T>只能用于限定方法入参，告诉编译器入参只能是T或其子类型，而返参只能用Object类接收
3、? 既不能用于入参也不能用于返参


使用大写字母A,B,C,D......X,Y,Z定义的，就都是泛型，把T换成A也一样，这里T只是名字上的意义而已

？ 表示不确定的java类型
T (type) 表示具体的一个java类型
K V (key value) 分别代表java键值中的Key Value
E (element) 代表Element





https://www.cnblogs.com/lwbqqyumidi/p/3837629.html
https://www.cnblogs.com/yepei/p/6591289.html
https://blog.csdn.net/claram/article/details/51943742


