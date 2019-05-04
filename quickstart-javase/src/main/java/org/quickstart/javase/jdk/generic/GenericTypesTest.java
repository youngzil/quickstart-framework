/**
 * 项目名称：quickstart-javase 文件名：GenericTypesTest.java 版本信息： 日期：2018年11月22日 Copyright asiainfo
 * Corporation 2018 版权所有 *
 */
package org.quickstart.javase.jdk.generic;

import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.formula.functions.T;

/**
 * GenericTypesTest
 *
 * @author：youngzil@163.com
 * @2018年11月22日 下午8:19:19
 * @since 1.0
 */
public class GenericTypesTest {


  //Lev 1
  class Food {

  }

  //Lev 2
  class Fruit extends Food {

  }

  class Meat extends Food {

  }

  //Lev 3
  class Apple extends Fruit {

  }

  class Banana extends Fruit {

  }

  class Pork extends Meat {

  }

  class Beef extends Meat {

  }

  //Lev 4
  class RedApple extends Apple {

  }

  class GreenApple extends Apple {

  }

  class Plate<T> {

    private T item;

    public Plate(T t) {
      item = t;
    }

    public void set(T t) {
      item = t;
    }

    public T get() {
      return item;
    }
  }

  //4.1 上界<? extends T>不能往里存，只能往外取
  //  <? extends Fruit>会使往盘子里放东西的set( )方法失效。但取东西get( )方法还有效。比如下面例子里两个set()方法，插入Apple和Fruit都报错。

  void test2() {

    Plate<? extends Fruit> p = new Plate<Apple>(new Apple());
    //不能存入任何元素
    p.set(new Fruit());    //Error
    p.set(new Apple());    //Error

//读取出来的东西只能存放在Fruit或它的基类里。
    Fruit newFruit1 = p.get();
    Food newFruit2 = p.get();
    Object newFruit3 = p.get();
    Apple newFruit4 = p.get();    //Error

    //4.2 下界<? super T>不影响往里存，但往外取只能放在Object对象里
    //使用下界<? super Fruit>会使从盘子里取东西的get( )方法部分失效，只能存放到Object对象里。set( )方法正常。
    Plate<? super Fruit> p2 = new Plate<Fruit>(new Fruit());
//存入元素正常
    p2.set(new Fruit());
    p2.set(new Apple());
//读取出来的东西只能存放在Object类里。
    Apple newFruit5 = p2.get();    //Error
    Fruit newFruit6 = p2.get();    //Error
    Object newFruit7 = p2.get();


  }


  class Super {

  }

  class Self extends Super {

  }

  class Son extends Self {

  }

  void test() {
    List<? extends Self> a = new ArrayList<>();// 参数类型上界是Self
    //a.add(new Son());// error 不能放入任何类型，因为编译器只知道a中应该放入Self的某个子类，但具体放哪种子类它并不知道，因此，除了null以外，不能放入任何类型
    //a.add(new Self());// error
    //a.add(new Super());// error
    a.add(null);// error
    Self s1 = a.get(
        0); // 返回类型是确定的Self类，因为<? extends T> 只能用于方法返回，告诉编译器此返参的类型的最小继承边界为T，T和T的父类都能接收，但是入参类型无法确定，只能接受null的传入
    Super s2 = a.get(0); // Self类型可以用Super接收
    //Son s3 = a.get(0); // error:子类不能接收父类型参数

    // --------------------------------------

    List<? super Self> b = new ArrayList<>();// 参数类型下界是Self
    b.add(new Son());// ok 只能放入T类型，且满足T类型的超类至少是Self，换句话说，就是只能放入Self的子类型
    b.add(new Self());// ok 本身类型也可以
    b.add(new Super());// ok 超类不可以
    b.add(null);// ok
    Object o1 = b.get(0);// 返回类型是未知的， 因为<? super T>只能用于限定方法入参，告诉编译器入参只能是T或其子类型，而返参只能用Object类接收
    Son o2 = b.get(0);// error
    Self o3 = b.get(0);// error
    Super o4 = b.get(0);// error

    List<?> c = new ArrayList<>();
    // 总结：
    // 1. <? extends T> 只能用于方法返回，告诉编译器此返参的类型的最小继承边界为T，T和T的父类都能接收，但是入参类型无法确定，只能接受null的传入
    // 2. <? super T>只能用于限定方法入参，告诉编译器入参只能是T或其子类型，而返参只能用Object类接收
    // 3. ? 既不能用于入参也不能用于返参

    // <? extends T>限定参数类型的上界：参数类型必须是T或T的子类型
    // <? super T> 限定参数类型的下界：参数类型必须是T或T的超类型
  }

}
