/**
 * 项目名称：quickstart-guice 
 * 文件名：UserServiceProvider.java
 * 版本信息：
 * 日期：2018年5月21日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.guice.sample.service.impl;

import org.quickstart.guice.sample.service.Application;
import org.quickstart.guice.sample.service.ApplicationProvider;
import org.quickstart.guice.sample.service.LogService;
import org.quickstart.guice.sample.service.UserService;

import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * UserServiceProvider
 * 
 * @author：yangzl@asiainfo.com
 * @2018年5月21日 下午5:47:10
 * @since 1.0
 */
public class AppProvider implements Provider<ApplicationProvider> {

//    private final Connection connection;
//
//    @Inject
//    public DatabaseTransactionLogProvider(Connection connection) {
//      this.connection = connection;
//    }

    @Override
    public ApplicationProvider get() {
        MyAppProvider transactionLog = new MyAppProvider();
        // transactionLog.setConnection(connection);
        return transactionLog;
    }

}
