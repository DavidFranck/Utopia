package com.david.guava;

import com.google.common.cache.*;
import com.google.common.graph.Graph;
import org.junit.Test;

import java.security.Key;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * Created by David
 * on 2017/3/2
 */
public class TestCache {

    //loadingCache的方式
    @Test
    public void TestLoadingCache() throws Exception{
        LoadingCache<String,String> cahceBuilder=CacheBuilder
                .newBuilder()
                .build(new CacheLoader<String, String>(){
                    @Override
                    public String load(String key) throws Exception {
                        String strProValue="hello "+key+"!";
                        return strProValue;
                    }

                });

        System.out.println("jerry value:"+cahceBuilder.apply("jerry"));
        System.out.println("jerry value:"+cahceBuilder.get("jerry"));
        System.out.println("peida value:"+cahceBuilder.get("peida"));
        System.out.println("peida value:"+cahceBuilder.apply("peida"));
        System.out.println("lisa value:"+cahceBuilder.apply("lisa"));
        cahceBuilder.put("harry", "ssdded");
        System.out.println("harry value:"+cahceBuilder.get("harry"));
    }
    //callBack的方式来实现cache
    @Test
    public void testcallableCache()throws Exception{
        Cache<String, String> cache = CacheBuilder.newBuilder().maximumSize(1000).build();

        String resultVal = cache.get("jerry", new Callable<String>() {
            public String call() {
                String strProValue="hello "+"jerry"+"!";
                return strProValue;
            }
        });
        System.out.println("jerry value : " + resultVal);

        resultVal = cache.get("peida", new Callable<String>() {
            public String call() {
                String strProValue="hello "+"peida"+"!";
                return strProValue;
            }
        });
        System.out.println("peida value : " + resultVal);
    }


    @Test
    public void testChache(){

//        LoadingCache<Key, Graph> graphs = CacheBuilder.newBuilder()
//                .maximumSize(1000)
//                .expireAfterWrite(10, TimeUnit.MINUTES)
//                .removalListener(new RemovalListener<Object, Object>() {
//                    public void onRemoval(RemovalNotification<Object, Object> removalNotification) {
//
//                    }
//                })
//                .removalListener(MY_LISTENER)
//                .build(
//                        new CacheLoader<Key, Graph>() {
//                            public Graph load(Key key) throws AnyException {
//                                return createExpensiveGraph(key);
//                            }
//                        });
    }
}
