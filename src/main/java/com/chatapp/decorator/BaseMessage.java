package com.chatapp.decorator;

import com.chatapp.Message;
import java.time.format.DateTimeFormatter;

// Done by: Dayana
public class BaseMessage implements MessageComponent {
    private final Message message;

    public BaseMessage(Message message) {
        this.message = message;
    }

    @Override
    public String getId() {
        return message.getId();
    }

    @Override
    public String getSender() {
        return message.getSender();
    }

    @Override
    public String getRecipient() {
        return message.getRecipient();
    }

    @Override
    public String getContent() {
        return message.getContent();
    }

    @Override
    public String getTimestamp() {
        return message.getTimestamp().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    @Override
    public String getType() {
        return message.getType().name();
    }
}