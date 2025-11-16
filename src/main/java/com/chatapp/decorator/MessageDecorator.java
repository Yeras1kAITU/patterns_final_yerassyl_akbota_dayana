package com.chatapp.decorator;

// Done by: Dayana
public abstract class MessageDecorator implements MessageComponent {
    protected MessageComponent wrapped;

    public MessageDecorator(MessageComponent wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public String getId() {
        return wrapped.getId();
    }

    @Override
    public String getSender() {
        return wrapped.getSender();
    }

    @Override
    public String getRecipient() {
        return wrapped.getRecipient();
    }

    @Override
    public String getContent() {
        return wrapped.getContent();
    }

    @Override
    public String getTimestamp() {
        return wrapped.getTimestamp();
    }

    @Override
    public String getType() {
        return wrapped.getType();
    }
}
