package com.david.pattern.factory;

import com.google.common.collect.Maps;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class BeanFactory {
    private static BeanFactory instance = new BeanFactory();
    private static Map<String, Class> classMap;

    private BeanFactory() {
        init();
    }

    private void init() {
        classMap = Maps.newConcurrentMap();
        Set<Class<?>> classes = ClassHelper.getClasses(Main.class.getPackage().getName());
        classes.forEach(c -> {
            Bean bean = c.getAnnotation(Bean.class);
            if (Objects.nonNull(bean)) {
                System.out.println(bean.value());
                classMap.put(bean.value(), c);
            }
        });

    }

    public static BeanFactory getInstance() {
        return instance;
    }


    public Object getBean(String name) {
        return null;
    }

    public <T> T getBean(String name, Class<T> clazz) throws IllegalAccessException, InstantiationException {
        Class<T> c = classMap.get(name);
        return c.newInstance();
    }

}
