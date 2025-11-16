package com.chatapp.observer;

import com.chatapp.Message;

// Done by: Akbota
public interface MessageObserver {
    void update(Message message);
    String getName();
}