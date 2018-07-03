package com.david.pattern.facade;

import com.david.pattern.factory.Circle;
import com.david.pattern.factory.Rectangle;
import com.david.pattern.factory.Shape;
import com.david.pattern.factory.Square;

/**
 * 对外提供统一接口 隐藏具体实现的复杂性
 * 感觉电脑的例子更形象：
 * 电脑整机是CUP、内存、硬盘的外观。有了外观以后，启动电脑和关闭电脑都简化了。
 * 启动电脑（按一下电源键）：启动CPU、启动内存、启动硬盘
 * 关闭电脑（按一下电源键）：关闭硬盘、关闭内存、关闭CPU
 */
public class ShapeMaker {
    private Shape circle;
    private Shape rectangle;
    private Shape square;

    public ShapeMaker() {
        circle = new Circle();
        rectangle = new Rectangle();
        square = new Square();
    }

    public void drawCircle() {
        circle.draw();
    }

    public void drawRectangle() {
        rectangle.draw();
    }

    public void drawSquare() {
        square.draw();
    }

    public static void main(String[] args) {
        ShapeMaker shapeMaker = new ShapeMaker();

        shapeMaker.drawCircle();
        shapeMaker.drawRectangle();
        shapeMaker.drawSquare();
    }
}
