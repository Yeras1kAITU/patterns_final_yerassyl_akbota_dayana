package com.chatapp.factories;

import com.chatapp.adapter.MessageService;
import com.chatapp.adapter.XMPPAdapter;

// Done by: Yerassyl
public class XMPPFactory implements ProtocolFactory {
    private final String server;
    private final int port;

    public XMPPFactory(String server, int port) {
        this.server = server;
        this.port = port;
    }

    @Override
    public MessageService createMessageService() {
        return new XMPPAdapter(server, port);
    }

    @Override
    public String getProtocolName() {
        return "XMPP";
    }
}