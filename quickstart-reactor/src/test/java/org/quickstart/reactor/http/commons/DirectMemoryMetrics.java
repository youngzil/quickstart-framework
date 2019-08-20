package org.quickstart.reactor.http.commons;

import java.lang.reflect.Field;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// import com.google.inject.Singleton;

/**
 * Created by wusir on 2018/10/31.
 */
// @Singleton
public class DirectMemoryMetrics {
    private static final Logger logger = LoggerFactory.getLogger(DirectMemoryMetrics.class);

    private final ScheduledExecutorService service ;

    public DirectMemoryMetrics(){
        service = Executors.newScheduledThreadPool(1);
    }

    @PostConstruct
    public void init(){
        service.scheduleWithFixedDelay(new Task(),10,10, TimeUnit.SECONDS);
    }

    public void stop(){
        service.shutdown();
    }

    class Task implements Runnable{

        @Override
        public void run(){

        }

        public Current mesure(){
            try{
                Class clazz = Class.forName("io.netty.util.internal.PlatformDependent");
                Field maxMemory = clazz.getDeclaredField("DIRECT_MEMORY_LIMIT");
                maxMemory.setAccessible(true);
                Field reserveMemory = clazz.getDeclaredField("DIRECT_MEMORY_COUNTER");
                reserveMemory.setAccessible(true);
                Current current = new Current();
                synchronized (clazz){
                    current.maxMemory = getMemoryValue(maxMemory);
                    current.reserverMemory = getMemoryValue(reserveMemory);
                    return  current;
                }
            }catch(Exception e){
                logger.warn("Error mesuring direct memory.",e);
                return null;
            }
        }

        private Long getMemoryValue(Field field) throws IllegalAccessException {
            Object value = field.get(null);
            if (value instanceof Long){
                return (Long)value;
            }else if (value instanceof AtomicLong){
                return ((AtomicLong)value).get();
            }else{
                return  null;
            }
        }
    }
    public class Current{
        Long maxMemory;
        Long reserverMemory;
    }
}
