1、CompletableFuture原理解析
2、CompletableFuture使用


---------------------------------------------------------------------------------------------------------------------
CompletableFuture原理解析



Runnable，无结果的同步行为
Callable，有结果的同步行为
Future，异步封装Callable/Runnable，如果是Callable有结果的句柄，需要自己轮询是否结果完成
CompletableFuture，封装Future，使其拥有回调功能，主动通知结果完成


CompletableFuture让Future的功能和使用场景得到极大的完善和扩展,提供了函数式编程能力,使代码更加美观优雅,而且可以通过回调的方式计算处理结果,对异常处理也有了更好的处理手段.




// 直接创建
CompletableFuture c0 = new CompletableFuture();
// 直接创建一个已经做完的蛋糕
val c1 = CompletableFuture.completedFuture("cake");

// 无返回值异步任务，会采用内部forkjoin线程池
val c2 = CompletableFuture.runAsync(()->{});
// 无返回值异步任务，采用定制的线程池
val c3 = CompletableFuture.runAsync(()->{}, newSingleThreadExecutor());

// 返回值异步任务，采用定制的线程池
val c4 = CompletableFuture.supplyAsync(()-> "cake", newSingleThreadExecutor());
// 返回值异步任务，采用内部forkjoin线程池
val c5 = CompletableFuture.supplyAsync(()-> "cake");

// 只要有一个完成，则完成，有一个抛异常，则携带异常
CompletableFuture.anyOf(c1, c2, c3, c4, c5);
// 当所有的 future 完成时,新的 future 同时完成
// 当某个方法出现了异常时,新 future 会在所有 future 完成的时候完成,并且包含一个异常.
CompletableFuture.allOf(c1, c2, c3, c4, c5);



//不抛出中断异常，看着你做蛋糕
//阻塞
cf.join();

//有异常，看着你做蛋糕
//阻塞
cf.get();

//有异常，看着你做蛋糕一小时
//阻塞
cf.get(1, TimeUnit.HOURS);

//蛋糕做好了吗？做好了我直接吃你做的，做不好我吃我的
//非阻塞
cf.getNow("my cake");

// 我问糕点师：蛋糕是否不做了？
//非阻塞
cf.isCancelled();

//我问糕点师：蛋糕是否做糊了？
//非阻塞
cf.isCompletedExceptionally();

// 我问糕点师：蛋糕做完了吗？
//非阻塞
cf.isDone();


参考
https://www.jianshu.com/p/abfa29c01e1d
https://blog.csdn.net/ahilll/article/details/83956261
http://ifeve.com/completablefuture/
https://blog.csdn.net/JoshuaXin/article/details/84880857
https://www.jianshu.com/p/3aa308a5f182



---------------------------------------------------------------------------------------------------------------------
CompletableFuture使用


文章和代码
https://colobu.com/2016/02/29/Java-CompletableFuture/
/Users/yangzl/git/quickstart-framework/quickstart-javase/src/main/java/org/quickstart/javase/jdk8/completablefuture

https://juejin.im/post/5adbf8226fb9a07aac240a67
https://colobu.com/2018/03/12/20-Examples-of-Using-Java%E2%80%99s-CompletableFuture/

https://www.ibm.com/developerworks/cn/java/j-cf-of-jdk8/index.html
https://www.baeldung.com/java-completablefuture
http://codingjunkie.net/completable-futures-part1/

https://www.jianshu.com/p/6f3ee90ab7d3
https://www.jianshu.com/p/bdc6bd50f7d2

https://github.com/manouti/completablefuture-examples

https://colobu.com/2016/02/29/Java-CompletableFuture/#%E4%B8%BB%E5%8A%A8%E5%AE%8C%E6%88%90%E8%AE%A1%E7%AE%97


1、创建CompletableFuture对象

CompletableFuture.completedFuture()

