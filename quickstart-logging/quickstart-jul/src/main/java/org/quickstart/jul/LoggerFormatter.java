package org.quickstart.jul;

import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019-07-28 10:46
 */

class LoggerFormatter extends Formatter {

  @Override
  public String format(LogRecord record) {
    return "[" + new Date() + "]" + " [" + record.getLevel() + "] " + record.getClass() + record.getMessage() + "\n";
  }

}
