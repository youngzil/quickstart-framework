Disruptor它是一个开源的并发框架，并获得2011 Duke’s 程序框架创新奖，能够在无锁的情况下实现网络的Queue并发操作。


http://ifeve.com/disruptor/
http://ifeve.com/disruptor-getting-started/


http://lmax-exchange.github.io/disruptor/
https://github.com/LMAX-Exchange/disruptor


LMAX-Exchange源码github地址：https://github.com/LMAX-Exchange/disruptor
带中文注释的源码github地址：https://github.com/daoqidelv/disruptor




Disruptor
面对复杂的系统，能够支撑稳定的高并发，是我们每个coder心目中追求的极致。Disruptor是一个高性能的异步处理框架，或者可以认为是最快的消息框架（轻量的JMS），也可以认为是一个观察者模式实现，或者事件-监听模式的实现，直接称disruptor模式。Disruptor最大特点是高性能，异步低延时，更少的java gc，实现无锁Ring Buffer, 可以高效地实现控制高并发.
应用场景：在一些获取验证码、发短信、发送邮件的场景下，对实时性要求不够，如果收不到，用户可以再次要求重发；对于一些奖品、抢购、卡券、积分的发放，在高峰期，可以只入队，之后用异步的方式慢慢发放；对于比较复杂的逻辑可以进行并发操作。















