package com.david.pattern.strategy;

import java.util.function.BiFunction;

/**
 * 使用BiFunction
 */
public class Jdk8Context {
    private BiFunction<Integer, Integer, Integer> strategy;

    public BiFunction<Integer, Integer, Integer> getStrategy() {
        return strategy;
    }

    public void setStrategy(BiFunction<Integer, Integer, Integer> strategy) {
        this.strategy = strategy;
    }

    public int executeStrategy(int num1, int num2) {
        return strategy.apply(num1, num2);
    }
}
