package com.david.others;

import com.david.pattern.factory.Circle;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class TestProxy {
    public static interface TestService {
        void test();
    }

    public static class TestServiceImpl implements TestService {

        @Override
        public void test() {
            System.out.println("t1");
        }
    }

    static class Handler implements InvocationHandler {
        private Object target;

        public Handler() {
        }

        public Handler(Object target) {
            this.target = target;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Object returnVal;
            System.out.println("before");
            returnVal = method.invoke(target, args);
            System.out.println("after");
            return returnVal;
        }
    }


    public static void main(String[] args) {
        Handler handler = new Handler(new TestServiceImpl());
        Object o = Proxy.newProxyInstance(TestProxy.class.getClassLoader(), TestServiceImpl.class.getInterfaces(), handler);
//        Object o = Proxy.newProxyInstance(TestProxy.class.getClassLoader(), new Class[]{TestProxy.TestService.class}, handler);
        ((TestService) o).test();
    }


}
