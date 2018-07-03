package com.david.pattern.proxy;

/**
 * http://www.runoob.com/design-pattern/proxy-pattern.html
 *  1、和适配器模式的区别：适配器模式主要改变所考虑对象的接口，而代理模式不能改变所代理类的接口。
 *  2、和装饰器模式的区别：装饰器模式为了增强功能，而代理模式是为了加以控制。
 */
public class ProxyImage implements Image{
    public static void main(String[] args) {
        Image image = new ProxyImage("test_10mb.jpg");

        //图像将从磁盘加载
        image.display();
        System.out.println("");
        //图像将无法从磁盘加载
        image.display();
    }
    private RealImage realImage;
    private String fileName;

    public ProxyImage(String fileName){
        this.fileName = fileName;
    }

    @Override
    public void display() {
        if(realImage == null){
            realImage = new RealImage(fileName);
        }
        realImage.display();
    }
}
