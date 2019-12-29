```
1、返回值是List，要返回空集合，不要返回null
2、返回值是对象Object，返回JDK8的Optional,或者使用guava的Optional.
3、入参：
    强制约束：通过jsr 303进行严格的约束声明，@NotNull
    文档性约束（弱提示）：jsr 305规范，给了我们一个描述接口入参的一个方式(需要引入库 com.google.code.findbugs:jsr305):可以使用注解: @Nullable @Nonnull @CheckForNull 进行接口说明
``` 

结论：
``` 
通过 空集合返回值,Optional,jsr 303，jsr 305这几种方式，可以让我们的代码可读性更强，出错率更低！

1、空集合返回值 ：如果有集合这样返回值时，除非真的有说服自己的理由，否则，一定要返回空集合，而不是null
2、Optional: 如果你的代码是jdk8，就引入它！如果不是，则使用Guava的Optional,或者升级jdk版本！它很大程度的能增加了接口的可读性！
3、jsr 303: 如果新的项目正在开发，不防加上这个试试！一定有一种特别爽的感觉!
4、jsr 305: 如果老的项目在你的手上，你可以尝试的加上这种文档型注解，有助于你后期的重构，或者新功能增加了，对于老接口的理解!
``` 


如果是讲数据库对象转为DTO，比如需求是将Person对象转化成PersonDTO

当然对于实际操作来讲，返回如果Person为空，将返回null,但是PersonDTO是不能返回null的（尤其Rest接口返回的这种DTO）。

解决办法：

- 1、创建一个实体特例实例
- 2、


```aidl
创建特例

static class NullPerson extends Person {
    @Override
    public String getAge() {
        return "";
    }

    @Override
    public String getName() {
        return "";

    }
}

 @Test
    public void shouldConvertDTO() {

        PersonDTO personDTO = new PersonDTO();

        Person person = getPerson();

        personDTO.setDtoAge(person.getAge());

        personDTO.setDtoName(person.getName());

    }

    private Person getPerson() {

        return new NullPerson();
        //如果Person是null ,则返回空对象

    }

//对于上述代码，还可以使用Optional进行优化。

    @Test
    public void shouldConvertDTO() {
        PersonDTO personDTO = new PersonDTO();

        Optional.ofNullable(getPerson()).ifPresent(person -> {
            personDTO.setDtoAge(person.getAge());
            personDTO.setDtoName(person.getName());
        });

    }

    private Person getPerson() {
        return null;
    }
```


使用示例

```aidl
使用Optional示例
public Optional<User> getOptional(Integer id) {

        return Optional.ofNullable(userRepository.selectByPrimaryKey(id));

    }
```
```aidl
返回空集合示例
public List<User> listUser() {

        List<User> userList = userListRepostity.selectByExample(new UserExample());

        if (CollectionUtils.isEmpty(userList)) {//spring util工具类
            return Lists.newArrayList();//guava类库提供的方式
        }
        return userList;
    }
```

```aidl
1.强制约束，我们可以通过jsr 303进行严格的约束声明：

public interface UserSearchService {

    /**
     * 根据用户id获取用户信息
     *
     * @param id 用户id
     * @return 用户实体
     */

    User get(@NotNull Integer id);

    /**
     * 根据用户id获取用户信息
     *
     * @param id 用户id
     * @return 用户实体, 此实体有可能是缺省值
     */

    Optional<User> getOptional(@NotNull Integer id);

}

当然，这样写，要配合AOP的操作进行验证，但让spring已经提供了很好的集成方案，在此就不在赘述了。
```

```aidl
2.文档性约束

在很多时候，我们会遇到遗留代码，对于遗留代码，整体性改造的可能性很小。

我们更希望通过阅读接口的实现，来进行接口的说明。

jsr 305规范，给了我们一个描述接口入参的一个方式(需要引入库 com.google.code.findbugs:jsr305):

可以使用注解: @Nullable @Nonnull @CheckForNull 进行接口说明。比如:
public interface UserSearchService {

    /**
     * 根据用户id获取用户信息
     *
     * @param id 用户id
     * @return 用户实体
     * @throws UserNotFoundException
     */

    @CheckForNull
    User get(@NonNull Integer id);

    /**
     * 根据用户id获取用户信息
     *
     * @param id 用户id
     * @return 用户实体, 此实体有可能是缺省值
     */

    Optional<User> getOptional(@NonNull Integer id);

}
```




参考
https://mp.weixin.qq.com/s/Uuyf3PXY2HyYS1O5w9l8cA


