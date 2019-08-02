package org.quickstart.reflections.sample;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Set;
import java.util.regex.Pattern;

import javax.ws.rs.PathParam;

import org.junit.Test;
import org.reflections.Reflections;

import com.google.inject.Module;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019-08-02 13:44
 */
public class ReflectionsTest {

  @Test
  public void testReflections() throws IllegalAccessException, InstantiationException {
    Reflections reflections = new Reflections("org.quickstart.reflections.sample");
    Set<Class<? extends UserService>> classes = reflections.getSubTypesOf(UserService.class);

    for (Class clazz : classes) {
      // logger.info(clazz.getName());
      System.out.println("Found: " + clazz.getName());

      UserService userService = (UserService) clazz.newInstance();
      String str = userService.getUser();
      System.out.println("str=" + str);

    }

  }

  @Test
  public void testReflections2() {
    // 假如想扫描整个工程的类，直接new一个不带参数的Reflections就好。值得一提的是，这东西在扫描的时候，连依赖的jar包都不放过。以Set为例：
    // Reflections reflections = new Reflections();//报错
    Reflections reflections = new Reflections("org.quickstart.reflections.sample");
    Set<Class<? extends Set>> classes = reflections.getSubTypesOf(Set.class);

    for (Class clazz : classes) {
      // logger.info(clazz.getName());
      System.out.println("Found: " + clazz.getName());
    }
  }

  @Test
  public void testReflectionsAnnotation() {
    // Reflections reflections = new Reflections();//报错
    Reflections reflections = new Reflections("org.quickstart.reflections.sample");
    Set<Class<?>> classes = reflections.getTypesAnnotatedWith(ReflectionsAnnotation.class);

    for (Class clazz : classes) {
      // logger.info(clazz.getName());
      System.out.println("Found: " + clazz.getName());
    }

  }

  @Test
  public void testReflectionsExample() {

    Reflections reflections = new Reflections("org.quickstart.reflections.sample");

    // SubTypesScanner
    Set<Class<? extends Module>> modules = reflections.getSubTypesOf(com.google.inject.Module.class);
    // TypeAnnotationsScanner
    Set<Class<?>> singletons = reflections.getTypesAnnotatedWith(javax.inject.Singleton.class);
    // ResourcesScanner
    Set<String> properties = reflections.getResources(Pattern.compile(".*\\.properties"));
    // MethodAnnotationsScanner
    Set<Method> resources = reflections.getMethodsAnnotatedWith(javax.ws.rs.Path.class);
    Set<Constructor> injectables = reflections.getConstructorsAnnotatedWith(javax.inject.Inject.class);
    // FieldAnnotationsScanner
    Set<Field> ids = reflections.getFieldsAnnotatedWith(javax.persistence.Id.class);
    // MethodParameterScanner
    Set<Method> someMethods = reflections.getMethodsMatchParams(long.class, int.class);
    Set<Method> voidMethods = reflections.getMethodsReturn(void.class);
    Set<Method> pathParamMethods = reflections.getMethodsWithAnyParamAnnotated(PathParam.class);
    // MethodParameterNamesScanner
    // List<String> parameterNames = reflections.getMethodParamNames(.Method.class);
    // MemberUsageScanner
    // Set<Member> usages = reflections.getMethodUsages(Method.class);
  }

  @Test
  public void testReflectionsExample2() {

    // Set<Method> getters = getAllMethods(someClass, withModifier(Modifier.PUBLIC), withPrefix("get"), withParametersCount(0));

    // or
    // Set<Method> listMethodsFromCollectionToBoolean = getAllMethods(List.class, withParametersAssignableTo(Collection.class), withReturnType(boolean.class));

    // Set<Field> fields = getAllFields(SomeClass.class, withAnnotation(annotation), withTypeAssignableTo(type));

  }

}
