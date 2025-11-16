package com.chatapp.adapter;

import com.chatapp.Message;
import java.util.List;

// Done by: Yerassyl
public interface MessageService {
    void sendMessage(Message message);
    List<Message> receiveMessages(String user);
    void connect();
    void disconnect();
}