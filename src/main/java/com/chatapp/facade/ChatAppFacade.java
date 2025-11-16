package com.chatapp.facade;

import com.chatapp.Message;
import com.chatapp.adapter.MessageService;
import com.chatapp.decorator.*;
import com.chatapp.factories.MessageCreator;
import com.chatapp.factories.ProtocolFactory;
import com.chatapp.factories.TextMessageCreator;
import com.chatapp.observer.ConcreteMessageSubject;
import com.chatapp.observer.MessageObserver;
import com.chatapp.visitor.MessageVisitor;
import java.util.ArrayList;
import java.util.List;

// Done by: Yerassyl
public class ChatAppFacade {
    private final MessageCreator messageCreator;
    private final ConcreteMessageSubject messageSubject;
    private MessageService currentService;
    private final List<Message> messageHistory;
    private boolean encryptionEnabled = false;
    private boolean loggingEnabled = false;
    private String encryptionKey = "default-key";

    public ChatAppFacade() {
        this.messageCreator = new TextMessageCreator();
        this.messageSubject = new ConcreteMessageSubject();
        this.messageHistory = new ArrayList<>();
    }

    public void connectToProtocol(ProtocolFactory protocolFactory) {
        if (currentService != null) {
            currentService.disconnect();
        }
        currentService = protocolFactory.createMessageService();
        currentService.connect();
        System.out.println("Connected to " + protocolFactory.getProtocolName());
    }

    public void sendMessage(String from, String to, String content) {
        Message originalMessage = messageCreator.createMessage(from, to, content);

        MessageComponent messageComponent = new BaseMessage(originalMessage);
        messageComponent = new TimestampDecorator(messageComponent);

        if (encryptionEnabled) {
            messageComponent = new EncryptionDecorator(messageComponent, encryptionKey);
        }

        if (loggingEnabled) {
            messageComponent = new LoggingDecorator(messageComponent, "chat.log");
        }

        Message decoratedMessage = createMessageFromComponent(originalMessage, messageComponent);

        if (currentService != null) {
            currentService.sendMessage(decoratedMessage);
        }

        messageHistory.add(decoratedMessage);
        messageSubject.notifyObservers(decoratedMessage);
    }

    public void receiveMessages(String user) {
        if (currentService != null) {
            List<Message> messages = currentService.receiveMessages(user);
            for (Message message : messages) {
                messageHistory.add(message);
                messageSubject.notifyObservers(message);
            }
        }
    }

    private Message createMessageFromComponent(Message original, MessageComponent component) {
        return new Message(
                original.getId(),
                original.getSender(),
                original.getRecipient(),
                component.getContent(),
                original.getTimestamp(),
                original.getType()
        );
    }

    public void generateReport(MessageVisitor visitor) {
        for (Message message : messageHistory) {
            message.accept(visitor);
        }
        System.out.println(visitor.getReport());
    }

    public void addObserver(MessageObserver observer) {
        messageSubject.attach(observer);
    }

    public void removeObserver(MessageObserver observer) {
        messageSubject.detach(observer);
    }

    public void enableEncryption(String key) {
        this.encryptionEnabled = true;
        this.encryptionKey = key;
    }

    public void enableLogging() {
        this.loggingEnabled = true;
    }

    public int getMessageCount() {
        return messageHistory.size();
    }

    public int getObserverCount() {
        return messageSubject.getObserverCount();
    }
}