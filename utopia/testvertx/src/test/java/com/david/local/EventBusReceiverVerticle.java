package com.david.local;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

/**
 * Created by David
 * on 2017/1/4
 */
public class EventBusReceiverVerticle extends AbstractVerticle {
    private String name = null;

    public EventBusReceiverVerticle(String name) {
        this.name = name;
    }

    @Override
    public void start(Future<Void> startFuture) throws Exception {
        vertx.eventBus().consumer("anAddress", message -> {
            System.out.println(this.name + " received message: " + message.body());
        });
    }
}
