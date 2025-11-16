package com.chatapp.visitor;

import com.chatapp.Message;

// Done by: Dayana
public class ContentAnalyzerVisitor implements MessageVisitor {
    private int totalCharacters = 0;
    private int totalWords = 0;
    private int messageCount = 0;

    @Override
    public void visit(Message message) {
        String content = message.getContent();
        totalCharacters += content.length();
        totalWords += content.split("\\s+").length;
        messageCount++;
    }

    @Override
    public String getReport() {
        double avgChars = messageCount > 0 ? (double) totalCharacters / messageCount : 0;
        double avgWords = messageCount > 0 ? (double) totalWords / messageCount : 0;

        return String.format(
                "=== Content Analysis ===\n" +
                        "Total Messages: %d\n" +
                        "Total Characters: %d\n" +
                        "Total Words: %d\n" +
                        "Avg Characters per Message: %.2f\n" +
                        "Avg Words per Message: %.2f",
                messageCount, totalCharacters, totalWords, avgChars, avgWords
        );
    }
}