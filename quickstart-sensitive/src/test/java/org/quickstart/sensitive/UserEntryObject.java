package org.quickstart.sensitive;

import com.github.houbb.sensitive.annotation.SensitiveEntry;
import lombok.Data;

import java.util.List;

/**
 * @description TODO
 *
 * @author youngzil@163.com
 * @createTime 2020/6/5 23:46
 */
@Data
public class UserEntryObject {

    @SensitiveEntry
    private User user;

    @SensitiveEntry
    private List<User> userList;

    @SensitiveEntry
    private User[] userArray;

    //...
}
