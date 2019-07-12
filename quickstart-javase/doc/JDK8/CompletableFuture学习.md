
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

1、创建CompletableFuture对象
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
    public <U> CompletionStage<U> thenApply(Function<? super T,? extends U> fn);
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


