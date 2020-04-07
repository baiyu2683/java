package com.zh.util;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;

public class PropertyDescriptorCache {

    private Map<String, PropertyDescriptor> propertyDescriptorMap;

    public PropertyDescriptorCache (Class clazz) throws IntrospectionException {
        propertyDescriptorMap = new ConcurrentHashMap<>(32);
        PropertyDescriptor[] propertyDescriptors = Introspector.getBeanInfo(clazz).getPropertyDescriptors();
        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            propertyDescriptorMap.putIfAbsent(propertyDescriptor.getName(), propertyDescriptor);
        }
    }

    public PropertyDescriptor fromName(String name) {
        return propertyDescriptorMap.get(name);
    }

}
