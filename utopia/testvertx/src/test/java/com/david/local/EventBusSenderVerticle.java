package com.david.local;

import io.vertx.core.AbstractVerticle;

/**
 * Created by David
 * on 2017/1/4
 */
public class EventBusSenderVerticle extends AbstractVerticle{
    @Override
    public void start() throws Exception {
        vertx.eventBus().publish("anAddress","message 2");
        vertx.eventBus().send("anAddress","message 1");
    }
}
