package com.david.local;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;

/**
 * Created by David
 * on 2017/1/4
 */
public class BaseVerticle extends AbstractVerticle{
    @Override
    public void start() throws Exception {
        System.out.println("BasicVerticle started");
        vertx.deployVerticle(new MyVerticle());
    }

    @Override
    public void stop() throws Exception {
        System.out.println("BasicVerticle stopped");
    }

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        //装载Verticle 装载成功后回调handler
//        local.deployVerticle(new BaseVerticle(), new Handler<AsyncResult<String>>() {
//            public void handle(AsyncResult<String> stringAsyncResult) {
//                System.out.println("BasicVerticle deployment complete");
//            }
//        });
        vertx.deployVerticle(new BaseVerticle(),stringAsyncResult -> {
            System.out.println("BasicVerticle deployment complete");
        });
    }
}
