package com.chatapp.factories;

import com.chatapp.Message;

// Done by: Yerassyl
public abstract class MessageCreator {
    public abstract Message createMessage(String sender, String recipient, String content);
}