package org.quickstart.javase.jdk8.stream;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019-08-08 19:52
 */
public class Person {
  private Long id;

  private String name;

  public Person(Long id, String name) {
    this.id = id;
    this.name = name;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "Person{" + "id=" + id + ", name='" + name + '\'' + '}';
  }

  // 重写Person对象的equals()方法和hashCode()方法:

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;

    Person person = (Person) o;

    if (!id.equals(person.id))
      return false;
    return name.equals(person.name);

  }

  @Override
  public int hashCode() {
    int result = id.hashCode();
    result = 31 * result + name.hashCode();
    return result;
  }

}
