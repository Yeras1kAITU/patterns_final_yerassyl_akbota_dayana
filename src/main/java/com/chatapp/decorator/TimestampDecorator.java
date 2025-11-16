package com.chatapp.decorator;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// Done by: Dayana
public class TimestampDecorator extends MessageDecorator {
    public TimestampDecorator(MessageComponent wrapped) {
        super(wrapped);
    }

    @Override
    public String getContent() {
        String currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        return String.format("[%s] %s", currentTime, wrapped.getContent());
    }
}