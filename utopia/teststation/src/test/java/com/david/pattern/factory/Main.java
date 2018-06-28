package com.david.pattern.factory;

import org.junit.Assert;

public class Main {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        Circle circle = BeanFactory.getInstance().getBean("circle", Circle.class);
        circle.draw();
        Square square = BeanFactory.getInstance().getBean("square", Square.class);
        square.draw();
//        Square error = BeanFactory.getInstance().getBean("1square", Square.class);
    }
}
