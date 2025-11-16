package com.chatapp.visitor;

// Done by: Dayana
public interface MessageElement {
    void accept(MessageVisitor visitor);
}