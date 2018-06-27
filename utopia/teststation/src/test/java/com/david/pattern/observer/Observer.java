package com.david.pattern.observer;

/**
 * 观察者
 */
public abstract class Observer {
    protected Subject subject;

    public abstract void update();
}
