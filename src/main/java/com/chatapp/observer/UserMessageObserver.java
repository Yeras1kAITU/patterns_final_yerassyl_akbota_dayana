package com.chatapp.observer;

import com.chatapp.Message;

// Done by: Akbota
public class UserMessageObserver implements MessageObserver {
    private final String username;

    public UserMessageObserver(String username) {
        this.username = username;
    }

    @Override
    public void update(Message message) {
        if (message.getRecipient().equals(username) || message.getRecipient().equals("ALL")) {
            System.out.println("[" + username + "] Received: " + message.getContent());
        }
    }

    @Override
    public String getName() {
        return "UserObserver-" + username;
    }
}