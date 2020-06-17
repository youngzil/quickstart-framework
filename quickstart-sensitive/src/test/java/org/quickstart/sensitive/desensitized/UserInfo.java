package org.quickstart.sensitive.desensitized;

import lombok.Data;
import org.quickstart.sensitive.desensitized.annotation.Desensitized;
import org.quickstart.sensitive.desensitized.enums.SensitiveTypeEnum;

/**
 * @description TODO
 *
 * @author yangzl
 * @createTime 2020/6/17 22:04
 */
@Data
public class UserInfo{
    @Desensitized(type = SensitiveTypeEnum.CHINESE_NAME)
    private String realName;

    @Desensitized(type = SensitiveTypeEnum.ID_CARD)
    private String idCardNo;

    @Desensitized(type = SensitiveTypeEnum.MOBILE_PHONE)
    private String mobileNo;

    private String account;

    @Desensitized(type = SensitiveTypeEnum.PASSWORD, isEffictiveMethod = "isEffictiveMethod")
    private String password;

    //setter、getter略
}
