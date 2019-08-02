package org.quickstart.reflections.example.apimapping;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.quickstart.reflections.example.apimapping.model.DataRestfulApi;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019-08-02 12:44
 */
public class DataRestfulApiMappingTest {

  @Test
  public void getDataRestfulApi() {
    List<String> lst = new ArrayList<String>();
    lst.add("org.quickstart.reflections.example.apimapping.api");

    DataRestfulApiMapping data = new DataRestfulApiMapping(lst);

    DataRestfulApi api = data.getDataRestfulApi("/app/addAopApp", "POST");
    System.out.println("api:" + api);

    // DataRestfulApi api = data.getDataRestfulApi("/ability/addAbility", "post");

    // System.out.println(api);
    // Class<?> cls = api.getMappingCls();
    // Field[] dfield=cls.getDeclaredFields();

  }
}
