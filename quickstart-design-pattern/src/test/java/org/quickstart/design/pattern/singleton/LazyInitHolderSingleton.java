package org.quickstart.design.pattern.singleton;

/**
 * <p>描述: [功能描述] </p >
 *
 * @author yangzl
 * @date 2020/8/24 22:08
 * @version v1.0
 */
public class LazyInitHolderSingleton {
    private LazyInitHolderSingleton() {
    }

    private static class SingletonHolder {
        private static final LazyInitHolderSingleton INSTANCE = new LazyInitHolderSingleton();
    }

    public static LazyInitHolderSingleton getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
