1. 停止不了的线程
2. 判断线程是否停止状态
3. 能停止的线程--异常法
4. 在沉睡中停止
5. 能停止的线程---暴力停止
6.方法stop()与java.lang.ThreadDeath异常
7. 释放锁的不良后果
8. 使用return停止线程



在java中有以下3种方法可以终止正在运行的线程：
1、使用退出标志，使线程正常退出，也就是当run方法完成后线程终止。
2、使用stop方法强行终止，但是不推荐这个方法，因为stop和suspend及resume一样都是过期作废的方法。
3、使用interrupt方法中断线程。



调用stop()方法时会抛出java.lang.ThreadDeath异常，但是通常情况下，此异常不需要显示地捕捉。





参考
https://www.cnblogs.com/greta/p/5624839.html



