package com.david.web;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;

/**
 * Created by David
 * on 2017/1/5
 */
public class HelloWorldServer extends AbstractVerticle{
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new HelloWorldServer());
    }
    @Override
    public void start() throws Exception {
        String name = config().getString("name", "david");
        vertx.createHttpServer().requestHandler(req -> req.response().end("Hello " + name + "!")).listen(8080);
    }
}
