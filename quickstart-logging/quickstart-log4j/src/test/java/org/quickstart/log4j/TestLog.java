package org.quickstart.log4j;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestLog {

    // org.apache.commons.logging
    private static Log commonsLog = LogFactory.getLog(TestLog.class);

    // org.slf4j
    private static Logger slf4jLogger = LoggerFactory.getLogger(TestLog.class);




}
