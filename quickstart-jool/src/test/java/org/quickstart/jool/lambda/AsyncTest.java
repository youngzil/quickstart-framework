package org.quickstart.jool.lambda;

import static org.junit.Assert.assertNull;

import java.util.concurrent.CompletionStage;

import org.jooq.lambda.Async;
import org.junit.Test;

public class AsyncTest {
    
    @Test
    public void testNoCustomExecutor() {
        CompletionStage<Void> completionStage = Async.runAsync(() -> {});
        assertNull(completionStage.toCompletableFuture().join());
        
        completionStage = Async.supplyAsync(() -> null);
        assertNull(completionStage.toCompletableFuture().join());
    }
}
