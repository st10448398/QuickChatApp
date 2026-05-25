package QuickChatApp;

import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import org.json.JSONObject;
import org.json.JSONArray;

public class MessageService {
    private List<Message> messages;
    private int totalMessagesSent;
    private Scanner scanner;
    private boolean isLoggedIn;
    
    public MessageService(boolean loggedInStatus) {
        this.messages = new ArrayList<>();
        this.totalMessagesSent = 0;
        this.scanner = new Scanner(System.in);
        this.isLoggedIn = loggedInStatus;
        createJsonFileIfNotExists();
    }
    
    private void createJsonFileIfNotExists() {
        try {
            File file = new File("messages.json");
            if (!file.exists()) {
                JSONArray initialArray = new JSONArray();
                try (FileWriter writer = new FileWriter("messages.json")) {
                    writer.write(initialArray.toString(4));
                }
            }
        } catch (IOException e) {
            System.out.println("Error creating JSON file: " + e.getMessage());
        }
    }
    
    public boolean checkMessageID(String messageID) {
        if (messageID == null) return false;
        return messageID.length() <= 10;
    }
    
    public String checkRecipientCell(String recipientNumber) {
        if (recipientNumber == null) {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
        String regex = "^\\+27[0-9]{9}$";
        if (Pattern.matches(regex, recipientNumber)) {
            return "Cell phone number successfully captured.";
        } else {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
    }
    
    public String checkMessageLength(String message, int maxLength) {
        if (message.length() <= maxLength) {
            return "Message ready to send.";
        } else {
            int excess = message.length() - maxLength;
            return "Message exceeds " + maxLength + " characters by " + excess + "; please reduce the size.";
        }
    }
    
    public String createMessageHash(String messageID, int messageNumber, String messageText) {
        String[] words = messageText.trim().split("\\s+");
        String firstWord = words[0];
        String lastWord = words[words.length - 1];
        String idPrefix = messageID.substring(0, 2);
        String hash = idPrefix + ":" + messageNumber + ":" + firstWord + lastWord;
        return hash.toUpperCase();
    }
    
    public String sendMessageOption(Message message) {
        System.out.println("\nMessage: " + message.getMessageText());
        System.out.println("Choose an option:");
        System.out.println("1. Send Message");
        System.out.println("2. Disregard Message");
        System.out.println("3. Store Message to send later");
        System.out.print("Enter choice: ");
        
        int choice = scanner.nextInt();
        scanner.nextLine();
        
        if (choice == 1) {
            message.setStatus("sent");
            totalMessagesSent++;
            return "Message successfully sent.";
        } else if (choice == 2) {
            message.setStatus("disregarded");
            return "Press 0 to delete the message.";
        } else if (choice == 3) {
            message.setStatus("stored");
            storeMessageToJSON(message);
            return "Message successfully stored.";
        } else {
            return "Invalid option.";
        }
    }
    
    public void storeMessageToJSON(Message message) {
        try {
            JSONArray messagesArray = new JSONArray();
            File file = new File("messages.json");
            
            if (file.exists() && file.length() > 0) {
                String content = new String(java.nio.file.Files.readAllBytes(file.toPath()));
                if (!content.trim().isEmpty()) {
                    messagesArray = new JSONArray(content);
                }
            }
            
            JSONObject messageObj = new JSONObject();
            messageObj.put("messageID", message.getMessageID());
            messageObj.put("messageNumber", message.getMessageNumber());
            messageObj.put("recipient", message.getRecipient());
            messageObj.put("messageText", message.getMessageText());
            messageObj.put("messageHash", message.getMessageHash());
            messageObj.put("status", message.getStatus());
            messageObj.put("timestamp", System.currentTimeMillis());
            
            messagesArray.put(messageObj);
            
            try (FileWriter fileWriter = new FileWriter("messages.json")) {
                fileWriter.write(messagesArray.toString(4));
                System.out.println("Message stored in JSON file successfully!");
            }
        } catch (IOException e) {
            System.out.println("Error storing message in JSON: " + e.getMessage());
        }
    }
    
    public String printMessages() {
        if (messages.isEmpty()) {
            return "No messages to display.";
        }
        StringBuilder output = new StringBuilder();
        for (Message msg : messages) {
            if (msg.getStatus().equals("sent")) {
                output.append("\n--- MESSAGE DETAILS ---\n");
                output.append("Message ID: ").append(msg.getMessageID()).append("\n");
                output.append("Message Hash: ").append(msg.getMessageHash()).append("\n");
                output.append("Recipient: ").append(msg.getRecipient()).append("\n");
                output.append("Message: ").append(msg.getMessageText()).append("\n");
                output.append("------------------------\n");
            }
        }
        return output.toString();
    }
    
    public int returnTotalMessages() {
        return totalMessagesSent;
    }
    
    public void displayMessageDetails(Message message) {
        System.out.println("\n--- MESSAGE DETAILS ---");
        System.out.println("Message ID: " + message.getMessageID());
        System.out.println("Message Hash: " + message.getMessageHash());
        System.out.println("Recipient: " + message.getRecipient());
        System.out.println("Message: " + message.getMessageText());
        System.out.println("------------------------\n");
    }
    
    public Message createAndProcessMessage(int msgNumber, String recipient, String messageText) {
        String recipientValidation = checkRecipientCell(recipient);
        System.out.println(recipientValidation);
        if (!recipientValidation.equals("Cell phone number successfully captured.")) {
            return null;
        }
        String lengthValidation = checkMessageLength(messageText, 250);
        System.out.println(lengthValidation);
        if (!lengthValidation.equals("Message ready to send.")) {
            return null;
        }
        Message message = new Message(msgNumber, recipient, messageText);
        String messageHash = createMessageHash(message.getMessageID(), msgNumber, messageText);
        message.setMessageHash(messageHash);
        System.out.println("Message ID generated: " + message.getMessageID());
        String sendResult = sendMessageOption(message);
        System.out.println(sendResult);
        if (message.getStatus().equals("sent") || message.getStatus().equals("stored")) {
            messages.add(message);
        }
        if (message.getStatus().equals("sent")) {
            displayMessageDetails(message);
        }
        return message;
    }
    
    public List<Message> getMessages() { return messages; }
    public boolean isLoggedIn() { return isLoggedIn; }
}