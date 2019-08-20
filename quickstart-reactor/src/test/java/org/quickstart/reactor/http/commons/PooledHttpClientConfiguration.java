package org.quickstart.reactor.http.commons;

import io.netty.util.CharsetUtil;
import java.nio.charset.Charset;
import lombok.Data;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019-08-18 20:47
 */
@Data
public class PooledHttpClientConfiguration {
  private Charset charset = CharsetUtil.UTF_8;

  private int connectTimeout = 500;
  private int maxConnectionsPerHost = 50;
  private int soReadTimeout = 5000;
  private int soWriteTimeout = 5000;

  private boolean socketKeepAlive = false;
  private boolean tcpNoDelay = false;
  private int writeBufferLowWaterMark = 8 * 1024;
  private int writeBufferHighWaterMark = 32 * 1024;

  private int tcpBufferSize = 32 * 1024;

}
