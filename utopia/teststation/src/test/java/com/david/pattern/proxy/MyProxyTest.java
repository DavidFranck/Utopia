package com.david.pattern.proxy;


import com.david.pattern.factory.Circle;
import com.david.pattern.factory.Shape;

import java.lang.reflect.Method;

public class MyProxyTest implements MyInvocationHandler {
    public static void main(String[] args) {
        Object instance = new MyProxyTest().getInstance(new Circle());
        ((Shape)instance).draw();
    }

    private Object target;


    public Object getInstance(Object target) {
        this.target = target;
        Class<?> aClass = target.getClass();
        return MyProxy.newProxyInstance(new MyClassLoader(), aClass.getInterfaces(), this);

    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("---my proxy before---");
        Object res = method.invoke(target, args);
        System.out.println("---my proxy after---");
        return res;
    }
}
