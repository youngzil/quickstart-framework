函数式接口
函数式接口（functional interface 也叫功能性接口，其实是同一个东西）。
简单来说，函数式接口是只包含一个方法的接口。比如Java标准库中的java.lang.Runnable和java.util.Comparator都是典型的函数式接口。
java 8提供 @FunctionalInterface作为注解,这个注解是非必须的，只要接口符合函数式接口的标准（即只包含一个方法的接口），虚拟机会自动判断，但 最好在接口上使用注解@FunctionalInterface进行声明，以免团队的其他人员错误地往接口中添加新的方法。 
Java中的lambda无法单独出现，它需要一个函数式接口来盛放，lambda表达式方法体其实就是函数接口的实现，



Lambda语法
包含三个部分
1、一个括号内用逗号分隔的形式参数，参数是函数式接口里面方法的参数
2、一个箭头符号：->
3、方法体，可以是表达式和代码块，方法体函数式接口里面方法的实现，如果是代码块，则必须用{}来包裹起来，且需要一个return 返回值，但有个例外，若函数式接口里面方法返回值是void，则无需{}

总体看起来像这样
(parameters) -> expression 或者 (parameters) -> { statements; }


方法引用
其实是lambda表达式的一个简化写法，所引用的方法其实是lambda表达式的方法体实现，语法也很简单，左边是容器（可以是类名，实例名），中间是"::"，右边是相应的方法名。如下所示：
ObjectReference::methodName


一般方法的引用格式是
1、如果是静态方法，则是ClassName::methodName。如 Object ::equals
2、如果是实例方法，则是Instance::methodName。如Object obj=new Object();obj::equals;
3、构造函数.则是ClassName::new




参考
https://my.oschina.net/benhaile/blog/175012

