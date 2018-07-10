Jackson /常用注解/ annotation（转）

1、@JsonAutoDetect

自动检测，（作用在类上）来开启/禁止自动检测。

fieldVisibility:字段的可见级别   

ANY:任何级别的字段都可以自动识别  

NONE:所有字段都不可以自动识别   

NON_PRIVATE:非private修饰的字段可以自动识别  

PROTECTED_AND_PUBLIC:被protected和public修饰的字段可以被自动识别   

PUBLIC_ONLY:只有被public修饰的字段才可以被自动识别     

DEFAULT:

 

jackson默认的字段属性发现规则如下：

所有被public修饰的字段->所有被public修饰的getter->所有被public修饰的setter）

2、@JsonIgnore

作用在字段或方法上，用来完全忽略被注解的字段和方法对应的属性.

 

3、@JsonProperty

作用在字段或方法上，用来对属性的序列化/反序列化，可以用来避免遗漏属性，同时提供对属性名称重命名
4、@JsonIgnoreProperties

作用在类上，用来说明有些属性在序列化/反序列化时需要忽略掉，可以将它看做是@JsonIgnore的批量操作，但它的功能比@JsonIgnore要强，比如一个类是代理类，我们无法将将@JsonIgnore标记在属性或方法上，此时便可用@JsonIgnoreProperties标注在类声明上，它还有一个重要的功能是作用在反序列化时解析字段时过滤一些未知的属性，否则通常情况下解析到我们定义的类不认识的属性便会抛出异常。

可以注明是想要忽略的属性列表如@JsonIgnoreProperties({"name","age","title"})，

也可以注明过滤掉未知的属性如@JsonIgnoreProperties(ignoreUnknown=true)

5、@JsonUnwrapped

作用在属性字段或方法上，用来将子JSON对象的属性添加到封闭的JSON对象。

 

6、@JsonIdentityInfo

2.0+版本新注解，作用于类或属性上，被用来在序列化/反序列化时为该对象或字段添加一个对象识别码，通常是用来解决循环嵌套的问题
 

 

 

7、@JsonNaming

 

jackson 2.1+版本的注解，作用于类或方法，注意这个注解是在jackson-databind包中而不是在jackson-annotations包里，它可以让你定制属性命名策略，作用和前面提到的@JsonProperty的重命名属性名称相同。

 

8、@JsonTypeInfo

作用于类/接口，被用来开启多态类型处理，对基类/接口和子类/实现类都有效

9、@JsonSubTypes

作用于类/接口，用来列出给定类的子类，只有当子类类型无法被检测到时才会使用它，一般是配合@JsonTypeInfo在基类上使用

10、@JsonTypeName
作用于子类，用来为多态子类指定类型标识符的值

11、@JsonTypeResolver和@JsonTypeIdResoler

 

作用于类，可以自定义多态的类型标识符，这个平时很少用到，主要是现有的一般就已经满足绝大多数的需求了，如果你需要比较特别的类型标识符，建议使用这2个注解，自己定制基于TypeResolverBuilder和TypeIdResolver的类即可

12、@JsonSerialize和@JsonDeserialize

作用于方法和字段上，通过 using(JsonSerializer)和using(JsonDeserializer)来指定序列化和反序列化的实现，通常我们在需要自定义序列化和反序列化时会用到，比如下面的例子中的日期转换

13、@JsonPropertyOrder

作用在类上，被用来指明当序列化时需要对属性做排序，它有2个属性
 

一个是alphabetic：布尔类型，表示是否采用字母拼音顺序排序，默认是为false，即不排序

14、JsonView

视图模板，作用于方法和属性上，用来指定哪些属性可以被包含在JSON视图中
15、@JsonFilter

Json属性过滤器，作用于类，作用同上面的@JsonView，都是过滤掉不想要的属性，输出自己想要的属性。和@FilterView不同的是@JsonFilter可以动态的过滤属性，比如我不想输出以system开头的所有属性

16、@JsonIgnoreType

作用于类，表示被注解该类型的属性将不会被序列化和反序列化
17、@JsonAnySetter

作用于方法，在反序列化时用来处理遇到未知的属性的时候调用，在本文前面我们知道可以通过注解@JsonIgnoreProperties(ignoreUnknown=true)来过滤未知的属性，但是如果需要这些未知的属性
18、@JsonCreator

 

作用于方法，通常用来标注构造方法或静态工厂方法上，使用该方法来构建实例，默认的是使用无参的构造方法，通常是和@JsonProperty或@JacksonInject配合使用

19、@JacksonInject

作用于属性、方法、构造参数上，被用来反序列化时标记已经被注入的属性
20、@JsonPOJOBuilder

 

作用于类，用来标注如何定制构建对象，使用的是builder模式来构建，比如Value v = new ValueBuilder().withX(3).withY(4).build();这种就是builder模式来构建对象，通常会喝@JsonDeserialize.builder来配合使用。