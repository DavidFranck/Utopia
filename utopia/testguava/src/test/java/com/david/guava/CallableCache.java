package com.david.guava;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created by David
 * on 2017/3/2
 */
public class CallableCache {
    private static Cache<String, String> cacheFormCallable = null;


    /**
     * 对需要延迟处理的可以采用这个机制；(泛型的方式封装)
     * @param <K>
     * @param <V>
     * @param key
     * @param callable
     * @return V
     * @throws Exception
     */
    public static <K,V> Cache<K , V> callableCached() throws Exception {
        Cache<K, V> cache = CacheBuilder
                .newBuilder()
                .maximumSize(10000)
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .build();
        return cache;
    }


    private String getCallableCache(final String userName) {
        try {
            //Callable只有在缓存值不存在时，才会调用
            return cacheFormCallable.get(userName, () -> {
//                Thread.sleep(2000);
                System.out.println(userName+" from db");
                return "hello "+userName+"!";
            });
        } catch (ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Test
    public void testCallableCache() throws Exception{
        final String u1name = "peida";
        final String u2name = "jerry";
        final String u3name = "lisa";
        cacheFormCallable=callableCached();
        System.out.println("peida:"+getCallableCache(u1name));
//        System.out.println("jerry:"+getCallableCache(u2name));
//        System.out.println("lisa:"+getCallableCache(u3name));
        cacheFormCallable.put("peida","peidaValue");
        System.out.println("peida:"+getCallableCache(u1name));

    }
}
