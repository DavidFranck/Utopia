package com.david.local;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;

/**
 * Created by David
 * on 2017/1/4
 */
public class MyVerticle extends AbstractVerticle{
    @Override
    public void start() throws Exception {
        System.out.println("MyVerticle start...");
    }

    @Override
    public void stop() throws Exception {
        System.out.println("MyVerticle stop...");
    }

    public static void main(String[] args) {
        Vertx vertx = Vertx.factory.vertx();
        System.out.println(MyVerticle.class.toString());
        vertx.deployVerticle(new MyVerticle());
    }
}
