package org.quickstart.design.pattern.singleton;

import java.io.Serializable;

/**
 * <p>描述: [功能描述] </p >
 *
 * @author yangzl
 * @date 2020/8/21 11:36
 * @version v1.0
 */
public enum SingleEnum implements Cloneable, Serializable {
    INSTANCE;

    private String name;

    public SingleEnum getInstance(){
        System.out.println(this == INSTANCE); // true
        return INSTANCE;
    }

    public static void main(String[] args) {
        SingleEnum singleEnum = SingleEnum.INSTANCE;
        singleEnum.name = "枚举";
        System.out.println(singleEnum.name); // 枚举
        System.out.println(singleEnum.getInstance());  // true INSTANCE
    }
}
