
sleep和wait方法：
1、这两个方法来自不同的类分别是Thread和Object
2、最主要是sleep方法没有释放锁，而wait方法释放了锁，使得其他线程可以使用同步控制块或者方法。
3、wait，notify和notifyAll只能在同步控制方法或者同步控制块里面使用，而sleep可以在任何地方使用(使用范围)
4、sleep必须捕获异常，而wait，notify和notifyAll不需要捕获异常

为什么wait()，notify()方法要和synchronized一起使用？
因为wait()方法是通知当前线程等待并释放对象锁，notify()方法是通知等待此对象锁的线程重新获得对象锁，然而，如果没有获得对象锁，wait方法和notify方法都是没有意义的，即必须先获得对象锁，才能对对象锁进行操作，于是，才必须把notify和wait方法写到synchronized方法或是synchronized代码块中了。


java 接口之多继承,类为什么不可以多继承
接口 是可以多继承的。接口（jdk 1.7 以下版本）里面的方法并有实现,即使接口之间具有相同的方法仍然是可以的 几个接口可以有想通的实现类和实现方法。而且接口 接口里面的成员变量都是 static   final的  有自己静态域 只能自己使用。
https://blog.csdn.net/buzaiguihun/article/details/52996818


---------------------------------------------------------------------------------------------------------------------


