package org.quickstart.javase.jdk8.stream;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2020/5/8 17:56
 */
public enum GenderColumn {

    BOY(0),GIRL(1),LADYBOY(2);

    private int code;

    GenderColumn() {}

    GenderColumn(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
