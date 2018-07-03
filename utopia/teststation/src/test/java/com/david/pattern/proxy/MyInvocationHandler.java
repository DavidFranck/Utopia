package com.david.pattern.proxy;

import java.lang.reflect.Method;

public interface MyInvocationHandler {
    //接口的方法invoke
    Object invoke(Object proxy, Method method, Object[] args) throws Throwable;

}