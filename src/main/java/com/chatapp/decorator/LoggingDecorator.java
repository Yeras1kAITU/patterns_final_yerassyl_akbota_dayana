package com.chatapp.decorator;

import java.io.FileWriter;
import java.io.IOException;

// Done by: Dayana
public class LoggingDecorator extends MessageDecorator {
    private final String logFilePath;

    public LoggingDecorator(MessageComponent wrapped, String logFilePath) {
        super(wrapped);
        this.logFilePath = logFilePath;
    }

    @Override
    public String getContent() {
        logMessage();
        return wrapped.getContent();
    }

    private void logMessage() {
        try (FileWriter writer = new FileWriter(logFilePath, true)) {
            String logEntry = String.format("[%s] %s -> %s: %s%n",
                    getTimestamp(),
                    getSender(),
                    getRecipient(),
                    super.getContent()); // Get original content without logging side effects
            writer.write(logEntry);
        } catch (IOException e) {
            System.err.println("Failed to write to log file: " + e.getMessage());
        }
    }
}