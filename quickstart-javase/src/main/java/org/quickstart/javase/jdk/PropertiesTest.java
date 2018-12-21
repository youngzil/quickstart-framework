package org.quickstart.javase.jdk;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Properties;
import org.junit.Test;


public class PropertiesTest {

  private static String config = "src/main/resources/config.properties";

  @Test
  public void testWrite() throws Exception {
    Properties prop = new Properties();
    OutputStream output = null;

    try {

      output = new FileOutputStream(config);

      // set the properties value
      prop.setProperty("database", "localhost");
      prop.setProperty("dbuser", "mkyong");
      prop.setProperty("dbpassword", "password");

      // save properties to project root folder
      prop.store(output, null);

    } catch (IOException io) {
      io.printStackTrace();
    } finally {
      if (output != null) {
        try {
          output.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }

    }
  }

  //Load a properties file from the file system and retrieved the property value.
  @Test
  public void testLoadFromFileSystem() throws Exception {

    Properties prop = new Properties();
    InputStream input = null;

    try {

      input = new FileInputStream(config);

      // load a properties file
      prop.load(input);

      // get the property value and print it out
      System.out.println(prop.getProperty("database"));
      System.out.println(prop.getProperty("dbuser"));
      System.out.println(prop.getProperty("dbpassword"));

    } catch (IOException ex) {
      ex.printStackTrace();
    } finally {
      if (input != null) {
        try {
          input.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }

  }


  //3. Load a properties file from classpath
  @Test
  public void testLoadFromClasspath() throws Exception {
    Properties prop = new Properties();
    InputStream input = null;

    try {

      String filename = "config.properties";
      input = PropertiesTest.class.getClassLoader().getResourceAsStream(filename);
      if (input == null) {
        System.out.println("Sorry, unable to find " + filename);
        return;
      }

      //load a properties file from class path, inside static method
      prop.load(input);

      //get the property value and print it out
      System.out.println(prop.getProperty("database"));
      System.out.println(prop.getProperty("dbuser"));
      System.out.println(prop.getProperty("dbpassword"));

    } catch (IOException ex) {
      ex.printStackTrace();
    } finally {
      if (input != null) {
        try {
          input.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }


  //4. Prints everything from a properties file
  @Test
  public void testPropertiesPrints() throws Exception {
    Properties prop = new Properties();
    InputStream input = null;

    try {

      String filename = "config.properties";
      input = getClass().getClassLoader().getResourceAsStream(filename);
      if (input == null) {
        System.out.println("Sorry, unable to find " + filename);
        return;
      }

      prop.load(input);

      Enumeration<?> e = prop.propertyNames();
      while (e.hasMoreElements()) {
        String key = (String) e.nextElement();
        String value = prop.getProperty(key);
        System.out.println("Key : " + key + ", Value : " + value);
      }

    } catch (IOException ex) {
      ex.printStackTrace();
    } finally {
      if (input != null) {
        try {
          input.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }

}

