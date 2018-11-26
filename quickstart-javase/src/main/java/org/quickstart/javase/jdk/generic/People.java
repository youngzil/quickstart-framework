package org.quickstart.javase.jdk.generic;

/**
 * Created by yanglu on 2016/10/18.
 */
public class People<T extends  Fruit> {

    public void eatFruit(T t){
        t.eat();
    }
}
