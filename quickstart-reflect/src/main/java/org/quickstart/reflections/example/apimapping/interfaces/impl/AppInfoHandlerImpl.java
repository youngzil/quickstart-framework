package org.quickstart.reflections.example.apimapping.interfaces.impl;

import java.util.concurrent.CompletableFuture;

import org.quickstart.reflections.example.apimapping.interfaces.AppInfoHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppInfoHandlerImpl implements AppInfoHandler {

  private static final Logger logger = LoggerFactory.getLogger(AppInfoHandlerImpl.class);

  @Override
  public CompletableFuture<String> query(String appCode) {

    return CompletableFuture.completedFuture("test");

  }

  @Override
  public CompletableFuture<Long> update(String jsonData) {

    return CompletableFuture.completedFuture(100L);

  }

  @Override
  public CompletableFuture<Long> delete(String jsonData) {
    return CompletableFuture.completedFuture(0L);
  }

}
