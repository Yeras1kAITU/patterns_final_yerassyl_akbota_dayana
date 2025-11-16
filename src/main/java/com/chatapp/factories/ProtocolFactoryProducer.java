package com.chatapp.factories;

// Done by: Yerassyl
public class ProtocolFactoryProducer {
    public static ProtocolFactory getFactory(String protocolType, String... params) {
        switch (protocolType.toUpperCase()) {
            case "XMPP":
                return new XMPPFactory(params[0], Integer.parseInt(params[1]));
            case "REST":
                return new RESTFactory(params[0], params[1]);
            default:
                throw new IllegalArgumentException("Unknown protocol: " + protocolType);
        }
    }
}