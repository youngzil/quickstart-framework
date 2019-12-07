/**
 * 
 */
package org.quickstart.reflections.example.apimapping;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.quickstart.reflections.example.apimapping.annotation.Controller;
import org.quickstart.reflections.example.apimapping.annotation.CookieValue;
import org.quickstart.reflections.example.apimapping.annotation.RequestBody;
import org.quickstart.reflections.example.apimapping.annotation.RequestHeader;
import org.quickstart.reflections.example.apimapping.annotation.RequestMapping;
import org.quickstart.reflections.example.apimapping.annotation.RequestMethod;
import org.quickstart.reflections.example.apimapping.annotation.RequestParam;
import org.quickstart.reflections.example.apimapping.exception.MethodNotAllowedException;
import org.quickstart.reflections.example.apimapping.exception.NotFoundException;
import org.quickstart.reflections.example.apimapping.model.DataRestfulApi;
import org.quickstart.reflections.example.apimapping.model.ReqParam;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.scanners.MethodParameterNamesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zh
 * @date 2019-05-21 17:14
 * 
 */
public class DataRestfulApiMapping {

  private static final Logger logger = LoggerFactory.getLogger(DataRestfulApiMapping.class);

  private final List<String> packages;

  private final Map<String, DataRestfulApi> mapping;

  public Map<String, DataRestfulApi> getMapping() {
    return mapping;
  }

  public DataRestfulApiMapping(List<String> packages) {
    this.packages = packages;
    this.mapping = new HashMap<String, DataRestfulApi>();
    init();
  }

  public void init() {

    if (packages == null || packages.size() == 0) {
      logger.warn("No data protocol package configured.");
    }

    ConfigurationBuilder config = new ConfigurationBuilder();
    config.filterInputsBy(new FilterBuilder().includePackage(packages.toArray(new String[0])));
    config.setUrls(ClasspathHelper.forPackage("org.quickstart.reflections.example.apimapping.api"));
    config.setScanners(new SubTypesScanner(false), new TypeAnnotationsScanner(), new MethodAnnotationsScanner(), new MethodParameterNamesScanner());
    Reflections reflections = new Reflections(config);
    Set<Class<?>> claz = reflections.getTypesAnnotatedWith(Controller.class);
    DataRestfulApi data = null;
    for (Class<?> cls : claz) {

      RequestMapping requestMapping = (RequestMapping) cls.getAnnotation(RequestMapping.class);
      // 类级别请求路径
      String[] path = requestMapping.value();
      // 方法级别
      Method[] method = cls.getDeclaredMethods();
      for (Method m : method) {

        // 获得方法注解
        Annotation[] annotations = m.getAnnotations();
        if (annotations.length == 0) {
          continue;
        }
        data = new DataRestfulApi();
        data.setMappingCls(cls);
        data.setMethod(m.getName());

        String mapKey = "";
        for (Annotation annotation : annotations) {
          // 方法注解判断
          if (annotation instanceof RequestMapping) {
            RequestMapping requestMappingChild = (RequestMapping) annotation;
            String[] pathChild = requestMappingChild.value();
            mapKey = combinationUrl(path, pathChild);
            data.setRequestUrl(mapKey);
            RequestMethod[] requestMehtod = requestMappingChild.method();
            data.setRequestMethod(combinationMethod(requestMehtod));
          }
        }
        // 获得方法参数
        List<ReqParam> lst = parsingMethod(m);
        data.setReqParams(lst);
        Class<?>[] params = m.getParameterTypes();
        data.setParamsCls(params);
        mapping.put(mapKey, data);

      }

    }

  }

  public DataRestfulApi getDataRestfulApi(String reqPath, String method) {
    DataRestfulApi result = null;

    for (Map.Entry<String, DataRestfulApi> entry : mapping.entrySet()) {
      String path = entry.getKey();
      // 多个连接
      if (path.indexOf(",") > 0) {
        String[] pathArray = StringUtils.split(path, ",");
        for (int i = 0; i < pathArray.length; i++) {
          if (pathArray[i].equals(reqPath)) {
            result = entry.getValue();
            if (StringUtils.containsIgnoreCase(result.getRequestMethod(), method)) {
              return result;
            } else {
              throw new MethodNotAllowedException();
            }
          }
        }
      } else {
        if (reqPath.equals(path)) {
          result = entry.getValue();
          if (StringUtils.containsIgnoreCase(result.getRequestMethod(), method)) {
            return result;
          } else {
            throw new MethodNotAllowedException();
          }
        }

      }

    }

    if (result == null) {
      throw new NotFoundException();
    }
    return result;
  }

  private String combinationUrl(String[] parentUrl, String[] childUrl) {
    Set<String> url = new HashSet();
    if (parentUrl == null && childUrl != null) {
      url = new HashSet(Arrays.asList(childUrl));
    } else {
      if (parentUrl.length == 0 && parentUrl[0].length() == 1) {
        url = new HashSet(Arrays.asList(childUrl));
      } else if (parentUrl.length == 0 && parentUrl[0].length() > 1) {
        for (int i = 0; i < childUrl.length; i++) {
          url.add(parentUrl[0] + childUrl[i]);
        }
      } else {

        for (int x = 0; x < parentUrl.length; x++) {
          for (int y = 0; y < childUrl.length; y++) {
            url.add(parentUrl[x] + childUrl[y]);

          }

        }

      }

    }
    return StringUtils.join(url, ",");
  }

  private List<ReqParam> parsingMethod(Method method) {

    List<ReqParam> lst = new ArrayList<ReqParam>();
    ReqParam reqParam = null;
    Parameter[] parameters = method.getParameters();
    for (Parameter parameter : parameters) {
      reqParam = new ReqParam();
      reqParam.setParamName(parameter.getName());
      // 获得参数类型
      reqParam.setParamType(parameter.getType());
      Annotation[] pa = parameter.getAnnotations();
      for (Annotation aan : pa) {

        if (aan instanceof RequestParam) {
          RequestParam param = (RequestParam) aan;
          reqParam.setAnnotitaionType(RequestParam.class);
          reqParam.setRequired(param.required());
          reqParam.setAnnotitaionValue(param.value());

        } else if (aan instanceof CookieValue) {
          CookieValue cookieValue = (CookieValue) aan;
          reqParam.setAnnotitaionType(CookieValue.class);
          reqParam.setRequired(cookieValue.required());
          reqParam.setAnnotitaionValue(cookieValue.value());

        }

        else if (aan instanceof RequestHeader) {
          RequestHeader header = (RequestHeader) aan;
          reqParam.setAnnotitaionType(RequestHeader.class);
          reqParam.setRequired(header.required());
          reqParam.setAnnotitaionValue(header.value());

        }

        else if (aan instanceof RequestBody) {
          RequestBody body = (RequestBody) aan;
          reqParam.setAnnotitaionType(RequestBody.class);
          reqParam.setRequired(body.required());
          reqParam.setAnnotitaionValue(null);

        } else {

        }

        lst.add(reqParam);

      }

    }
    return lst;

  }

  private String combinationMethod(RequestMethod[] methods) {
    Set<String> method = new HashSet<>();
    for (int i = 0; i < methods.length; i++) {
      RequestMethod value = methods[i];
      method.add(value.name());
    }
    return StringUtils.join(method, ",");
  }

}
