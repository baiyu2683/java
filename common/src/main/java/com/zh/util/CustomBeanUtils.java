package com.zh.util;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CustomBeanUtils {

    private static Map<String, PropertyDescriptorCache> propertyDescriptorCacheMap = new ConcurrentHashMap<>(32);

    public static void copy(Object src, Object dest) throws Exception {
        if (src == null || dest == null) {
            throw new IllegalArgumentException("");
        }
        PropertyDescriptor[] destPropertyDescriptors = Introspector.getBeanInfo(dest.getClass()).getPropertyDescriptors();
        for (PropertyDescriptor destPropertyDescriptor : destPropertyDescriptors) {
            if (destPropertyDescriptor.getName().equals("class")) {
                continue;
            }
            PropertyDescriptor srcPropertyDescriptor = getPropertyDescriptorByName(destPropertyDescriptor.getName(), src.getClass());
            if (srcPropertyDescriptor == null) {
                continue;
            }
            if (srcPropertyDescriptor.getPropertyType() != destPropertyDescriptor.getPropertyType()) {
                continue;
            }
            Method srcReadMethod = srcPropertyDescriptor.getReadMethod();
            Object value = srcReadMethod.invoke(src);
            Method destWriteMethod = destPropertyDescriptor.getWriteMethod();
            destWriteMethod.invoke(dest, value);
        }
    }

    private static PropertyDescriptor getPropertyDescriptorByName(String name, Class clazz) throws IntrospectionException {
        PropertyDescriptorCache propertyDescriptorCache = propertyDescriptorCacheMap.computeIfAbsent(clazz.getName(), key -> {
            try {
                return new PropertyDescriptorCache(clazz);
            } catch (IntrospectionException e) {
                e.printStackTrace();
            }
            return null;
        });
        if (propertyDescriptorCache == null) {
            return null;
        }
        return propertyDescriptorCache.fromName(name);
    }

    public static void main(String[] args) throws Exception {
        Info1 info1 = new Info1();
        info1.setAge(10);
        info1.setName("100");
        Info2 info2 = new Info2();
        CustomBeanUtils.copy(info1, info2);
        System.out.println(info2.getAge() + "-" + info2.getName());
    }

    static class Info1 {
        private int age;
        private String name;

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    static class Info2 {
        private int age;
        private String name;

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
