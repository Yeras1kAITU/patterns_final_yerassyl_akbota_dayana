package com.chatapp;

import com.chatapp.visitor.MessageElement;
import com.chatapp.visitor.MessageVisitor;
import java.time.LocalDateTime;

// Done by:
public class Message implements MessageElement {
    private final String id;
    private final String sender;
    private final String recipient;
    private final String content;
    private final LocalDateTime timestamp;
    private final MessageType type;

    public Message(String id, String sender, String recipient, String content,
                   LocalDateTime timestamp, MessageType type) {
        this.id = id;
        this.sender = sender;
        this.recipient = recipient;
        this.content = content;
        this.timestamp = timestamp;
        this.type = type;
    }

    // Getters
    public String getId() { return id; }
    public String getSender() { return sender; }
    public String getRecipient() { return recipient; }
    public String getContent() { return content; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public MessageType getType() { return type; }

    @Override
    public void accept(MessageVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s -> %s: %s", timestamp, sender, recipient, content);
    }
}