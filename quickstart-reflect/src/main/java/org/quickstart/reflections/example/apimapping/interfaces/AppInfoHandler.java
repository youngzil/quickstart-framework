package org.quickstart.reflections.example.apimapping.interfaces;

import java.util.concurrent.CompletableFuture;

public interface AppInfoHandler {

  public CompletableFuture<String> query(String appCode);

  public CompletableFuture<Long> update(String jsonData);

  public CompletableFuture<Long> delete(String jsonData);

}
