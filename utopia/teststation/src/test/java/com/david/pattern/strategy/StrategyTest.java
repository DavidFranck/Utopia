package com.david.pattern.strategy;

public class StrategyTest {
    public static void main(String[] args) {
        Context context = new Context();
        context.setStrategy((i1, i2) -> i1 * i2);
        System.out.println(context.executeStrategy(10, 2));
        context.setStrategy(new OperationAdd());
        System.out.println(context.executeStrategy(10, 2));

        Jdk8Context jdk8Context = new Jdk8Context();
        jdk8Context.setStrategy((i1, i2) -> i1 / i2);
        System.out.println(jdk8Context.executeStrategy(10, 2));
    }
}
