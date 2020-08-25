package org.quickstart.lombok.lombok;

import lombok.Builder;

/**
 * <p>描述: [功能描述] </p >
 *
 * @author yangzl
 * @date 2020/8/25 14:59
 * @version v1.0
 */
@Builder
public class Student {
    private String name;
    private int age;

    public static void main(String[] args) {
        Student student = Student.builder().name("zs").age(24).build();
    }
}
