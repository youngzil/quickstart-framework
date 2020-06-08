package org.quickstart.sensitive;

import com.github.houbb.sensitive.annotation.Sensitive;
import com.github.houbb.sensitive.core.api.strategory.StrategyPassword;

/**
 * @description TODO
 *
 * @author youngzil@163.com
 * @createTime 2020/6/5 23:40
 */
public class UserPasswordCondition {

    @Sensitive(condition = ConditionFooPassword.class, strategy = StrategyPassword.class)
    private String password;
}
