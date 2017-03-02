package com.david;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.math.BigIntegerMath;
import com.sun.corba.se.impl.orbutil.graph.Graph;
import org.junit.Test;

import java.math.BigInteger;
import java.math.RoundingMode;
import java.security.Key;

/**
 * Created by David
 * on 2017/2/10
 */
public class TestGuava {
    @Test
    public void main() {
        Cache<Key, Graph> build = CacheBuilder.newBuilder().maximumSize(1000).build();
        System.out.println(build);
    }
    @Test
    public void testMath(){
        BigInteger sqrt = BigIntegerMath.sqrt(BigInteger.TEN.pow(99), RoundingMode.HALF_EVEN);
        System.out.println(sqrt);
    }
}