final CompletableFuture<Integer> future = new CompletableFuture<Integer>();

  public static CompletableFuture<Void> runAsync(Runnable runnable)
  public static CompletableFuture<Void> runAsync(Runnable runnable, Executor executor)
  public static <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier)
  public static <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier, Executor executor)
  CompletableFuture.completedFuture(10000).get();
  
  
2、计算完成时的处理：当Action执行完毕后它的结果返回原始的CompletableFuture的计算结果或者返回异常。所以不会对结果产生任何的作用。
  public CompletionStage<T> whenComplete(BiConsumer<? super T, ? super Throwable> action);
  public CompletionStage<T> whenCompleteAsync(BiConsumer<? super T, ? super Throwable> action);
  public CompletionStage<T> whenCompleteAsync(BiConsumer<? super T, ? super Throwable> action,Executor executor);
  
 当运行时出现了异常，可以通过exceptionally进行补偿。会对最终的结果产生影响
     public CompletionStage<T> exceptionally(Function<Throwable, ? extends T> fn);
 
 运行完成时，对结果的处理和转换
  public <U> CompletionStage<U> handle(BiFunction<? super T, Throwable, ? extends U> fn);
  public <U> CompletionStage<U> handleAsync(BiFunction<? super T, Throwable, ? extends U> fn);
  public <U> CompletionStage<U> handleAsync(BiFunction<? super T, Throwable, ? extends U> fn,Executor executor);


3、转换
  3.1、进行变换
    public <U> CompletionStage<U> thenApply(Function<? super T,? extends 文件描述符示意> fn);
    public <U> CompletionStage<U> thenApplyAsync(Function<? super T,? extends U> fn);
    public <U> CompletionStage<U> thenApplyAsync(Function<? super T,? extends U> fn,Executor executor);
    
  3.2、进行消耗：
    public CompletionStage<Void> thenAccept(Consumer<? super T> action);
    public CompletionStage<Void> thenAcceptAsync(Consumer<? super T> action);
    public CompletionStage<Void> thenAcceptAsync(Consumer<? super T> action,Executor executor);
    
  3.3、对上一步的计算结果不关心，执行下一个操作Runnable
    public CompletionStage<Void> thenRun(Runnable action);
    public CompletionStage<Void> thenRunAsync(Runnable action);
    public CompletionStage<Void> thenRunAsync(Runnable action,Executor executor);
    
  
    
