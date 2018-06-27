package com.david.pattern.observer;

import com.google.common.collect.Lists;

import java.util.List;

public class Subject {
    private List<Observer> observers = Lists.newArrayList();
    private int state;


    public void notifyAllObservers() {
        getObservers().forEach(Observer::update);
    }

    public void attach(Observer observer) {
        observers.add(observer);
    }

    public List<Observer> getObservers() {
        return observers;
    }

    public void setObservers(List<Observer> observers) {
        this.observers = observers;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
        notifyAllObservers();
    }
}
