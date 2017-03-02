package com.david.guice;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * Created by David
 * on 2017/2/13
 */
public class AddClient {
    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new AddModel());
        Add add = injector.getInstance(Add.class);
        System.out.println(add.add(10, 54));
    }
}