4、组合
  4.1、结合两个CompletionStage的结果，进行转化后返回，没有顺序的
      public <U,V> CompletionStage<V> thenCombine(CompletionStage<? extends U> other,BiFunction<? super T,? super U,? extends V> fn);
      public <U,V> CompletionStage<V> thenCombineAsync(CompletionStage<? extends U> other,BiFunction<? super T,? super U,? extends V> fn);
      public <U,V> CompletionStage<V> thenCombineAsync(CompletionStage<? extends U> other,BiFunction<? super T,? super U,? extends V> fn,Executor executor);
      
  4.2、结合两个CompletionStage的结果，进行消耗
    public <U> CompletionStage<Void> thenAcceptBoth(CompletionStage<? extends U> other,BiConsumer<? super T, ? super U> action);
    public <U> CompletionStage<Void> thenAcceptBothAsync(CompletionStage<? extends U> other,BiConsumer<? super T, ? super U> action);
    public <U> CompletionStage<Void> thenAcceptBothAsync(CompletionStage<? extends U> other,BiConsumer<? super T, ? super U> action,     Executor executor);
    
  4.3、在两个CompletionStage都运行完执行Runnable
    public CompletionStage<Void> runAfterBoth(CompletionStage<?> other,Runnable action);
    public CompletionStage<Void> runAfterBothAsync(CompletionStage<?> other,Runnable action);
    public CompletionStage<Void> runAfterBothAsync(CompletionStage<?> other,Runnable action,Executor executor);
    
  4.4、两个CompletionStage，谁计算的快，我就用那个CompletionStage的结果进行下一步的转化操作。
    public <U> CompletionStage<U> applyToEither(CompletionStage<? extends T> other,Function<? super T, U> fn);
    public <U> CompletionStage<U> applyToEitherAsync(CompletionStage<? extends T> other,Function<? super T, U> fn);
    public <U> CompletionStage<U> applyToEitherAsync(CompletionStage<? extends T> other,Function<? super T, U> fn,Executor executor);
  
  4.5、两个CompletionStage，谁计算的快，我就用那个CompletionStage的结果进行下一步的消耗操作
    public CompletionStage<Void> acceptEither(CompletionStage<? extends T> other,Consumer<? super T> action);
    public CompletionStage<Void> acceptEitherAsync(CompletionStage<? extends T> other,Consumer<? super T> action);
    public CompletionStage<Void> acceptEitherAsync(CompletionStage<? extends T> other,Consumer<? super T> action,Executor executor);
  
  4.6、两个CompletionStage，任何一个完成了都会执行下一步的操作（Runnable）
    public CompletionStage<Void> runAfterEither(CompletionStage<?> other,Runnable action);
    public CompletionStage<Void> runAfterEitherAsync(CompletionStage<?> other,Runnable action);
    public CompletionStage<Void> runAfterEitherAsync(CompletionStage<?> other,Runnable action,Executor executor);
    
  4.7、//thenCompose 接受一个 Function 并且返回一个新的 CompletableFuture 对象.有顺序的
     //所收集到的对象是没有嵌套的 CompletableFuture 对象. 类似 flatMap 的效果
     public <U> CompletableFuture<U> thenCompose(Function<? super T, ? extends CompletionStage<U>> fn);
     public <U> CompletableFuture<U> thenComposeAsync(Function<? super T, ? extends CompletionStage<U>> fn);
     public <U> CompletableFuture<U> thenComposeAsync(Function<? super T, ? extends CompletionStage<U>> fn,Executor executor);


5、// 辅助方法 allOf 和 anyOf

  // anyOf返回值的计算结果是参数中其中一个CompletableFuture的计算结果，
      // applyToEither返回值的计算结果却是要经过fn处理的
      // allOf方法是当所有的CompletableFuture都执行完后执行计算。
      // anyOf方法是当任意一个CompletableFuture执行完后就会执行计算，计算的结果相同。
      // public static CompletableFuture<Void> 	    allOf(CompletableFuture<?>... cfs)
      // public static CompletableFuture<Object> 	anyOf(CompletableFuture<?>... cfs)




串行关系
then 直译【然后】，也就是表示下一步，所以通常是一种串行关系体现, then 后面的单词（比如 run /apply/accept）就是上面说的函数式接口中的抽象方法名称了，它的作用和那几个函数式接口的作用是一样一样滴

CompletableFuture<Void> thenRun(Runnable action)
CompletableFuture<Void> thenRunAsync(Runnable action)
CompletableFuture<Void> thenRunAsync(Runnable action, Executor executor)
  
<U> CompletableFuture<U> thenApply(Function<? super T,? extends U> fn)
<U> CompletableFuture<U> thenApplyAsync(Function<? super T,? extends U> fn)
<U> CompletableFuture<U> thenApplyAsync(Function<? super T,? extends U> fn, Executor executor)
  
CompletableFuture<Void> thenAccept(Consumer<? super T> action) 
CompletableFuture<Void> thenAcceptAsync(Consumer<? super T> action)
CompletableFuture<Void> thenAcceptAsync(Consumer<? super T> action, Executor executor)
  
<U> CompletableFuture<U> thenCompose(Function<? super T, ? extends CompletionStage<U>> fn)  
<U> CompletableFuture<U> thenComposeAsync(Function<? super T, ? extends CompletionStage<U>> fn)
<U> CompletableFuture<U> thenComposeAsync(Function<? super T, ? extends CompletionStage<U>> fn, Executor executor)





