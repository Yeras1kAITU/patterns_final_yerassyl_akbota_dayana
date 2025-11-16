package com.chatapp.factories;

import com.chatapp.adapter.MessageService;

// Done by: Yerassyl
public interface ProtocolFactory {
    MessageService createMessageService();
    String getProtocolName();
}