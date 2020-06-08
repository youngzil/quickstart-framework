package org.quickstart.sensitive;

import com.github.houbb.sensitive.api.IStrategy;
import com.github.houbb.sensitive.core.api.SensitiveUtil;
import com.github.houbb.sensitive.core.api.strategory.StrategyEmail;
import org.junit.Test;

import java.util.Arrays;

/**
 * @description TODO
 *
 * @author youngzil@163.com
 * @createTime 2020/6/5 23:36
 */
public class SensitiveTest {

    @Test
    public void singleSensitiveTest() {
        final String email = "123456@qq.com";
        IStrategy strategy = new StrategyEmail();
        final String emailSensitive = (String) strategy.des(email, null);
        System.out.println("脱敏后的邮箱：" + emailSensitive);
    }

    @Test
    public void UserSensitiveTest() {
        User user = buildUser();
        System.out.println("脱敏前原始： " + user);
        User sensitiveUser = SensitiveUtil.desCopy(user);
        System.out.println("脱敏对象： " + sensitiveUser);
        System.out.println("脱敏后原始： " + user);
    }

    /**
     * 用户属性中有集合或者map，集合中属性是基础类型-脱敏测试
     * @since 0.0.2
     */
    @Test
    public void sensitiveEntryBaseTypeTest() {
        UserEntryBaseType userEntryBaseType = buildUserEntryBaseType();
        System.out.println("脱敏前原始： " + userEntryBaseType);
        UserEntryBaseType sensitive = SensitiveUtil.desCopy(userEntryBaseType);
        System.out.println("脱敏对象： " + sensitive);
        System.out.println("脱敏后原始： " + userEntryBaseType);
    }

    /**
     * 用户属性中有集合或者对象，集合中属性是对象-脱敏测试
     * @since 0.0.2
     */
    @Test
    public void sensitiveEntryObjectTest() {
        UserEntryObject userEntryObject = buildUserEntryObject();
        System.out.println("脱敏前原始： " + userEntryObject);
        UserEntryObject sensitiveUserEntryObject = SensitiveUtil.desCopy(userEntryObject);
        System.out.println("脱敏对象： " + sensitiveUserEntryObject);
        System.out.println("脱敏后原始： " + userEntryObject);
    }



    private static User buildUser() {
        User user = new User();
        user.setUsername("脱敏君");
        user.setPassword("123456");
        user.setEmail("12345@qq.com");
        user.setIdCard("123456190001011234");
        user.setPhone("18888888888");
        return user;
    }

    /**
     * 构建用户-属性为列表，列表中为基础属性
     * @return 构建嵌套信息
     * @since 0.0.2
     */
    public static UserEntryBaseType buildUserEntryBaseType() {
        UserEntryBaseType userEntryBaseType = new UserEntryBaseType();
        userEntryBaseType.setChineseNameList(Arrays.asList("盘古", "女娲", "伏羲"));
        userEntryBaseType.setChineseNameArray(new String[]{"盘古", "女娲", "伏羲"});
        return userEntryBaseType;
    }

    /**
     * 构建用户-属性为列表，数组。列表中为对象。
     * @return 构建嵌套信息
     * @since 0.0.2
     */
    public static UserEntryObject buildUserEntryObject() {
        UserEntryObject userEntryObject = new UserEntryObject();
        User user = buildUser();
        User user2 = buildUser();
        User user3 = buildUser();
        userEntryObject.setUser(user);
        userEntryObject.setUserList(Arrays.asList(user2));
        userEntryObject.setUserArray(new User[]{user3});
        return userEntryObject;
    }

}
