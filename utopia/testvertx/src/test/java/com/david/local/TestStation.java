package com.david.local;

import io.vertx.core.CompositeFuture;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.net.NetServer;
import org.junit.Test;

/**
 * Created by David
 * on 2017/1/4
 */
public class TestStation {
    /**
     * 测试EventBus
     */
    @Test
    public void testEventBus() throws InterruptedException {
        /**
         * publish 给所有verticle 发 send随机发一个
         */
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new EventBusReceiverVerticle("v1"));
        vertx.deployVerticle(new EventBusReceiverVerticle("v2"));
        Thread.sleep(2000);
        vertx.deployVerticle(new EventBusSenderVerticle());
    }
    @Test
    public void testFuture(){
//        Future<HttpServer> httpServerFuture = Future.future();
//        httpServer.listen(httpServerFuture.completer());
//
//        Future<NetServer> netServerFuture = Future.future();
//        netServer.listen(netServerFuture.completer());
//
//        CompositeFuture.all(httpServerFuture, netServerFuture).setHandler(ar -> {
//            if (ar.succeeded()) {
//                // All servers started
//            } else {
//                // At least one server failed
//            }
//        });
    }

}
