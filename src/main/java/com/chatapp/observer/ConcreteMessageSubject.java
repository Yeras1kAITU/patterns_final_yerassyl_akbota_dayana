package com.chatapp.observer;

import com.chatapp.Message;
import java.util.ArrayList;
import java.util.List;

// Done by: Akbota
public class ConcreteMessageSubject implements MessageSubject {
    private final List<MessageObserver> observers = new ArrayList<>();

    @Override
    public void attach(MessageObserver observer) {
        observers.add(observer);
        System.out.println("Attached observer: " + observer.getName());
    }

    @Override
    public void detach(MessageObserver observer) {
        observers.remove(observer);
        System.out.println("Detached observer: " + observer.getName());
    }

    @Override
    public void notifyObservers(Message message) {
        for (MessageObserver observer : observers) {
            observer.update(message);
        }
    }

    public int getObserverCount() {
        return observers.size();
    }
}