package com.chatapp.observer;

import com.chatapp.Message;

// Done by: Akbota
public interface MessageSubject {
    void attach(MessageObserver observer);
    void detach(MessageObserver observer);
    void notifyObservers(Message message);
}