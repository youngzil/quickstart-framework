package org.quickstart.design.pattern.singleton;

/**
 * <p>描述: [功能描述] </p >
 *
 * @author yangzl
 * @date 2020/8/24 22:07
 * @version v1.0
 */
public class LazySingleton {
    private static LazySingleton uniqueInstance;

    private LazySingleton() {
    }

    public static synchronized LazySingleton getInstance() {
        if (uniqueInstance == null)
            uniqueInstance = new LazySingleton();
        return uniqueInstance;
    }
}
