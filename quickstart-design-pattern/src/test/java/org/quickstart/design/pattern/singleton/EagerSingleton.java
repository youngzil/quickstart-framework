package org.quickstart.design.pattern.singleton;

/**
 * <p>描述: [功能描述] </p >
 *
 * @author yangzl
 * @date 2020/8/24 22:06
 * @version v1.0
 */
public class EagerSingleton {
    // jvm保证在任何线程访问uniqueInstance静态变量之前一定先创建了此实例
    private static EagerSingleton uniqueInstance = new EagerSingleton();

    // 私有的默认构造子，保证外界无法直接实例化
    private EagerSingleton() {
    }

    // 提供全局访问点获取唯一的实例
    public static EagerSingleton getInstance() {
        return uniqueInstance;
    }
}
