package com.david.pattern.factory;
@Bean("rectangle")
public class Rectangle implements Shape {
    @Override
    public void draw() {
        System.out.println("Inside Rectangle::draw() method.");
    }
}
