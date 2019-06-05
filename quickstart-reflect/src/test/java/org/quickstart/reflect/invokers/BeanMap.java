/**
 * 项目名称：quickstart-reflect 
 * 文件名：BeanMap.java
 * 版本信息：
 * 日期：2018年11月12日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.reflect.invokers;
import java.beans.Introspector;  
import java.beans.PropertyDescriptor;  
import java.io.Serializable;  
import java.lang.reflect.Method;  
import java.util.AbstractMap;  
import java.util.AbstractSet;  
import java.util.Iterator;  
import java.util.Map;  
import java.util.Set;  
import java.util.TreeMap;  
import java.util.WeakHashMap;  
/**
 * BeanMap 
 *  
 * @author：youngzil@163.com
 * @2018年11月12日 下午11:04:27 
 * @since 1.0
 */
/** 
 * 自己实现的一个BeanMap，使用Invokers调用，最大限度提高性能 
 *  
 *  
 */  
public class BeanMap extends AbstractMap<String, Object> implements  
        Serializable {  
    public static interface Getter {  
        Object get(Object obj);  
    }  
  
    public static interface Setter {  
        void set(Object obj, Object value);  
    }  
  
    public class PropertyMeta {  
        PropertyDescriptor propertyDescriptor;  
        String name;  
        Getter reader;  
        Setter writer;  
        Class<?> type;  
        private boolean hasReader;  
        private boolean hasWriter;  
  
        PropertyMeta(PropertyDescriptor propertyDescriptor) {  
            this.propertyDescriptor = propertyDescriptor;  
        }  
  
        public String getName() {  
            if (name == null) {  
                name = propertyDescriptor.getName();  
            }  
            return name;  
        }  
  
        public Getter getReader() {  
            if (reader == null && !hasReader) {  
                Method method = propertyDescriptor.getReadMethod();  
                if (method != null) {  
                    reader = Invokers.newInvoker(Getter.class,  
                            method.getDeclaringClass(), method.getName(),  
                            method.getParameterTypes(), method.getReturnType());  
                }  
                hasReader = true;  
            }  
            return reader;  
        }  
  
        public Setter getWriter() {  
            if (writer == null && !hasWriter) {  
                Method method = propertyDescriptor.getWriteMethod();  
                if (method != null) {  
                    writer = Invokers.newInvoker(Setter.class,  
                            method.getDeclaringClass(), method.getName(),  
                            method.getParameterTypes(), method.getReturnType());  
                }  
                hasWriter = true;  
            }  
            return writer;  
        }  
  
        public Class<?> getPropertyType() {  
            if (type == null) {  
                type = propertyDescriptor.getPropertyType();  
            }  
            return type;  
        }  
  
    }  
  
    public interface PropertyEntry<K, V> extends Entry<K, V> {  
        public PropertyMeta getProperty();  
  
        public Class<?> getType();  
  
        public boolean canRead();  
  
        public boolean canWrite();  
    }  
  
    /** 
     * 类字段映射 
     */  
    private static final Map<Class<?>, Map<String, PropertyMeta>> classBeanMap = new WeakHashMap<Class<?>, Map<String, PropertyMeta>>();  
    /** 
     *  
     */  
    private static final long serialVersionUID = -3627407279602086245L;  
  
    final private Object source;  
  
    final private Class<?> sourceClass;  
  
    private Map<String, PropertyMeta> propertyMetaMap;  
  
    public BeanMap(Object source) {  
        if (source == null) {  
            throw new NullPointerException("The source object should't be null");  
        }  
        this.source = source;  
        this.sourceClass = source.getClass();  
        generateBeanMap();  
    }  
  
    public Set<java.util.Map.Entry<String, Object>> entrySet() {  
        final Map<String, PropertyMeta> propertyMap = propertyMetaMap;  
        Set<java.util.Map.Entry<String, Object>> entrySet = new AbstractSet<Map.Entry<String, Object>>() {  
            public Iterator<java.util.Map.Entry<String, Object>> iterator() {  
                final Iterator<PropertyMeta> propertyIterator = propertyMap  
                        .values().iterator();  
                return new Iterator<java.util.Map.Entry<String, Object>>() {  
                    public boolean hasNext() {  
                        return propertyIterator.hasNext();  
                    }  
  
                    public java.util.Map.Entry<String, Object> next() {  
                        final PropertyMeta property = propertyIterator.next();  
                        return new PropertyEntry<String, Object>() {  
                            public String getKey() {  
                                return property.getName();  
                            }  
  
                            public Object getValue() {  
                                try {  
                                    Getter read = property.getReader();  
                                    Object value = read == null ? null : read  
                                            .get(source);  
                                    return value;  
                                } catch (Exception e) {  
                                    throw wrapCause(e);  
                                }  
                            }  
  
                            public Object setValue(Object value) {  
                                try {  
                                    Setter write = property.getWriter();  
                                    Getter read = property.getReader();  
                                    Object old = read == null ? null : read  
                                            .get(source);  
                                    if (write != null) {  
                                        write.set(source, value);  
                                    }  
                                    return old;  
                                } catch (Throwable e) {  
                                    throw wrapCause(e);  
                                }  
                            }  
  
                            public Class<?> getType() {  
                                return property.getPropertyType();  
                            }  
  
                            public boolean canWrite() {  
                                return property.getWriter() != null;  
                            }  
  
                            public PropertyMeta getProperty() {  
                                return property;  
                            }  
  
                            public boolean canRead() {  
                                return property.getReader() != null;  
                            }  
                        };  
                    }  
  
                    public void remove() {  
                        throw new UnsupportedOperationException();  
                    }  
                };  
            }  
  
            public int size() {  
                return propertyMap.size();  
            }  
        };  
        return entrySet;  
    }  
  
    public Map<String, java.util.Map.Entry<String, Object>> entryMap() {  
        final Map<String, PropertyMeta> propertyMap = propertyMetaMap;  
        return new AbstractMap<String, Map.Entry<String, Object>>() {  
  
            public int size() {  
                return propertyMap.size();  
            }  
  
            public boolean containsKey(Object key) {  
                return propertyMap.containsKey(key);  
            }  
  
            public Entry<String, Object> get(Object key) {  
                final PropertyMeta property = propertyMap.get(key);  
                return new PropertyEntry<String, Object>() {  
                    public String getKey() {  
                        return property.getName();  
                    }  
  
                    public Object getValue() {  
                        try {  
                            Getter read = property.getReader();  
                            Object value = read == null ? null : read  
                                    .get(source);  
                            return value;  
                        } catch (Exception e) {  
                            throw wrapCause(e);  
                        }  
                    }  
  
                    public Object setValue(Object value) {  
                        try {  
                            Setter write = property.getWriter();  
                            Getter read = property.getReader();  
                            Object old = read == null ? null : read.get(source);  
                            if (write != null) {  
                                write.set(source, value);  
                            }  
                            return old;  
                        } catch (Throwable e) {  
                            throw wrapCause(e);  
                        }  
                    }  
  
                    public Class<?> getType() {  
                        return property.getPropertyType();  
                    }  
  
                    public boolean canWrite() {  
                        return property.getWriter() != null;  
                    }  
  
                    public PropertyMeta getProperty() {  
                        return property;  
                    }  
  
                    public boolean canRead() {  
                        return property.getReader() != null;  
                    }  
                };  
            }  
  
            public Set<java.util.Map.Entry<String, java.util.Map.Entry<String, Object>>> entrySet() {  
                Set<java.util.Map.Entry<String, java.util.Map.Entry<String, Object>>> entrySet = new AbstractSet<java.util.Map.Entry<String, java.util.Map.Entry<String, Object>>>() {  
  
                    public int size() {  
                        return propertyMap.size();  
                    }  
  
                    public Iterator<java.util.Map.Entry<String, java.util.Map.Entry<String, Object>>> iterator() {  
                        final Iterator<PropertyMeta> propertyIterator = propertyMap  
                                .values().iterator();  
                        return new Iterator<Map.Entry<String, Entry<String, Object>>>() {  
                            public boolean hasNext() {  
                                return propertyIterator.hasNext();  
                            }  
  
                            public java.util.Map.Entry<String, java.util.Map.Entry<String, Object>> next() {  
  
                                return new java.util.Map.Entry<String, java.util.Map.Entry<String, Object>>() {  
                                    PropertyMeta property = propertyIterator  
                                            .next();  
  
                                    public String getKey() {  
                                        return property.getName();  
                                    }  
  
                                    public java.util.Map.Entry<String, Object> getValue() {  
                                        return new PropertyEntry<String, Object>() {  
                                            public String getKey() {  
                                                return property.getName();  
                                            }  
  
                                            public Object getValue() {  
                                                try {  
                                                    Getter read = property  
                                                            .getReader();  
                                                    Object value = read == null ? null  
                                                            : read.get(source);  
                                                    return value;  
                                                } catch (Exception e) {  
                                                    throw wrapCause(e);  
                                                }  
                                            }  
  
                                            public Object setValue(Object value) {  
                                                try {  
                                                    Setter write = property  
                                                            .getWriter();  
                                                    Getter read = property  
                                                            .getReader();  
                                                    Object old = read == null ? null  
                                                            : read.get(source);  
                                                    if (write != null) {  
                                                        write.set(source, value);  
                                                    }  
                                                    return old;  
                                                } catch (Throwable e) {  
                                                    throw wrapCause(e);  
                                                }  
                                            }  
  
                                            public Class<?> getType() {  
                                                return property  
                                                        .getPropertyType();  
                                            }  
  
                                            public boolean canWrite() {  
                                                return property.getWriter() != null;  
                                            }  
  
                                            public PropertyMeta getProperty() {  
                                                return property;  
                                            }  
  
                                            public boolean canRead() {  
                                                return property.getReader() != null;  
                                            }  
                                        };  
                                    }  
  
                                    public java.util.Map.Entry<String, Object> setValue(  
                                            java.util.Map.Entry<String, Object> value) {  
                                        throw new UnsupportedOperationException();  
                                    }  
                                };  
                            }  
  
                            public void remove() {  
                                throw new UnsupportedOperationException();  
                            }  
                        };  
                    }  
                };  
                return entrySet;  
            }  
        };  
    }  
  
    public Set<String> keySet() {  
        return propertyMetaMap.keySet();  
    }  
  
    private Map<String, PropertyMeta> generateBeanMap() {  
        try {  
            if (propertyMetaMap == null) {  
                propertyMetaMap = classBeanMap.get(sourceClass);  
                if (propertyMetaMap == null) {  
                    propertyMetaMap = new TreeMap<String, PropertyMeta>();  
                    PropertyDescriptor[] propertys = Introspector.getBeanInfo(  
                            sourceClass).getPropertyDescriptors();  
                    for (PropertyDescriptor property : propertys) {  
                        String name = property.getName();  
                        if ("class".equals(name)) {  
                            continue;  
                        }  
                        PropertyMeta propertyMeta = new PropertyMeta(property);  
                        propertyMetaMap.put(name, propertyMeta);  
                    }  
                    classBeanMap.put(sourceClass, propertyMetaMap);  
                }  
            }  
            return propertyMetaMap;  
        } catch (Throwable e) {  
            throw wrapCause(e);  
        }  
    }  
  
    public int size() {  
        return propertyMetaMap.size();  
    }  
  
    public boolean isEmpty() {  
        return propertyMetaMap.isEmpty();  
    }  
  
    public boolean containsKey(Object key) {  
        return propertyMetaMap.containsKey(key);  
    }  
  
    public Object get(Object key) {  
        try {  
            PropertyMeta property = propertyMetaMap.get(key);  
            Getter read = property.getReader();  
            Object value = read == null ? null : read.get(source);  
            return value;  
        } catch (IllegalArgumentException e) {  
            throw wrapCause(e);  
        }  
    }  
  
    public Object put(String key, Object value) {  
        try {  
            PropertyMeta property = propertyMetaMap.get(key);  
            Setter write = property.getWriter();  
            Getter read = property.getReader();  
            Object old = read == null ? null : read.get(source);  
            if (write != null) {  
                write.set(source, value);  
            }  
            return old;  
        } catch (IllegalArgumentException e) {  
            throw wrapCause(e);  
        }  
    }  
  
    /** 
     * 设置值（避免put中返回旧值的性能损失） 
     *  
     * @param key 
     *            键 
     * @param value 
     *            值 
     */  
    public void set(String key, Object value) {  
        try {  
            PropertyMeta property = propertyMetaMap.get(key);  
            Setter write = property.getWriter();  
            if (write != null) {  
                write.set(source, value);  
            }  
        } catch (IllegalArgumentException e) {  
            throw wrapCause(e);  
        }  
    }  
  
    /** 
     * 包裹异常 
     *  
     * @param cause 
     * @return 
     */  
    public static RuntimeException wrapCause(Throwable cause) {  
        if (cause instanceof RuntimeException) {  
            return (RuntimeException) cause;  
        }  
        return new RuntimeException(cause);  
    }  
}  
