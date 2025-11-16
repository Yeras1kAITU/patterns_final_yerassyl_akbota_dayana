package com.chatapp.builder;

import com.chatapp.Message;
import com.chatapp.MessageType;
import java.time.LocalDateTime;
import java.util.UUID;

// Done by: Akbota
public class MessageBuilder {
    private String id;
    private String sender;
    private String recipient;
    private String content;
    private LocalDateTime timestamp;
    private MessageType type;

    public MessageBuilder() {
        this.id = UUID.randomUUID().toString();
        this.timestamp = LocalDateTime.now();
        this.type = MessageType.TEXT;
    }

    public MessageBuilder withId(String id) {
        this.id = id;
        return this;
    }

    public MessageBuilder from(String sender) {
        this.sender = sender;
        return this;
    }

    public MessageBuilder to(String recipient) {
        this.recipient = recipient;
        return this;
    }

    public MessageBuilder withContent(String content) {
        this.content = content;
        return this;
    }

    public MessageBuilder withTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public MessageBuilder ofType(MessageType type) {
        this.type = type;
        return this;
    }

    public Message build() {
        if (sender == null || recipient == null || content == null) {
            throw new IllegalStateException("Sender, recipient and content are required");
        }
        return new Message(id, sender, recipient, content, timestamp, type);
    }
}