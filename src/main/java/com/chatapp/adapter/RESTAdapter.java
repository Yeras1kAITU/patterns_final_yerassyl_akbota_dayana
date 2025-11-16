package com.chatapp.adapter;

import com.chatapp.Message;
import com.chatapp.builder.MessageBuilder;
import java.util.Arrays;
import java.util.List;

// Done by: Yerassyl
public class RESTAdapter implements MessageService {
    private final String baseUrl;
    private final String apiKey;
    private boolean connected;

    public RESTAdapter(String baseUrl, String apiKey) {
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
        this.connected = false;
    }

    @Override
    public void connect() {
        System.out.println("Connecting to REST API at " + baseUrl + " with API key: " + apiKey);
        this.connected = true;
        System.out.println("Connected to REST API");
    }

    @Override
    public void disconnect() {
        System.out.println("Disconnecting from REST API");
        this.connected = false;
    }

    @Override
    public void sendMessage(Message message) {
        if (!connected) {
            throw new IllegalStateException("Not connected to REST API");
        }
        System.out.println("REST → Sending to " + message.getRecipient() + ": " + message.getContent());
    }

    @Override
    public List<Message> receiveMessages(String user) {
        if (!connected) {
            throw new IllegalStateException("Not connected to REST API");
        }
        System.out.println("REST → Fetching messages for: " + user);

        Message message1 = new MessageBuilder()
                .from("rest-server")
                .to(user)
                .withContent("REST: Welcome to the chat!")
                .build();

        Message message2 = new MessageBuilder()
                .from("api-gateway")
                .to(user)
                .withContent("REST: API call successful")
                .build();

        return Arrays.asList(message1, message2);
    }
}