package com.chatapp.decorator;

import java.util.Base64;

// Done by: Dayana
public class EncryptionDecorator extends MessageDecorator {
    private final String encryptionKey;

    public EncryptionDecorator(MessageComponent wrapped, String encryptionKey) {
        super(wrapped);
        this.encryptionKey = encryptionKey;
    }

    @Override
    public String getContent() {
        String originalContent = wrapped.getContent();
        return encrypt(originalContent);
    }

    private String encrypt(String content) {
        try {
            String combined = content + "|" + encryptionKey;
            return Base64.getEncoder().encodeToString(combined.getBytes());
        } catch (Exception e) {
            throw new RuntimeException("Encryption failed", e);
        }
    }

    public String getDecryptedContent() {
        String encrypted = super.getContent();
        try {
            byte[] decodedBytes = Base64.getDecoder().decode(encrypted);
            String decoded = new String(decodedBytes);
            return decoded.split("\\|")[0];
        } catch (Exception e) {
            throw new RuntimeException("Decryption failed", e);
        }
    }
}