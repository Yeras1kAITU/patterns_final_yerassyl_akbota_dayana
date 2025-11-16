package com.chatapp.adapter;

import com.chatapp.Message;
import com.chatapp.builder.MessageBuilder;
import java.util.Arrays;
import java.util.List;

// Done by: Yerassyl
public class XMPPAdapter implements MessageService {
    private final String server;
    private final int port;
    private boolean connected;

    public XMPPAdapter(String server, int port) {
        this.server = server;
        this.port = port;
        this.connected = false;
    }

    @Override
    public void connect() {
        System.out.println("Connecting to XMPP server " + server + ":" + port);
        this.connected = true;
        System.out.println("Connected to XMPP server");
    }

    @Override
    public void disconnect() {
        System.out.println("Disconnecting from XMPP server");
        this.connected = false;
    }

    @Override
    public void sendMessage(Message message) {
        if (!connected) {
            throw new IllegalStateException("Not connected to XMPP server");
        }
        System.out.println("XMPP → Sending to " + message.getRecipient() + ": " + message.getContent());
    }

    @Override
    public List<Message> receiveMessages(String user) {
        if (!connected) {
            throw new IllegalStateException("Not connected to XMPP server");
        }
        System.out.println("XMPP → Receiving messages for: " + user);

        // Simulate receiving XMPP messages
        Message message1 = new MessageBuilder()
                .from("xmpp-server")
                .to(user)
                .withContent("XMPP: Welcome to the chat!")
                .build();

        Message message2 = new MessageBuilder()
                .from("system")
                .to(user)
                .withContent("XMPP: Connection stable")
                .build();

        return Arrays.asList(message1, message2);
    }
}