聚合 And 关系
combine... with... 和 both...and... 都是要求两者都满足，也就是 and 的关系了

<U,V> CompletableFuture<V> thenCombine(CompletionStage<? extends U> other, BiFunction<? super T,? super U,? extends V> fn)
<U,V> CompletableFuture<V> thenCombineAsync(CompletionStage<? extends U> other, BiFunction<? super T,? super U,? extends V> fn)
<U,V> CompletableFuture<V> thenCombineAsync(CompletionStage<? extends U> other, BiFunction<? super T,? super U,? extends V> fn, Executor executor)

<U> CompletableFuture<Void> thenAcceptBoth(CompletionStage<? extends U> other, BiConsumer<? super T, ? super U> action)
<U> CompletableFuture<Void> thenAcceptBothAsync(CompletionStage<? extends U> other, BiConsumer<? super T, ? super U> action)
<U> CompletableFuture<Void> thenAcceptBothAsync( CompletionStage<? extends U> other, BiConsumer<? super T, ? super U> action, Executor executor)
  
CompletableFuture<Void> runAfterBoth(CompletionStage<?> other, Runnable action)
CompletableFuture<Void> runAfterBothAsync(CompletionStage<?> other, Runnable action)
CompletableFuture<Void> runAfterBothAsync(CompletionStage<?> other, Runnable action, Executor executor)





聚合 Or 关系
Either...or... 表示两者中的一个，自然也就是 Or 的体现了

<U> CompletableFuture<U> applyToEither(CompletionStage<? extends T> other, Function<? super T, U> fn)
<U> CompletableFuture<U> applyToEitherAsync(、CompletionStage<? extends T> other, Function<? super T, U> fn)
<U> CompletableFuture<U> applyToEitherAsync(CompletionStage<? extends T> other, Function<? super T, U> fn, Executor executor)

CompletableFuture<Void> acceptEither(CompletionStage<? extends T> other, Consumer<? super T> action)
CompletableFuture<Void> acceptEitherAsync(CompletionStage<? extends T> other, Consumer<? super T> action)
CompletableFuture<Void> acceptEitherAsync(CompletionStage<? extends T> other, Consumer<? super T> action, Executor executor)

CompletableFuture<Void> runAfterEither(CompletionStage<?> other, Runnable action)
CompletableFuture<Void> runAfterEitherAsync(CompletionStage<?> other, Runnable action)
CompletableFuture<Void> runAfterEitherAsync(CompletionStage<?> other, Runnable action, Executor executor)





异常处理
exceptionally-------》try/catch
whenComplete 和 handle-------》try/finally

whenComplete 和 handle 的区别如果你看接受的参数函数式接口名称你也就能看出差别了，
前者使用Comsumer, 自然也就不会有返回值；后者使用 Function，自然也就会有返回值

CompletableFuture<T> exceptionally(Function<Throwable, ? extends T> fn)
CompletableFuture<T> exceptionallyAsync(Function<Throwable, ? extends T> fn)
CompletableFuture<T> exceptionallyAsync(Function<Throwable, ? extends T> fn, Executor executor)
        
CompletableFuture<T> whenComplete(BiConsumer<? super T, ? super Throwable> action)
CompletableFuture<T> whenCompleteAsync(BiConsumer<? super T, ? super Throwable> action)
CompletableFuture<T> whenCompleteAsync(BiConsumer<? super T, ? super Throwable> action, Executor executor)
        
       
<U> CompletableFuture<U> handle(BiFunction<? super T, Throwable, ? extends U> fn)
<U> CompletableFuture<U> handleAsync(BiFunction<? super T, Throwable, ? extends U> fn)
<U> CompletableFuture<U> handleAsync(BiFunction<? super T, Throwable, ? extends U> fn, Executor executor)








---------------------------------------------------------------------------------------------------------------------





---------------------------------------------------------------------------------------------------------------------









