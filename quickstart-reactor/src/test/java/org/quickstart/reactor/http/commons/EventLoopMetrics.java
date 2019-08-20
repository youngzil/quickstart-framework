package org.quickstart.reactor.http.commons;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by wusir on 2018/10/30.
 */
public class EventLoopMetrics implements EventLoopGroupMetrics.EventLoopInfo{
    private final String name;
    private final AtomicInteger currentRequests = new AtomicInteger(0);
    private final AtomicInteger currentConnections = new AtomicInteger(0);

    public EventLoopMetrics(String name) {
        this.name = name;
    }

    @Override
    public int currentConnectionsCount()
    {
        return currentConnections.get();
    }

    @Override
    public int currentHttpRequestsCount()
    {
        return currentRequests.get();
    }

    public void incrementCurrentRequests()
    {
        this.currentRequests.incrementAndGet();
    }

    public void decrementCurrentRequests()
    {
        this.currentRequests.decrementAndGet();
    }

    public void incrementCurrentConnections()
    {
        this.currentConnections.incrementAndGet();
    }

    public void decrementCurrentConnections()
    {
        this.currentConnections.decrementAndGet();
    }

}
