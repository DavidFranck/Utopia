package com.david.pattern.decorator;

import com.david.pattern.factory.Circle;
import com.david.pattern.factory.Rectangle;
import com.david.pattern.factory.Shape;

public class RedShape extends ShapeDecorator {
    public RedShape(Shape shape) {
        super(shape);
    }

    @Override
    public void draw() {
        super.draw();
        setRedBorder(shape);
    }

    private void setRedBorder(Shape decoratedShape) {
        System.out.println("Border Color: Red");
    }

    public static void main(String[] args) {
        new RedShape(new Circle()).draw();
        new RedShape(new Rectangle()).draw();
    }
}
