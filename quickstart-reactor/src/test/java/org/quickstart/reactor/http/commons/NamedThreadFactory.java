package org.quickstart.reactor.http.commons;


import java.util.concurrent.ThreadFactory;

import io.netty.util.concurrent.FastThreadLocalThread;

/**
 * Created by wusir on 2018/10/30.
 */
public class NamedThreadFactory implements ThreadFactory {
    private String name;
    private int num = 0;

    public NamedThreadFactory(String name) {
        super();
        this.name = name;
    }

    /**
     * Constructs a new {@code Thread}.  Implementations may also initialize
     * priority, name, daemon status, {@code ThreadGroup}, etc.
     *
     * @param r a runnable to be executed by new thread instance
     * @return constructed thread, or {@code null} if the request to
     * create a thread is rejected
     */
    @Override
    public Thread newThread(Runnable r) {
        final FastThreadLocalThread t = new FastThreadLocalThread(r,name + "-" + num++);
        return t;
    }
}
