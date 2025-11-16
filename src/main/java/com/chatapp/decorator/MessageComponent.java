package com.chatapp.decorator;

// Done by: Dayana
public interface MessageComponent {
    String getId();
    String getSender();
    String getRecipient();
    String getContent();
    String getTimestamp();
    String getType();
}