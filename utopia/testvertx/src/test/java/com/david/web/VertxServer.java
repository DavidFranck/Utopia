package com.david.web;


import io.vertx.core.*;
import io.vertx.core.http.HttpServerRequest;

/**
 * Created by David
 * on 2017/1/4
 */
public class VertxServer extends AbstractVerticle  {
    public static void main(String[] args) throws Exception {

        Vertx.vertx().createHttpServer().requestHandler(req -> req.response().end("Hello World!")).listen(8080);
//        new VertxServer().start(null);
    }

    @Override
    public void start(Future<Void> future) throws Exception {
//        vertx.createHttpServer().requestHandler(new Handler<HttpServerRequest>() {
//            public void handle(HttpServerRequest req) {
//                System.out.println("Got request: " + req.uri());
//                req.response().headers().set("Content-Type", "text/html; charset=UTF-8");
//                req.response().end("<html><body><h1>Hello from vert.x!</h1></body></html>");
//            }
//        }).listen(8080);
        vertx.createHttpServer().requestHandler(req -> {
            System.out.println("Got request: " + req.uri());
            req.response().headers().set("Content-Type", "text/html; charset=UTF-8");
            req.response().end("<html><body><h1>Hello from vert.x!</h1></body></html>");

        }).listen(8080);
    }
}
