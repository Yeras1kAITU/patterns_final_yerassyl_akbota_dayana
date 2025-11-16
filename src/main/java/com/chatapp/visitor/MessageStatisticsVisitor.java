package com.chatapp.visitor;

import com.chatapp.Message;
import com.chatapp.MessageType;
import java.util.HashMap;
import java.util.Map;

// Done by: Dayana
public class MessageStatisticsVisitor implements MessageVisitor {
    private int totalMessages = 0;
    private final Map<String, Integer> messagesPerUser = new HashMap<>();
    private final Map<MessageType, Integer> messagesPerType = new HashMap<>();

    @Override
    public void visit(Message message) {
        totalMessages++;
        messagesPerUser.merge(message.getSender(), 1, Integer::sum);
        messagesPerType.merge(message.getType(), 1, Integer::sum);
    }

    public String getReport() {
        StringBuilder report = new StringBuilder();
        report.append("=== Message Statistics ===\n");
        report.append("Total Messages: ").append(totalMessages).append("\n");
        report.append("\nMessages per User:\n");
        messagesPerUser.forEach((user, count) ->
                report.append("  ").append(user).append(": ").append(count).append("\n"));
        report.append("\nMessages per Type:\n");
        messagesPerType.forEach((type, count) ->
                report.append("  ").append(type).append(": ").append(count).append("\n"));
        return report.toString();
    }
}