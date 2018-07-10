关键字synchronized与wait()和notify()/notifyAll()方法相结合可以实现等待/通知模式。 
类ReentrantLock同样可以实现该功能，但是要借助于Condition对象。它具有更好的灵活性，比如可以实现多路通知功能，也就是在一个Lock对象里面可以创建多个Condition(对象监视器)实例，线程对象可以注册在指定Condition中，从而有选择性的进行线程通知，在调度线程上更加灵活 
使用notify和notifyAll方法进行通知时，被通知的线程是由JVM随机选择的，但是ReentrantLock结合Condition可以实现前面介绍过的“选择性通知”,这个功能是非常重要的。 
synchronized相当于整个Lock对象中只有单一的Condition对象，所有线程都注册在它一个对象上，线程开始notifyAll（）时，需要通知所有waiting的线程，没有选择权，会出现相当大的效率问题

关键字synchronized与wait()和notify()/notifyAll()方法相结合可以实现等待/通知模式。
ReentrantLock结合Condition可以实现前面介绍过的“选择性通知”,这个功能是非常重要的。 
https://blog.csdn.net/chenchaofuck1/article/details/51592429
https://blog.csdn.net/zhang199416/article/details/70771238



