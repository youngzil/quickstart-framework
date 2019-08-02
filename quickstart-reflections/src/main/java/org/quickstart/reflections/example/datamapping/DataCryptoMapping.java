package org.quickstart.reflections.example.datamapping;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019-07-30 08:57
 */
public class DataCryptoMapping {

  private static final Logger logger = LoggerFactory.getLogger(DataCryptoMapping.class);

  private final List<String> packages;

  private final Map<String, DataCryptoHolder> dataCryptoMapping;

  public DataCryptoMapping(List<String> packages) {
    this.packages = packages;
    this.dataCryptoMapping = new HashMap();
    init();
  }

  private void init() {
    if (packages == null || packages.size() == 0) {
      logger.warn("No data crypto package configured.");
    }
    Set<Class<?>> clazzSet = getSubClasses(packages, DataCryptoHolder.class);
    for (Class clazz : clazzSet) {
      try {
        DataCryptoHolder dataAccessProtocol = (DataCryptoHolder) clazz.newInstance();
        dataCryptoMapping.put(dataAccessProtocol.getName(), dataAccessProtocol);
      } catch (Exception e) {
        logger.error("Initializes crypto class exception", e);
      }
    }
  }

  private Set<Class<?>> getSubClasses(Collection<String> packages, Class interfaceClass) {

    ConfigurationBuilder config = new ConfigurationBuilder();
    config.filterInputsBy(new FilterBuilder().includePackage(packages.toArray(new String[0])));
    config.setUrls(ClasspathHelper.forPackage("org.quickstart.reflections.example"));
    config.setScanners(new SubTypesScanner());

    // config.setScanners(new SubTypesScanner(false), new TypeAnnotationsScanner(), new MethodAnnotationsScanner(), new MethodParameterNamesScanner());

    Reflections reflections = new Reflections(config);
    // Set<Class<?>> annotatedClazz = reflections.getTypesAnnotatedWith(annotationClass);
    Set<Class<?>> subClazz = reflections.getSubTypesOf(interfaceClass);
    return subClazz;
  }

  public DataCryptoHolder getDataCryptoHolder(String encryptMethod) {
    DataCryptoHolder result = dataCryptoMapping.get(encryptMethod);

    if (result == null) {
      logger.error("No data crypto implement for {} algorithm found!", encryptMethod);
    }
    return result;
  }

}
