package com.david.pattern.proxy;

import com.david.others.TestProxy;
import com.david.pattern.factory.Circle;
import com.david.pattern.factory.Shape;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理
 */
public class DynamicProxy implements InvocationHandler {
    private Object target;

    public Object getInstance(Object target) {
        this.target = target;
        Class clazz = target.getClass();
        return Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("-------before-----");
        Object ret = method.invoke(target, args);
        System.out.println("-------after-----");
        return ret;
    }

    public static void main(String[] args) {
        Object instance = new DynamicProxy().getInstance(new TestProxy.TestServiceImpl());
        ((TestProxy.TestService) instance).test();

        Object circle = new DynamicProxy().getInstance(new Circle());
        ((Shape) circle).draw();
    }
}
