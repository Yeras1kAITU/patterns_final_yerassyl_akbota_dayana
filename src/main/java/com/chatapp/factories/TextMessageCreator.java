package com.chatapp.factories;

import com.chatapp.Message;
import com.chatapp.MessageType;
import com.chatapp.builder.MessageBuilder;

// Done by: Yerassyl
public class TextMessageCreator extends MessageCreator {
    @Override
    public Message createMessage(String sender, String recipient, String content) {
        return new MessageBuilder()
                .from(sender)
                .to(recipient)
                .withContent(content)
                .ofType(MessageType.TEXT)
                .build();
    }
}