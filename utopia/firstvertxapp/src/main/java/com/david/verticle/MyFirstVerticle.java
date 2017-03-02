package com.david.verticle;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

/**
 * Created by David
 * on 2017/1/5
 */
public class MyFirstVerticle extends AbstractVerticle {
    @Override
    public void start(Future<Void> fut) throws Exception {

    }
    //    @Override
//    public void start(Future<Void> fut) throws Exception {
//        createHttpServer(config().getInteger("http.port", 8080),fut);
////        createHttpServer(8080,fut);
//    }

    /**
     * 创建HttpServer
     *
     * @param port
     * @param fut
     */
    public void createHttpServer(Integer port, Future<Void> fut) {
        vertx.createHttpServer().requestHandler(req -> req.response().end("<h1>Hello from my first " +
                "Vert.x 3 application</h1>")).listen(//配置端口
                port,
                result -> {
                    if (result.succeeded()) {
                        fut.complete();
                    } else {
                        fut.fail(result.cause());
                    }
                });
    }

}
