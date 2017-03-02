package com.david.local;

import io.vertx.core.Vertx;
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

}
