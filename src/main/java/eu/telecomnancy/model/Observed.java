package eu.telecomnancy.model;

import java.util.ArrayList;

import eu.telecomnancy.observer.Observer;

public abstract class Observed {

    protected ArrayList<Observer> observers = new ArrayList<>();

    public void addObserver(Observer observer){
        observers.add(observer);
    }

    public void removeObserver(Observer observer){
        observers.remove(observer);
    }

    public void notifyObservers(){
        for (Observer observer : observers) {
            observer.react();
        }
    }
}
