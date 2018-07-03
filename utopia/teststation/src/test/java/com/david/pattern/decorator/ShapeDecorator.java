package com.david.pattern.decorator;

import com.david.pattern.factory.Shape;

/**
 * 继承同一接口 把对象包装进来 重写方法调用被包装类方法 并进行扩展
 * java io 用到了decorator模式
 */
public abstract class ShapeDecorator implements Shape {
    protected Shape shape;

    public ShapeDecorator(Shape shape) {
        this.shape = shape;
    }

    @Override
    public void draw() {
        shape.draw();
    }
}
