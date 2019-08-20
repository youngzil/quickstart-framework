package org.quickstart.reactor.http.commons;

import java.util.HashMap;
import java.util.Map;

// import javax.inject.Singleton;

/**
 * Created by wusir on 2018/10/30.
 * Metrics of Event Loop's connections and http requests.
 */
// @Singleton
public class EventLoopGroupMetrics {

    private final ThreadLocal<EventLoopMetrics> metricsForCurrentThreadLocal;

    private final Map<Thread,EventLoopMetrics> byEventLoop = new HashMap<>();

    public EventLoopGroupMetrics() {
        this.metricsForCurrentThreadLocal = ThreadLocal.withInitial(()->{
            String name = nameForCurrentEventLoop();
            EventLoopMetrics metrics = new EventLoopMetrics(name);
            byEventLoop.put(Thread.currentThread(),metrics);
            return metrics;
        });
    }

    public Map<Thread,Integer> connectionsPerEventLoop(){
        Map<Thread,Integer> map = new HashMap<>(byEventLoop.size());
        for (Map.Entry<Thread,EventLoopMetrics> entry:byEventLoop.entrySet() ) {
           map.put(entry.getKey(),entry.getValue().currentConnectionsCount());
        }
        return map;
    }

    public Map<Thread,Integer> httpRequestsPerEventLoop(){
        Map<Thread,Integer> map = new HashMap<>(byEventLoop.size());
        for (Map.Entry<Thread,EventLoopMetrics> entry:byEventLoop.entrySet() ) {
            map.put(entry.getKey(),entry.getValue().currentHttpRequestsCount());
        }
        return map;
    }

    public EventLoopMetrics getMetricsForCurrentEventLoop(){
        return metricsForCurrentThreadLocal.get();
    }

    private static String nameForCurrentEventLoop(){
        String threadName = Thread.currentThread().getName();
        String splits[] = threadName.split("-ClientToAiGwWorker-");
        if(splits.length==2){
            return splits[1];
        }
        return threadName;
    }


    interface EventLoopInfo
    {
        int currentConnectionsCount();
        int currentHttpRequestsCount();
    }
}
