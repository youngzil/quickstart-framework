/**
 * 项目名称：quickstart-dropwizard 
 * 文件名：TemplateHealthCheck.java
 * 版本信息：
 * 日期：2018年10月22日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.dropwizard.health;

import com.codahale.metrics.health.HealthCheck;
import com.codahale.metrics.health.HealthCheck.Result;

/**
 * TemplateHealthCheck
 * 
 * @author：youngzil@163.com
 * @2018年10月22日 上午11:30:07
 * @since 1.0
 */
public class TemplateHealthCheck extends HealthCheck {
    private final String template;

    public TemplateHealthCheck(String template) {
        this.template = template;
    }

    @Override
    protected Result check() throws Exception {
        final String saying = String.format(template, "TEST");
        if (!saying.contains("TEST")) {
            return Result.unhealthy("template doesn't include a name");
        }
        return Result.healthy();
    }
}
