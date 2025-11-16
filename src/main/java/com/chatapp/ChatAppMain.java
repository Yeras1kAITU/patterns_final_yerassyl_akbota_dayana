package com.chatapp;

import com.chatapp.facade.ChatAppFacade;
import com.chatapp.factories.ProtocolFactory;
import com.chatapp.factories.ProtocolFactoryProducer;
import com.chatapp.observer.LoggingMessageObserver;
import com.chatapp.observer.UserMessageObserver;
import com.chatapp.visitor.ContentAnalyzerVisitor;
import com.chatapp.visitor.MessageStatisticsVisitor;

public class ChatAppMain {
    public static void main(String[] args) {
        System.out.println("=== Chat/Messaging App - All Design Patterns ===\n");

        ChatAppFacade chatApp = new ChatAppFacade();

        System.out.println("--- Setting up Observers ---");
        UserMessageObserver aliceObserver = new UserMessageObserver("Alice");
        UserMessageObserver bobObserver = new UserMessageObserver("Bob");
        LoggingMessageObserver loggerObserver = new LoggingMessageObserver("chat_system.log");

        chatApp.addObserver(aliceObserver);
        chatApp.addObserver(bobObserver);
        chatApp.addObserver(loggerObserver);
        System.out.println("Active observers: " + chatApp.getObserverCount());

        System.out.println("\n--- Connecting via XMPP (Abstract Factory + Adapter) ---");
        ProtocolFactory xmppFactory = ProtocolFactoryProducer.getFactory("XMPP", "xmpp.example.com", "5222");
        chatApp.connectToProtocol(xmppFactory);

        System.out.println("\n--- Enabling Decorators ---");
        chatApp.enableEncryption("secure-chat-key-2025");
        chatApp.enableLogging();

        // Message creation
        System.out.println("\n--- Sending Messages (Builder + Factory Method) ---");
        chatApp.sendMessage("Alice", "Bob", "Hello Bob! This message is encrypted and logged.");
        chatApp.sendMessage("Bob", "Alice", "Hi Alice! The decorators are working perfectly!");

        // Bob leaves the chat
        System.out.println("\n--- Demonstrating removeObserver ---");
        System.out.println("Bob is leaving the chat...");
        chatApp.removeObserver(bobObserver);
        System.out.println("Active observers after Bob left: " + chatApp.getObserverCount());

        // Bob won't receive these
        chatApp.sendMessage("Alice", "Bob", "Are you still there, Bob?");
        chatApp.sendMessage("Charlie", "ALL", "Hello everyone!");

        // Receiving messages
        System.out.println("\n--- Receiving Messages (Adapter Pattern) ---");
        chatApp.receiveMessages("Alice");

        // Switch to REST
        System.out.println("\n--- Switching to REST (Abstract Factory + Adapter) ---");
        ProtocolFactory restFactory = ProtocolFactoryProducer.getFactory("REST", "https://api.chat.com", "rest-api-key-123");
        chatApp.connectToProtocol(restFactory);

        // Remove logging observer
        System.out.println("\n--- Removing Logging Observer ---");
        chatApp.removeObserver(loggerObserver);
        System.out.println("Active observers after removing logger: " + chatApp.getObserverCount());

        chatApp.sendMessage("Alice", "ALL", "This message won't be logged to file");

        // Add Bob back
        System.out.println("\n--- Bob rejoins the chat ---");
        chatApp.addObserver(bobObserver);
        System.out.println("Active observers after Bob rejoined: " + chatApp.getObserverCount());

        chatApp.sendMessage("Bob", "Alice", "I'm back! Got all the messages I missed?");

        // Message analysis
        System.out.println("\n--- Analyzing Messages (Visitor Pattern) ---");
        MessageStatisticsVisitor statsVisitor = new MessageStatisticsVisitor();
        chatApp.generateReport(statsVisitor);

        ContentAnalyzerVisitor contentVisitor = new ContentAnalyzerVisitor();
        chatApp.generateReport(contentVisitor);

        // Final Summary
        System.out.println("\n=== Design Patterns Summary ===");
        System.out.println("+ BUILDER: Complex message construction");
        System.out.println("+ FACTORY METHOD: Flexible message creation");
        System.out.println("+ ABSTRACT FACTORY: Cross-protocol families");
        System.out.println("+ ADAPTER: XMPP/REST protocol compatibility");
        System.out.println("+ DECORATOR: Encryption, logging, timestamps");
        System.out.println("+ OBSERVER: Real-time message notifications with dynamic management");
        System.out.println("+ VISITOR: Message analysis and reporting");
        System.out.println("+ FACADE: Simplified chat application interface");

        System.out.println("\nApplication Statistics:");
        System.out.println("• Messages processed: " + chatApp.getMessageCount());
        System.out.println("• Final active observers: " + chatApp.getObserverCount());
        System.out.println("• Protocols used: XMPP, REST");
        System.out.println("• Features: Encryption, Logging, Timestamps");
        System.out.println("• Observer management: addObserver/removeObserver demonstrated");

        System.out.println("\n=== All 8 design patterns implemented successfully! ===");
    }
}