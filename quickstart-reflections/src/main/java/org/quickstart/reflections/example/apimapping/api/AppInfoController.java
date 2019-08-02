package org.quickstart.reflections.example.apimapping.api;

import java.util.concurrent.CompletableFuture;

import org.apache.commons.lang3.StringUtils;
import org.quickstart.reflections.example.apimapping.annotation.Controller;
import org.quickstart.reflections.example.apimapping.annotation.RequestBody;
import org.quickstart.reflections.example.apimapping.annotation.RequestMapping;
import org.quickstart.reflections.example.apimapping.annotation.RequestMethod;
import org.quickstart.reflections.example.apimapping.annotation.RequestParam;
import org.quickstart.reflections.example.apimapping.exception.RestfulApiExceptionCode;
import org.quickstart.reflections.example.apimapping.exception.RestfulException;
import org.quickstart.reflections.example.apimapping.interfaces.impl.AppInfoHandlerImpl;
import org.quickstart.reflections.example.apimapping.model.RespResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



@Controller
@RequestMapping("/app")
public class AppInfoController {

  private static final Logger logger = LoggerFactory.getLogger(AppInfoController.class);

  private AppInfoHandlerImpl appHandlerImpl;

  public void setAppHandlerImpl(AppInfoHandlerImpl appHandlerImpl) {
    this.appHandlerImpl = appHandlerImpl;
  }

  @RequestMapping(value = "/query", method = RequestMethod.GET)
  public CompletableFuture<RespResult> query(@RequestParam(value = "appCode") String appCode) {

    if (StringUtils.isBlank(appCode)) {
      throw new RestfulException(RestfulApiExceptionCode.DATA_IS_NULL);
    }

    CompletableFuture<String> future = appHandlerImpl.query(appCode);
    return future.thenApply(handleResult -> {
      return RespResult.ok().put("data", handleResult);
    }).handle((s, throwable) -> {
      if (throwable != null) {
        if (throwable.getCause() instanceof RestfulException) {
          RestfulException ex = (RestfulException) throwable.getCause();
          RespResult result = new RespResult();
          return result.error(Integer.valueOf(ex.getCode()), ex.getMsg());
        }
      }
      return s;
    });

  }

  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public CompletableFuture<RespResult> update(@RequestBody String jsonData) {

    if (StringUtils.isBlank(jsonData)) {
      throw new RestfulException(RestfulApiExceptionCode.DATA_IS_NULL);
    }

    CompletableFuture<Long> future = appHandlerImpl.update(jsonData);
    return future.thenApply(handleResult -> {
      if (handleResult > 0L) {
        return RespResult.ok();
      } else {
        return RespResult.error();
      }
    }).handle((s, throwable) -> {
      if (throwable != null) {
        if (throwable.getCause() instanceof RestfulException) {
          RestfulException ex = (RestfulException) throwable.getCause();
          RespResult result = new RespResult();
          return result.error(Integer.valueOf(ex.getCode()), ex.getMsg());
        }
      }
      return s;
    });

  }

  @RequestMapping(value = "/delete", method = RequestMethod.POST)
  public CompletableFuture<RespResult> delete(@RequestBody String jsonData) {

    if (StringUtils.isBlank(jsonData)) {
      throw new RestfulException(RestfulApiExceptionCode.DATA_IS_NULL);
    }

    CompletableFuture<Long> future = appHandlerImpl.delete(jsonData);
    return future.thenApply(handleResult -> {
      if (handleResult > 0L) {
        return RespResult.ok();
      } else {
        return RespResult.error();
      }
    });

  }

}
