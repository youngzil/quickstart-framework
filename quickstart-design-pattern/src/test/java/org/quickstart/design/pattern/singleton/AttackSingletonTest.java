package org.quickstart.design.pattern.singleton;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * <p>描述: [功能描述] </p >
 *
 * @author yangzl
 * @date 2020/8/21 19:50
 * @version v1.0
 */
public class AttackSingletonTest {

    @Test
    public void testAttack() throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException,
        NoSuchMethodException, InvocationTargetException {

        System.out.println("枚举实验");
        SingleEnum singleEnum1 = SingleEnum.INSTANCE;

        System.out.println("直接获取");
        SingleEnum singleEnum2 = SingleEnum.INSTANCE;
        System.out.println(singleEnum1 == singleEnum2); // true

        System.out.println("枚举克隆攻击通过了吗？");
        System.out.println("枚举无法克隆");

        System.out.println("枚举序列化攻击通过了吗？");
        File enumTxt = new File("enumTest.txt");
        //序列化
        FileOutputStream fosEnum = new FileOutputStream(enumTxt);
        ObjectOutputStream oosEnum = new ObjectOutputStream(fosEnum);
        oosEnum.writeObject(singleEnum1);
        oosEnum.flush();
        oosEnum.close();
        fosEnum.close();
        //反序列化
        FileInputStream fisEnum = new FileInputStream(enumTxt);
        ObjectInputStream oisEnum = new ObjectInputStream(fisEnum);
        SingleEnum singleEnum3 = (SingleEnum)oisEnum.readObject();
        fisEnum.close();
        oisEnum.close();
        System.out.println(singleEnum1 == singleEnum3); // true

        System.out.println("枚举反射攻击通过了吗？");
        Class enumClass = singleEnum1.getClass();
        /*
        java.lang.InstantiationException
        Caused by: java.lang.NoSuchMethodException: SingleEnum.<init>()
         */
        //stop run
        SingleEnum singleEnum5 = (SingleEnum)enumClass.newInstance();
        System.out.println(singleEnum1 == singleEnum5);

        //stop run
        Constructor enumConstructor = SingleEnum.class.getConstructor(); // java.lang.NoSuchMethodException
        enumConstructor.setAccessible(true);

        SingleEnum singleEnum4 = (SingleEnum)enumConstructor.newInstance();
        System.out.println(singleEnum1 == singleEnum4);

    }

}
