package org.quickstart.sensitive;

import com.github.houbb.sensitive.annotation.Sensitive;
import com.github.houbb.sensitive.annotation.SensitiveEntry;
import com.github.houbb.sensitive.core.api.strategory.StrategyChineseName;
import lombok.Data;

import java.util.List;

/**
 * @description TODO
 *
 * @author youngzil@163.com
 * @createTime 2020/6/5 23:44
 */
@Data
public class UserEntryBaseType {

    @SensitiveEntry
    @Sensitive(strategy = StrategyChineseName.class)
    private List<String> chineseNameList;

    @SensitiveEntry
    @Sensitive(strategy = StrategyChineseName.class)
    private String[] chineseNameArray;

}