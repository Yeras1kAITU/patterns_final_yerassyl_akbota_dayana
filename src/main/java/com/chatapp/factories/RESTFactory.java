package com.chatapp.factories;

import com.chatapp.adapter.MessageService;
import com.chatapp.adapter.RESTAdapter;

// Done by: Yerassyl
public class RESTFactory implements ProtocolFactory {
    private final String baseUrl;
    private final String apiKey;

    public RESTFactory(String baseUrl, String apiKey) {
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
    }

    @Override
    public MessageService createMessageService() {
        return new RESTAdapter(baseUrl, apiKey);
    }

    @Override
    public String getProtocolName() {
        return "REST";
    }
}