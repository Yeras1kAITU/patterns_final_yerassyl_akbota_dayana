package com.chatapp.observer;

import com.chatapp.Message;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

// Done by: Akbota
public class LoggingMessageObserver implements MessageObserver {
    private final String logFile;

    public LoggingMessageObserver(String logFile) {
        this.logFile = logFile;
    }

    @Override
    public void update(Message message) {
        try (FileWriter writer = new FileWriter(logFile, true)) {
            String logEntry = String.format("[%s] OBSERVER_LOG: %s -> %s: %s%n",
                    message.getTimestamp().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                    message.getSender(),
                    message.getRecipient(),
                    message.getContent());
            writer.write(logEntry);
        } catch (IOException e) {
            System.err.println("Failed to write observer log: " + e.getMessage());
        }
    }

    @Override
    public String getName() {
        return "Logger";
    }
}