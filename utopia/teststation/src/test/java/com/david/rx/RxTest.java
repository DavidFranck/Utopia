package com.david.rx;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class RxTest {
    @Test
    public void deferredDependency() {
        //由于在编译期get 确定 还没发事件所以get为0
        AtomicInteger count = new AtomicInteger();

        Observable.range(1, 10)
                .doOnNext(ignored -> count.incrementAndGet())
                .ignoreElements()
                .andThen(Single.just(count.get()))
                .subscribe(System.out::println);
        // 下面就可以了
        AtomicInteger count1 = new AtomicInteger();
        Observable.range(1, 10)
                .doOnNext(ignored -> count1.incrementAndGet())
                .ignoreElements()
                .andThen(Single.defer(() -> Single.just(count1.get())))
                .subscribe(System.out::println);

        AtomicInteger count2 = new AtomicInteger();

        Observable.range(1, 10)
                .doOnNext(ignored -> count2.incrementAndGet())
                .ignoreElements()
                .andThen(Single.fromCallable(() -> count2.get()))
                .subscribe(System.out::println);
    }

    @Test
    public void testBackground() throws InterruptedException {
        Flowable.fromCallable(() -> {
            Thread.sleep(1000); //  imitate expensive computation
            return "Done";
        }).subscribeOn(Schedulers.io())
                .observeOn(Schedulers.single())
                .subscribe(System.out::println, Throwable::printStackTrace);

        Thread.sleep(2000); // <--- wait for the flow to finish
    }

    @Test
    public void testParallel() {
        Flowable.range(1, 10)
                .observeOn(Schedulers.computation())
                .map(v -> v * v)
                .blockingSubscribe(System.out::println);

        System.out.println("------------------------------");

        //并行处理
        Flowable.range(1, 10)
                .flatMap(v ->
                        Flowable.just(v)
                                .subscribeOn(Schedulers.computation())
                                .map(w -> w * w)
                )
                .blockingSubscribe(System.out::println);
        System.out.println("------------------------------");
        Flowable.range(1, 10)
                .parallel()
                .runOn(Schedulers.computation())
                .map(v -> v * v)
                .sequential()
                .blockingSubscribe(System.out::println);
    }

    @Test
    public void testRuntime() {
        Observable.create(emitter -> {
            while (!emitter.isDisposed()) {
                long time = System.currentTimeMillis();
                emitter.onNext(time);
                if (time % 2 != 0) {
                    emitter.onError(new IllegalStateException("Odd millisecond!"));
                    break;
                }
            }
        }).subscribe(System.out::println, Throwable::printStackTrace);
    }

    @Test
    public void helloWorld() {
        Flowable.just("Hello world").subscribe(System.out::println);
    }
}
