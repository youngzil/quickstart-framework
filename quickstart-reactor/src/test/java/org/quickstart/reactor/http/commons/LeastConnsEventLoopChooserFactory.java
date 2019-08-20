package org.quickstart.reactor.http.commons;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import io.netty.util.concurrent.EventExecutor;
import io.netty.util.concurrent.EventExecutorChooserFactory;

/**
 * Created by wusir on 2018/10/30.
 * Choose the event loop that owns least connections
 */
public class LeastConnsEventLoopChooserFactory implements EventExecutorChooserFactory{
    private static final Logger logger = LoggerFactory.getLogger(LeastConnsEventLoopChooserFactory.class);
    private final EventLoopGroupMetrics eventLoopGroupMetrics;

    public LeastConnsEventLoopChooserFactory(EventLoopGroupMetrics eventLoopGroupMetrics) {

        this.eventLoopGroupMetrics = eventLoopGroupMetrics;
    }

    @Override
    public EventExecutorChooser newChooser(EventExecutor[] eventExecutors) {
        return new LeastConnsEventLoopChooser(eventExecutors,eventLoopGroupMetrics);
    }

    private static class LeastConnsEventLoopChooser implements EventExecutorChooser{
        private List<EventExecutor> executors;
        private EventLoopGroupMetrics eventLoopGroupMetrics;

        public LeastConnsEventLoopChooser(EventExecutor[] executors,final EventLoopGroupMetrics eventLoopGroupMetrics) {
            this.executors = Arrays.asList(executors);
            this.eventLoopGroupMetrics = eventLoopGroupMetrics;
        }

        @Override
        public EventExecutor next() {
            return chooseOwnsLeastConns();
        }

        private EventExecutor chooseOwnsLeastConns(){
            EventExecutor leastExec = null;
            int leastConnCount = Integer.MAX_VALUE;
            Map<Thread, Integer> connsPer = eventLoopGroupMetrics.connectionsPerEventLoop();

            Collections.shuffle(executors, ThreadLocalRandom.current());

            for (EventExecutor executor : executors) {
                int count = connsPer.getOrDefault(Thread.currentThread(),0);
                if(count < leastConnCount){
                    leastConnCount = count;
                    leastExec = executor;
                }
            }
            if(leastExec == null){
                leastExec = executors.get(0);
            }
            if(logger.isDebugEnabled()){
                logger.debug("Choose eventloop:"+String.valueOf(leastExec)+", leastCount="+leastConnCount+", connsCountPerEventloop="+connsPer);
            }
            return leastExec;
        }
    }
}
