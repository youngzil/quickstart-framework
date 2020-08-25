package org.quickstart.design.pattern.singleton;

import java.io.Serializable;

/**
 * <p>描述: [功能描述] </p >
 *
 * @author yangzl
 * @date 2020/8/21 11:35
 * @version v1.0
 */
public class SingleTon2 implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;

    private static volatile SingleTon2 singleTon;

    private static boolean flag = true;

    private SingleTon2() {
        if (flag) {
            flag = false;
            //code
        } else {
            throw new RuntimeException("对象已存在");
        }
    }

    public static SingleTon2 getInstance() {
        if (singleTon == null) {
            synchronized (SingleTon2.class) {
                if (singleTon == null) {
                    singleTon = new SingleTon2();
                }
            }
        }
        return singleTon;
    }

    /**
     * 防止克隆攻击
     * @return
     * @throws CloneNotSupportedException
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return getInstance();
    }

    //    public static boolean getFlag() {
    //        return flag;
    //    }
    //
    //    public static void setFlag(boolean flag) {
    //        SingleTon.flag = flag;
    //    }

    /**
     * 防止序列化攻击
     * @return
     */
    private Object readResolve() {
        return getInstance();
    }
}
