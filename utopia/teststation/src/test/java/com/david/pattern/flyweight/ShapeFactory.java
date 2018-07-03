package com.david.pattern.flyweight;

import com.google.common.collect.Maps;

import java.util.concurrent.ConcurrentMap;

/**
 * 享元模式
 * 1.缓存用到这种
 * 2.java String 串池 池中有的话就直接返回 没有的话放入串池
 * 3.数据库数据池
 */
public class ShapeFactory {
    private static final ConcurrentMap<String, Circle> factory = Maps.newConcurrentMap();

    public static Circle getCircle(String color) {
        return factory.computeIfAbsent(color, c -> {
            System.out.println("Creating circle of color : " + color);
            return new Circle(c);
        });
    }


    private static final String colors[] = {"Red", "Green", "Blue", "White", "Black"};

    public static void main(String[] args) {

        for (int i = 0; i < 20; ++i) {
            Circle circle = ShapeFactory.getCircle(getRandomColor());
            circle.setX(getRandomX());
            circle.setY(getRandomY());
            circle.setRadius(100);
            circle.draw();
        }
    }

    private static String getRandomColor() {
        return colors[(int) (Math.random() * colors.length)];
    }

    private static int getRandomX() {
        return (int) (Math.random() * 100);
    }

    private static int getRandomY() {
        return (int) (Math.random() * 100);
    }
}
