package com.david;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.math.BigIntegerMath;
import com.google.common.reflect.AbstractInvocationHandler;
import com.google.common.reflect.TypeToken;
import com.google.common.util.concurrent.*;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.Function;

/**
 * Created by David
 * on 2017/2/10
 */
public class TestGuava {
    @Test
    public void testFuture() throws ExecutionException, InterruptedException {
        ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));
        ListenableFuture<Integer> future1 = service.submit(() -> {
            Thread.sleep(1000);
            System.out.println(1);
            return 1;
        });
        ListenableFuture<Integer> future2 = service.submit(() -> {
            Thread.sleep(1000);
            System.out.println(2);
            return 2;
        });
        ListenableFuture<List<Integer>> all = Futures.allAsList(future1, future2);
        ListenableFuture<String> transform = Futures.transform(all, (AsyncFunction<List<Integer>, String>) results -> Futures.immediateFuture(String.format("success futuare:%d", results.size())));


//        ListenableFuture transform = Futures.transform(all, (AsyncFunction) result -> Futures.immediateFuture(String.format("success future", result)));
        Futures.addCallback(transform, new FutureCallback<Object>() {
            @Override
            public void onSuccess(Object result) {
                System.out.println(result.getClass());
                System.out.printf("success with: %s%n", result);
            }

            @Override
            public void onFailure(Throwable t) {
                System.out.printf("onFailure%s%n", t.getMessage());
            }
        });
        System.out.println(transform.get());
    }

    @Test
    public void testCache() throws ExecutionException, IOException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        LoadingCache<String, Object> cache = CacheBuilder.newBuilder().maximumSize(1000).refreshAfterWrite(5, TimeUnit.MINUTES).build(new CacheLoader<String, Object>() {
            @Override
            public Object load(String s) throws Exception {
                System.out.println(s);
                return s;
            }

            @Override
            public ListenableFuture<Object> reload(String key, Object oldValue) throws Exception {
                // asynchronous!
                ListenableFutureTask<Object> task = ListenableFutureTask.create(() -> {
                    System.out.println(key);
                    return key;
                });
                executor.execute(task);
                return task;
            }
        });
        System.out.println(cache.get("com.test.guava"));
        System.out.println(cache.get("com.test.guava2", () -> "call"));
        System.out.println(cache.get("com.test.guava1", () -> "call"));
        cache.put("com.test.guava", "hehe");
        System.out.println(cache.getIfPresent("com.test.guava"));
        cache.invalidateAll();
        cache.refresh("com.test.guava");
        System.out.println(cache.getIfPresent("com.test.guava"));
        cache.cleanUp();
        System.out.println(cache.getIfPresent("com.test.guava"));
    }

    @Test
    public void t1() {
        ImmutableList<Class<TestGuava>> of = ImmutableList.of(TestGuava.class);
        ImmutableList<String> of1 = ImmutableList.of(TestGuava.class.getName());
        System.out.println(of);
        System.out.println(of1);
        System.out.println(TestGuava.class);
        System.out.println(TestGuava.class.getName());
    }

    @Test
    public void testTypeToken() {
        class Gen<T> {
            protected T t;

            public T getT() {
                System.out.println(this.getClass().getName());
                return t;
            }

            public void setT(T t) {
                this.t = t;
            }
        }
        class Gen1 extends Gen<String> {
//            @Override
//            public String getT() {
//                System.out.println(this.getClass().getName());
//                return "Gen1";
//            }
        }


        new Gen1().getT();
        new Gen().getT();
//        Gen<String> stringGen = new Gen<>();
//        System.out.println(stringGen.getClass().getGenericSuperclass());

        TypeToken<Gen1> gen1 = new TypeToken<Gen1>() {
        };
        TypeToken<?> resolveType = gen1.resolveType(Gen.class.getTypeParameters()[0]);
        System.out.println(resolveType.getType().getTypeName());

//        TypeToken<ArrayList<String>> typeToken = new TypeToken<ArrayList<String>>() {};
        TypeToken<ArrayList<String>> typeToken = new TypeToken<ArrayList<String>>() {
        };
        TypeToken<?> genericTypeToken = typeToken.resolveType(ArrayList.class.getTypeParameters()[0]);
        System.out.println(genericTypeToken.getType().getTypeName());


//        TypeToken<Function<String, Object>> functionTypeToken = new TypeToken<Function<String, Object>>(){};
        TypeToken<Function<? extends String, ? extends Object>> functionTypeToken = new TypeToken<Function<? extends String, ? extends Object>>() {
        };
        System.out.println(functionTypeToken.resolveType(Function.class.getTypeParameters()[0]).getType().getTypeName());
        System.out.println(functionTypeToken.resolveType(Function.class.getTypeParameters()[1]).getType().getTypeName());
    }

    @Test
    public void testTypeToken1() {
        TypeToken<String> of = TypeToken.of(String.class);
        System.out.println(of.getType());
        System.out.println(String.class);
    }

    @Test
    public void main() throws ExecutionException {
        Cache<String, Object> cache = CacheBuilder.newBuilder().maximumSize(1000).build();
        cache.put("1", "hehe");
        System.out.println(cache.get("1", () -> "heheda"));

    }

    @Test
    public void cache() throws ExecutionException {
        Cache<String, Object> cache = CacheBuilder.newBuilder().maximumSize(1000).build(new CacheLoader<String, Object>() {
            @Override
            public Object load(String s) throws Exception {
                System.out.println(s);
                return s;
            }
        });
        cache.put("1", "hehe");
        System.out.println(cache.get("1", () -> "heheda"));
        System.out.println(cache.get("2", () -> "heheda"));
        System.out.println(cache.get("2", () -> "heheda"));
    }

    @Test
    public void testMath() {
        BigInteger sqrt = BigIntegerMath.sqrt(BigInteger.TEN.pow(99), RoundingMode.HALF_EVEN);
        System.out.println(sqrt);
    }
}
