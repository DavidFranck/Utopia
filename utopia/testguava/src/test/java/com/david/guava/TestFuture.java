package com.david.guava;

import com.google.common.util.concurrent.*;
import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

/**
 * Created by David
 * on 2017/3/2
 */
public class TestFuture {
    @Test
    public void testFuture(){
        ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));
//        ListenableFuture explosion = service.submit(new Callable() {
//            public Explosion call() {
//                return ();
//            }
//        });
        ListenableFuture ex = service.submit(new Callable() {
            public Object call() throws Exception {
                return "hahaha";
            }
        });

//        Futures.addCallback(explosion, new FutureCallback() {
//            // we want this handler to run immediately after we push the big red button!
//            public void onSuccess(Explosion explosion) {
//                walkAwayFrom(explosion);
//            }
//            public void onFailure(Throwable thrown) {
//                battleArchNemesis(); // escaped the explosion!
//            }
//        });
    }
}
