package org.quickstart.yaml.snakeyaml;

import java.io.InputStream;

import org.quickstart.yaml.snakeyaml.model.SnakeyamlModel;
import org.yaml.snakeyaml.Yaml;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019-08-02 18:18
 */
public class SnakeyamlTest {

  public static void main(String[] args) {
    Yaml yaml = new Yaml();
    InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("snakeyaml.yaml");
    SnakeyamlModel configturation = yaml.loadAs(in, SnakeyamlModel.class);
    System.out.println(configturation);

  }
}
