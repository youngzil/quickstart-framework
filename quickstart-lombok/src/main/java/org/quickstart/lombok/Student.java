package org.quickstart.lombok;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>描述: [功能描述] </p >
 *
 * @author yangzl
 * @date 2020/8/25 14:49
 * @version v1.0
 */
@Accessors(chain = true)
@Setter
@Getter
//@RequiredArgsConstructor(staticName = "ofName")
@RequiredArgsConstructor(staticName = "of")
public class Student {
    @NonNull
    private String name;
    private int age;

    //    必传参数的构造方法，使用 Lombok 将更改成如下写法（@RequiredArgsConstructor 和 @NonNull）

    public static void main(String[] args) {

        //Student student = Student.ofName("zs")//
        Student student = Student.of("zs")//
            .setAge(24)//
            .setName("zs");

    }

}
