package com.chatapp.visitor;

import com.chatapp.Message;

// Done by: Dayana
public interface MessageVisitor {
    void visit(Message message);
    String getReport();
}