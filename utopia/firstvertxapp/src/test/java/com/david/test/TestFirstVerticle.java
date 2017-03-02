package com.david.test;

import com.david.verticle.MyFirstVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * Created by David
 * on 2017/1/5
 */
@RunWith(VertxUnitRunner.class)
public class TestFirstVerticle {
    private Vertx vertx;
    int port = 8081;

    @Before
    public void setUp(TestContext context) throws IOException {
        vertx = Vertx.vertx();
        //一般加载verticle
//        vertx.deployVerticle(MyFirstVerticle.class.getName(), context.asyncAssertSuccess());
        //random port
        ServerSocket socket = new ServerSocket(0);
        port = socket.getLocalPort();
        socket.close();
        System.out.println(port);
        //带option加载verticle
        DeploymentOptions options = new DeploymentOptions().setConfig(new JsonObject().put("http.port", port));
        vertx.deployVerticle(MyFirstVerticle.class.getName(), options, context.asyncAssertSuccess());
    }

    @After
    public void tearDown(TestContext context) {
        vertx.close(context.asyncAssertSuccess());
    }

    @Test
    public void testMyApplication(TestContext context) {
        final Async async = context.async();
        vertx.createHttpClient().getNow(port, "localhost", "/",
                response -> response.handler(body -> {
                    System.out.println(body);
                    context.assertTrue(body.toString().contains("Hello"));
                    async.complete();
                }));
    }
}

